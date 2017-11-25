package com.spirovanni.blackshields.service;

import com.spirovanni.blackshields.service.dto.SkillsDTO;
import java.util.List;

/**
 * Service Interface for managing Skills.
 */
public interface SkillsService {

    /**
     * Save a skills.
     *
     * @param skillsDTO the entity to save
     * @return the persisted entity
     */
    SkillsDTO save(SkillsDTO skillsDTO);

    /**
     * Get all the skills.
     *
     * @return the list of entities
     */
    List<SkillsDTO> findAll();

    /**
     * Get the "id" skills.
     *
     * @param id the id of the entity
     * @return the entity
     */
    SkillsDTO findOne(Long id);

    /**
     * Delete the "id" skills.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the skills corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<SkillsDTO> search(String query);
}
