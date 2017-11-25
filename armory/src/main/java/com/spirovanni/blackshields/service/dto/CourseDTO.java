package com.spirovanni.blackshields.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;
import com.spirovanni.blackshields.domain.enumeration.Level;

/**
 * A DTO for the Course entity.
 */
public class CourseDTO implements Serializable {

    private Long id;

    @NotNull
    private String courseTitle;

    @Lob
    private byte[] courseAvator;
    private String courseAvatorContentType;

    private String courseDescription;

    private Long coursePrice;

    private Level courseLevel;

    private Set<LessonDTO> lessons = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public byte[] getCourseAvator() {
        return courseAvator;
    }

    public void setCourseAvator(byte[] courseAvator) {
        this.courseAvator = courseAvator;
    }

    public String getCourseAvatorContentType() {
        return courseAvatorContentType;
    }

    public void setCourseAvatorContentType(String courseAvatorContentType) {
        this.courseAvatorContentType = courseAvatorContentType;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public Long getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(Long coursePrice) {
        this.coursePrice = coursePrice;
    }

    public Level getCourseLevel() {
        return courseLevel;
    }

    public void setCourseLevel(Level courseLevel) {
        this.courseLevel = courseLevel;
    }

    public Set<LessonDTO> getLessons() {
        return lessons;
    }

    public void setLessons(Set<LessonDTO> lessons) {
        this.lessons = lessons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CourseDTO courseDTO = (CourseDTO) o;
        if(courseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), courseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
            "id=" + getId() +
            ", courseTitle='" + getCourseTitle() + "'" +
            ", courseAvator='" + getCourseAvator() + "'" +
            ", courseDescription='" + getCourseDescription() + "'" +
            ", coursePrice=" + getCoursePrice() +
            ", courseLevel='" + getCourseLevel() + "'" +
            "}";
    }
}
