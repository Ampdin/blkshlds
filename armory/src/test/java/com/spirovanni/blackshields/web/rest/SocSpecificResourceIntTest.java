package com.spirovanni.blackshields.web.rest;

import com.spirovanni.blackshields.ArmoryApp;

import com.spirovanni.blackshields.domain.SocSpecific;
import com.spirovanni.blackshields.repository.SocSpecificRepository;
import com.spirovanni.blackshields.service.SocSpecificService;
import com.spirovanni.blackshields.repository.search.SocSpecificSearchRepository;
import com.spirovanni.blackshields.service.dto.SocSpecificDTO;
import com.spirovanni.blackshields.service.mapper.SocSpecificMapper;
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

import com.spirovanni.blackshields.domain.enumeration.Group;
/**
 * Test class for the SocSpecificResource REST controller.
 *
 * @see SocSpecificResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArmoryApp.class)
public class SocSpecificResourceIntTest {

    private static final String DEFAULT_SOC_SPECIFIC_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SOC_SPECIFIC_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SOC_SPECIFIC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SOC_SPECIFIC_CODE = "BBBBBBBBBB";

    private static final byte[] DEFAULT_SOC_SPECIFIC_AVATOR = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_SOC_SPECIFIC_AVATOR = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_SOC_SPECIFIC_AVATOR_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_SOC_SPECIFIC_AVATOR_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_SOC_SPECIFIC_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_SOC_SPECIFIC_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_SOC_SPECIFIC_URL = "AAAAAAAAAA";
    private static final String UPDATED_SOC_SPECIFIC_URL = "BBBBBBBBBB";

    private static final String DEFAULT_SOC_SPECIFIC_PREVIEW_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_SOC_SPECIFIC_PREVIEW_IMAGE = "BBBBBBBBBB";

    private static final Group DEFAULT_GROUP = Group.MAIN;
    private static final Group UPDATED_GROUP = Group.MAJOR;

    private static final Integer DEFAULT_TOT_EMP = 1;
    private static final Integer UPDATED_TOT_EMP = 2;

    private static final Long DEFAULT_EMP_PRSE = 1L;
    private static final Long UPDATED_EMP_PRSE = 2L;

    private static final Long DEFAULT_HOURLY_MEAN = 1L;
    private static final Long UPDATED_HOURLY_MEAN = 2L;

    private static final Long DEFAULT_ANNUAL_MEAN = 1L;
    private static final Long UPDATED_ANNUAL_MEAN = 2L;

    private static final Long DEFAULT_MEAN_PRSE = 1L;
    private static final Long UPDATED_MEAN_PRSE = 2L;

    private static final Long DEFAULT_HR_PCT_HIGHEST = 1L;
    private static final Long UPDATED_HR_PCT_HIGHEST = 2L;

    private static final Long DEFAULT_HR_PCT_HIGH = 1L;
    private static final Long UPDATED_HR_PCT_HIGH = 2L;

    private static final Long DEFAULT_HOURLY_MEDIAN = 1L;
    private static final Long UPDATED_HOURLY_MEDIAN = 2L;

    private static final Long DEFAULT_HR_PCT_BELOW = 1L;
    private static final Long UPDATED_HR_PCT_BELOW = 2L;

    private static final Long DEFAULT_HR_PCT_LOWEST = 1L;
    private static final Long UPDATED_HR_PCT_LOWEST = 2L;

    private static final Long DEFAULT_AN_PCT_HIGHEST = 1L;
    private static final Long UPDATED_AN_PCT_HIGHEST = 2L;

    private static final Long DEFAULT_AN_PCT_HIGH = 1L;
    private static final Long UPDATED_AN_PCT_HIGH = 2L;

    private static final Long DEFAULT_ANNUAL_MEDIAN = 1L;
    private static final Long UPDATED_ANNUAL_MEDIAN = 2L;

    private static final Long DEFAULT_AN_PCT_BELOW = 1L;
    private static final Long UPDATED_AN_PCT_BELOW = 2L;

    private static final Long DEFAULT_AN_PCT_LOWEST = 1L;
    private static final Long UPDATED_AN_PCT_LOWEST = 2L;

    @Autowired
    private SocSpecificRepository socSpecificRepository;

    @Autowired
    private SocSpecificMapper socSpecificMapper;

    @Autowired
    private SocSpecificService socSpecificService;

    @Autowired
    private SocSpecificSearchRepository socSpecificSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSocSpecificMockMvc;

    private SocSpecific socSpecific;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SocSpecificResource socSpecificResource = new SocSpecificResource(socSpecificService);
        this.restSocSpecificMockMvc = MockMvcBuilders.standaloneSetup(socSpecificResource)
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
    public static SocSpecific createEntity(EntityManager em) {
        SocSpecific socSpecific = new SocSpecific()
            .socSpecificName(DEFAULT_SOC_SPECIFIC_NAME)
            .socSpecificCode(DEFAULT_SOC_SPECIFIC_CODE)
            .socSpecificAvator(DEFAULT_SOC_SPECIFIC_AVATOR)
            .socSpecificAvatorContentType(DEFAULT_SOC_SPECIFIC_AVATOR_CONTENT_TYPE)
            .socSpecificDescription(DEFAULT_SOC_SPECIFIC_DESCRIPTION)
            .socSpecificURL(DEFAULT_SOC_SPECIFIC_URL)
            .socSpecificPreviewImage(DEFAULT_SOC_SPECIFIC_PREVIEW_IMAGE)
            .group(DEFAULT_GROUP)
            .totEmp(DEFAULT_TOT_EMP)
            .empPrse(DEFAULT_EMP_PRSE)
            .hourlyMean(DEFAULT_HOURLY_MEAN)
            .annualMean(DEFAULT_ANNUAL_MEAN)
            .meanPrse(DEFAULT_MEAN_PRSE)
            .hrPctHighest(DEFAULT_HR_PCT_HIGHEST)
            .hrPctHigh(DEFAULT_HR_PCT_HIGH)
            .hourlyMedian(DEFAULT_HOURLY_MEDIAN)
            .hrPctBelow(DEFAULT_HR_PCT_BELOW)
            .hrPctLowest(DEFAULT_HR_PCT_LOWEST)
            .anPctHighest(DEFAULT_AN_PCT_HIGHEST)
            .anPctHigh(DEFAULT_AN_PCT_HIGH)
            .annualMedian(DEFAULT_ANNUAL_MEDIAN)
            .anPctBelow(DEFAULT_AN_PCT_BELOW)
            .anPctLowest(DEFAULT_AN_PCT_LOWEST);
        return socSpecific;
    }

    @Before
    public void initTest() {
        socSpecificSearchRepository.deleteAll();
        socSpecific = createEntity(em);
    }

    @Test
    @Transactional
    public void createSocSpecific() throws Exception {
        int databaseSizeBeforeCreate = socSpecificRepository.findAll().size();

        // Create the SocSpecific
        SocSpecificDTO socSpecificDTO = socSpecificMapper.toDto(socSpecific);
        restSocSpecificMockMvc.perform(post("/api/soc-specifics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(socSpecificDTO)))
            .andExpect(status().isCreated());

        // Validate the SocSpecific in the database
        List<SocSpecific> socSpecificList = socSpecificRepository.findAll();
        assertThat(socSpecificList).hasSize(databaseSizeBeforeCreate + 1);
        SocSpecific testSocSpecific = socSpecificList.get(socSpecificList.size() - 1);
        assertThat(testSocSpecific.getSocSpecificName()).isEqualTo(DEFAULT_SOC_SPECIFIC_NAME);
        assertThat(testSocSpecific.getSocSpecificCode()).isEqualTo(DEFAULT_SOC_SPECIFIC_CODE);
        assertThat(testSocSpecific.getSocSpecificAvator()).isEqualTo(DEFAULT_SOC_SPECIFIC_AVATOR);
        assertThat(testSocSpecific.getSocSpecificAvatorContentType()).isEqualTo(DEFAULT_SOC_SPECIFIC_AVATOR_CONTENT_TYPE);
        assertThat(testSocSpecific.getSocSpecificDescription()).isEqualTo(DEFAULT_SOC_SPECIFIC_DESCRIPTION);
        assertThat(testSocSpecific.getSocSpecificURL()).isEqualTo(DEFAULT_SOC_SPECIFIC_URL);
        assertThat(testSocSpecific.getSocSpecificPreviewImage()).isEqualTo(DEFAULT_SOC_SPECIFIC_PREVIEW_IMAGE);
        assertThat(testSocSpecific.getGroup()).isEqualTo(DEFAULT_GROUP);
        assertThat(testSocSpecific.getTotEmp()).isEqualTo(DEFAULT_TOT_EMP);
        assertThat(testSocSpecific.getEmpPrse()).isEqualTo(DEFAULT_EMP_PRSE);
        assertThat(testSocSpecific.getHourlyMean()).isEqualTo(DEFAULT_HOURLY_MEAN);
        assertThat(testSocSpecific.getAnnualMean()).isEqualTo(DEFAULT_ANNUAL_MEAN);
        assertThat(testSocSpecific.getMeanPrse()).isEqualTo(DEFAULT_MEAN_PRSE);
        assertThat(testSocSpecific.getHrPctHighest()).isEqualTo(DEFAULT_HR_PCT_HIGHEST);
        assertThat(testSocSpecific.getHrPctHigh()).isEqualTo(DEFAULT_HR_PCT_HIGH);
        assertThat(testSocSpecific.getHourlyMedian()).isEqualTo(DEFAULT_HOURLY_MEDIAN);
        assertThat(testSocSpecific.getHrPctBelow()).isEqualTo(DEFAULT_HR_PCT_BELOW);
        assertThat(testSocSpecific.getHrPctLowest()).isEqualTo(DEFAULT_HR_PCT_LOWEST);
        assertThat(testSocSpecific.getAnPctHighest()).isEqualTo(DEFAULT_AN_PCT_HIGHEST);
        assertThat(testSocSpecific.getAnPctHigh()).isEqualTo(DEFAULT_AN_PCT_HIGH);
        assertThat(testSocSpecific.getAnnualMedian()).isEqualTo(DEFAULT_ANNUAL_MEDIAN);
        assertThat(testSocSpecific.getAnPctBelow()).isEqualTo(DEFAULT_AN_PCT_BELOW);
        assertThat(testSocSpecific.getAnPctLowest()).isEqualTo(DEFAULT_AN_PCT_LOWEST);

        // Validate the SocSpecific in Elasticsearch
        SocSpecific socSpecificEs = socSpecificSearchRepository.findOne(testSocSpecific.getId());
        assertThat(socSpecificEs).isEqualToComparingFieldByField(testSocSpecific);
    }

    @Test
    @Transactional
    public void createSocSpecificWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = socSpecificRepository.findAll().size();

        // Create the SocSpecific with an existing ID
        socSpecific.setId(1L);
        SocSpecificDTO socSpecificDTO = socSpecificMapper.toDto(socSpecific);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSocSpecificMockMvc.perform(post("/api/soc-specifics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(socSpecificDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SocSpecific in the database
        List<SocSpecific> socSpecificList = socSpecificRepository.findAll();
        assertThat(socSpecificList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSocSpecifics() throws Exception {
        // Initialize the database
        socSpecificRepository.saveAndFlush(socSpecific);

        // Get all the socSpecificList
        restSocSpecificMockMvc.perform(get("/api/soc-specifics?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(socSpecific.getId().intValue())))
            .andExpect(jsonPath("$.[*].socSpecificName").value(hasItem(DEFAULT_SOC_SPECIFIC_NAME.toString())))
            .andExpect(jsonPath("$.[*].socSpecificCode").value(hasItem(DEFAULT_SOC_SPECIFIC_CODE.toString())))
            .andExpect(jsonPath("$.[*].socSpecificAvatorContentType").value(hasItem(DEFAULT_SOC_SPECIFIC_AVATOR_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].socSpecificAvator").value(hasItem(Base64Utils.encodeToString(DEFAULT_SOC_SPECIFIC_AVATOR))))
            .andExpect(jsonPath("$.[*].socSpecificDescription").value(hasItem(DEFAULT_SOC_SPECIFIC_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].socSpecificURL").value(hasItem(DEFAULT_SOC_SPECIFIC_URL.toString())))
            .andExpect(jsonPath("$.[*].socSpecificPreviewImage").value(hasItem(DEFAULT_SOC_SPECIFIC_PREVIEW_IMAGE.toString())))
            .andExpect(jsonPath("$.[*].group").value(hasItem(DEFAULT_GROUP.toString())))
            .andExpect(jsonPath("$.[*].totEmp").value(hasItem(DEFAULT_TOT_EMP)))
            .andExpect(jsonPath("$.[*].empPrse").value(hasItem(DEFAULT_EMP_PRSE.intValue())))
            .andExpect(jsonPath("$.[*].hourlyMean").value(hasItem(DEFAULT_HOURLY_MEAN.intValue())))
            .andExpect(jsonPath("$.[*].annualMean").value(hasItem(DEFAULT_ANNUAL_MEAN.intValue())))
            .andExpect(jsonPath("$.[*].meanPrse").value(hasItem(DEFAULT_MEAN_PRSE.intValue())))
            .andExpect(jsonPath("$.[*].hrPctHighest").value(hasItem(DEFAULT_HR_PCT_HIGHEST.intValue())))
            .andExpect(jsonPath("$.[*].hrPctHigh").value(hasItem(DEFAULT_HR_PCT_HIGH.intValue())))
            .andExpect(jsonPath("$.[*].hourlyMedian").value(hasItem(DEFAULT_HOURLY_MEDIAN.intValue())))
            .andExpect(jsonPath("$.[*].hrPctBelow").value(hasItem(DEFAULT_HR_PCT_BELOW.intValue())))
            .andExpect(jsonPath("$.[*].hrPctLowest").value(hasItem(DEFAULT_HR_PCT_LOWEST.intValue())))
            .andExpect(jsonPath("$.[*].anPctHighest").value(hasItem(DEFAULT_AN_PCT_HIGHEST.intValue())))
            .andExpect(jsonPath("$.[*].anPctHigh").value(hasItem(DEFAULT_AN_PCT_HIGH.intValue())))
            .andExpect(jsonPath("$.[*].annualMedian").value(hasItem(DEFAULT_ANNUAL_MEDIAN.intValue())))
            .andExpect(jsonPath("$.[*].anPctBelow").value(hasItem(DEFAULT_AN_PCT_BELOW.intValue())))
            .andExpect(jsonPath("$.[*].anPctLowest").value(hasItem(DEFAULT_AN_PCT_LOWEST.intValue())));
    }

    @Test
    @Transactional
    public void getSocSpecific() throws Exception {
        // Initialize the database
        socSpecificRepository.saveAndFlush(socSpecific);

        // Get the socSpecific
        restSocSpecificMockMvc.perform(get("/api/soc-specifics/{id}", socSpecific.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(socSpecific.getId().intValue()))
            .andExpect(jsonPath("$.socSpecificName").value(DEFAULT_SOC_SPECIFIC_NAME.toString()))
            .andExpect(jsonPath("$.socSpecificCode").value(DEFAULT_SOC_SPECIFIC_CODE.toString()))
            .andExpect(jsonPath("$.socSpecificAvatorContentType").value(DEFAULT_SOC_SPECIFIC_AVATOR_CONTENT_TYPE))
            .andExpect(jsonPath("$.socSpecificAvator").value(Base64Utils.encodeToString(DEFAULT_SOC_SPECIFIC_AVATOR)))
            .andExpect(jsonPath("$.socSpecificDescription").value(DEFAULT_SOC_SPECIFIC_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.socSpecificURL").value(DEFAULT_SOC_SPECIFIC_URL.toString()))
            .andExpect(jsonPath("$.socSpecificPreviewImage").value(DEFAULT_SOC_SPECIFIC_PREVIEW_IMAGE.toString()))
            .andExpect(jsonPath("$.group").value(DEFAULT_GROUP.toString()))
            .andExpect(jsonPath("$.totEmp").value(DEFAULT_TOT_EMP))
            .andExpect(jsonPath("$.empPrse").value(DEFAULT_EMP_PRSE.intValue()))
            .andExpect(jsonPath("$.hourlyMean").value(DEFAULT_HOURLY_MEAN.intValue()))
            .andExpect(jsonPath("$.annualMean").value(DEFAULT_ANNUAL_MEAN.intValue()))
            .andExpect(jsonPath("$.meanPrse").value(DEFAULT_MEAN_PRSE.intValue()))
            .andExpect(jsonPath("$.hrPctHighest").value(DEFAULT_HR_PCT_HIGHEST.intValue()))
            .andExpect(jsonPath("$.hrPctHigh").value(DEFAULT_HR_PCT_HIGH.intValue()))
            .andExpect(jsonPath("$.hourlyMedian").value(DEFAULT_HOURLY_MEDIAN.intValue()))
            .andExpect(jsonPath("$.hrPctBelow").value(DEFAULT_HR_PCT_BELOW.intValue()))
            .andExpect(jsonPath("$.hrPctLowest").value(DEFAULT_HR_PCT_LOWEST.intValue()))
            .andExpect(jsonPath("$.anPctHighest").value(DEFAULT_AN_PCT_HIGHEST.intValue()))
            .andExpect(jsonPath("$.anPctHigh").value(DEFAULT_AN_PCT_HIGH.intValue()))
            .andExpect(jsonPath("$.annualMedian").value(DEFAULT_ANNUAL_MEDIAN.intValue()))
            .andExpect(jsonPath("$.anPctBelow").value(DEFAULT_AN_PCT_BELOW.intValue()))
            .andExpect(jsonPath("$.anPctLowest").value(DEFAULT_AN_PCT_LOWEST.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSocSpecific() throws Exception {
        // Get the socSpecific
        restSocSpecificMockMvc.perform(get("/api/soc-specifics/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSocSpecific() throws Exception {
        // Initialize the database
        socSpecificRepository.saveAndFlush(socSpecific);
        socSpecificSearchRepository.save(socSpecific);
        int databaseSizeBeforeUpdate = socSpecificRepository.findAll().size();

        // Update the socSpecific
        SocSpecific updatedSocSpecific = socSpecificRepository.findOne(socSpecific.getId());
        updatedSocSpecific
            .socSpecificName(UPDATED_SOC_SPECIFIC_NAME)
            .socSpecificCode(UPDATED_SOC_SPECIFIC_CODE)
            .socSpecificAvator(UPDATED_SOC_SPECIFIC_AVATOR)
            .socSpecificAvatorContentType(UPDATED_SOC_SPECIFIC_AVATOR_CONTENT_TYPE)
            .socSpecificDescription(UPDATED_SOC_SPECIFIC_DESCRIPTION)
            .socSpecificURL(UPDATED_SOC_SPECIFIC_URL)
            .socSpecificPreviewImage(UPDATED_SOC_SPECIFIC_PREVIEW_IMAGE)
            .group(UPDATED_GROUP)
            .totEmp(UPDATED_TOT_EMP)
            .empPrse(UPDATED_EMP_PRSE)
            .hourlyMean(UPDATED_HOURLY_MEAN)
            .annualMean(UPDATED_ANNUAL_MEAN)
            .meanPrse(UPDATED_MEAN_PRSE)
            .hrPctHighest(UPDATED_HR_PCT_HIGHEST)
            .hrPctHigh(UPDATED_HR_PCT_HIGH)
            .hourlyMedian(UPDATED_HOURLY_MEDIAN)
            .hrPctBelow(UPDATED_HR_PCT_BELOW)
            .hrPctLowest(UPDATED_HR_PCT_LOWEST)
            .anPctHighest(UPDATED_AN_PCT_HIGHEST)
            .anPctHigh(UPDATED_AN_PCT_HIGH)
            .annualMedian(UPDATED_ANNUAL_MEDIAN)
            .anPctBelow(UPDATED_AN_PCT_BELOW)
            .anPctLowest(UPDATED_AN_PCT_LOWEST);
        SocSpecificDTO socSpecificDTO = socSpecificMapper.toDto(updatedSocSpecific);

        restSocSpecificMockMvc.perform(put("/api/soc-specifics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(socSpecificDTO)))
            .andExpect(status().isOk());

        // Validate the SocSpecific in the database
        List<SocSpecific> socSpecificList = socSpecificRepository.findAll();
        assertThat(socSpecificList).hasSize(databaseSizeBeforeUpdate);
        SocSpecific testSocSpecific = socSpecificList.get(socSpecificList.size() - 1);
        assertThat(testSocSpecific.getSocSpecificName()).isEqualTo(UPDATED_SOC_SPECIFIC_NAME);
        assertThat(testSocSpecific.getSocSpecificCode()).isEqualTo(UPDATED_SOC_SPECIFIC_CODE);
        assertThat(testSocSpecific.getSocSpecificAvator()).isEqualTo(UPDATED_SOC_SPECIFIC_AVATOR);
        assertThat(testSocSpecific.getSocSpecificAvatorContentType()).isEqualTo(UPDATED_SOC_SPECIFIC_AVATOR_CONTENT_TYPE);
        assertThat(testSocSpecific.getSocSpecificDescription()).isEqualTo(UPDATED_SOC_SPECIFIC_DESCRIPTION);
        assertThat(testSocSpecific.getSocSpecificURL()).isEqualTo(UPDATED_SOC_SPECIFIC_URL);
        assertThat(testSocSpecific.getSocSpecificPreviewImage()).isEqualTo(UPDATED_SOC_SPECIFIC_PREVIEW_IMAGE);
        assertThat(testSocSpecific.getGroup()).isEqualTo(UPDATED_GROUP);
        assertThat(testSocSpecific.getTotEmp()).isEqualTo(UPDATED_TOT_EMP);
        assertThat(testSocSpecific.getEmpPrse()).isEqualTo(UPDATED_EMP_PRSE);
        assertThat(testSocSpecific.getHourlyMean()).isEqualTo(UPDATED_HOURLY_MEAN);
        assertThat(testSocSpecific.getAnnualMean()).isEqualTo(UPDATED_ANNUAL_MEAN);
        assertThat(testSocSpecific.getMeanPrse()).isEqualTo(UPDATED_MEAN_PRSE);
        assertThat(testSocSpecific.getHrPctHighest()).isEqualTo(UPDATED_HR_PCT_HIGHEST);
        assertThat(testSocSpecific.getHrPctHigh()).isEqualTo(UPDATED_HR_PCT_HIGH);
        assertThat(testSocSpecific.getHourlyMedian()).isEqualTo(UPDATED_HOURLY_MEDIAN);
        assertThat(testSocSpecific.getHrPctBelow()).isEqualTo(UPDATED_HR_PCT_BELOW);
        assertThat(testSocSpecific.getHrPctLowest()).isEqualTo(UPDATED_HR_PCT_LOWEST);
        assertThat(testSocSpecific.getAnPctHighest()).isEqualTo(UPDATED_AN_PCT_HIGHEST);
        assertThat(testSocSpecific.getAnPctHigh()).isEqualTo(UPDATED_AN_PCT_HIGH);
        assertThat(testSocSpecific.getAnnualMedian()).isEqualTo(UPDATED_ANNUAL_MEDIAN);
        assertThat(testSocSpecific.getAnPctBelow()).isEqualTo(UPDATED_AN_PCT_BELOW);
        assertThat(testSocSpecific.getAnPctLowest()).isEqualTo(UPDATED_AN_PCT_LOWEST);

        // Validate the SocSpecific in Elasticsearch
        SocSpecific socSpecificEs = socSpecificSearchRepository.findOne(testSocSpecific.getId());
        assertThat(socSpecificEs).isEqualToComparingFieldByField(testSocSpecific);
    }

    @Test
    @Transactional
    public void updateNonExistingSocSpecific() throws Exception {
        int databaseSizeBeforeUpdate = socSpecificRepository.findAll().size();

        // Create the SocSpecific
        SocSpecificDTO socSpecificDTO = socSpecificMapper.toDto(socSpecific);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSocSpecificMockMvc.perform(put("/api/soc-specifics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(socSpecificDTO)))
            .andExpect(status().isCreated());

        // Validate the SocSpecific in the database
        List<SocSpecific> socSpecificList = socSpecificRepository.findAll();
        assertThat(socSpecificList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSocSpecific() throws Exception {
        // Initialize the database
        socSpecificRepository.saveAndFlush(socSpecific);
        socSpecificSearchRepository.save(socSpecific);
        int databaseSizeBeforeDelete = socSpecificRepository.findAll().size();

        // Get the socSpecific
        restSocSpecificMockMvc.perform(delete("/api/soc-specifics/{id}", socSpecific.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean socSpecificExistsInEs = socSpecificSearchRepository.exists(socSpecific.getId());
        assertThat(socSpecificExistsInEs).isFalse();

        // Validate the database is empty
        List<SocSpecific> socSpecificList = socSpecificRepository.findAll();
        assertThat(socSpecificList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchSocSpecific() throws Exception {
        // Initialize the database
        socSpecificRepository.saveAndFlush(socSpecific);
        socSpecificSearchRepository.save(socSpecific);

        // Search the socSpecific
        restSocSpecificMockMvc.perform(get("/api/_search/soc-specifics?query=id:" + socSpecific.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(socSpecific.getId().intValue())))
            .andExpect(jsonPath("$.[*].socSpecificName").value(hasItem(DEFAULT_SOC_SPECIFIC_NAME.toString())))
            .andExpect(jsonPath("$.[*].socSpecificCode").value(hasItem(DEFAULT_SOC_SPECIFIC_CODE.toString())))
            .andExpect(jsonPath("$.[*].socSpecificAvatorContentType").value(hasItem(DEFAULT_SOC_SPECIFIC_AVATOR_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].socSpecificAvator").value(hasItem(Base64Utils.encodeToString(DEFAULT_SOC_SPECIFIC_AVATOR))))
            .andExpect(jsonPath("$.[*].socSpecificDescription").value(hasItem(DEFAULT_SOC_SPECIFIC_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].socSpecificURL").value(hasItem(DEFAULT_SOC_SPECIFIC_URL.toString())))
            .andExpect(jsonPath("$.[*].socSpecificPreviewImage").value(hasItem(DEFAULT_SOC_SPECIFIC_PREVIEW_IMAGE.toString())))
            .andExpect(jsonPath("$.[*].group").value(hasItem(DEFAULT_GROUP.toString())))
            .andExpect(jsonPath("$.[*].totEmp").value(hasItem(DEFAULT_TOT_EMP)))
            .andExpect(jsonPath("$.[*].empPrse").value(hasItem(DEFAULT_EMP_PRSE.intValue())))
            .andExpect(jsonPath("$.[*].hourlyMean").value(hasItem(DEFAULT_HOURLY_MEAN.intValue())))
            .andExpect(jsonPath("$.[*].annualMean").value(hasItem(DEFAULT_ANNUAL_MEAN.intValue())))
            .andExpect(jsonPath("$.[*].meanPrse").value(hasItem(DEFAULT_MEAN_PRSE.intValue())))
            .andExpect(jsonPath("$.[*].hrPctHighest").value(hasItem(DEFAULT_HR_PCT_HIGHEST.intValue())))
            .andExpect(jsonPath("$.[*].hrPctHigh").value(hasItem(DEFAULT_HR_PCT_HIGH.intValue())))
            .andExpect(jsonPath("$.[*].hourlyMedian").value(hasItem(DEFAULT_HOURLY_MEDIAN.intValue())))
            .andExpect(jsonPath("$.[*].hrPctBelow").value(hasItem(DEFAULT_HR_PCT_BELOW.intValue())))
            .andExpect(jsonPath("$.[*].hrPctLowest").value(hasItem(DEFAULT_HR_PCT_LOWEST.intValue())))
            .andExpect(jsonPath("$.[*].anPctHighest").value(hasItem(DEFAULT_AN_PCT_HIGHEST.intValue())))
            .andExpect(jsonPath("$.[*].anPctHigh").value(hasItem(DEFAULT_AN_PCT_HIGH.intValue())))
            .andExpect(jsonPath("$.[*].annualMedian").value(hasItem(DEFAULT_ANNUAL_MEDIAN.intValue())))
            .andExpect(jsonPath("$.[*].anPctBelow").value(hasItem(DEFAULT_AN_PCT_BELOW.intValue())))
            .andExpect(jsonPath("$.[*].anPctLowest").value(hasItem(DEFAULT_AN_PCT_LOWEST.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SocSpecific.class);
        SocSpecific socSpecific1 = new SocSpecific();
        socSpecific1.setId(1L);
        SocSpecific socSpecific2 = new SocSpecific();
        socSpecific2.setId(socSpecific1.getId());
        assertThat(socSpecific1).isEqualTo(socSpecific2);
        socSpecific2.setId(2L);
        assertThat(socSpecific1).isNotEqualTo(socSpecific2);
        socSpecific1.setId(null);
        assertThat(socSpecific1).isNotEqualTo(socSpecific2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SocSpecificDTO.class);
        SocSpecificDTO socSpecificDTO1 = new SocSpecificDTO();
        socSpecificDTO1.setId(1L);
        SocSpecificDTO socSpecificDTO2 = new SocSpecificDTO();
        assertThat(socSpecificDTO1).isNotEqualTo(socSpecificDTO2);
        socSpecificDTO2.setId(socSpecificDTO1.getId());
        assertThat(socSpecificDTO1).isEqualTo(socSpecificDTO2);
        socSpecificDTO2.setId(2L);
        assertThat(socSpecificDTO1).isNotEqualTo(socSpecificDTO2);
        socSpecificDTO1.setId(null);
        assertThat(socSpecificDTO1).isNotEqualTo(socSpecificDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(socSpecificMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(socSpecificMapper.fromId(null)).isNull();
    }
}
