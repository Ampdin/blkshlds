package com.spirovanni.blackshields.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the SocMajor entity.
 */
public class SocMajorDTO implements Serializable {

    private Long id;

    private String socMajorName;

    private String socMajorCode;

    @Lob
    private byte[] socMajorAvator;
    private String socMajorAvatorContentType;

    private String socMajorDescription;

    private String socMajorURL;

    private String socMajorPreviewImage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSocMajorName() {
        return socMajorName;
    }

    public void setSocMajorName(String socMajorName) {
        this.socMajorName = socMajorName;
    }

    public String getSocMajorCode() {
        return socMajorCode;
    }

    public void setSocMajorCode(String socMajorCode) {
        this.socMajorCode = socMajorCode;
    }

    public byte[] getSocMajorAvator() {
        return socMajorAvator;
    }

    public void setSocMajorAvator(byte[] socMajorAvator) {
        this.socMajorAvator = socMajorAvator;
    }

    public String getSocMajorAvatorContentType() {
        return socMajorAvatorContentType;
    }

    public void setSocMajorAvatorContentType(String socMajorAvatorContentType) {
        this.socMajorAvatorContentType = socMajorAvatorContentType;
    }

    public String getSocMajorDescription() {
        return socMajorDescription;
    }

    public void setSocMajorDescription(String socMajorDescription) {
        this.socMajorDescription = socMajorDescription;
    }

    public String getSocMajorURL() {
        return socMajorURL;
    }

    public void setSocMajorURL(String socMajorURL) {
        this.socMajorURL = socMajorURL;
    }

    public String getSocMajorPreviewImage() {
        return socMajorPreviewImage;
    }

    public void setSocMajorPreviewImage(String socMajorPreviewImage) {
        this.socMajorPreviewImage = socMajorPreviewImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SocMajorDTO socMajorDTO = (SocMajorDTO) o;
        if(socMajorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), socMajorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SocMajorDTO{" +
            "id=" + getId() +
            ", socMajorName='" + getSocMajorName() + "'" +
            ", socMajorCode='" + getSocMajorCode() + "'" +
            ", socMajorAvator='" + getSocMajorAvator() + "'" +
            ", socMajorDescription='" + getSocMajorDescription() + "'" +
            ", socMajorURL='" + getSocMajorURL() + "'" +
            ", socMajorPreviewImage='" + getSocMajorPreviewImage() + "'" +
            "}";
    }
}
