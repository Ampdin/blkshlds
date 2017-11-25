package com.spirovanni.blackshields.service;

import com.spirovanni.blackshields.service.dto.SocSpecificDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing SocSpecific.
 */
public interface SocSpecificService {

    /**
     * Save a socSpecific.
     *
     * @param socSpecificDTO the entity to save
     * @return the persisted entity
     */
    SocSpecificDTO save(SocSpecificDTO socSpecificDTO);

    /**
     * Get all the socSpecifics.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SocSpecificDTO> findAll(Pageable pageable);

    /**
     * Get the "id" socSpecific.
     *
     * @param id the id of the entity
     * @return the entity
     */
    SocSpecificDTO findOne(Long id);

    /**
     * Delete the "id" socSpecific.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the socSpecific corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SocSpecificDTO> search(String query, Pageable pageable);
}
