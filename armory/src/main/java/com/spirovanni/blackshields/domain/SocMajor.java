package com.spirovanni.blackshields.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A SocMajor.
 */
@Entity
@Table(name = "soc_major")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "socmajor")
public class SocMajor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "soc_major_name")
    private String socMajorName;

    @Column(name = "soc_major_code")
    private String socMajorCode;

    @Lob
    @Column(name = "soc_major_avator")
    private byte[] socMajorAvator;

    @Column(name = "soc_major_avator_content_type")
    private String socMajorAvatorContentType;

    @Column(name = "soc_major_description")
    private String socMajorDescription;

    @Column(name = "soc_major_url")
    private String socMajorURL;

    @Column(name = "soc_major_preview_image")
    private String socMajorPreviewImage;

    @OneToMany(mappedBy = "socMajor")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SocMinor> socminors = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSocMajorName() {
        return socMajorName;
    }

    public SocMajor socMajorName(String socMajorName) {
        this.socMajorName = socMajorName;
        return this;
    }

    public void setSocMajorName(String socMajorName) {
        this.socMajorName = socMajorName;
    }

    public String getSocMajorCode() {
        return socMajorCode;
    }

    public SocMajor socMajorCode(String socMajorCode) {
        this.socMajorCode = socMajorCode;
        return this;
    }

    public void setSocMajorCode(String socMajorCode) {
        this.socMajorCode = socMajorCode;
    }

    public byte[] getSocMajorAvator() {
        return socMajorAvator;
    }

    public SocMajor socMajorAvator(byte[] socMajorAvator) {
        this.socMajorAvator = socMajorAvator;
        return this;
    }

    public void setSocMajorAvator(byte[] socMajorAvator) {
        this.socMajorAvator = socMajorAvator;
    }

    public String getSocMajorAvatorContentType() {
        return socMajorAvatorContentType;
    }

    public SocMajor socMajorAvatorContentType(String socMajorAvatorContentType) {
        this.socMajorAvatorContentType = socMajorAvatorContentType;
        return this;
    }

    public void setSocMajorAvatorContentType(String socMajorAvatorContentType) {
        this.socMajorAvatorContentType = socMajorAvatorContentType;
    }

    public String getSocMajorDescription() {
        return socMajorDescription;
    }

    public SocMajor socMajorDescription(String socMajorDescription) {
        this.socMajorDescription = socMajorDescription;
        return this;
    }

    public void setSocMajorDescription(String socMajorDescription) {
        this.socMajorDescription = socMajorDescription;
    }

    public String getSocMajorURL() {
        return socMajorURL;
    }

    public SocMajor socMajorURL(String socMajorURL) {
        this.socMajorURL = socMajorURL;
        return this;
    }

    public void setSocMajorURL(String socMajorURL) {
        this.socMajorURL = socMajorURL;
    }

    public String getSocMajorPreviewImage() {
        return socMajorPreviewImage;
    }

    public SocMajor socMajorPreviewImage(String socMajorPreviewImage) {
        this.socMajorPreviewImage = socMajorPreviewImage;
        return this;
    }

    public void setSocMajorPreviewImage(String socMajorPreviewImage) {
        this.socMajorPreviewImage = socMajorPreviewImage;
    }

    public Set<SocMinor> getSocminors() {
        return socminors;
    }

    public SocMajor socminors(Set<SocMinor> socMinors) {
        this.socminors = socMinors;
        return this;
    }

    public SocMajor addSocminor(SocMinor socMinor) {
        this.socminors.add(socMinor);
        socMinor.setSocMajor(this);
        return this;
    }

    public SocMajor removeSocminor(SocMinor socMinor) {
        this.socminors.remove(socMinor);
        socMinor.setSocMajor(null);
        return this;
    }

    public void setSocminors(Set<SocMinor> socMinors) {
        this.socminors = socMinors;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SocMajor socMajor = (SocMajor) o;
        if (socMajor.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), socMajor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SocMajor{" +
            "id=" + getId() +
            ", socMajorName='" + getSocMajorName() + "'" +
            ", socMajorCode='" + getSocMajorCode() + "'" +
            ", socMajorAvator='" + getSocMajorAvator() + "'" +
            ", socMajorAvatorContentType='" + getSocMajorAvatorContentType() + "'" +
            ", socMajorDescription='" + getSocMajorDescription() + "'" +
            ", socMajorURL='" + getSocMajorURL() + "'" +
            ", socMajorPreviewImage='" + getSocMajorPreviewImage() + "'" +
            "}";
    }
}
