package com.spirovanni.blackshields.web.rest;

import com.spirovanni.blackshields.ArmoryApp;

import com.spirovanni.blackshields.domain.Course;
import com.spirovanni.blackshields.repository.CourseRepository;
import com.spirovanni.blackshields.service.CourseService;
import com.spirovanni.blackshields.repository.search.CourseSearchRepository;
import com.spirovanni.blackshields.service.dto.CourseDTO;
import com.spirovanni.blackshields.service.mapper.CourseMapper;
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

import com.spirovanni.blackshields.domain.enumeration.Level;
/**
 * Test class for the CourseResource REST controller.
 *
 * @see CourseResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArmoryApp.class)
public class CourseResourceIntTest {

    private static final String DEFAULT_COURSE_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_COURSE_TITLE = "BBBBBBBBBB";

    private static final byte[] DEFAULT_COURSE_AVATOR = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_COURSE_AVATOR = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_COURSE_AVATOR_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_COURSE_AVATOR_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_COURSE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_COURSE_DESCRIPTION = "BBBBBBBBBB";

    private static final Long DEFAULT_COURSE_PRICE = 1L;
    private static final Long UPDATED_COURSE_PRICE = 2L;

    private static final Level DEFAULT_COURSE_LEVEL = Level.NOVICE;
    private static final Level UPDATED_COURSE_LEVEL = Level.BEGINNER;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseSearchRepository courseSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCourseMockMvc;

    private Course course;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CourseResource courseResource = new CourseResource(courseService);
        this.restCourseMockMvc = MockMvcBuilders.standaloneSetup(courseResource)
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
    public static Course createEntity(EntityManager em) {
        Course course = new Course()
            .courseTitle(DEFAULT_COURSE_TITLE)
            .courseAvator(DEFAULT_COURSE_AVATOR)
            .courseAvatorContentType(DEFAULT_COURSE_AVATOR_CONTENT_TYPE)
            .courseDescription(DEFAULT_COURSE_DESCRIPTION)
            .coursePrice(DEFAULT_COURSE_PRICE)
            .courseLevel(DEFAULT_COURSE_LEVEL);
        return course;
    }

    @Before
    public void initTest() {
        courseSearchRepository.deleteAll();
        course = createEntity(em);
    }

    @Test
    @Transactional
    public void createCourse() throws Exception {
        int databaseSizeBeforeCreate = courseRepository.findAll().size();

        // Create the Course
        CourseDTO courseDTO = courseMapper.toDto(course);
        restCourseMockMvc.perform(post("/api/courses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseDTO)))
            .andExpect(status().isCreated());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeCreate + 1);
        Course testCourse = courseList.get(courseList.size() - 1);
        assertThat(testCourse.getCourseTitle()).isEqualTo(DEFAULT_COURSE_TITLE);
        assertThat(testCourse.getCourseAvator()).isEqualTo(DEFAULT_COURSE_AVATOR);
        assertThat(testCourse.getCourseAvatorContentType()).isEqualTo(DEFAULT_COURSE_AVATOR_CONTENT_TYPE);
        assertThat(testCourse.getCourseDescription()).isEqualTo(DEFAULT_COURSE_DESCRIPTION);
        assertThat(testCourse.getCoursePrice()).isEqualTo(DEFAULT_COURSE_PRICE);
        assertThat(testCourse.getCourseLevel()).isEqualTo(DEFAULT_COURSE_LEVEL);

        // Validate the Course in Elasticsearch
        Course courseEs = courseSearchRepository.findOne(testCourse.getId());
        assertThat(courseEs).isEqualToComparingFieldByField(testCourse);
    }

    @Test
    @Transactional
    public void createCourseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = courseRepository.findAll().size();

        // Create the Course with an existing ID
        course.setId(1L);
        CourseDTO courseDTO = courseMapper.toDto(course);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCourseMockMvc.perform(post("/api/courses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCourseTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = courseRepository.findAll().size();
        // set the field null
        course.setCourseTitle(null);

        // Create the Course, which fails.
        CourseDTO courseDTO = courseMapper.toDto(course);

        restCourseMockMvc.perform(post("/api/courses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseDTO)))
            .andExpect(status().isBadRequest());

        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCourses() throws Exception {
        // Initialize the database
        courseRepository.saveAndFlush(course);

        // Get all the courseList
        restCourseMockMvc.perform(get("/api/courses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(course.getId().intValue())))
            .andExpect(jsonPath("$.[*].courseTitle").value(hasItem(DEFAULT_COURSE_TITLE.toString())))
            .andExpect(jsonPath("$.[*].courseAvatorContentType").value(hasItem(DEFAULT_COURSE_AVATOR_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].courseAvator").value(hasItem(Base64Utils.encodeToString(DEFAULT_COURSE_AVATOR))))
            .andExpect(jsonPath("$.[*].courseDescription").value(hasItem(DEFAULT_COURSE_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].coursePrice").value(hasItem(DEFAULT_COURSE_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].courseLevel").value(hasItem(DEFAULT_COURSE_LEVEL.toString())));
    }

    @Test
    @Transactional
    public void getCourse() throws Exception {
        // Initialize the database
        courseRepository.saveAndFlush(course);

        // Get the course
        restCourseMockMvc.perform(get("/api/courses/{id}", course.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(course.getId().intValue()))
            .andExpect(jsonPath("$.courseTitle").value(DEFAULT_COURSE_TITLE.toString()))
            .andExpect(jsonPath("$.courseAvatorContentType").value(DEFAULT_COURSE_AVATOR_CONTENT_TYPE))
            .andExpect(jsonPath("$.courseAvator").value(Base64Utils.encodeToString(DEFAULT_COURSE_AVATOR)))
            .andExpect(jsonPath("$.courseDescription").value(DEFAULT_COURSE_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.coursePrice").value(DEFAULT_COURSE_PRICE.intValue()))
            .andExpect(jsonPath("$.courseLevel").value(DEFAULT_COURSE_LEVEL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCourse() throws Exception {
        // Get the course
        restCourseMockMvc.perform(get("/api/courses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCourse() throws Exception {
        // Initialize the database
        courseRepository.saveAndFlush(course);
        courseSearchRepository.save(course);
        int databaseSizeBeforeUpdate = courseRepository.findAll().size();

        // Update the course
        Course updatedCourse = courseRepository.findOne(course.getId());
        updatedCourse
            .courseTitle(UPDATED_COURSE_TITLE)
            .courseAvator(UPDATED_COURSE_AVATOR)
            .courseAvatorContentType(UPDATED_COURSE_AVATOR_CONTENT_TYPE)
            .courseDescription(UPDATED_COURSE_DESCRIPTION)
            .coursePrice(UPDATED_COURSE_PRICE)
            .courseLevel(UPDATED_COURSE_LEVEL);
        CourseDTO courseDTO = courseMapper.toDto(updatedCourse);

        restCourseMockMvc.perform(put("/api/courses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseDTO)))
            .andExpect(status().isOk());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeUpdate);
        Course testCourse = courseList.get(courseList.size() - 1);
        assertThat(testCourse.getCourseTitle()).isEqualTo(UPDATED_COURSE_TITLE);
        assertThat(testCourse.getCourseAvator()).isEqualTo(UPDATED_COURSE_AVATOR);
        assertThat(testCourse.getCourseAvatorContentType()).isEqualTo(UPDATED_COURSE_AVATOR_CONTENT_TYPE);
        assertThat(testCourse.getCourseDescription()).isEqualTo(UPDATED_COURSE_DESCRIPTION);
        assertThat(testCourse.getCoursePrice()).isEqualTo(UPDATED_COURSE_PRICE);
        assertThat(testCourse.getCourseLevel()).isEqualTo(UPDATED_COURSE_LEVEL);

        // Validate the Course in Elasticsearch
        Course courseEs = courseSearchRepository.findOne(testCourse.getId());
        assertThat(courseEs).isEqualToComparingFieldByField(testCourse);
    }

    @Test
    @Transactional
    public void updateNonExistingCourse() throws Exception {
        int databaseSizeBeforeUpdate = courseRepository.findAll().size();

        // Create the Course
        CourseDTO courseDTO = courseMapper.toDto(course);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCourseMockMvc.perform(put("/api/courses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courseDTO)))
            .andExpect(status().isCreated());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCourse() throws Exception {
        // Initialize the database
        courseRepository.saveAndFlush(course);
        courseSearchRepository.save(course);
        int databaseSizeBeforeDelete = courseRepository.findAll().size();

        // Get the course
        restCourseMockMvc.perform(delete("/api/courses/{id}", course.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean courseExistsInEs = courseSearchRepository.exists(course.getId());
        assertThat(courseExistsInEs).isFalse();

        // Validate the database is empty
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchCourse() throws Exception {
        // Initialize the database
        courseRepository.saveAndFlush(course);
        courseSearchRepository.save(course);

        // Search the course
        restCourseMockMvc.perform(get("/api/_search/courses?query=id:" + course.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(course.getId().intValue())))
            .andExpect(jsonPath("$.[*].courseTitle").value(hasItem(DEFAULT_COURSE_TITLE.toString())))
            .andExpect(jsonPath("$.[*].courseAvatorContentType").value(hasItem(DEFAULT_COURSE_AVATOR_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].courseAvator").value(hasItem(Base64Utils.encodeToString(DEFAULT_COURSE_AVATOR))))
            .andExpect(jsonPath("$.[*].courseDescription").value(hasItem(DEFAULT_COURSE_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].coursePrice").value(hasItem(DEFAULT_COURSE_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].courseLevel").value(hasItem(DEFAULT_COURSE_LEVEL.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Course.class);
        Course course1 = new Course();
        course1.setId(1L);
        Course course2 = new Course();
        course2.setId(course1.getId());
        assertThat(course1).isEqualTo(course2);
        course2.setId(2L);
        assertThat(course1).isNotEqualTo(course2);
        course1.setId(null);
        assertThat(course1).isNotEqualTo(course2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CourseDTO.class);
        CourseDTO courseDTO1 = new CourseDTO();
        courseDTO1.setId(1L);
        CourseDTO courseDTO2 = new CourseDTO();
        assertThat(courseDTO1).isNotEqualTo(courseDTO2);
        courseDTO2.setId(courseDTO1.getId());
        assertThat(courseDTO1).isEqualTo(courseDTO2);
        courseDTO2.setId(2L);
        assertThat(courseDTO1).isNotEqualTo(courseDTO2);
        courseDTO1.setId(null);
        assertThat(courseDTO1).isNotEqualTo(courseDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(courseMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(courseMapper.fromId(null)).isNull();
    }
}
