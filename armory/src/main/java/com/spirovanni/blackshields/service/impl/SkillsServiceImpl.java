package com.spirovanni.blackshields.service.impl;

import com.spirovanni.blackshields.service.SkillsService;
import com.spirovanni.blackshields.domain.Skills;
import com.spirovanni.blackshields.repository.SkillsRepository;
import com.spirovanni.blackshields.repository.search.SkillsSearchRepository;
import com.spirovanni.blackshields.service.dto.SkillsDTO;
import com.spirovanni.blackshields.service.mapper.SkillsMapper;
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
 * Service Implementation for managing Skills.
 */
@Service
@Transactional
public class SkillsServiceImpl implements SkillsService{

    private final Logger log = LoggerFactory.getLogger(SkillsServiceImpl.class);

    private final SkillsRepository skillsRepository;

    private final SkillsMapper skillsMapper;

    private final SkillsSearchRepository skillsSearchRepository;

    public SkillsServiceImpl(SkillsRepository skillsRepository, SkillsMapper skillsMapper, SkillsSearchRepository skillsSearchRepository) {
        this.skillsRepository = skillsRepository;
        this.skillsMapper = skillsMapper;
        this.skillsSearchRepository = skillsSearchRepository;
    }

    /**
     * Save a skills.
     *
     * @param skillsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SkillsDTO save(SkillsDTO skillsDTO) {
        log.debug("Request to save Skills : {}", skillsDTO);
        Skills skills = skillsMapper.toEntity(skillsDTO);
        skills = skillsRepository.save(skills);
        SkillsDTO result = skillsMapper.toDto(skills);
        skillsSearchRepository.save(skills);
        return result;
    }

    /**
     * Get all the skills.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SkillsDTO> findAll() {
        log.debug("Request to get all Skills");
        return skillsRepository.findAll().stream()
            .map(skillsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one skills by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public SkillsDTO findOne(Long id) {
        log.debug("Request to get Skills : {}", id);
        Skills skills = skillsRepository.findOne(id);
        return skillsMapper.toDto(skills);
    }

    /**
     * Delete the skills by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Skills : {}", id);
        skillsRepository.delete(id);
        skillsSearchRepository.delete(id);
    }

    /**
     * Search for the skills corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SkillsDTO> search(String query) {
        log.debug("Request to search Skills for query {}", query);
        return StreamSupport
            .stream(skillsSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(skillsMapper::toDto)
            .collect(Collectors.toList());
    }
}
