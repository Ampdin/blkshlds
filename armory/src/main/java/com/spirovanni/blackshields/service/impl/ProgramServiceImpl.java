package com.spirovanni.blackshields.service.impl;

import com.spirovanni.blackshields.service.ProgramService;
import com.spirovanni.blackshields.domain.Program;
import com.spirovanni.blackshields.repository.ProgramRepository;
import com.spirovanni.blackshields.repository.search.ProgramSearchRepository;
import com.spirovanni.blackshields.service.dto.ProgramDTO;
import com.spirovanni.blackshields.service.mapper.ProgramMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Program.
 */
@Service
@Transactional
public class ProgramServiceImpl implements ProgramService{

    private final Logger log = LoggerFactory.getLogger(ProgramServiceImpl.class);

    private final ProgramRepository programRepository;

    private final ProgramMapper programMapper;

    private final ProgramSearchRepository programSearchRepository;

    public ProgramServiceImpl(ProgramRepository programRepository, ProgramMapper programMapper, ProgramSearchRepository programSearchRepository) {
        this.programRepository = programRepository;
        this.programMapper = programMapper;
        this.programSearchRepository = programSearchRepository;
    }

    /**
     * Save a program.
     *
     * @param programDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProgramDTO save(ProgramDTO programDTO) {
        log.debug("Request to save Program : {}", programDTO);
        Program program = programMapper.toEntity(programDTO);
        program = programRepository.save(program);
        ProgramDTO result = programMapper.toDto(program);
        programSearchRepository.save(program);
        return result;
    }

    /**
     * Get all the programs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProgramDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Programs");
        return programRepository.findAll(pageable)
            .map(programMapper::toDto);
    }

    /**
     * Get one program by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ProgramDTO findOne(Long id) {
        log.debug("Request to get Program : {}", id);
        Program program = programRepository.findOneWithEagerRelationships(id);
        return programMapper.toDto(program);
    }

    /**
     * Delete the program by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Program : {}", id);
        programRepository.delete(id);
        programSearchRepository.delete(id);
    }

    /**
     * Search for the program corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProgramDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Programs for query {}", query);
        Page<Program> result = programSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(programMapper::toDto);
    }
}
