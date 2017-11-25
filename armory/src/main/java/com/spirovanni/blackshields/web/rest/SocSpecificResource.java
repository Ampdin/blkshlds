package com.spirovanni.blackshields.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.spirovanni.blackshields.service.SocSpecificService;
import com.spirovanni.blackshields.web.rest.errors.BadRequestAlertException;
import com.spirovanni.blackshields.web.rest.util.HeaderUtil;
import com.spirovanni.blackshields.web.rest.util.PaginationUtil;
import com.spirovanni.blackshields.service.dto.SocSpecificDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing SocSpecific.
 */
@RestController
@RequestMapping("/api")
public class SocSpecificResource {

    private final Logger log = LoggerFactory.getLogger(SocSpecificResource.class);

    private static final String ENTITY_NAME = "socSpecific";

    private final SocSpecificService socSpecificService;

    public SocSpecificResource(SocSpecificService socSpecificService) {
        this.socSpecificService = socSpecificService;
    }

    /**
     * POST  /soc-specifics : Create a new socSpecific.
     *
     * @param socSpecificDTO the socSpecificDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new socSpecificDTO, or with status 400 (Bad Request) if the socSpecific has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/soc-specifics")
    @Timed
    public ResponseEntity<SocSpecificDTO> createSocSpecific(@RequestBody SocSpecificDTO socSpecificDTO) throws URISyntaxException {
        log.debug("REST request to save SocSpecific : {}", socSpecificDTO);
        if (socSpecificDTO.getId() != null) {
            throw new BadRequestAlertException("A new socSpecific cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SocSpecificDTO result = socSpecificService.save(socSpecificDTO);
        return ResponseEntity.created(new URI("/api/soc-specifics/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /soc-specifics : Updates an existing socSpecific.
     *
     * @param socSpecificDTO the socSpecificDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated socSpecificDTO,
     * or with status 400 (Bad Request) if the socSpecificDTO is not valid,
     * or with status 500 (Internal Server Error) if the socSpecificDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/soc-specifics")
    @Timed
    public ResponseEntity<SocSpecificDTO> updateSocSpecific(@RequestBody SocSpecificDTO socSpecificDTO) throws URISyntaxException {
        log.debug("REST request to update SocSpecific : {}", socSpecificDTO);
        if (socSpecificDTO.getId() == null) {
            return createSocSpecific(socSpecificDTO);
        }
        SocSpecificDTO result = socSpecificService.save(socSpecificDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, socSpecificDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /soc-specifics : get all the socSpecifics.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of socSpecifics in body
     */
    @GetMapping("/soc-specifics")
    @Timed
    public ResponseEntity<List<SocSpecificDTO>> getAllSocSpecifics(Pageable pageable) {
        log.debug("REST request to get a page of SocSpecifics");
        Page<SocSpecificDTO> page = socSpecificService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/soc-specifics");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /soc-specifics/:id : get the "id" socSpecific.
     *
     * @param id the id of the socSpecificDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the socSpecificDTO, or with status 404 (Not Found)
     */
    @GetMapping("/soc-specifics/{id}")
    @Timed
    public ResponseEntity<SocSpecificDTO> getSocSpecific(@PathVariable Long id) {
        log.debug("REST request to get SocSpecific : {}", id);
        SocSpecificDTO socSpecificDTO = socSpecificService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(socSpecificDTO));
    }

    /**
     * DELETE  /soc-specifics/:id : delete the "id" socSpecific.
     *
     * @param id the id of the socSpecificDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/soc-specifics/{id}")
    @Timed
    public ResponseEntity<Void> deleteSocSpecific(@PathVariable Long id) {
        log.debug("REST request to delete SocSpecific : {}", id);
        socSpecificService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/soc-specifics?query=:query : search for the socSpecific corresponding
     * to the query.
     *
     * @param query the query of the socSpecific search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/soc-specifics")
    @Timed
    public ResponseEntity<List<SocSpecificDTO>> searchSocSpecifics(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of SocSpecifics for query {}", query);
        Page<SocSpecificDTO> page = socSpecificService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/soc-specifics");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
