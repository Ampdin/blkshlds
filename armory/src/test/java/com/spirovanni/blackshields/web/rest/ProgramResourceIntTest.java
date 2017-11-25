package com.spirovanni.blackshields.web.rest;

import com.spirovanni.blackshields.ArmoryApp;

import com.spirovanni.blackshields.domain.Program;
import com.spirovanni.blackshields.repository.ProgramRepository;
import com.spirovanni.blackshields.service.ProgramService;
import com.spirovanni.blackshields.repository.search.ProgramSearchRepository;
import com.spirovanni.blackshields.service.dto.ProgramDTO;
import com.spirovanni.blackshields.service.mapper.ProgramMapper;
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
 * Test class for the ProgramResource REST controller.
 *
 * @see ProgramResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArmoryApp.class)
public class ProgramResourceIntTest {

    private static final String DEFAULT_PROGRAM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PROGRAM_NAME = "BBBBBBBBBB";

    private static final byte[] DEFAULT_PROGRAM_AVATOR = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PROGRAM_AVATOR = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_PROGRAM_AVATOR_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PROGRAM_AVATOR_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_PROGRAM_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PROGRAM_DESCRIPTION = "BBBBBBBBBB";

    private static final Long DEFAULT_PROGRAM_PRICE = 1L;
    private static final Long UPDATED_PROGRAM_PRICE = 2L;

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private ProgramMapper programMapper;

    @Autowired
    private ProgramService programService;

    @Autowired
    private ProgramSearchRepository programSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProgramMockMvc;

    private Program program;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProgramResource programResource = new ProgramResource(programService);
        this.restProgramMockMvc = MockMvcBuilders.standaloneSetup(programResource)
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
    public static Program createEntity(EntityManager em) {
        Program program = new Program()
            .programName(DEFAULT_PROGRAM_NAME)
            .programAvator(DEFAULT_PROGRAM_AVATOR)
            .programAvatorContentType(DEFAULT_PROGRAM_AVATOR_CONTENT_TYPE)
            .programDescription(DEFAULT_PROGRAM_DESCRIPTION)
            .programPrice(DEFAULT_PROGRAM_PRICE);
        return program;
    }

    @Before
    public void initTest() {
        programSearchRepository.deleteAll();
        program = createEntity(em);
    }

    @Test
    @Transactional
    public void createProgram() throws Exception {
        int databaseSizeBeforeCreate = programRepository.findAll().size();

        // Create the Program
        ProgramDTO programDTO = programMapper.toDto(program);
        restProgramMockMvc.perform(post("/api/programs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programDTO)))
            .andExpect(status().isCreated());

        // Validate the Program in the database
        List<Program> programList = programRepository.findAll();
        assertThat(programList).hasSize(databaseSizeBeforeCreate + 1);
        Program testProgram = programList.get(programList.size() - 1);
        assertThat(testProgram.getProgramName()).isEqualTo(DEFAULT_PROGRAM_NAME);
        assertThat(testProgram.getProgramAvator()).isEqualTo(DEFAULT_PROGRAM_AVATOR);
        assertThat(testProgram.getProgramAvatorContentType()).isEqualTo(DEFAULT_PROGRAM_AVATOR_CONTENT_TYPE);
        assertThat(testProgram.getProgramDescription()).isEqualTo(DEFAULT_PROGRAM_DESCRIPTION);
        assertThat(testProgram.getProgramPrice()).isEqualTo(DEFAULT_PROGRAM_PRICE);

        // Validate the Program in Elasticsearch
        Program programEs = programSearchRepository.findOne(testProgram.getId());
        assertThat(programEs).isEqualToComparingFieldByField(testProgram);
    }

    @Test
    @Transactional
    public void createProgramWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = programRepository.findAll().size();

        // Create the Program with an existing ID
        program.setId(1L);
        ProgramDTO programDTO = programMapper.toDto(program);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProgramMockMvc.perform(post("/api/programs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Program in the database
        List<Program> programList = programRepository.findAll();
        assertThat(programList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkProgramNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = programRepository.findAll().size();
        // set the field null
        program.setProgramName(null);

        // Create the Program, which fails.
        ProgramDTO programDTO = programMapper.toDto(program);

        restProgramMockMvc.perform(post("/api/programs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programDTO)))
            .andExpect(status().isBadRequest());

        List<Program> programList = programRepository.findAll();
        assertThat(programList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPrograms() throws Exception {
        // Initialize the database
        programRepository.saveAndFlush(program);

        // Get all the programList
        restProgramMockMvc.perform(get("/api/programs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(program.getId().intValue())))
            .andExpect(jsonPath("$.[*].programName").value(hasItem(DEFAULT_PROGRAM_NAME.toString())))
            .andExpect(jsonPath("$.[*].programAvatorContentType").value(hasItem(DEFAULT_PROGRAM_AVATOR_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].programAvator").value(hasItem(Base64Utils.encodeToString(DEFAULT_PROGRAM_AVATOR))))
            .andExpect(jsonPath("$.[*].programDescription").value(hasItem(DEFAULT_PROGRAM_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].programPrice").value(hasItem(DEFAULT_PROGRAM_PRICE.intValue())));
    }

    @Test
    @Transactional
    public void getProgram() throws Exception {
        // Initialize the database
        programRepository.saveAndFlush(program);

        // Get the program
        restProgramMockMvc.perform(get("/api/programs/{id}", program.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(program.getId().intValue()))
            .andExpect(jsonPath("$.programName").value(DEFAULT_PROGRAM_NAME.toString()))
            .andExpect(jsonPath("$.programAvatorContentType").value(DEFAULT_PROGRAM_AVATOR_CONTENT_TYPE))
            .andExpect(jsonPath("$.programAvator").value(Base64Utils.encodeToString(DEFAULT_PROGRAM_AVATOR)))
            .andExpect(jsonPath("$.programDescription").value(DEFAULT_PROGRAM_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.programPrice").value(DEFAULT_PROGRAM_PRICE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingProgram() throws Exception {
        // Get the program
        restProgramMockMvc.perform(get("/api/programs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProgram() throws Exception {
        // Initialize the database
        programRepository.saveAndFlush(program);
        programSearchRepository.save(program);
        int databaseSizeBeforeUpdate = programRepository.findAll().size();

        // Update the program
        Program updatedProgram = programRepository.findOne(program.getId());
        updatedProgram
            .programName(UPDATED_PROGRAM_NAME)
            .programAvator(UPDATED_PROGRAM_AVATOR)
            .programAvatorContentType(UPDATED_PROGRAM_AVATOR_CONTENT_TYPE)
            .programDescription(UPDATED_PROGRAM_DESCRIPTION)
            .programPrice(UPDATED_PROGRAM_PRICE);
        ProgramDTO programDTO = programMapper.toDto(updatedProgram);

        restProgramMockMvc.perform(put("/api/programs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programDTO)))
            .andExpect(status().isOk());

        // Validate the Program in the database
        List<Program> programList = programRepository.findAll();
        assertThat(programList).hasSize(databaseSizeBeforeUpdate);
        Program testProgram = programList.get(programList.size() - 1);
        assertThat(testProgram.getProgramName()).isEqualTo(UPDATED_PROGRAM_NAME);
        assertThat(testProgram.getProgramAvator()).isEqualTo(UPDATED_PROGRAM_AVATOR);
        assertThat(testProgram.getProgramAvatorContentType()).isEqualTo(UPDATED_PROGRAM_AVATOR_CONTENT_TYPE);
        assertThat(testProgram.getProgramDescription()).isEqualTo(UPDATED_PROGRAM_DESCRIPTION);
        assertThat(testProgram.getProgramPrice()).isEqualTo(UPDATED_PROGRAM_PRICE);

        // Validate the Program in Elasticsearch
        Program programEs = programSearchRepository.findOne(testProgram.getId());
        assertThat(programEs).isEqualToComparingFieldByField(testProgram);
    }

    @Test
    @Transactional
    public void updateNonExistingProgram() throws Exception {
        int databaseSizeBeforeUpdate = programRepository.findAll().size();

        // Create the Program
        ProgramDTO programDTO = programMapper.toDto(program);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProgramMockMvc.perform(put("/api/programs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programDTO)))
            .andExpect(status().isCreated());

        // Validate the Program in the database
        List<Program> programList = programRepository.findAll();
        assertThat(programList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteProgram() throws Exception {
        // Initialize the database
        programRepository.saveAndFlush(program);
        programSearchRepository.save(program);
        int databaseSizeBeforeDelete = programRepository.findAll().size();

        // Get the program
        restProgramMockMvc.perform(delete("/api/programs/{id}", program.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean programExistsInEs = programSearchRepository.exists(program.getId());
        assertThat(programExistsInEs).isFalse();

        // Validate the database is empty
        List<Program> programList = programRepository.findAll();
        assertThat(programList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchProgram() throws Exception {
        // Initialize the database
        programRepository.saveAndFlush(program);
        programSearchRepository.save(program);

        // Search the program
        restProgramMockMvc.perform(get("/api/_search/programs?query=id:" + program.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(program.getId().intValue())))
            .andExpect(jsonPath("$.[*].programName").value(hasItem(DEFAULT_PROGRAM_NAME.toString())))
            .andExpect(jsonPath("$.[*].programAvatorContentType").value(hasItem(DEFAULT_PROGRAM_AVATOR_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].programAvator").value(hasItem(Base64Utils.encodeToString(DEFAULT_PROGRAM_AVATOR))))
            .andExpect(jsonPath("$.[*].programDescription").value(hasItem(DEFAULT_PROGRAM_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].programPrice").value(hasItem(DEFAULT_PROGRAM_PRICE.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Program.class);
        Program program1 = new Program();
        program1.setId(1L);
        Program program2 = new Program();
        program2.setId(program1.getId());
        assertThat(program1).isEqualTo(program2);
        program2.setId(2L);
        assertThat(program1).isNotEqualTo(program2);
        program1.setId(null);
        assertThat(program1).isNotEqualTo(program2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProgramDTO.class);
        ProgramDTO programDTO1 = new ProgramDTO();
        programDTO1.setId(1L);
        ProgramDTO programDTO2 = new ProgramDTO();
        assertThat(programDTO1).isNotEqualTo(programDTO2);
        programDTO2.setId(programDTO1.getId());
        assertThat(programDTO1).isEqualTo(programDTO2);
        programDTO2.setId(2L);
        assertThat(programDTO1).isNotEqualTo(programDTO2);
        programDTO1.setId(null);
        assertThat(programDTO1).isNotEqualTo(programDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(programMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(programMapper.fromId(null)).isNull();
    }
}
