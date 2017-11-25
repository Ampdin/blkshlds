package com.spirovanni.blackshields.web.rest;

import com.spirovanni.blackshields.ArmoryApp;

import com.spirovanni.blackshields.domain.SocMajor;
import com.spirovanni.blackshields.repository.SocMajorRepository;
import com.spirovanni.blackshields.service.SocMajorService;
import com.spirovanni.blackshields.repository.search.SocMajorSearchRepository;
import com.spirovanni.blackshields.service.dto.SocMajorDTO;
import com.spirovanni.blackshields.service.mapper.SocMajorMapper;
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
 * Test class for the SocMajorResource REST controller.
 *
 * @see SocMajorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArmoryApp.class)
public class SocMajorResourceIntTest {

    private static final String DEFAULT_SOC_MAJOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SOC_MAJOR_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SOC_MAJOR_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SOC_MAJOR_CODE = "BBBBBBBBBB";

    private static final byte[] DEFAULT_SOC_MAJOR_AVATOR = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_SOC_MAJOR_AVATOR = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_SOC_MAJOR_AVATOR_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_SOC_MAJOR_AVATOR_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_SOC_MAJOR_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_SOC_MAJOR_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_SOC_MAJOR_URL = "AAAAAAAAAA";
    private static final String UPDATED_SOC_MAJOR_URL = "BBBBBBBBBB";

    private static final String DEFAULT_SOC_MAJOR_PREVIEW_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_SOC_MAJOR_PREVIEW_IMAGE = "BBBBBBBBBB";

    @Autowired
    private SocMajorRepository socMajorRepository;

    @Autowired
    private SocMajorMapper socMajorMapper;

    @Autowired
    private SocMajorService socMajorService;

    @Autowired
    private SocMajorSearchRepository socMajorSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSocMajorMockMvc;

    private SocMajor socMajor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SocMajorResource socMajorResource = new SocMajorResource(socMajorService);
        this.restSocMajorMockMvc = MockMvcBuilders.standaloneSetup(socMajorResource)
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
    public static SocMajor createEntity(EntityManager em) {
        SocMajor socMajor = new SocMajor()
            .socMajorName(DEFAULT_SOC_MAJOR_NAME)
            .socMajorCode(DEFAULT_SOC_MAJOR_CODE)
            .socMajorAvator(DEFAULT_SOC_MAJOR_AVATOR)
            .socMajorAvatorContentType(DEFAULT_SOC_MAJOR_AVATOR_CONTENT_TYPE)
            .socMajorDescription(DEFAULT_SOC_MAJOR_DESCRIPTION)
            .socMajorURL(DEFAULT_SOC_MAJOR_URL)
            .socMajorPreviewImage(DEFAULT_SOC_MAJOR_PREVIEW_IMAGE);
        return socMajor;
    }

    @Before
    public void initTest() {
        socMajorSearchRepository.deleteAll();
        socMajor = createEntity(em);
    }

    @Test
    @Transactional
    public void createSocMajor() throws Exception {
        int databaseSizeBeforeCreate = socMajorRepository.findAll().size();

        // Create the SocMajor
        SocMajorDTO socMajorDTO = socMajorMapper.toDto(socMajor);
        restSocMajorMockMvc.perform(post("/api/soc-majors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(socMajorDTO)))
            .andExpect(status().isCreated());

        // Validate the SocMajor in the database
        List<SocMajor> socMajorList = socMajorRepository.findAll();
        assertThat(socMajorList).hasSize(databaseSizeBeforeCreate + 1);
        SocMajor testSocMajor = socMajorList.get(socMajorList.size() - 1);
        assertThat(testSocMajor.getSocMajorName()).isEqualTo(DEFAULT_SOC_MAJOR_NAME);
        assertThat(testSocMajor.getSocMajorCode()).isEqualTo(DEFAULT_SOC_MAJOR_CODE);
        assertThat(testSocMajor.getSocMajorAvator()).isEqualTo(DEFAULT_SOC_MAJOR_AVATOR);
        assertThat(testSocMajor.getSocMajorAvatorContentType()).isEqualTo(DEFAULT_SOC_MAJOR_AVATOR_CONTENT_TYPE);
        assertThat(testSocMajor.getSocMajorDescription()).isEqualTo(DEFAULT_SOC_MAJOR_DESCRIPTION);
        assertThat(testSocMajor.getSocMajorURL()).isEqualTo(DEFAULT_SOC_MAJOR_URL);
        assertThat(testSocMajor.getSocMajorPreviewImage()).isEqualTo(DEFAULT_SOC_MAJOR_PREVIEW_IMAGE);

        // Validate the SocMajor in Elasticsearch
        SocMajor socMajorEs = socMajorSearchRepository.findOne(testSocMajor.getId());
        assertThat(socMajorEs).isEqualToComparingFieldByField(testSocMajor);
    }

    @Test
    @Transactional
    public void createSocMajorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = socMajorRepository.findAll().size();

        // Create the SocMajor with an existing ID
        socMajor.setId(1L);
        SocMajorDTO socMajorDTO = socMajorMapper.toDto(socMajor);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSocMajorMockMvc.perform(post("/api/soc-majors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(socMajorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SocMajor in the database
        List<SocMajor> socMajorList = socMajorRepository.findAll();
        assertThat(socMajorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSocMajors() throws Exception {
        // Initialize the database
        socMajorRepository.saveAndFlush(socMajor);

        // Get all the socMajorList
        restSocMajorMockMvc.perform(get("/api/soc-majors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(socMajor.getId().intValue())))
            .andExpect(jsonPath("$.[*].socMajorName").value(hasItem(DEFAULT_SOC_MAJOR_NAME.toString())))
            .andExpect(jsonPath("$.[*].socMajorCode").value(hasItem(DEFAULT_SOC_MAJOR_CODE.toString())))
            .andExpect(jsonPath("$.[*].socMajorAvatorContentType").value(hasItem(DEFAULT_SOC_MAJOR_AVATOR_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].socMajorAvator").value(hasItem(Base64Utils.encodeToString(DEFAULT_SOC_MAJOR_AVATOR))))
            .andExpect(jsonPath("$.[*].socMajorDescription").value(hasItem(DEFAULT_SOC_MAJOR_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].socMajorURL").value(hasItem(DEFAULT_SOC_MAJOR_URL.toString())))
            .andExpect(jsonPath("$.[*].socMajorPreviewImage").value(hasItem(DEFAULT_SOC_MAJOR_PREVIEW_IMAGE.toString())));
    }

    @Test
    @Transactional
    public void getSocMajor() throws Exception {
        // Initialize the database
        socMajorRepository.saveAndFlush(socMajor);

        // Get the socMajor
        restSocMajorMockMvc.perform(get("/api/soc-majors/{id}", socMajor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(socMajor.getId().intValue()))
            .andExpect(jsonPath("$.socMajorName").value(DEFAULT_SOC_MAJOR_NAME.toString()))
            .andExpect(jsonPath("$.socMajorCode").value(DEFAULT_SOC_MAJOR_CODE.toString()))
            .andExpect(jsonPath("$.socMajorAvatorContentType").value(DEFAULT_SOC_MAJOR_AVATOR_CONTENT_TYPE))
            .andExpect(jsonPath("$.socMajorAvator").value(Base64Utils.encodeToString(DEFAULT_SOC_MAJOR_AVATOR)))
            .andExpect(jsonPath("$.socMajorDescription").value(DEFAULT_SOC_MAJOR_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.socMajorURL").value(DEFAULT_SOC_MAJOR_URL.toString()))
            .andExpect(jsonPath("$.socMajorPreviewImage").value(DEFAULT_SOC_MAJOR_PREVIEW_IMAGE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSocMajor() throws Exception {
        // Get the socMajor
        restSocMajorMockMvc.perform(get("/api/soc-majors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSocMajor() throws Exception {
        // Initialize the database
        socMajorRepository.saveAndFlush(socMajor);
        socMajorSearchRepository.save(socMajor);
        int databaseSizeBeforeUpdate = socMajorRepository.findAll().size();

        // Update the socMajor
        SocMajor updatedSocMajor = socMajorRepository.findOne(socMajor.getId());
        updatedSocMajor
            .socMajorName(UPDATED_SOC_MAJOR_NAME)
            .socMajorCode(UPDATED_SOC_MAJOR_CODE)
            .socMajorAvator(UPDATED_SOC_MAJOR_AVATOR)
            .socMajorAvatorContentType(UPDATED_SOC_MAJOR_AVATOR_CONTENT_TYPE)
            .socMajorDescription(UPDATED_SOC_MAJOR_DESCRIPTION)
            .socMajorURL(UPDATED_SOC_MAJOR_URL)
            .socMajorPreviewImage(UPDATED_SOC_MAJOR_PREVIEW_IMAGE);
        SocMajorDTO socMajorDTO = socMajorMapper.toDto(updatedSocMajor);

        restSocMajorMockMvc.perform(put("/api/soc-majors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(socMajorDTO)))
            .andExpect(status().isOk());

        // Validate the SocMajor in the database
        List<SocMajor> socMajorList = socMajorRepository.findAll();
        assertThat(socMajorList).hasSize(databaseSizeBeforeUpdate);
        SocMajor testSocMajor = socMajorList.get(socMajorList.size() - 1);
        assertThat(testSocMajor.getSocMajorName()).isEqualTo(UPDATED_SOC_MAJOR_NAME);
        assertThat(testSocMajor.getSocMajorCode()).isEqualTo(UPDATED_SOC_MAJOR_CODE);
        assertThat(testSocMajor.getSocMajorAvator()).isEqualTo(UPDATED_SOC_MAJOR_AVATOR);
        assertThat(testSocMajor.getSocMajorAvatorContentType()).isEqualTo(UPDATED_SOC_MAJOR_AVATOR_CONTENT_TYPE);
        assertThat(testSocMajor.getSocMajorDescription()).isEqualTo(UPDATED_SOC_MAJOR_DESCRIPTION);
        assertThat(testSocMajor.getSocMajorURL()).isEqualTo(UPDATED_SOC_MAJOR_URL);
        assertThat(testSocMajor.getSocMajorPreviewImage()).isEqualTo(UPDATED_SOC_MAJOR_PREVIEW_IMAGE);

        // Validate the SocMajor in Elasticsearch
        SocMajor socMajorEs = socMajorSearchRepository.findOne(testSocMajor.getId());
        assertThat(socMajorEs).isEqualToComparingFieldByField(testSocMajor);
    }

    @Test
    @Transactional
    public void updateNonExistingSocMajor() throws Exception {
        int databaseSizeBeforeUpdate = socMajorRepository.findAll().size();

        // Create the SocMajor
        SocMajorDTO socMajorDTO = socMajorMapper.toDto(socMajor);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSocMajorMockMvc.perform(put("/api/soc-majors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(socMajorDTO)))
            .andExpect(status().isCreated());

        // Validate the SocMajor in the database
        List<SocMajor> socMajorList = socMajorRepository.findAll();
        assertThat(socMajorList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSocMajor() throws Exception {
        // Initialize the database
        socMajorRepository.saveAndFlush(socMajor);
        socMajorSearchRepository.save(socMajor);
        int databaseSizeBeforeDelete = socMajorRepository.findAll().size();

        // Get the socMajor
        restSocMajorMockMvc.perform(delete("/api/soc-majors/{id}", socMajor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean socMajorExistsInEs = socMajorSearchRepository.exists(socMajor.getId());
        assertThat(socMajorExistsInEs).isFalse();

        // Validate the database is empty
        List<SocMajor> socMajorList = socMajorRepository.findAll();
        assertThat(socMajorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchSocMajor() throws Exception {
        // Initialize the database
        socMajorRepository.saveAndFlush(socMajor);
        socMajorSearchRepository.save(socMajor);

        // Search the socMajor
        restSocMajorMockMvc.perform(get("/api/_search/soc-majors?query=id:" + socMajor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(socMajor.getId().intValue())))
            .andExpect(jsonPath("$.[*].socMajorName").value(hasItem(DEFAULT_SOC_MAJOR_NAME.toString())))
            .andExpect(jsonPath("$.[*].socMajorCode").value(hasItem(DEFAULT_SOC_MAJOR_CODE.toString())))
            .andExpect(jsonPath("$.[*].socMajorAvatorContentType").value(hasItem(DEFAULT_SOC_MAJOR_AVATOR_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].socMajorAvator").value(hasItem(Base64Utils.encodeToString(DEFAULT_SOC_MAJOR_AVATOR))))
            .andExpect(jsonPath("$.[*].socMajorDescription").value(hasItem(DEFAULT_SOC_MAJOR_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].socMajorURL").value(hasItem(DEFAULT_SOC_MAJOR_URL.toString())))
            .andExpect(jsonPath("$.[*].socMajorPreviewImage").value(hasItem(DEFAULT_SOC_MAJOR_PREVIEW_IMAGE.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SocMajor.class);
        SocMajor socMajor1 = new SocMajor();
        socMajor1.setId(1L);
        SocMajor socMajor2 = new SocMajor();
        socMajor2.setId(socMajor1.getId());
        assertThat(socMajor1).isEqualTo(socMajor2);
        socMajor2.setId(2L);
        assertThat(socMajor1).isNotEqualTo(socMajor2);
        socMajor1.setId(null);
        assertThat(socMajor1).isNotEqualTo(socMajor2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SocMajorDTO.class);
        SocMajorDTO socMajorDTO1 = new SocMajorDTO();
        socMajorDTO1.setId(1L);
        SocMajorDTO socMajorDTO2 = new SocMajorDTO();
        assertThat(socMajorDTO1).isNotEqualTo(socMajorDTO2);
        socMajorDTO2.setId(socMajorDTO1.getId());
        assertThat(socMajorDTO1).isEqualTo(socMajorDTO2);
        socMajorDTO2.setId(2L);
        assertThat(socMajorDTO1).isNotEqualTo(socMajorDTO2);
        socMajorDTO1.setId(null);
        assertThat(socMajorDTO1).isNotEqualTo(socMajorDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(socMajorMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(socMajorMapper.fromId(null)).isNull();
    }
}
