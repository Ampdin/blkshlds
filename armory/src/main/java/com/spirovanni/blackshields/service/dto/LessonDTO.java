package com.spirovanni.blackshields.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;
import com.spirovanni.blackshields.domain.enumeration.Language;

/**
 * A DTO for the Lesson entity.
 */
public class LessonDTO implements Serializable {

    private Long id;

    @NotNull
    private String lessonTitle;

    @Lob
    private byte[] lessonAvator;
    private String lessonAvatorContentType;

    private String lessonDescription;

    private Language language;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public byte[] getLessonAvator() {
        return lessonAvator;
    }

    public void setLessonAvator(byte[] lessonAvator) {
        this.lessonAvator = lessonAvator;
    }

    public String getLessonAvatorContentType() {
        return lessonAvatorContentType;
    }

    public void setLessonAvatorContentType(String lessonAvatorContentType) {
        this.lessonAvatorContentType = lessonAvatorContentType;
    }

    public String getLessonDescription() {
        return lessonDescription;
    }

    public void setLessonDescription(String lessonDescription) {
        this.lessonDescription = lessonDescription;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LessonDTO lessonDTO = (LessonDTO) o;
        if(lessonDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), lessonDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LessonDTO{" +
            "id=" + getId() +
            ", lessonTitle='" + getLessonTitle() + "'" +
            ", lessonAvator='" + getLessonAvator() + "'" +
            ", lessonDescription='" + getLessonDescription() + "'" +
            ", language='" + getLanguage() + "'" +
            "}";
    }
}
