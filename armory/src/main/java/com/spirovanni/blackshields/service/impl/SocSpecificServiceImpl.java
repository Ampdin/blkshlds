package com.spirovanni.blackshields.service.impl;

import com.spirovanni.blackshields.service.SocSpecificService;
import com.spirovanni.blackshields.domain.SocSpecific;
import com.spirovanni.blackshields.repository.SocSpecificRepository;
import com.spirovanni.blackshields.repository.search.SocSpecificSearchRepository;
import com.spirovanni.blackshields.service.dto.SocSpecificDTO;
import com.spirovanni.blackshields.service.mapper.SocSpecificMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing SocSpecific.
 */
@Service
@Transactional
public class SocSpecificServiceImpl implements SocSpecificService{

    private final Logger log = LoggerFactory.getLogger(SocSpecificServiceImpl.class);

    private final SocSpecificRepository socSpecificRepository;

    private final SocSpecificMapper socSpecificMapper;

    private final SocSpecificSearchRepository socSpecificSearchRepository;

    public SocSpecificServiceImpl(SocSpecificRepository socSpecificRepository, SocSpecificMapper socSpecificMapper, SocSpecificSearchRepository socSpecificSearchRepository) {
        this.socSpecificRepository = socSpecificRepository;
        this.socSpecificMapper = socSpecificMapper;
        this.socSpecificSearchRepository = socSpecificSearchRepository;
    }

    /**
     * Save a socSpecific.
     *
     * @param socSpecificDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SocSpecificDTO save(SocSpecificDTO socSpecificDTO) {
        log.debug("Request to save SocSpecific : {}", socSpecificDTO);
        SocSpecific socSpecific = socSpecificMapper.toEntity(socSpecificDTO);
        socSpecific = socSpecificRepository.save(socSpecific);
        SocSpecificDTO result = socSpecificMapper.toDto(socSpecific);
        socSpecificSearchRepository.save(socSpecific);
        return result;
    }

    /**
     * Get all the socSpecifics.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SocSpecificDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SocSpecifics");
        return socSpecificRepository.findAll(pageable)
            .map(socSpecificMapper::toDto);
    }

    /**
     * Get one socSpecific by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public SocSpecificDTO findOne(Long id) {
        log.debug("Request to get SocSpecific : {}", id);
        SocSpecific socSpecific = socSpecificRepository.findOne(id);
        return socSpecificMapper.toDto(socSpecific);
    }

    /**
     * Delete the socSpecific by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SocSpecific : {}", id);
        socSpecificRepository.delete(id);
        socSpecificSearchRepository.delete(id);
    }

    /**
     * Search for the socSpecific corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SocSpecificDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of SocSpecifics for query {}", query);
        Page<SocSpecific> result = socSpecificSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(socSpecificMapper::toDto);
    }
}
