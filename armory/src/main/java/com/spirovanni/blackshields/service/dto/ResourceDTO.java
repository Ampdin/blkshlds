package com.spirovanni.blackshields.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;
import com.spirovanni.blackshields.domain.enumeration.ResourceType;

/**
 * A DTO for the Resource entity.
 */
public class ResourceDTO implements Serializable {

    private Long id;

    private String resourceName;

    @Lob
    private byte[] resourceAvator;
    private String resourceAvatorContentType;

    private String resourceDescription;

    private String resourceURL;

    private String resourcePreviewImage;

    private ResourceType resourceType;

    private Integer weight;

    private Long disciplineId;

    private Long programId;

    private Long courseId;

    private Long lessonId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public byte[] getResourceAvator() {
        return resourceAvator;
    }

    public void setResourceAvator(byte[] resourceAvator) {
        this.resourceAvator = resourceAvator;
    }

    public String getResourceAvatorContentType() {
        return resourceAvatorContentType;
    }

    public void setResourceAvatorContentType(String resourceAvatorContentType) {
        this.resourceAvatorContentType = resourceAvatorContentType;
    }

    public String getResourceDescription() {
        return resourceDescription;
    }

    public void setResourceDescription(String resourceDescription) {
        this.resourceDescription = resourceDescription;
    }

    public String getResourceURL() {
        return resourceURL;
    }

    public void setResourceURL(String resourceURL) {
        this.resourceURL = resourceURL;
    }

    public String getResourcePreviewImage() {
        return resourcePreviewImage;
    }

    public void setResourcePreviewImage(String resourcePreviewImage) {
        this.resourcePreviewImage = resourcePreviewImage;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Long getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(Long disciplineId) {
        this.disciplineId = disciplineId;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ResourceDTO resourceDTO = (ResourceDTO) o;
        if(resourceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), resourceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ResourceDTO{" +
            "id=" + getId() +
            ", resourceName='" + getResourceName() + "'" +
            ", resourceAvator='" + getResourceAvator() + "'" +
            ", resourceDescription='" + getResourceDescription() + "'" +
            ", resourceURL='" + getResourceURL() + "'" +
            ", resourcePreviewImage='" + getResourcePreviewImage() + "'" +
            ", resourceType='" + getResourceType() + "'" +
            ", weight=" + getWeight() +
            "}";
    }
}
