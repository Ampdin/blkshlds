package com.spirovanni.blackshields.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the SocMinor entity.
 */
public class SocMinorDTO implements Serializable {

    private Long id;

    private String socMinorName;

    private String socMinorCode;

    @Lob
    private byte[] socMinorAvator;
    private String socMinorAvatorContentType;

    private String socMinorDescription;

    private String socMinorURL;

    private String socMinorPreviewImage;

    private Long socMajorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSocMinorName() {
        return socMinorName;
    }

    public void setSocMinorName(String socMinorName) {
        this.socMinorName = socMinorName;
    }

    public String getSocMinorCode() {
        return socMinorCode;
    }

    public void setSocMinorCode(String socMinorCode) {
        this.socMinorCode = socMinorCode;
    }

    public byte[] getSocMinorAvator() {
        return socMinorAvator;
    }

    public void setSocMinorAvator(byte[] socMinorAvator) {
        this.socMinorAvator = socMinorAvator;
    }

    public String getSocMinorAvatorContentType() {
        return socMinorAvatorContentType;
    }

    public void setSocMinorAvatorContentType(String socMinorAvatorContentType) {
        this.socMinorAvatorContentType = socMinorAvatorContentType;
    }

    public String getSocMinorDescription() {
        return socMinorDescription;
    }

    public void setSocMinorDescription(String socMinorDescription) {
        this.socMinorDescription = socMinorDescription;
    }

    public String getSocMinorURL() {
        return socMinorURL;
    }

    public void setSocMinorURL(String socMinorURL) {
        this.socMinorURL = socMinorURL;
    }

    public String getSocMinorPreviewImage() {
        return socMinorPreviewImage;
    }

    public void setSocMinorPreviewImage(String socMinorPreviewImage) {
        this.socMinorPreviewImage = socMinorPreviewImage;
    }

    public Long getSocMajorId() {
        return socMajorId;
    }

    public void setSocMajorId(Long socMajorId) {
        this.socMajorId = socMajorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SocMinorDTO socMinorDTO = (SocMinorDTO) o;
        if(socMinorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), socMinorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SocMinorDTO{" +
            "id=" + getId() +
            ", socMinorName='" + getSocMinorName() + "'" +
            ", socMinorCode='" + getSocMinorCode() + "'" +
            ", socMinorAvator='" + getSocMinorAvator() + "'" +
            ", socMinorDescription='" + getSocMinorDescription() + "'" +
            ", socMinorURL='" + getSocMinorURL() + "'" +
            ", socMinorPreviewImage='" + getSocMinorPreviewImage() + "'" +
            "}";
    }
}
