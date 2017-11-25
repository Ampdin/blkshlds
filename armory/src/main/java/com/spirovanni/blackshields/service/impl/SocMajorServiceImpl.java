package com.spirovanni.blackshields.service.impl;

import com.spirovanni.blackshields.service.SocMajorService;
import com.spirovanni.blackshields.domain.SocMajor;
import com.spirovanni.blackshields.repository.SocMajorRepository;
import com.spirovanni.blackshields.repository.search.SocMajorSearchRepository;
import com.spirovanni.blackshields.service.dto.SocMajorDTO;
import com.spirovanni.blackshields.service.mapper.SocMajorMapper;
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
 * Service Implementation for managing SocMajor.
 */
@Service
@Transactional
public class SocMajorServiceImpl implements SocMajorService{

    private final Logger log = LoggerFactory.getLogger(SocMajorServiceImpl.class);

    private final SocMajorRepository socMajorRepository;

    private final SocMajorMapper socMajorMapper;

    private final SocMajorSearchRepository socMajorSearchRepository;

    public SocMajorServiceImpl(SocMajorRepository socMajorRepository, SocMajorMapper socMajorMapper, SocMajorSearchRepository socMajorSearchRepository) {
        this.socMajorRepository = socMajorRepository;
        this.socMajorMapper = socMajorMapper;
        this.socMajorSearchRepository = socMajorSearchRepository;
    }

    /**
     * Save a socMajor.
     *
     * @param socMajorDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SocMajorDTO save(SocMajorDTO socMajorDTO) {
        log.debug("Request to save SocMajor : {}", socMajorDTO);
        SocMajor socMajor = socMajorMapper.toEntity(socMajorDTO);
        socMajor = socMajorRepository.save(socMajor);
        SocMajorDTO result = socMajorMapper.toDto(socMajor);
        socMajorSearchRepository.save(socMajor);
        return result;
    }

    /**
     * Get all the socMajors.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SocMajorDTO> findAll() {
        log.debug("Request to get all SocMajors");
        return socMajorRepository.findAll().stream()
            .map(socMajorMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one socMajor by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public SocMajorDTO findOne(Long id) {
        log.debug("Request to get SocMajor : {}", id);
        SocMajor socMajor = socMajorRepository.findOne(id);
        return socMajorMapper.toDto(socMajor);
    }

    /**
     * Delete the socMajor by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SocMajor : {}", id);
        socMajorRepository.delete(id);
        socMajorSearchRepository.delete(id);
    }

    /**
     * Search for the socMajor corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SocMajorDTO> search(String query) {
        log.debug("Request to search SocMajors for query {}", query);
        return StreamSupport
            .stream(socMajorSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(socMajorMapper::toDto)
            .collect(Collectors.toList());
    }
}
