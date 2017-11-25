package com.spirovanni.blackshields.service.impl;

import com.spirovanni.blackshields.service.SocMinorService;
import com.spirovanni.blackshields.domain.SocMinor;
import com.spirovanni.blackshields.repository.SocMinorRepository;
import com.spirovanni.blackshields.repository.search.SocMinorSearchRepository;
import com.spirovanni.blackshields.service.dto.SocMinorDTO;
import com.spirovanni.blackshields.service.mapper.SocMinorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing SocMinor.
 */
@Service
@Transactional
public class SocMinorServiceImpl implements SocMinorService{

    private final Logger log = LoggerFactory.getLogger(SocMinorServiceImpl.class);

    private final SocMinorRepository socMinorRepository;

    private final SocMinorMapper socMinorMapper;

    private final SocMinorSearchRepository socMinorSearchRepository;

    public SocMinorServiceImpl(SocMinorRepository socMinorRepository, SocMinorMapper socMinorMapper, SocMinorSearchRepository socMinorSearchRepository) {
        this.socMinorRepository = socMinorRepository;
        this.socMinorMapper = socMinorMapper;
        this.socMinorSearchRepository = socMinorSearchRepository;
    }

    /**
     * Save a socMinor.
     *
     * @param socMinorDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SocMinorDTO save(SocMinorDTO socMinorDTO) {
        log.debug("Request to save SocMinor : {}", socMinorDTO);
        SocMinor socMinor = socMinorMapper.toEntity(socMinorDTO);
        socMinor = socMinorRepository.save(socMinor);
        SocMinorDTO result = socMinorMapper.toDto(socMinor);
        socMinorSearchRepository.save(socMinor);
        return result;
    }

    /**
     * Get all the socMinors.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SocMinorDTO> findAll() {
        log.debug("Request to get all SocMinors");
        return socMinorRepository.findAll().stream()
            .map(socMinorMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one socMinor by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public SocMinorDTO findOne(Long id) {
        log.debug("Request to get SocMinor : {}", id);
        SocMinor socMinor = socMinorRepository.findOne(id);
        return socMinorMapper.toDto(socMinor);
    }

    /**
     * Delete the socMinor by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SocMinor : {}", id);
        socMinorRepository.delete(id);
        socMinorSearchRepository.delete(id);
    }

    /**
     * Search for the socMinor corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SocMinorDTO> search(String query) {
        log.debug("Request to search SocMinors for query {}", query);
        return StreamSupport
            .stream(socMinorSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(socMinorMapper::toDto)
            .collect(Collectors.toList());
    }
}
