package com.spirovanni.blackshields.service.impl;

import com.spirovanni.blackshields.service.DisciplineService;
import com.spirovanni.blackshields.domain.Discipline;
import com.spirovanni.blackshields.repository.DisciplineRepository;
import com.spirovanni.blackshields.repository.search.DisciplineSearchRepository;
import com.spirovanni.blackshields.service.dto.DisciplineDTO;
import com.spirovanni.blackshields.service.mapper.DisciplineMapper;
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
 * Service Implementation for managing Discipline.
 */
@Service
@Transactional
public class DisciplineServiceImpl implements DisciplineService{

    private final Logger log = LoggerFactory.getLogger(DisciplineServiceImpl.class);

    private final DisciplineRepository disciplineRepository;

    private final DisciplineMapper disciplineMapper;

    private final DisciplineSearchRepository disciplineSearchRepository;

    public DisciplineServiceImpl(DisciplineRepository disciplineRepository, DisciplineMapper disciplineMapper, DisciplineSearchRepository disciplineSearchRepository) {
        this.disciplineRepository = disciplineRepository;
        this.disciplineMapper = disciplineMapper;
        this.disciplineSearchRepository = disciplineSearchRepository;
    }

    /**
     * Save a discipline.
     *
     * @param disciplineDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DisciplineDTO save(DisciplineDTO disciplineDTO) {
        log.debug("Request to save Discipline : {}", disciplineDTO);
        Discipline discipline = disciplineMapper.toEntity(disciplineDTO);
        discipline = disciplineRepository.save(discipline);
        DisciplineDTO result = disciplineMapper.toDto(discipline);
        disciplineSearchRepository.save(discipline);
        return result;
    }

    /**
     * Get all the disciplines.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<DisciplineDTO> findAll() {
        log.debug("Request to get all Disciplines");
        return disciplineRepository.findAllWithEagerRelationships().stream()
            .map(disciplineMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one discipline by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DisciplineDTO findOne(Long id) {
        log.debug("Request to get Discipline : {}", id);
        Discipline discipline = disciplineRepository.findOneWithEagerRelationships(id);
        return disciplineMapper.toDto(discipline);
    }

    /**
     * Delete the discipline by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Discipline : {}", id);
        disciplineRepository.delete(id);
        disciplineSearchRepository.delete(id);
    }

    /**
     * Search for the discipline corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<DisciplineDTO> search(String query) {
        log.debug("Request to search Disciplines for query {}", query);
        return StreamSupport
            .stream(disciplineSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(disciplineMapper::toDto)
            .collect(Collectors.toList());
    }
}
