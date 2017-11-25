package com.spirovanni.blackshields.service.impl;

import com.spirovanni.blackshields.service.SocBroadService;
import com.spirovanni.blackshields.domain.SocBroad;
import com.spirovanni.blackshields.repository.SocBroadRepository;
import com.spirovanni.blackshields.repository.search.SocBroadSearchRepository;
import com.spirovanni.blackshields.service.dto.SocBroadDTO;
import com.spirovanni.blackshields.service.mapper.SocBroadMapper;
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
 * Service Implementation for managing SocBroad.
 */
@Service
@Transactional
public class SocBroadServiceImpl implements SocBroadService{

    private final Logger log = LoggerFactory.getLogger(SocBroadServiceImpl.class);

    private final SocBroadRepository socBroadRepository;

    private final SocBroadMapper socBroadMapper;

    private final SocBroadSearchRepository socBroadSearchRepository;

    public SocBroadServiceImpl(SocBroadRepository socBroadRepository, SocBroadMapper socBroadMapper, SocBroadSearchRepository socBroadSearchRepository) {
        this.socBroadRepository = socBroadRepository;
        this.socBroadMapper = socBroadMapper;
        this.socBroadSearchRepository = socBroadSearchRepository;
    }

    /**
     * Save a socBroad.
     *
     * @param socBroadDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SocBroadDTO save(SocBroadDTO socBroadDTO) {
        log.debug("Request to save SocBroad : {}", socBroadDTO);
        SocBroad socBroad = socBroadMapper.toEntity(socBroadDTO);
        socBroad = socBroadRepository.save(socBroad);
        SocBroadDTO result = socBroadMapper.toDto(socBroad);
        socBroadSearchRepository.save(socBroad);
        return result;
    }

    /**
     * Get all the socBroads.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SocBroadDTO> findAll() {
        log.debug("Request to get all SocBroads");
        return socBroadRepository.findAll().stream()
            .map(socBroadMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one socBroad by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public SocBroadDTO findOne(Long id) {
        log.debug("Request to get SocBroad : {}", id);
        SocBroad socBroad = socBroadRepository.findOne(id);
        return socBroadMapper.toDto(socBroad);
    }

    /**
     * Delete the socBroad by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SocBroad : {}", id);
        socBroadRepository.delete(id);
        socBroadSearchRepository.delete(id);
    }

    /**
     * Search for the socBroad corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SocBroadDTO> search(String query) {
        log.debug("Request to search SocBroads for query {}", query);
        return StreamSupport
            .stream(socBroadSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(socBroadMapper::toDto)
            .collect(Collectors.toList());
    }
}
