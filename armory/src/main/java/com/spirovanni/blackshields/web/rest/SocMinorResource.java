package com.spirovanni.blackshields.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.spirovanni.blackshields.service.SocMinorService;
import com.spirovanni.blackshields.web.rest.errors.BadRequestAlertException;
import com.spirovanni.blackshields.web.rest.util.HeaderUtil;
import com.spirovanni.blackshields.service.dto.SocMinorDTO;
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
 * REST controller for managing SocMinor.
 */
@RestController
@RequestMapping("/api")
public class SocMinorResource {

    private final Logger log = LoggerFactory.getLogger(SocMinorResource.class);

    private static final String ENTITY_NAME = "socMinor";

    private final SocMinorService socMinorService;

    public SocMinorResource(SocMinorService socMinorService) {
        this.socMinorService = socMinorService;
    }

    /**
     * POST  /soc-minors : Create a new socMinor.
     *
     * @param socMinorDTO the socMinorDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new socMinorDTO, or with status 400 (Bad Request) if the socMinor has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/soc-minors")
    @Timed
    public ResponseEntity<SocMinorDTO> createSocMinor(@RequestBody SocMinorDTO socMinorDTO) throws URISyntaxException {
        log.debug("REST request to save SocMinor : {}", socMinorDTO);
        if (socMinorDTO.getId() != null) {
            throw new BadRequestAlertException("A new socMinor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SocMinorDTO result = socMinorService.save(socMinorDTO);
        return ResponseEntity.created(new URI("/api/soc-minors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /soc-minors : Updates an existing socMinor.
     *
     * @param socMinorDTO the socMinorDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated socMinorDTO,
     * or with status 400 (Bad Request) if the socMinorDTO is not valid,
     * or with status 500 (Internal Server Error) if the socMinorDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/soc-minors")
    @Timed
    public ResponseEntity<SocMinorDTO> updateSocMinor(@RequestBody SocMinorDTO socMinorDTO) throws URISyntaxException {
        log.debug("REST request to update SocMinor : {}", socMinorDTO);
        if (socMinorDTO.getId() == null) {
            return createSocMinor(socMinorDTO);
        }
        SocMinorDTO result = socMinorService.save(socMinorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, socMinorDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /soc-minors : get all the socMinors.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of socMinors in body
     */
    @GetMapping("/soc-minors")
    @Timed
    public List<SocMinorDTO> getAllSocMinors() {
        log.debug("REST request to get all SocMinors");
        return socMinorService.findAll();
        }

    /**
     * GET  /soc-minors/:id : get the "id" socMinor.
     *
     * @param id the id of the socMinorDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the socMinorDTO, or with status 404 (Not Found)
     */
    @GetMapping("/soc-minors/{id}")
    @Timed
    public ResponseEntity<SocMinorDTO> getSocMinor(@PathVariable Long id) {
        log.debug("REST request to get SocMinor : {}", id);
        SocMinorDTO socMinorDTO = socMinorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(socMinorDTO));
    }

    /**
     * DELETE  /soc-minors/:id : delete the "id" socMinor.
     *
     * @param id the id of the socMinorDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/soc-minors/{id}")
    @Timed
    public ResponseEntity<Void> deleteSocMinor(@PathVariable Long id) {
        log.debug("REST request to delete SocMinor : {}", id);
        socMinorService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/soc-minors?query=:query : search for the socMinor corresponding
     * to the query.
     *
     * @param query the query of the socMinor search
     * @return the result of the search
     */
    @GetMapping("/_search/soc-minors")
    @Timed
    public List<SocMinorDTO> searchSocMinors(@RequestParam String query) {
        log.debug("REST request to search SocMinors for query {}", query);
        return socMinorService.search(query);
    }

}
