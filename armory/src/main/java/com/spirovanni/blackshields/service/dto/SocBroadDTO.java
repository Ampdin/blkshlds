package com.spirovanni.blackshields.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the SocBroad entity.
 */
public class SocBroadDTO implements Serializable {

    private Long id;

    private String socBroadName;

    private String socBroadCode;

    @Lob
    private byte[] socBroadAvator;
    private String socBroadAvatorContentType;

    private String socBroadDescription;

    private String socBroadURL;

    private String socBroadPreviewImage;

    private Long socMinorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSocBroadName() {
        return socBroadName;
    }

    public void setSocBroadName(String socBroadName) {
        this.socBroadName = socBroadName;
    }

    public String getSocBroadCode() {
        return socBroadCode;
    }

    public void setSocBroadCode(String socBroadCode) {
        this.socBroadCode = socBroadCode;
    }

    public byte[] getSocBroadAvator() {
        return socBroadAvator;
    }

    public void setSocBroadAvator(byte[] socBroadAvator) {
        this.socBroadAvator = socBroadAvator;
    }

    public String getSocBroadAvatorContentType() {
        return socBroadAvatorContentType;
    }

    public void setSocBroadAvatorContentType(String socBroadAvatorContentType) {
        this.socBroadAvatorContentType = socBroadAvatorContentType;
    }

    public String getSocBroadDescription() {
        return socBroadDescription;
    }

    public void setSocBroadDescription(String socBroadDescription) {
        this.socBroadDescription = socBroadDescription;
    }

    public String getSocBroadURL() {
        return socBroadURL;
    }

    public void setSocBroadURL(String socBroadURL) {
        this.socBroadURL = socBroadURL;
    }

    public String getSocBroadPreviewImage() {
        return socBroadPreviewImage;
    }

    public void setSocBroadPreviewImage(String socBroadPreviewImage) {
        this.socBroadPreviewImage = socBroadPreviewImage;
    }

    public Long getSocMinorId() {
        return socMinorId;
    }

    public void setSocMinorId(Long socMinorId) {
        this.socMinorId = socMinorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SocBroadDTO socBroadDTO = (SocBroadDTO) o;
        if(socBroadDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), socBroadDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SocBroadDTO{" +
            "id=" + getId() +
            ", socBroadName='" + getSocBroadName() + "'" +
            ", socBroadCode='" + getSocBroadCode() + "'" +
            ", socBroadAvator='" + getSocBroadAvator() + "'" +
            ", socBroadDescription='" + getSocBroadDescription() + "'" +
            ", socBroadURL='" + getSocBroadURL() + "'" +
            ", socBroadPreviewImage='" + getSocBroadPreviewImage() + "'" +
            "}";
    }
}
