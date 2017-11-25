package com.spirovanni.blackshields.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.spirovanni.blackshields.service.ResourceService;
import com.spirovanni.blackshields.web.rest.errors.BadRequestAlertException;
import com.spirovanni.blackshields.web.rest.util.HeaderUtil;
import com.spirovanni.blackshields.service.dto.ResourceDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Resource.
 */
@RestController
@RequestMapping("/api")
public class ResourceResource {

    private final Logger log = LoggerFactory.getLogger(ResourceResource.class);

    private static final String ENTITY_NAME = "resource";

    private final ResourceService resourceService;

    public ResourceResource(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    /**
     * POST  /resources : Create a new resource.
     *
     * @param resourceDTO the resourceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new resourceDTO, or with status 400 (Bad Request) if the resource has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/resources")
    @Timed
    public ResponseEntity<ResourceDTO> createResource(@RequestBody ResourceDTO resourceDTO) throws URISyntaxException {
        log.debug("REST request to save Resource : {}", resourceDTO);
        if (resourceDTO.getId() != null) {
            throw new BadRequestAlertException("A new resource cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ResourceDTO result = resourceService.save(resourceDTO);
        return ResponseEntity.created(new URI("/api/resources/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /resources : Updates an existing resource.
     *
     * @param resourceDTO the resourceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated resourceDTO,
     * or with status 400 (Bad Request) if the resourceDTO is not valid,
     * or with status 500 (Internal Server Error) if the resourceDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/resources")
    @Timed
    public ResponseEntity<ResourceDTO> updateResource(@RequestBody ResourceDTO resourceDTO) throws URISyntaxException {
        log.debug("REST request to update Resource : {}", resourceDTO);
        if (resourceDTO.getId() == null) {
            return createResource(resourceDTO);
        }
        ResourceDTO result = resourceService.save(resourceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, resourceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /resources : get all the resources.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of resources in body
     */
    @GetMapping("/resources")
    @Timed
    public List<ResourceDTO> getAllResources() {
        log.debug("REST request to get all Resources");
        return resourceService.findAll();
        }

    /**
     * GET  /resources/:id : get the "id" resource.
     *
     * @param id the id of the resourceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the resourceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/resources/{id}")
    @Timed
    public ResponseEntity<ResourceDTO> getResource(@PathVariable Long id) {
        log.debug("REST request to get Resource : {}", id);
        ResourceDTO resourceDTO = resourceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(resourceDTO));
    }

    /**
     * DELETE  /resources/:id : delete the "id" resource.
     *
     * @param id the id of the resourceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/resources/{id}")
    @Timed
    public ResponseEntity<Void> deleteResource(@PathVariable Long id) {
        log.debug("REST request to delete Resource : {}", id);
        resourceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/resources?query=:query : search for the resource corresponding
     * to the query.
     *
     * @param query the query of the resource search
     * @return the result of the search
     */
    @GetMapping("/_search/resources")
    @Timed
    public List<ResourceDTO> searchResources(@RequestParam String query) {
        log.debug("REST request to search Resources for query {}", query);
        return resourceService.search(query);
    }

}
