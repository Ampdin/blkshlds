package com.spirovanni.blackshields.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.spirovanni.blackshields.service.SocMajorService;
import com.spirovanni.blackshields.web.rest.errors.BadRequestAlertException;
import com.spirovanni.blackshields.web.rest.util.HeaderUtil;
import com.spirovanni.blackshields.service.dto.SocMajorDTO;
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
 * REST controller for managing SocMajor.
 */
@RestController
@RequestMapping("/api")
public class SocMajorResource {

    private final Logger log = LoggerFactory.getLogger(SocMajorResource.class);

    private static final String ENTITY_NAME = "socMajor";

    private final SocMajorService socMajorService;

    public SocMajorResource(SocMajorService socMajorService) {
        this.socMajorService = socMajorService;
    }

    /**
     * POST  /soc-majors : Create a new socMajor.
     *
     * @param socMajorDTO the socMajorDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new socMajorDTO, or with status 400 (Bad Request) if the socMajor has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/soc-majors")
    @Timed
    public ResponseEntity<SocMajorDTO> createSocMajor(@RequestBody SocMajorDTO socMajorDTO) throws URISyntaxException {
        log.debug("REST request to save SocMajor : {}", socMajorDTO);
        if (socMajorDTO.getId() != null) {
            throw new BadRequestAlertException("A new socMajor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SocMajorDTO result = socMajorService.save(socMajorDTO);
        return ResponseEntity.created(new URI("/api/soc-majors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /soc-majors : Updates an existing socMajor.
     *
     * @param socMajorDTO the socMajorDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated socMajorDTO,
     * or with status 400 (Bad Request) if the socMajorDTO is not valid,
     * or with status 500 (Internal Server Error) if the socMajorDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/soc-majors")
    @Timed
    public ResponseEntity<SocMajorDTO> updateSocMajor(@RequestBody SocMajorDTO socMajorDTO) throws URISyntaxException {
        log.debug("REST request to update SocMajor : {}", socMajorDTO);
        if (socMajorDTO.getId() == null) {
            return createSocMajor(socMajorDTO);
        }
        SocMajorDTO result = socMajorService.save(socMajorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, socMajorDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /soc-majors : get all the socMajors.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of socMajors in body
     */
    @GetMapping("/soc-majors")
    @Timed
    public List<SocMajorDTO> getAllSocMajors() {
        log.debug("REST request to get all SocMajors");
        return socMajorService.findAll();
        }

    /**
     * GET  /soc-majors/:id : get the "id" socMajor.
     *
     * @param id the id of the socMajorDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the socMajorDTO, or with status 404 (Not Found)
     */
    @GetMapping("/soc-majors/{id}")
    @Timed
    public ResponseEntity<SocMajorDTO> getSocMajor(@PathVariable Long id) {
        log.debug("REST request to get SocMajor : {}", id);
        SocMajorDTO socMajorDTO = socMajorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(socMajorDTO));
    }

    /**
     * DELETE  /soc-majors/:id : delete the "id" socMajor.
     *
     * @param id the id of the socMajorDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/soc-majors/{id}")
    @Timed
    public ResponseEntity<Void> deleteSocMajor(@PathVariable Long id) {
        log.debug("REST request to delete SocMajor : {}", id);
        socMajorService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/soc-majors?query=:query : search for the socMajor corresponding
     * to the query.
     *
     * @param query the query of the socMajor search
     * @return the result of the search
     */
    @GetMapping("/_search/soc-majors")
    @Timed
    public List<SocMajorDTO> searchSocMajors(@RequestParam String query) {
        log.debug("REST request to search SocMajors for query {}", query);
        return socMajorService.search(query);
    }

}
