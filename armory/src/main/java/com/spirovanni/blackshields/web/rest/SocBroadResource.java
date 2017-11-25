package com.spirovanni.blackshields.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.spirovanni.blackshields.service.SocBroadService;
import com.spirovanni.blackshields.web.rest.errors.BadRequestAlertException;
import com.spirovanni.blackshields.web.rest.util.HeaderUtil;
import com.spirovanni.blackshields.service.dto.SocBroadDTO;
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
 * REST controller for managing SocBroad.
 */
@RestController
@RequestMapping("/api")
public class SocBroadResource {

    private final Logger log = LoggerFactory.getLogger(SocBroadResource.class);

    private static final String ENTITY_NAME = "socBroad";

    private final SocBroadService socBroadService;

    public SocBroadResource(SocBroadService socBroadService) {
        this.socBroadService = socBroadService;
    }

    /**
     * POST  /soc-broads : Create a new socBroad.
     *
     * @param socBroadDTO the socBroadDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new socBroadDTO, or with status 400 (Bad Request) if the socBroad has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/soc-broads")
    @Timed
    public ResponseEntity<SocBroadDTO> createSocBroad(@RequestBody SocBroadDTO socBroadDTO) throws URISyntaxException {
        log.debug("REST request to save SocBroad : {}", socBroadDTO);
        if (socBroadDTO.getId() != null) {
            throw new BadRequestAlertException("A new socBroad cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SocBroadDTO result = socBroadService.save(socBroadDTO);
        return ResponseEntity.created(new URI("/api/soc-broads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /soc-broads : Updates an existing socBroad.
     *
     * @param socBroadDTO the socBroadDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated socBroadDTO,
     * or with status 400 (Bad Request) if the socBroadDTO is not valid,
     * or with status 500 (Internal Server Error) if the socBroadDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/soc-broads")
    @Timed
    public ResponseEntity<SocBroadDTO> updateSocBroad(@RequestBody SocBroadDTO socBroadDTO) throws URISyntaxException {
        log.debug("REST request to update SocBroad : {}", socBroadDTO);
        if (socBroadDTO.getId() == null) {
            return createSocBroad(socBroadDTO);
        }
        SocBroadDTO result = socBroadService.save(socBroadDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, socBroadDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /soc-broads : get all the socBroads.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of socBroads in body
     */
    @GetMapping("/soc-broads")
    @Timed
    public List<SocBroadDTO> getAllSocBroads() {
        log.debug("REST request to get all SocBroads");
        return socBroadService.findAll();
        }

    /**
     * GET  /soc-broads/:id : get the "id" socBroad.
     *
     * @param id the id of the socBroadDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the socBroadDTO, or with status 404 (Not Found)
     */
    @GetMapping("/soc-broads/{id}")
    @Timed
    public ResponseEntity<SocBroadDTO> getSocBroad(@PathVariable Long id) {
        log.debug("REST request to get SocBroad : {}", id);
        SocBroadDTO socBroadDTO = socBroadService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(socBroadDTO));
    }

    /**
     * DELETE  /soc-broads/:id : delete the "id" socBroad.
     *
     * @param id the id of the socBroadDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/soc-broads/{id}")
    @Timed
    public ResponseEntity<Void> deleteSocBroad(@PathVariable Long id) {
        log.debug("REST request to delete SocBroad : {}", id);
        socBroadService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/soc-broads?query=:query : search for the socBroad corresponding
     * to the query.
     *
     * @param query the query of the socBroad search
     * @return the result of the search
     */
    @GetMapping("/_search/soc-broads")
    @Timed
    public List<SocBroadDTO> searchSocBroads(@RequestParam String query) {
        log.debug("REST request to search SocBroads for query {}", query);
        return socBroadService.search(query);
    }

}
