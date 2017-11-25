package com.spirovanni.blackshields.service;

import com.spirovanni.blackshields.service.dto.SocBroadDTO;
import java.util.List;

/**
 * Service Interface for managing SocBroad.
 */
public interface SocBroadService {

    /**
     * Save a socBroad.
     *
     * @param socBroadDTO the entity to save
     * @return the persisted entity
     */
    SocBroadDTO save(SocBroadDTO socBroadDTO);

    /**
     * Get all the socBroads.
     *
     * @return the list of entities
     */
    List<SocBroadDTO> findAll();

    /**
     * Get the "id" socBroad.
     *
     * @param id the id of the entity
     * @return the entity
     */
    SocBroadDTO findOne(Long id);

    /**
     * Delete the "id" socBroad.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the socBroad corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<SocBroadDTO> search(String query);
}
