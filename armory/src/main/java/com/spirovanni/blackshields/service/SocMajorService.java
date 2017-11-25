package com.spirovanni.blackshields.service;

import com.spirovanni.blackshields.service.dto.SocMajorDTO;
import java.util.List;

/**
 * Service Interface for managing SocMajor.
 */
public interface SocMajorService {

    /**
     * Save a socMajor.
     *
     * @param socMajorDTO the entity to save
     * @return the persisted entity
     */
    SocMajorDTO save(SocMajorDTO socMajorDTO);

    /**
     * Get all the socMajors.
     *
     * @return the list of entities
     */
    List<SocMajorDTO> findAll();

    /**
     * Get the "id" socMajor.
     *
     * @param id the id of the entity
     * @return the entity
     */
    SocMajorDTO findOne(Long id);

    /**
     * Delete the "id" socMajor.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the socMajor corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<SocMajorDTO> search(String query);
}
