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
 * A SocMinor.
 */
@Entity
@Table(name = "soc_minor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "socminor")
public class SocMinor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "soc_minor_name")
    private String socMinorName;

    @Column(name = "soc_minor_code")
    private String socMinorCode;

    @Lob
    @Column(name = "soc_minor_avator")
    private byte[] socMinorAvator;

    @Column(name = "soc_minor_avator_content_type")
    private String socMinorAvatorContentType;

    @Column(name = "soc_minor_description")
    private String socMinorDescription;

    @Column(name = "soc_minor_url")
    private String socMinorURL;

    @Column(name = "soc_minor_preview_image")
    private String socMinorPreviewImage;

    @ManyToOne
    private SocMajor socMajor;

    @OneToMany(mappedBy = "socMinor")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SocBroad> socbroads = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSocMinorName() {
        return socMinorName;
    }

    public SocMinor socMinorName(String socMinorName) {
        this.socMinorName = socMinorName;
        return this;
    }

    public void setSocMinorName(String socMinorName) {
        this.socMinorName = socMinorName;
    }

    public String getSocMinorCode() {
        return socMinorCode;
    }

    public SocMinor socMinorCode(String socMinorCode) {
        this.socMinorCode = socMinorCode;
        return this;
    }

    public void setSocMinorCode(String socMinorCode) {
        this.socMinorCode = socMinorCode;
    }

    public byte[] getSocMinorAvator() {
        return socMinorAvator;
    }

    public SocMinor socMinorAvator(byte[] socMinorAvator) {
        this.socMinorAvator = socMinorAvator;
        return this;
    }

    public void setSocMinorAvator(byte[] socMinorAvator) {
        this.socMinorAvator = socMinorAvator;
    }

    public String getSocMinorAvatorContentType() {
        return socMinorAvatorContentType;
    }

    public SocMinor socMinorAvatorContentType(String socMinorAvatorContentType) {
        this.socMinorAvatorContentType = socMinorAvatorContentType;
        return this;
    }

    public void setSocMinorAvatorContentType(String socMinorAvatorContentType) {
        this.socMinorAvatorContentType = socMinorAvatorContentType;
    }

    public String getSocMinorDescription() {
        return socMinorDescription;
    }

    public SocMinor socMinorDescription(String socMinorDescription) {
        this.socMinorDescription = socMinorDescription;
        return this;
    }

    public void setSocMinorDescription(String socMinorDescription) {
        this.socMinorDescription = socMinorDescription;
    }

    public String getSocMinorURL() {
        return socMinorURL;
    }

    public SocMinor socMinorURL(String socMinorURL) {
        this.socMinorURL = socMinorURL;
        return this;
    }

    public void setSocMinorURL(String socMinorURL) {
        this.socMinorURL = socMinorURL;
    }

    public String getSocMinorPreviewImage() {
        return socMinorPreviewImage;
    }

    public SocMinor socMinorPreviewImage(String socMinorPreviewImage) {
        this.socMinorPreviewImage = socMinorPreviewImage;
        return this;
    }

    public void setSocMinorPreviewImage(String socMinorPreviewImage) {
        this.socMinorPreviewImage = socMinorPreviewImage;
    }

    public SocMajor getSocMajor() {
        return socMajor;
    }

    public SocMinor socMajor(SocMajor socMajor) {
        this.socMajor = socMajor;
        return this;
    }

    public void setSocMajor(SocMajor socMajor) {
        this.socMajor = socMajor;
    }

    public Set<SocBroad> getSocbroads() {
        return socbroads;
    }

    public SocMinor socbroads(Set<SocBroad> socBroads) {
        this.socbroads = socBroads;
        return this;
    }

    public SocMinor addSocbroad(SocBroad socBroad) {
        this.socbroads.add(socBroad);
        socBroad.setSocMinor(this);
        return this;
    }

    public SocMinor removeSocbroad(SocBroad socBroad) {
        this.socbroads.remove(socBroad);
        socBroad.setSocMinor(null);
        return this;
    }

    public void setSocbroads(Set<SocBroad> socBroads) {
        this.socbroads = socBroads;
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
        SocMinor socMinor = (SocMinor) o;
        if (socMinor.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), socMinor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SocMinor{" +
            "id=" + getId() +
            ", socMinorName='" + getSocMinorName() + "'" +
            ", socMinorCode='" + getSocMinorCode() + "'" +
            ", socMinorAvator='" + getSocMinorAvator() + "'" +
            ", socMinorAvatorContentType='" + getSocMinorAvatorContentType() + "'" +
            ", socMinorDescription='" + getSocMinorDescription() + "'" +
            ", socMinorURL='" + getSocMinorURL() + "'" +
            ", socMinorPreviewImage='" + getSocMinorPreviewImage() + "'" +
            "}";
    }
}
