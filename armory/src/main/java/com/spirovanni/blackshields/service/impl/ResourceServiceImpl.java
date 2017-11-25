package com.spirovanni.blackshields.service.impl;

import com.spirovanni.blackshields.service.ResourceService;
import com.spirovanni.blackshields.domain.Resource;
import com.spirovanni.blackshields.repository.ResourceRepository;
import com.spirovanni.blackshields.repository.search.ResourceSearchRepository;
import com.spirovanni.blackshields.service.dto.ResourceDTO;
import com.spirovanni.blackshields.service.mapper.ResourceMapper;
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
 * Service Implementation for managing Resource.
 */
@Service
@Transactional
public class ResourceServiceImpl implements ResourceService{

    private final Logger log = LoggerFactory.getLogger(ResourceServiceImpl.class);

    private final ResourceRepository resourceRepository;

    private final ResourceMapper resourceMapper;

    private final ResourceSearchRepository resourceSearchRepository;

    public ResourceServiceImpl(ResourceRepository resourceRepository, ResourceMapper resourceMapper, ResourceSearchRepository resourceSearchRepository) {
        this.resourceRepository = resourceRepository;
        this.resourceMapper = resourceMapper;
        this.resourceSearchRepository = resourceSearchRepository;
    }

    /**
     * Save a resource.
     *
     * @param resourceDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ResourceDTO save(ResourceDTO resourceDTO) {
        log.debug("Request to save Resource : {}", resourceDTO);
        Resource resource = resourceMapper.toEntity(resourceDTO);
        resource = resourceRepository.save(resource);
        ResourceDTO result = resourceMapper.toDto(resource);
        resourceSearchRepository.save(resource);
        return result;
    }

    /**
     * Get all the resources.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ResourceDTO> findAll() {
        log.debug("Request to get all Resources");
        return resourceRepository.findAll().stream()
            .map(resourceMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one resource by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ResourceDTO findOne(Long id) {
        log.debug("Request to get Resource : {}", id);
        Resource resource = resourceRepository.findOne(id);
        return resourceMapper.toDto(resource);
    }

    /**
     * Delete the resource by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Resource : {}", id);
        resourceRepository.delete(id);
        resourceSearchRepository.delete(id);
    }

    /**
     * Search for the resource corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ResourceDTO> search(String query) {
        log.debug("Request to search Resources for query {}", query);
        return StreamSupport
            .stream(resourceSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(resourceMapper::toDto)
            .collect(Collectors.toList());
    }
}
