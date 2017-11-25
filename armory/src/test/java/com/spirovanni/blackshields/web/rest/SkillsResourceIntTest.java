package com.spirovanni.blackshields.web.rest;

import com.spirovanni.blackshields.ArmoryApp;

import com.spirovanni.blackshields.domain.Skills;
import com.spirovanni.blackshields.repository.SkillsRepository;
import com.spirovanni.blackshields.service.SkillsService;
import com.spirovanni.blackshields.repository.search.SkillsSearchRepository;
import com.spirovanni.blackshields.service.dto.SkillsDTO;
import com.spirovanni.blackshields.service.mapper.SkillsMapper;
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

import javax.persistence.EntityManager;
import java.util.List;

import static com.spirovanni.blackshields.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the SkillsResource REST controller.
 *
 * @see SkillsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArmoryApp.class)
public class SkillsResourceIntTest {

    private static final String DEFAULT_SOC_SPECIFIC_SK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SOC_SPECIFIC_SK_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SOC_SPECIFIC_SK_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SOC_SPECIFIC_SK_CODE = "BBBBBBBBBB";

    private static final Long DEFAULT_AN_DA_IN_LV = 1L;
    private static final Long UPDATED_AN_DA_IN_LV = 2L;

    private static final Long DEFAULT_AN_DA_IN_IM = 1L;
    private static final Long UPDATED_AN_DA_IN_IM = 2L;

    private static final Long DEFAULT_AS_CA_OTH_LV = 1L;
    private static final Long UPDATED_AS_CA_OTH_LV = 2L;

    private static final Long DEFAULT_AS_CA_OTH_IM = 1L;
    private static final Long UPDATED_AS_CA_OTH_IM = 2L;

    private static final Long DEFAULT_CO_DEV_OTH_LV = 1L;
    private static final Long UPDATED_CO_DEV_OTH_LV = 2L;

    private static final Long DEFAULT_CO_DEV_OTH_IM = 1L;
    private static final Long UPDATED_CO_DEV_OTH_IM = 2L;

    private static final Long DEFAULT_CO_PER_OUT_ORG_LV = 1L;
    private static final Long UPDATED_CO_PER_OUT_ORG_LV = 2L;

    private static final Long DEFAULT_CO_PER_OUT_ORG_IM = 1L;
    private static final Long UPDATED_CO_PER_OUT_ORG_IM = 2L;

    private static final Long DEFAULT_CO_SUP_PE_SUB_LV = 1L;
    private static final Long UPDATED_CO_SUP_PE_SUB_LV = 2L;

    private static final Long DEFAULT_CO_SUP_PE_SUB_IM = 1L;
    private static final Long UPDATED_CO_SUP_PE_SUB_IM = 2L;

    private static final Long DEFAULT_CON_MA_PRO_LV = 1L;
    private static final Long UPDATED_CON_MA_PRO_LV = 2L;

    private static final Long DEFAULT_CON_MA_PRO_IM = 1L;
    private static final Long UPDATED_CON_MA_PRO_IM = 2L;

    private static final Long DEFAULT_COO_WOR_ACT_OTH_LV = 1L;
    private static final Long UPDATED_COO_WOR_ACT_OTH_LV = 2L;

    private static final Long DEFAULT_COO_WOR_ACT_OTH_IM = 1L;
    private static final Long UPDATED_COO_WOR_ACT_OTH_IM = 2L;

    private static final Long DEFAULT_DEV_BUILD_TEAMS_LV = 1L;
    private static final Long UPDATED_DEV_BUILD_TEAMS_LV = 2L;

    private static final Long DEFAULT_DEV_BUILD_TEAMS_IM = 1L;
    private static final Long UPDATED_DEV_BUILD_TEAMS_IM = 2L;

    private static final Long DEFAULT_DEV_OBJ_STRAT_LV = 1L;
    private static final Long UPDATED_DEV_OBJ_STRAT_LV = 2L;

    private static final Long DEFAULT_DEV_OBJ_STRAT_IM = 1L;
    private static final Long UPDATED_DEV_OBJ_STRAT_IM = 2L;

    private static final Long DEFAULT_DOC_REC_INFO_LV = 1L;
    private static final Long UPDATED_DOC_REC_INFO_LV = 2L;

    private static final Long DEFAULT_DOC_REC_INFO_IM = 1L;
    private static final Long UPDATED_DOC_REC_INFO_IM = 2L;

    private static final Long DEFAULT_DR_LAY_OUT_SPEC_LV = 1L;
    private static final Long UPDATED_DR_LAY_OUT_SPEC_LV = 2L;

    private static final Long DEFAULT_DR_LAY_OUT_SPEC_IM = 1L;
    private static final Long UPDATED_DR_LAY_OUT_SPEC_IM = 2L;

    private static final Long DEFAULT_EST_MA_INT_REL_LV = 1L;
    private static final Long UPDATED_EST_MA_INT_REL_LV = 2L;

    private static final Long DEFAULT_EST_MA_INT_REL_IM = 1L;
    private static final Long UPDATED_EST_MA_INT_REL_IM = 2L;

    @Autowired
    private SkillsRepository skillsRepository;

    @Autowired
    private SkillsMapper skillsMapper;

    @Autowired
    private SkillsService skillsService;

    @Autowired
    private SkillsSearchRepository skillsSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSkillsMockMvc;

    private Skills skills;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SkillsResource skillsResource = new SkillsResource(skillsService);
        this.restSkillsMockMvc = MockMvcBuilders.standaloneSetup(skillsResource)
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
    public static Skills createEntity(EntityManager em) {
        Skills skills = new Skills()
            .socSpecificSkName(DEFAULT_SOC_SPECIFIC_SK_NAME)
            .socSpecificSkCode(DEFAULT_SOC_SPECIFIC_SK_CODE)
            .anDaInLv(DEFAULT_AN_DA_IN_LV)
            .anDaInIm(DEFAULT_AN_DA_IN_IM)
            .asCaOthLv(DEFAULT_AS_CA_OTH_LV)
            .asCaOthIm(DEFAULT_AS_CA_OTH_IM)
            .coDevOthLv(DEFAULT_CO_DEV_OTH_LV)
            .coDevOthIm(DEFAULT_CO_DEV_OTH_IM)
            .coPerOutOrgLv(DEFAULT_CO_PER_OUT_ORG_LV)
            .coPerOutOrgIm(DEFAULT_CO_PER_OUT_ORG_IM)
            .coSupPeSubLv(DEFAULT_CO_SUP_PE_SUB_LV)
            .coSupPeSubIm(DEFAULT_CO_SUP_PE_SUB_IM)
            .conMaProLv(DEFAULT_CON_MA_PRO_LV)
            .conMaProIm(DEFAULT_CON_MA_PRO_IM)
            .cooWorActOthLv(DEFAULT_COO_WOR_ACT_OTH_LV)
            .cooWorActOthIm(DEFAULT_COO_WOR_ACT_OTH_IM)
            .devBuildTeamsLv(DEFAULT_DEV_BUILD_TEAMS_LV)
            .devBuildTeamsIm(DEFAULT_DEV_BUILD_TEAMS_IM)
            .devObjStratLv(DEFAULT_DEV_OBJ_STRAT_LV)
            .devObjStratIm(DEFAULT_DEV_OBJ_STRAT_IM)
            .docRecInfoLv(DEFAULT_DOC_REC_INFO_LV)
            .docRecInfoIm(DEFAULT_DOC_REC_INFO_IM)
            .drLayOutSpecLv(DEFAULT_DR_LAY_OUT_SPEC_LV)
            .drLayOutSpecIm(DEFAULT_DR_LAY_OUT_SPEC_IM)
            .estMaIntRelLv(DEFAULT_EST_MA_INT_REL_LV)
            .estMaIntRelIm(DEFAULT_EST_MA_INT_REL_IM);
        return skills;
    }

    @Before
    public void initTest() {
        skillsSearchRepository.deleteAll();
        skills = createEntity(em);
    }

    @Test
    @Transactional
    public void createSkills() throws Exception {
        int databaseSizeBeforeCreate = skillsRepository.findAll().size();

        // Create the Skills
        SkillsDTO skillsDTO = skillsMapper.toDto(skills);
        restSkillsMockMvc.perform(post("/api/skills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(skillsDTO)))
            .andExpect(status().isCreated());

        // Validate the Skills in the database
        List<Skills> skillsList = skillsRepository.findAll();
        assertThat(skillsList).hasSize(databaseSizeBeforeCreate + 1);
        Skills testSkills = skillsList.get(skillsList.size() - 1);
        assertThat(testSkills.getSocSpecificSkName()).isEqualTo(DEFAULT_SOC_SPECIFIC_SK_NAME);
        assertThat(testSkills.getSocSpecificSkCode()).isEqualTo(DEFAULT_SOC_SPECIFIC_SK_CODE);
        assertThat(testSkills.getAnDaInLv()).isEqualTo(DEFAULT_AN_DA_IN_LV);
        assertThat(testSkills.getAnDaInIm()).isEqualTo(DEFAULT_AN_DA_IN_IM);
        assertThat(testSkills.getAsCaOthLv()).isEqualTo(DEFAULT_AS_CA_OTH_LV);
        assertThat(testSkills.getAsCaOthIm()).isEqualTo(DEFAULT_AS_CA_OTH_IM);
        assertThat(testSkills.getCoDevOthLv()).isEqualTo(DEFAULT_CO_DEV_OTH_LV);
        assertThat(testSkills.getCoDevOthIm()).isEqualTo(DEFAULT_CO_DEV_OTH_IM);
        assertThat(testSkills.getCoPerOutOrgLv()).isEqualTo(DEFAULT_CO_PER_OUT_ORG_LV);
        assertThat(testSkills.getCoPerOutOrgIm()).isEqualTo(DEFAULT_CO_PER_OUT_ORG_IM);
        assertThat(testSkills.getCoSupPeSubLv()).isEqualTo(DEFAULT_CO_SUP_PE_SUB_LV);
        assertThat(testSkills.getCoSupPeSubIm()).isEqualTo(DEFAULT_CO_SUP_PE_SUB_IM);
        assertThat(testSkills.getConMaProLv()).isEqualTo(DEFAULT_CON_MA_PRO_LV);
        assertThat(testSkills.getConMaProIm()).isEqualTo(DEFAULT_CON_MA_PRO_IM);
        assertThat(testSkills.getCooWorActOthLv()).isEqualTo(DEFAULT_COO_WOR_ACT_OTH_LV);
        assertThat(testSkills.getCooWorActOthIm()).isEqualTo(DEFAULT_COO_WOR_ACT_OTH_IM);
        assertThat(testSkills.getDevBuildTeamsLv()).isEqualTo(DEFAULT_DEV_BUILD_TEAMS_LV);
        assertThat(testSkills.getDevBuildTeamsIm()).isEqualTo(DEFAULT_DEV_BUILD_TEAMS_IM);
        assertThat(testSkills.getDevObjStratLv()).isEqualTo(DEFAULT_DEV_OBJ_STRAT_LV);
        assertThat(testSkills.getDevObjStratIm()).isEqualTo(DEFAULT_DEV_OBJ_STRAT_IM);
        assertThat(testSkills.getDocRecInfoLv()).isEqualTo(DEFAULT_DOC_REC_INFO_LV);
        assertThat(testSkills.getDocRecInfoIm()).isEqualTo(DEFAULT_DOC_REC_INFO_IM);
        assertThat(testSkills.getDrLayOutSpecLv()).isEqualTo(DEFAULT_DR_LAY_OUT_SPEC_LV);
        assertThat(testSkills.getDrLayOutSpecIm()).isEqualTo(DEFAULT_DR_LAY_OUT_SPEC_IM);
        assertThat(testSkills.getEstMaIntRelLv()).isEqualTo(DEFAULT_EST_MA_INT_REL_LV);
        assertThat(testSkills.getEstMaIntRelIm()).isEqualTo(DEFAULT_EST_MA_INT_REL_IM);

        // Validate the Skills in Elasticsearch
        Skills skillsEs = skillsSearchRepository.findOne(testSkills.getId());
        assertThat(skillsEs).isEqualToComparingFieldByField(testSkills);
    }

    @Test
    @Transactional
    public void createSkillsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = skillsRepository.findAll().size();

        // Create the Skills with an existing ID
        skills.setId(1L);
        SkillsDTO skillsDTO = skillsMapper.toDto(skills);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSkillsMockMvc.perform(post("/api/skills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(skillsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Skills in the database
        List<Skills> skillsList = skillsRepository.findAll();
        assertThat(skillsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSkills() throws Exception {
        // Initialize the database
        skillsRepository.saveAndFlush(skills);

        // Get all the skillsList
        restSkillsMockMvc.perform(get("/api/skills?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(skills.getId().intValue())))
            .andExpect(jsonPath("$.[*].socSpecificSkName").value(hasItem(DEFAULT_SOC_SPECIFIC_SK_NAME.toString())))
            .andExpect(jsonPath("$.[*].socSpecificSkCode").value(hasItem(DEFAULT_SOC_SPECIFIC_SK_CODE.toString())))
            .andExpect(jsonPath("$.[*].anDaInLv").value(hasItem(DEFAULT_AN_DA_IN_LV.intValue())))
            .andExpect(jsonPath("$.[*].anDaInIm").value(hasItem(DEFAULT_AN_DA_IN_IM.intValue())))
            .andExpect(jsonPath("$.[*].asCaOthLv").value(hasItem(DEFAULT_AS_CA_OTH_LV.intValue())))
            .andExpect(jsonPath("$.[*].asCaOthIm").value(hasItem(DEFAULT_AS_CA_OTH_IM.intValue())))
            .andExpect(jsonPath("$.[*].coDevOthLv").value(hasItem(DEFAULT_CO_DEV_OTH_LV.intValue())))
            .andExpect(jsonPath("$.[*].coDevOthIm").value(hasItem(DEFAULT_CO_DEV_OTH_IM.intValue())))
            .andExpect(jsonPath("$.[*].coPerOutOrgLv").value(hasItem(DEFAULT_CO_PER_OUT_ORG_LV.intValue())))
            .andExpect(jsonPath("$.[*].coPerOutOrgIm").value(hasItem(DEFAULT_CO_PER_OUT_ORG_IM.intValue())))
            .andExpect(jsonPath("$.[*].coSupPeSubLv").value(hasItem(DEFAULT_CO_SUP_PE_SUB_LV.intValue())))
            .andExpect(jsonPath("$.[*].coSupPeSubIm").value(hasItem(DEFAULT_CO_SUP_PE_SUB_IM.intValue())))
            .andExpect(jsonPath("$.[*].conMaProLv").value(hasItem(DEFAULT_CON_MA_PRO_LV.intValue())))
            .andExpect(jsonPath("$.[*].conMaProIm").value(hasItem(DEFAULT_CON_MA_PRO_IM.intValue())))
            .andExpect(jsonPath("$.[*].cooWorActOthLv").value(hasItem(DEFAULT_COO_WOR_ACT_OTH_LV.intValue())))
            .andExpect(jsonPath("$.[*].cooWorActOthIm").value(hasItem(DEFAULT_COO_WOR_ACT_OTH_IM.intValue())))
            .andExpect(jsonPath("$.[*].devBuildTeamsLv").value(hasItem(DEFAULT_DEV_BUILD_TEAMS_LV.intValue())))
            .andExpect(jsonPath("$.[*].devBuildTeamsIm").value(hasItem(DEFAULT_DEV_BUILD_TEAMS_IM.intValue())))
            .andExpect(jsonPath("$.[*].devObjStratLv").value(hasItem(DEFAULT_DEV_OBJ_STRAT_LV.intValue())))
            .andExpect(jsonPath("$.[*].devObjStratIm").value(hasItem(DEFAULT_DEV_OBJ_STRAT_IM.intValue())))
            .andExpect(jsonPath("$.[*].docRecInfoLv").value(hasItem(DEFAULT_DOC_REC_INFO_LV.intValue())))
            .andExpect(jsonPath("$.[*].docRecInfoIm").value(hasItem(DEFAULT_DOC_REC_INFO_IM.intValue())))
            .andExpect(jsonPath("$.[*].drLayOutSpecLv").value(hasItem(DEFAULT_DR_LAY_OUT_SPEC_LV.intValue())))
            .andExpect(jsonPath("$.[*].drLayOutSpecIm").value(hasItem(DEFAULT_DR_LAY_OUT_SPEC_IM.intValue())))
            .andExpect(jsonPath("$.[*].estMaIntRelLv").value(hasItem(DEFAULT_EST_MA_INT_REL_LV.intValue())))
            .andExpect(jsonPath("$.[*].estMaIntRelIm").value(hasItem(DEFAULT_EST_MA_INT_REL_IM.intValue())));
    }

    @Test
    @Transactional
    public void getSkills() throws Exception {
        // Initialize the database
        skillsRepository.saveAndFlush(skills);

        // Get the skills
        restSkillsMockMvc.perform(get("/api/skills/{id}", skills.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(skills.getId().intValue()))
            .andExpect(jsonPath("$.socSpecificSkName").value(DEFAULT_SOC_SPECIFIC_SK_NAME.toString()))
            .andExpect(jsonPath("$.socSpecificSkCode").value(DEFAULT_SOC_SPECIFIC_SK_CODE.toString()))
            .andExpect(jsonPath("$.anDaInLv").value(DEFAULT_AN_DA_IN_LV.intValue()))
            .andExpect(jsonPath("$.anDaInIm").value(DEFAULT_AN_DA_IN_IM.intValue()))
            .andExpect(jsonPath("$.asCaOthLv").value(DEFAULT_AS_CA_OTH_LV.intValue()))
            .andExpect(jsonPath("$.asCaOthIm").value(DEFAULT_AS_CA_OTH_IM.intValue()))
            .andExpect(jsonPath("$.coDevOthLv").value(DEFAULT_CO_DEV_OTH_LV.intValue()))
            .andExpect(jsonPath("$.coDevOthIm").value(DEFAULT_CO_DEV_OTH_IM.intValue()))
            .andExpect(jsonPath("$.coPerOutOrgLv").value(DEFAULT_CO_PER_OUT_ORG_LV.intValue()))
            .andExpect(jsonPath("$.coPerOutOrgIm").value(DEFAULT_CO_PER_OUT_ORG_IM.intValue()))
            .andExpect(jsonPath("$.coSupPeSubLv").value(DEFAULT_CO_SUP_PE_SUB_LV.intValue()))
            .andExpect(jsonPath("$.coSupPeSubIm").value(DEFAULT_CO_SUP_PE_SUB_IM.intValue()))
            .andExpect(jsonPath("$.conMaProLv").value(DEFAULT_CON_MA_PRO_LV.intValue()))
            .andExpect(jsonPath("$.conMaProIm").value(DEFAULT_CON_MA_PRO_IM.intValue()))
            .andExpect(jsonPath("$.cooWorActOthLv").value(DEFAULT_COO_WOR_ACT_OTH_LV.intValue()))
            .andExpect(jsonPath("$.cooWorActOthIm").value(DEFAULT_COO_WOR_ACT_OTH_IM.intValue()))
            .andExpect(jsonPath("$.devBuildTeamsLv").value(DEFAULT_DEV_BUILD_TEAMS_LV.intValue()))
            .andExpect(jsonPath("$.devBuildTeamsIm").value(DEFAULT_DEV_BUILD_TEAMS_IM.intValue()))
            .andExpect(jsonPath("$.devObjStratLv").value(DEFAULT_DEV_OBJ_STRAT_LV.intValue()))
            .andExpect(jsonPath("$.devObjStratIm").value(DEFAULT_DEV_OBJ_STRAT_IM.intValue()))
            .andExpect(jsonPath("$.docRecInfoLv").value(DEFAULT_DOC_REC_INFO_LV.intValue()))
            .andExpect(jsonPath("$.docRecInfoIm").value(DEFAULT_DOC_REC_INFO_IM.intValue()))
            .andExpect(jsonPath("$.drLayOutSpecLv").value(DEFAULT_DR_LAY_OUT_SPEC_LV.intValue()))
            .andExpect(jsonPath("$.drLayOutSpecIm").value(DEFAULT_DR_LAY_OUT_SPEC_IM.intValue()))
            .andExpect(jsonPath("$.estMaIntRelLv").value(DEFAULT_EST_MA_INT_REL_LV.intValue()))
            .andExpect(jsonPath("$.estMaIntRelIm").value(DEFAULT_EST_MA_INT_REL_IM.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSkills() throws Exception {
        // Get the skills
        restSkillsMockMvc.perform(get("/api/skills/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSkills() throws Exception {
        // Initialize the database
        skillsRepository.saveAndFlush(skills);
        skillsSearchRepository.save(skills);
        int databaseSizeBeforeUpdate = skillsRepository.findAll().size();

        // Update the skills
        Skills updatedSkills = skillsRepository.findOne(skills.getId());
        updatedSkills
            .socSpecificSkName(UPDATED_SOC_SPECIFIC_SK_NAME)
            .socSpecificSkCode(UPDATED_SOC_SPECIFIC_SK_CODE)
            .anDaInLv(UPDATED_AN_DA_IN_LV)
            .anDaInIm(UPDATED_AN_DA_IN_IM)
            .asCaOthLv(UPDATED_AS_CA_OTH_LV)
            .asCaOthIm(UPDATED_AS_CA_OTH_IM)
            .coDevOthLv(UPDATED_CO_DEV_OTH_LV)
            .coDevOthIm(UPDATED_CO_DEV_OTH_IM)
            .coPerOutOrgLv(UPDATED_CO_PER_OUT_ORG_LV)
            .coPerOutOrgIm(UPDATED_CO_PER_OUT_ORG_IM)
            .coSupPeSubLv(UPDATED_CO_SUP_PE_SUB_LV)
            .coSupPeSubIm(UPDATED_CO_SUP_PE_SUB_IM)
            .conMaProLv(UPDATED_CON_MA_PRO_LV)
            .conMaProIm(UPDATED_CON_MA_PRO_IM)
            .cooWorActOthLv(UPDATED_COO_WOR_ACT_OTH_LV)
            .cooWorActOthIm(UPDATED_COO_WOR_ACT_OTH_IM)
            .devBuildTeamsLv(UPDATED_DEV_BUILD_TEAMS_LV)
            .devBuildTeamsIm(UPDATED_DEV_BUILD_TEAMS_IM)
            .devObjStratLv(UPDATED_DEV_OBJ_STRAT_LV)
            .devObjStratIm(UPDATED_DEV_OBJ_STRAT_IM)
            .docRecInfoLv(UPDATED_DOC_REC_INFO_LV)
            .docRecInfoIm(UPDATED_DOC_REC_INFO_IM)
            .drLayOutSpecLv(UPDATED_DR_LAY_OUT_SPEC_LV)
            .drLayOutSpecIm(UPDATED_DR_LAY_OUT_SPEC_IM)
            .estMaIntRelLv(UPDATED_EST_MA_INT_REL_LV)
            .estMaIntRelIm(UPDATED_EST_MA_INT_REL_IM);
        SkillsDTO skillsDTO = skillsMapper.toDto(updatedSkills);

        restSkillsMockMvc.perform(put("/api/skills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(skillsDTO)))
            .andExpect(status().isOk());

        // Validate the Skills in the database
        List<Skills> skillsList = skillsRepository.findAll();
        assertThat(skillsList).hasSize(databaseSizeBeforeUpdate);
        Skills testSkills = skillsList.get(skillsList.size() - 1);
        assertThat(testSkills.getSocSpecificSkName()).isEqualTo(UPDATED_SOC_SPECIFIC_SK_NAME);
        assertThat(testSkills.getSocSpecificSkCode()).isEqualTo(UPDATED_SOC_SPECIFIC_SK_CODE);
        assertThat(testSkills.getAnDaInLv()).isEqualTo(UPDATED_AN_DA_IN_LV);
        assertThat(testSkills.getAnDaInIm()).isEqualTo(UPDATED_AN_DA_IN_IM);
        assertThat(testSkills.getAsCaOthLv()).isEqualTo(UPDATED_AS_CA_OTH_LV);
        assertThat(testSkills.getAsCaOthIm()).isEqualTo(UPDATED_AS_CA_OTH_IM);
        assertThat(testSkills.getCoDevOthLv()).isEqualTo(UPDATED_CO_DEV_OTH_LV);
        assertThat(testSkills.getCoDevOthIm()).isEqualTo(UPDATED_CO_DEV_OTH_IM);
        assertThat(testSkills.getCoPerOutOrgLv()).isEqualTo(UPDATED_CO_PER_OUT_ORG_LV);
        assertThat(testSkills.getCoPerOutOrgIm()).isEqualTo(UPDATED_CO_PER_OUT_ORG_IM);
        assertThat(testSkills.getCoSupPeSubLv()).isEqualTo(UPDATED_CO_SUP_PE_SUB_LV);
        assertThat(testSkills.getCoSupPeSubIm()).isEqualTo(UPDATED_CO_SUP_PE_SUB_IM);
        assertThat(testSkills.getConMaProLv()).isEqualTo(UPDATED_CON_MA_PRO_LV);
        assertThat(testSkills.getConMaProIm()).isEqualTo(UPDATED_CON_MA_PRO_IM);
        assertThat(testSkills.getCooWorActOthLv()).isEqualTo(UPDATED_COO_WOR_ACT_OTH_LV);
        assertThat(testSkills.getCooWorActOthIm()).isEqualTo(UPDATED_COO_WOR_ACT_OTH_IM);
        assertThat(testSkills.getDevBuildTeamsLv()).isEqualTo(UPDATED_DEV_BUILD_TEAMS_LV);
        assertThat(testSkills.getDevBuildTeamsIm()).isEqualTo(UPDATED_DEV_BUILD_TEAMS_IM);
        assertThat(testSkills.getDevObjStratLv()).isEqualTo(UPDATED_DEV_OBJ_STRAT_LV);
        assertThat(testSkills.getDevObjStratIm()).isEqualTo(UPDATED_DEV_OBJ_STRAT_IM);
        assertThat(testSkills.getDocRecInfoLv()).isEqualTo(UPDATED_DOC_REC_INFO_LV);
        assertThat(testSkills.getDocRecInfoIm()).isEqualTo(UPDATED_DOC_REC_INFO_IM);
        assertThat(testSkills.getDrLayOutSpecLv()).isEqualTo(UPDATED_DR_LAY_OUT_SPEC_LV);
        assertThat(testSkills.getDrLayOutSpecIm()).isEqualTo(UPDATED_DR_LAY_OUT_SPEC_IM);
        assertThat(testSkills.getEstMaIntRelLv()).isEqualTo(UPDATED_EST_MA_INT_REL_LV);
        assertThat(testSkills.getEstMaIntRelIm()).isEqualTo(UPDATED_EST_MA_INT_REL_IM);

        // Validate the Skills in Elasticsearch
        Skills skillsEs = skillsSearchRepository.findOne(testSkills.getId());
        assertThat(skillsEs).isEqualToComparingFieldByField(testSkills);
    }

    @Test
    @Transactional
    public void updateNonExistingSkills() throws Exception {
        int databaseSizeBeforeUpdate = skillsRepository.findAll().size();

        // Create the Skills
        SkillsDTO skillsDTO = skillsMapper.toDto(skills);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSkillsMockMvc.perform(put("/api/skills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(skillsDTO)))
            .andExpect(status().isCreated());

        // Validate the Skills in the database
        List<Skills> skillsList = skillsRepository.findAll();
        assertThat(skillsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSkills() throws Exception {
        // Initialize the database
        skillsRepository.saveAndFlush(skills);
        skillsSearchRepository.save(skills);
        int databaseSizeBeforeDelete = skillsRepository.findAll().size();

        // Get the skills
        restSkillsMockMvc.perform(delete("/api/skills/{id}", skills.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean skillsExistsInEs = skillsSearchRepository.exists(skills.getId());
        assertThat(skillsExistsInEs).isFalse();

        // Validate the database is empty
        List<Skills> skillsList = skillsRepository.findAll();
        assertThat(skillsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchSkills() throws Exception {
        // Initialize the database
        skillsRepository.saveAndFlush(skills);
        skillsSearchRepository.save(skills);

        // Search the skills
        restSkillsMockMvc.perform(get("/api/_search/skills?query=id:" + skills.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(skills.getId().intValue())))
            .andExpect(jsonPath("$.[*].socSpecificSkName").value(hasItem(DEFAULT_SOC_SPECIFIC_SK_NAME.toString())))
            .andExpect(jsonPath("$.[*].socSpecificSkCode").value(hasItem(DEFAULT_SOC_SPECIFIC_SK_CODE.toString())))
            .andExpect(jsonPath("$.[*].anDaInLv").value(hasItem(DEFAULT_AN_DA_IN_LV.intValue())))
            .andExpect(jsonPath("$.[*].anDaInIm").value(hasItem(DEFAULT_AN_DA_IN_IM.intValue())))
            .andExpect(jsonPath("$.[*].asCaOthLv").value(hasItem(DEFAULT_AS_CA_OTH_LV.intValue())))
            .andExpect(jsonPath("$.[*].asCaOthIm").value(hasItem(DEFAULT_AS_CA_OTH_IM.intValue())))
            .andExpect(jsonPath("$.[*].coDevOthLv").value(hasItem(DEFAULT_CO_DEV_OTH_LV.intValue())))
            .andExpect(jsonPath("$.[*].coDevOthIm").value(hasItem(DEFAULT_CO_DEV_OTH_IM.intValue())))
            .andExpect(jsonPath("$.[*].coPerOutOrgLv").value(hasItem(DEFAULT_CO_PER_OUT_ORG_LV.intValue())))
            .andExpect(jsonPath("$.[*].coPerOutOrgIm").value(hasItem(DEFAULT_CO_PER_OUT_ORG_IM.intValue())))
            .andExpect(jsonPath("$.[*].coSupPeSubLv").value(hasItem(DEFAULT_CO_SUP_PE_SUB_LV.intValue())))
            .andExpect(jsonPath("$.[*].coSupPeSubIm").value(hasItem(DEFAULT_CO_SUP_PE_SUB_IM.intValue())))
            .andExpect(jsonPath("$.[*].conMaProLv").value(hasItem(DEFAULT_CON_MA_PRO_LV.intValue())))
            .andExpect(jsonPath("$.[*].conMaProIm").value(hasItem(DEFAULT_CON_MA_PRO_IM.intValue())))
            .andExpect(jsonPath("$.[*].cooWorActOthLv").value(hasItem(DEFAULT_COO_WOR_ACT_OTH_LV.intValue())))
            .andExpect(jsonPath("$.[*].cooWorActOthIm").value(hasItem(DEFAULT_COO_WOR_ACT_OTH_IM.intValue())))
            .andExpect(jsonPath("$.[*].devBuildTeamsLv").value(hasItem(DEFAULT_DEV_BUILD_TEAMS_LV.intValue())))
            .andExpect(jsonPath("$.[*].devBuildTeamsIm").value(hasItem(DEFAULT_DEV_BUILD_TEAMS_IM.intValue())))
            .andExpect(jsonPath("$.[*].devObjStratLv").value(hasItem(DEFAULT_DEV_OBJ_STRAT_LV.intValue())))
            .andExpect(jsonPath("$.[*].devObjStratIm").value(hasItem(DEFAULT_DEV_OBJ_STRAT_IM.intValue())))
            .andExpect(jsonPath("$.[*].docRecInfoLv").value(hasItem(DEFAULT_DOC_REC_INFO_LV.intValue())))
            .andExpect(jsonPath("$.[*].docRecInfoIm").value(hasItem(DEFAULT_DOC_REC_INFO_IM.intValue())))
            .andExpect(jsonPath("$.[*].drLayOutSpecLv").value(hasItem(DEFAULT_DR_LAY_OUT_SPEC_LV.intValue())))
            .andExpect(jsonPath("$.[*].drLayOutSpecIm").value(hasItem(DEFAULT_DR_LAY_OUT_SPEC_IM.intValue())))
            .andExpect(jsonPath("$.[*].estMaIntRelLv").value(hasItem(DEFAULT_EST_MA_INT_REL_LV.intValue())))
            .andExpect(jsonPath("$.[*].estMaIntRelIm").value(hasItem(DEFAULT_EST_MA_INT_REL_IM.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Skills.class);
        Skills skills1 = new Skills();
        skills1.setId(1L);
        Skills skills2 = new Skills();
        skills2.setId(skills1.getId());
        assertThat(skills1).isEqualTo(skills2);
        skills2.setId(2L);
        assertThat(skills1).isNotEqualTo(skills2);
        skills1.setId(null);
        assertThat(skills1).isNotEqualTo(skills2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SkillsDTO.class);
        SkillsDTO skillsDTO1 = new SkillsDTO();
        skillsDTO1.setId(1L);
        SkillsDTO skillsDTO2 = new SkillsDTO();
        assertThat(skillsDTO1).isNotEqualTo(skillsDTO2);
        skillsDTO2.setId(skillsDTO1.getId());
        assertThat(skillsDTO1).isEqualTo(skillsDTO2);
        skillsDTO2.setId(2L);
        assertThat(skillsDTO1).isNotEqualTo(skillsDTO2);
        skillsDTO1.setId(null);
        assertThat(skillsDTO1).isNotEqualTo(skillsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(skillsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(skillsMapper.fromId(null)).isNull();
    }
}
