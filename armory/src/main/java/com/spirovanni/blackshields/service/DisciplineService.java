package com.spirovanni.blackshields.service;

import com.spirovanni.blackshields.service.dto.DisciplineDTO;
import java.util.List;

/**
 * Service Interface for managing Discipline.
 */
public interface DisciplineService {

    /**
     * Save a discipline.
     *
     * @param disciplineDTO the entity to save
     * @return the persisted entity
     */
    DisciplineDTO save(DisciplineDTO disciplineDTO);

    /**
     * Get all the disciplines.
     *
     * @return the list of entities
     */
    List<DisciplineDTO> findAll();

    /**
     * Get the "id" discipline.
     *
     * @param id the id of the entity
     * @return the entity
     */
    DisciplineDTO findOne(Long id);

    /**
     * Delete the "id" discipline.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the discipline corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<DisciplineDTO> search(String query);
}
