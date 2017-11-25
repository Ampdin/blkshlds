package com.spirovanni.blackshields.web.rest;

import com.spirovanni.blackshields.ArmoryApp;

import com.spirovanni.blackshields.domain.SocMinor;
import com.spirovanni.blackshields.repository.SocMinorRepository;
import com.spirovanni.blackshields.service.SocMinorService;
import com.spirovanni.blackshields.repository.search.SocMinorSearchRepository;
import com.spirovanni.blackshields.service.dto.SocMinorDTO;
import com.spirovanni.blackshields.service.mapper.SocMinorMapper;
import com.spirovanni.blackshields.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.spirovanni.blackshields.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the SocMinorResource REST controller.
 *
 * @see SocMinorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArmoryApp.class)
public class SocMinorResourceIntTest {

    private static final String DEFAULT_SOC_MINOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SOC_MINOR_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SOC_MINOR_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SOC_MINOR_CODE = "BBBBBBBBBB";

    private static final byte[] DEFAULT_SOC_MINOR_AVATOR = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_SOC_MINOR_AVATOR = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_SOC_MINOR_AVATOR_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_SOC_MINOR_AVATOR_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_SOC_MINOR_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_SOC_MINOR_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_SOC_MINOR_URL = "AAAAAAAAAA";
    private static final String UPDATED_SOC_MINOR_URL = "BBBBBBBBBB";

    private static final String DEFAULT_SOC_MINOR_PREVIEW_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_SOC_MINOR_PREVIEW_IMAGE = "BBBBBBBBBB";

    @Autowired
    private SocMinorRepository socMinorRepository;

    @Autowired
    private SocMinorMapper socMinorMapper;

    @Autowired
    private SocMinorService socMinorService;

    @Autowired
    private SocMinorSearchRepository socMinorSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSocMinorMockMvc;

    private SocMinor socMinor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SocMinorResource socMinorResource = new SocMinorResource(socMinorService);
        this.restSocMinorMockMvc = MockMvcBuilders.standaloneSetup(socMinorResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SocMinor createEntity(EntityManager em) {
        SocMinor socMinor = new SocMinor()
            .socMinorName(DEFAULT_SOC_MINOR_NAME)
            .socMinorCode(DEFAULT_SOC_MINOR_CODE)
            .socMinorAvator(DEFAULT_SOC_MINOR_AVATOR)
            .socMinorAvatorContentType(DEFAULT_SOC_MINOR_AVATOR_CONTENT_TYPE)
            .socMinorDescription(DEFAULT_SOC_MINOR_DESCRIPTION)
            .socMinorURL(DEFAULT_SOC_MINOR_URL)
            .socMinorPreviewImage(DEFAULT_SOC_MINOR_PREVIEW_IMAGE);
        return socMinor;
    }

    @Before
    public void initTest() {
        socMinorSearchRepository.deleteAll();
        socMinor = createEntity(em);
    }

    @Test
    @Transactional
    public void createSocMinor() throws Exception {
        int databaseSizeBeforeCreate = socMinorRepository.findAll().size();

        // Create the SocMinor
        SocMinorDTO socMinorDTO = socMinorMapper.toDto(socMinor);
        restSocMinorMockMvc.perform(post("/api/soc-minors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(socMinorDTO)))
            .andExpect(status().isCreated());

        // Validate the SocMinor in the database
        List<SocMinor> socMinorList = socMinorRepository.findAll();
        assertThat(socMinorList).hasSize(databaseSizeBeforeCreate + 1);
        SocMinor testSocMinor = socMinorList.get(socMinorList.size() - 1);
        assertThat(testSocMinor.getSocMinorName()).isEqualTo(DEFAULT_SOC_MINOR_NAME);
        assertThat(testSocMinor.getSocMinorCode()).isEqualTo(DEFAULT_SOC_MINOR_CODE);
        assertThat(testSocMinor.getSocMinorAvator()).isEqualTo(DEFAULT_SOC_MINOR_AVATOR);
        assertThat(testSocMinor.getSocMinorAvatorContentType()).isEqualTo(DEFAULT_SOC_MINOR_AVATOR_CONTENT_TYPE);
        assertThat(testSocMinor.getSocMinorDescription()).isEqualTo(DEFAULT_SOC_MINOR_DESCRIPTION);
        assertThat(testSocMinor.getSocMinorURL()).isEqualTo(DEFAULT_SOC_MINOR_URL);
        assertThat(testSocMinor.getSocMinorPreviewImage()).isEqualTo(DEFAULT_SOC_MINOR_PREVIEW_IMAGE);

        // Validate the SocMinor in Elasticsearch
        SocMinor socMinorEs = socMinorSearchRepository.findOne(testSocMinor.getId());
        assertThat(socMinorEs).isEqualToComparingFieldByField(testSocMinor);
    }

    @Test
    @Transactional
    public void createSocMinorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = socMinorRepository.findAll().size();

        // Create the SocMinor with an existing ID
        socMinor.setId(1L);
        SocMinorDTO socMinorDTO = socMinorMapper.toDto(socMinor);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSocMinorMockMvc.perform(post("/api/soc-minors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(socMinorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SocMinor in the database
        List<SocMinor> socMinorList = socMinorRepository.findAll();
        assertThat(socMinorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSocMinors() throws Exception {
        // Initialize the database
        socMinorRepository.saveAndFlush(socMinor);

        // Get all the socMinorList
        restSocMinorMockMvc.perform(get("/api/soc-minors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(socMinor.getId().intValue())))
            .andExpect(jsonPath("$.[*].socMinorName").value(hasItem(DEFAULT_SOC_MINOR_NAME.toString())))
            .andExpect(jsonPath("$.[*].socMinorCode").value(hasItem(DEFAULT_SOC_MINOR_CODE.toString())))
            .andExpect(jsonPath("$.[*].socMinorAvatorContentType").value(hasItem(DEFAULT_SOC_MINOR_AVATOR_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].socMinorAvator").value(hasItem(Base64Utils.encodeToString(DEFAULT_SOC_MINOR_AVATOR))))
            .andExpect(jsonPath("$.[*].socMinorDescription").value(hasItem(DEFAULT_SOC_MINOR_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].socMinorURL").value(hasItem(DEFAULT_SOC_MINOR_URL.toString())))
            .andExpect(jsonPath("$.[*].socMinorPreviewImage").value(hasItem(DEFAULT_SOC_MINOR_PREVIEW_IMAGE.toString())));
    }

    @Test
    @Transactional
    public void getSocMinor() throws Exception {
        // Initialize the database
        socMinorRepository.saveAndFlush(socMinor);

        // Get the socMinor
        restSocMinorMockMvc.perform(get("/api/soc-minors/{id}", socMinor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(socMinor.getId().intValue()))
            .andExpect(jsonPath("$.socMinorName").value(DEFAULT_SOC_MINOR_NAME.toString()))
            .andExpect(jsonPath("$.socMinorCode").value(DEFAULT_SOC_MINOR_CODE.toString()))
            .andExpect(jsonPath("$.socMinorAvatorContentType").value(DEFAULT_SOC_MINOR_AVATOR_CONTENT_TYPE))
            .andExpect(jsonPath("$.socMinorAvator").value(Base64Utils.encodeToString(DEFAULT_SOC_MINOR_AVATOR)))
            .andExpect(jsonPath("$.socMinorDescription").value(DEFAULT_SOC_MINOR_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.socMinorURL").value(DEFAULT_SOC_MINOR_URL.toString()))
            .andExpect(jsonPath("$.socMinorPreviewImage").value(DEFAULT_SOC_MINOR_PREVIEW_IMAGE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSocMinor() throws Exception {
        // Get the socMinor
        restSocMinorMockMvc.perform(get("/api/soc-minors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSocMinor() throws Exception {
        // Initialize the database
        socMinorRepository.saveAndFlush(socMinor);
        socMinorSearchRepository.save(socMinor);
        int databaseSizeBeforeUpdate = socMinorRepository.findAll().size();

        // Update the socMinor
        SocMinor updatedSocMinor = socMinorRepository.findOne(socMinor.getId());
        updatedSocMinor
            .socMinorName(UPDATED_SOC_MINOR_NAME)
            .socMinorCode(UPDATED_SOC_MINOR_CODE)
            .socMinorAvator(UPDATED_SOC_MINOR_AVATOR)
            .socMinorAvatorContentType(UPDATED_SOC_MINOR_AVATOR_CONTENT_TYPE)
            .socMinorDescription(UPDATED_SOC_MINOR_DESCRIPTION)
            .socMinorURL(UPDATED_SOC_MINOR_URL)
            .socMinorPreviewImage(UPDATED_SOC_MINOR_PREVIEW_IMAGE);
        SocMinorDTO socMinorDTO = socMinorMapper.toDto(updatedSocMinor);

        restSocMinorMockMvc.perform(put("/api/soc-minors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(socMinorDTO)))
            .andExpect(status().isOk());

        // Validate the SocMinor in the database
        List<SocMinor> socMinorList = socMinorRepository.findAll();
        assertThat(socMinorList).hasSize(databaseSizeBeforeUpdate);
        SocMinor testSocMinor = socMinorList.get(socMinorList.size() - 1);
        assertThat(testSocMinor.getSocMinorName()).isEqualTo(UPDATED_SOC_MINOR_NAME);
        assertThat(testSocMinor.getSocMinorCode()).isEqualTo(UPDATED_SOC_MINOR_CODE);
        assertThat(testSocMinor.getSocMinorAvator()).isEqualTo(UPDATED_SOC_MINOR_AVATOR);
        assertThat(testSocMinor.getSocMinorAvatorContentType()).isEqualTo(UPDATED_SOC_MINOR_AVATOR_CONTENT_TYPE);
        assertThat(testSocMinor.getSocMinorDescription()).isEqualTo(UPDATED_SOC_MINOR_DESCRIPTION);
        assertThat(testSocMinor.getSocMinorURL()).isEqualTo(UPDATED_SOC_MINOR_URL);
        assertThat(testSocMinor.getSocMinorPreviewImage()).isEqualTo(UPDATED_SOC_MINOR_PREVIEW_IMAGE);

        // Validate the SocMinor in Elasticsearch
        SocMinor socMinorEs = socMinorSearchRepository.findOne(testSocMinor.getId());
        assertThat(socMinorEs).isEqualToComparingFieldByField(testSocMinor);
    }

    @Test
    @Transactional
    public void updateNonExistingSocMinor() throws Exception {
        int databaseSizeBeforeUpdate = socMinorRepository.findAll().size();

        // Create the SocMinor
        SocMinorDTO socMinorDTO = socMinorMapper.toDto(socMinor);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSocMinorMockMvc.perform(put("/api/soc-minors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(socMinorDTO)))
            .andExpect(status().isCreated());

        // Validate the SocMinor in the database
        List<SocMinor> socMinorList = socMinorRepository.findAll();
        assertThat(socMinorList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSocMinor() throws Exception {
        // Initialize the database
        socMinorRepository.saveAndFlush(socMinor);
        socMinorSearchRepository.save(socMinor);
        int databaseSizeBeforeDelete = socMinorRepository.findAll().size();

        // Get the socMinor
        restSocMinorMockMvc.perform(delete("/api/soc-minors/{id}", socMinor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean socMinorExistsInEs = socMinorSearchRepository.exists(socMinor.getId());
        assertThat(socMinorExistsInEs).isFalse();

        // Validate the database is empty
        List<SocMinor> socMinorList = socMinorRepository.findAll();
        assertThat(socMinorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchSocMinor() throws Exception {
        // Initialize the database
        socMinorRepository.saveAndFlush(socMinor);
        socMinorSearchRepository.save(socMinor);

        // Search the socMinor
        restSocMinorMockMvc.perform(get("/api/_search/soc-minors?query=id:" + socMinor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(socMinor.getId().intValue())))
            .andExpect(jsonPath("$.[*].socMinorName").value(hasItem(DEFAULT_SOC_MINOR_NAME.toString())))
            .andExpect(jsonPath("$.[*].socMinorCode").value(hasItem(DEFAULT_SOC_MINOR_CODE.toString())))
            .andExpect(jsonPath("$.[*].socMinorAvatorContentType").value(hasItem(DEFAULT_SOC_MINOR_AVATOR_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].socMinorAvator").value(hasItem(Base64Utils.encodeToString(DEFAULT_SOC_MINOR_AVATOR))))
            .andExpect(jsonPath("$.[*].socMinorDescription").value(hasItem(DEFAULT_SOC_MINOR_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].socMinorURL").value(hasItem(DEFAULT_SOC_MINOR_URL.toString())))
            .andExpect(jsonPath("$.[*].socMinorPreviewImage").value(hasItem(DEFAULT_SOC_MINOR_PREVIEW_IMAGE.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SocMinor.class);
        SocMinor socMinor1 = new SocMinor();
        socMinor1.setId(1L);
        SocMinor socMinor2 = new SocMinor();
        socMinor2.setId(socMinor1.getId());
        assertThat(socMinor1).isEqualTo(socMinor2);
        socMinor2.setId(2L);
        assertThat(socMinor1).isNotEqualTo(socMinor2);
        socMinor1.setId(null);
        assertThat(socMinor1).isNotEqualTo(socMinor2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SocMinorDTO.class);
        SocMinorDTO socMinorDTO1 = new SocMinorDTO();
        socMinorDTO1.setId(1L);
        SocMinorDTO socMinorDTO2 = new SocMinorDTO();
        assertThat(socMinorDTO1).isNotEqualTo(socMinorDTO2);
        socMinorDTO2.setId(socMinorDTO1.getId());
        assertThat(socMinorDTO1).isEqualTo(socMinorDTO2);
        socMinorDTO2.setId(2L);
        assertThat(socMinorDTO1).isNotEqualTo(socMinorDTO2);
        socMinorDTO1.setId(null);
        assertThat(socMinorDTO1).isNotEqualTo(socMinorDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(socMinorMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(socMinorMapper.fromId(null)).isNull();
    }
}
