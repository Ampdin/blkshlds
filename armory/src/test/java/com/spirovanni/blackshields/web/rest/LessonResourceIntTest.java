package com.spirovanni.blackshields.web.rest;

import com.spirovanni.blackshields.ArmoryApp;

import com.spirovanni.blackshields.domain.Lesson;
import com.spirovanni.blackshields.repository.LessonRepository;
import com.spirovanni.blackshields.service.LessonService;
import com.spirovanni.blackshields.repository.search.LessonSearchRepository;
import com.spirovanni.blackshields.service.dto.LessonDTO;
import com.spirovanni.blackshields.service.mapper.LessonMapper;
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

import com.spirovanni.blackshields.domain.enumeration.Language;
/**
 * Test class for the LessonResource REST controller.
 *
 * @see LessonResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArmoryApp.class)
public class LessonResourceIntTest {

    private static final String DEFAULT_LESSON_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_LESSON_TITLE = "BBBBBBBBBB";

    private static final byte[] DEFAULT_LESSON_AVATOR = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_LESSON_AVATOR = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_LESSON_AVATOR_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_LESSON_AVATOR_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_LESSON_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_LESSON_DESCRIPTION = "BBBBBBBBBB";

    private static final Language DEFAULT_LANGUAGE = Language.ENGLISH;
    private static final Language UPDATED_LANGUAGE = Language.SPANISH;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private LessonMapper lessonMapper;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private LessonSearchRepository lessonSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLessonMockMvc;

    private Lesson lesson;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LessonResource lessonResource = new LessonResource(lessonService);
        this.restLessonMockMvc = MockMvcBuilders.standaloneSetup(lessonResource)
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
    public static Lesson createEntity(EntityManager em) {
        Lesson lesson = new Lesson()
            .lessonTitle(DEFAULT_LESSON_TITLE)
            .lessonAvator(DEFAULT_LESSON_AVATOR)
            .lessonAvatorContentType(DEFAULT_LESSON_AVATOR_CONTENT_TYPE)
            .lessonDescription(DEFAULT_LESSON_DESCRIPTION)
            .language(DEFAULT_LANGUAGE);
        return lesson;
    }

    @Before
    public void initTest() {
        lessonSearchRepository.deleteAll();
        lesson = createEntity(em);
    }

    @Test
    @Transactional
    public void createLesson() throws Exception {
        int databaseSizeBeforeCreate = lessonRepository.findAll().size();

        // Create the Lesson
        LessonDTO lessonDTO = lessonMapper.toDto(lesson);
        restLessonMockMvc.perform(post("/api/lessons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lessonDTO)))
            .andExpect(status().isCreated());

        // Validate the Lesson in the database
        List<Lesson> lessonList = lessonRepository.findAll();
        assertThat(lessonList).hasSize(databaseSizeBeforeCreate + 1);
        Lesson testLesson = lessonList.get(lessonList.size() - 1);
        assertThat(testLesson.getLessonTitle()).isEqualTo(DEFAULT_LESSON_TITLE);
        assertThat(testLesson.getLessonAvator()).isEqualTo(DEFAULT_LESSON_AVATOR);
        assertThat(testLesson.getLessonAvatorContentType()).isEqualTo(DEFAULT_LESSON_AVATOR_CONTENT_TYPE);
        assertThat(testLesson.getLessonDescription()).isEqualTo(DEFAULT_LESSON_DESCRIPTION);
        assertThat(testLesson.getLanguage()).isEqualTo(DEFAULT_LANGUAGE);

        // Validate the Lesson in Elasticsearch
        Lesson lessonEs = lessonSearchRepository.findOne(testLesson.getId());
        assertThat(lessonEs).isEqualToComparingFieldByField(testLesson);
    }

    @Test
    @Transactional
    public void createLessonWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = lessonRepository.findAll().size();

        // Create the Lesson with an existing ID
        lesson.setId(1L);
        LessonDTO lessonDTO = lessonMapper.toDto(lesson);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLessonMockMvc.perform(post("/api/lessons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lessonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Lesson in the database
        List<Lesson> lessonList = lessonRepository.findAll();
        assertThat(lessonList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkLessonTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = lessonRepository.findAll().size();
        // set the field null
        lesson.setLessonTitle(null);

        // Create the Lesson, which fails.
        LessonDTO lessonDTO = lessonMapper.toDto(lesson);

        restLessonMockMvc.perform(post("/api/lessons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lessonDTO)))
            .andExpect(status().isBadRequest());

        List<Lesson> lessonList = lessonRepository.findAll();
        assertThat(lessonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLessons() throws Exception {
        // Initialize the database
        lessonRepository.saveAndFlush(lesson);

        // Get all the lessonList
        restLessonMockMvc.perform(get("/api/lessons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lesson.getId().intValue())))
            .andExpect(jsonPath("$.[*].lessonTitle").value(hasItem(DEFAULT_LESSON_TITLE.toString())))
            .andExpect(jsonPath("$.[*].lessonAvatorContentType").value(hasItem(DEFAULT_LESSON_AVATOR_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].lessonAvator").value(hasItem(Base64Utils.encodeToString(DEFAULT_LESSON_AVATOR))))
            .andExpect(jsonPath("$.[*].lessonDescription").value(hasItem(DEFAULT_LESSON_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].language").value(hasItem(DEFAULT_LANGUAGE.toString())));
    }

    @Test
    @Transactional
    public void getLesson() throws Exception {
        // Initialize the database
        lessonRepository.saveAndFlush(lesson);

        // Get the lesson
        restLessonMockMvc.perform(get("/api/lessons/{id}", lesson.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(lesson.getId().intValue()))
            .andExpect(jsonPath("$.lessonTitle").value(DEFAULT_LESSON_TITLE.toString()))
            .andExpect(jsonPath("$.lessonAvatorContentType").value(DEFAULT_LESSON_AVATOR_CONTENT_TYPE))
            .andExpect(jsonPath("$.lessonAvator").value(Base64Utils.encodeToString(DEFAULT_LESSON_AVATOR)))
            .andExpect(jsonPath("$.lessonDescription").value(DEFAULT_LESSON_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.language").value(DEFAULT_LANGUAGE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLesson() throws Exception {
        // Get the lesson
        restLessonMockMvc.perform(get("/api/lessons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLesson() throws Exception {
        // Initialize the database
        lessonRepository.saveAndFlush(lesson);
        lessonSearchRepository.save(lesson);
        int databaseSizeBeforeUpdate = lessonRepository.findAll().size();

        // Update the lesson
        Lesson updatedLesson = lessonRepository.findOne(lesson.getId());
        updatedLesson
            .lessonTitle(UPDATED_LESSON_TITLE)
            .lessonAvator(UPDATED_LESSON_AVATOR)
            .lessonAvatorContentType(UPDATED_LESSON_AVATOR_CONTENT_TYPE)
            .lessonDescription(UPDATED_LESSON_DESCRIPTION)
            .language(UPDATED_LANGUAGE);
        LessonDTO lessonDTO = lessonMapper.toDto(updatedLesson);

        restLessonMockMvc.perform(put("/api/lessons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lessonDTO)))
            .andExpect(status().isOk());

        // Validate the Lesson in the database
        List<Lesson> lessonList = lessonRepository.findAll();
        assertThat(lessonList).hasSize(databaseSizeBeforeUpdate);
        Lesson testLesson = lessonList.get(lessonList.size() - 1);
        assertThat(testLesson.getLessonTitle()).isEqualTo(UPDATED_LESSON_TITLE);
        assertThat(testLesson.getLessonAvator()).isEqualTo(UPDATED_LESSON_AVATOR);
        assertThat(testLesson.getLessonAvatorContentType()).isEqualTo(UPDATED_LESSON_AVATOR_CONTENT_TYPE);
        assertThat(testLesson.getLessonDescription()).isEqualTo(UPDATED_LESSON_DESCRIPTION);
        assertThat(testLesson.getLanguage()).isEqualTo(UPDATED_LANGUAGE);

        // Validate the Lesson in Elasticsearch
        Lesson lessonEs = lessonSearchRepository.findOne(testLesson.getId());
        assertThat(lessonEs).isEqualToComparingFieldByField(testLesson);
    }

    @Test
    @Transactional
    public void updateNonExistingLesson() throws Exception {
        int databaseSizeBeforeUpdate = lessonRepository.findAll().size();

        // Create the Lesson
        LessonDTO lessonDTO = lessonMapper.toDto(lesson);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLessonMockMvc.perform(put("/api/lessons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lessonDTO)))
            .andExpect(status().isCreated());

        // Validate the Lesson in the database
        List<Lesson> lessonList = lessonRepository.findAll();
        assertThat(lessonList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteLesson() throws Exception {
        // Initialize the database
        lessonRepository.saveAndFlush(lesson);
        lessonSearchRepository.save(lesson);
        int databaseSizeBeforeDelete = lessonRepository.findAll().size();

        // Get the lesson
        restLessonMockMvc.perform(delete("/api/lessons/{id}", lesson.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean lessonExistsInEs = lessonSearchRepository.exists(lesson.getId());
        assertThat(lessonExistsInEs).isFalse();

        // Validate the database is empty
        List<Lesson> lessonList = lessonRepository.findAll();
        assertThat(lessonList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchLesson() throws Exception {
        // Initialize the database
        lessonRepository.saveAndFlush(lesson);
        lessonSearchRepository.save(lesson);

        // Search the lesson
        restLessonMockMvc.perform(get("/api/_search/lessons?query=id:" + lesson.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lesson.getId().intValue())))
            .andExpect(jsonPath("$.[*].lessonTitle").value(hasItem(DEFAULT_LESSON_TITLE.toString())))
            .andExpect(jsonPath("$.[*].lessonAvatorContentType").value(hasItem(DEFAULT_LESSON_AVATOR_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].lessonAvator").value(hasItem(Base64Utils.encodeToString(DEFAULT_LESSON_AVATOR))))
            .andExpect(jsonPath("$.[*].lessonDescription").value(hasItem(DEFAULT_LESSON_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].language").value(hasItem(DEFAULT_LANGUAGE.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Lesson.class);
        Lesson lesson1 = new Lesson();
        lesson1.setId(1L);
        Lesson lesson2 = new Lesson();
        lesson2.setId(lesson1.getId());
        assertThat(lesson1).isEqualTo(lesson2);
        lesson2.setId(2L);
        assertThat(lesson1).isNotEqualTo(lesson2);
        lesson1.setId(null);
        assertThat(lesson1).isNotEqualTo(lesson2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LessonDTO.class);
        LessonDTO lessonDTO1 = new LessonDTO();
        lessonDTO1.setId(1L);
        LessonDTO lessonDTO2 = new LessonDTO();
        assertThat(lessonDTO1).isNotEqualTo(lessonDTO2);
        lessonDTO2.setId(lessonDTO1.getId());
        assertThat(lessonDTO1).isEqualTo(lessonDTO2);
        lessonDTO2.setId(2L);
        assertThat(lessonDTO1).isNotEqualTo(lessonDTO2);
        lessonDTO1.setId(null);
        assertThat(lessonDTO1).isNotEqualTo(lessonDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(lessonMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(lessonMapper.fromId(null)).isNull();
    }
}
