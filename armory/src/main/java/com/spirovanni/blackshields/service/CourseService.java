package com.spirovanni.blackshields.service;

import com.spirovanni.blackshields.service.dto.CourseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Course.
 */
public interface CourseService {

    /**
     * Save a course.
     *
     * @param courseDTO the entity to save
     * @return the persisted entity
     */
    CourseDTO save(CourseDTO courseDTO);

    /**
     * Get all the courses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CourseDTO> findAll(Pageable pageable);

    /**
     * Get the "id" course.
     *
     * @param id the id of the entity
     * @return the entity
     */
    CourseDTO findOne(Long id);

    /**
     * Delete the "id" course.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the course corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CourseDTO> search(String query, Pageable pageable);
}
