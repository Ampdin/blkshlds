package com.spirovanni.blackshields.service.impl;

import com.spirovanni.blackshields.service.LessonService;
import com.spirovanni.blackshields.domain.Lesson;
import com.spirovanni.blackshields.repository.LessonRepository;
import com.spirovanni.blackshields.repository.search.LessonSearchRepository;
import com.spirovanni.blackshields.service.dto.LessonDTO;
import com.spirovanni.blackshields.service.mapper.LessonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Lesson.
 */
@Service
@Transactional
public class LessonServiceImpl implements LessonService{

    private final Logger log = LoggerFactory.getLogger(LessonServiceImpl.class);

    private final LessonRepository lessonRepository;

    private final LessonMapper lessonMapper;

    private final LessonSearchRepository lessonSearchRepository;

    public LessonServiceImpl(LessonRepository lessonRepository, LessonMapper lessonMapper, LessonSearchRepository lessonSearchRepository) {
        this.lessonRepository = lessonRepository;
        this.lessonMapper = lessonMapper;
        this.lessonSearchRepository = lessonSearchRepository;
    }

    /**
     * Save a lesson.
     *
     * @param lessonDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public LessonDTO save(LessonDTO lessonDTO) {
        log.debug("Request to save Lesson : {}", lessonDTO);
        Lesson lesson = lessonMapper.toEntity(lessonDTO);
        lesson = lessonRepository.save(lesson);
        LessonDTO result = lessonMapper.toDto(lesson);
        lessonSearchRepository.save(lesson);
        return result;
    }

    /**
     * Get all the lessons.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LessonDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Lessons");
        return lessonRepository.findAll(pageable)
            .map(lessonMapper::toDto);
    }

    /**
     * Get one lesson by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public LessonDTO findOne(Long id) {
        log.debug("Request to get Lesson : {}", id);
        Lesson lesson = lessonRepository.findOne(id);
        return lessonMapper.toDto(lesson);
    }

    /**
     * Delete the lesson by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Lesson : {}", id);
        lessonRepository.delete(id);
        lessonSearchRepository.delete(id);
    }

    /**
     * Search for the lesson corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LessonDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Lessons for query {}", query);
        Page<Lesson> result = lessonSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(lessonMapper::toDto);
    }
}
