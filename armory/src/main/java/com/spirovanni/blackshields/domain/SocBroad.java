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
 * A SocBroad.
 */
@Entity
@Table(name = "soc_broad")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "socbroad")
public class SocBroad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "soc_broad_name")
    private String socBroadName;

    @Column(name = "soc_broad_code")
    private String socBroadCode;

    @Lob
    @Column(name = "soc_broad_avator")
    private byte[] socBroadAvator;

    @Column(name = "soc_broad_avator_content_type")
    private String socBroadAvatorContentType;

    @Column(name = "soc_broad_description")
    private String socBroadDescription;

    @Column(name = "soc_broad_url")
    private String socBroadURL;

    @Column(name = "soc_broad_preview_image")
    private String socBroadPreviewImage;

    @ManyToOne
    private SocMinor socMinor;

    @OneToMany(mappedBy = "socBroad")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SocSpecific> socspecifics = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSocBroadName() {
        return socBroadName;
    }

    public SocBroad socBroadName(String socBroadName) {
        this.socBroadName = socBroadName;
        return this;
    }

    public void setSocBroadName(String socBroadName) {
        this.socBroadName = socBroadName;
    }

    public String getSocBroadCode() {
        return socBroadCode;
    }

    public SocBroad socBroadCode(String socBroadCode) {
        this.socBroadCode = socBroadCode;
        return this;
    }

    public void setSocBroadCode(String socBroadCode) {
        this.socBroadCode = socBroadCode;
    }

    public byte[] getSocBroadAvator() {
        return socBroadAvator;
    }

    public SocBroad socBroadAvator(byte[] socBroadAvator) {
        this.socBroadAvator = socBroadAvator;
        return this;
    }

    public void setSocBroadAvator(byte[] socBroadAvator) {
        this.socBroadAvator = socBroadAvator;
    }

    public String getSocBroadAvatorContentType() {
        return socBroadAvatorContentType;
    }

    public SocBroad socBroadAvatorContentType(String socBroadAvatorContentType) {
        this.socBroadAvatorContentType = socBroadAvatorContentType;
        return this;
    }

    public void setSocBroadAvatorContentType(String socBroadAvatorContentType) {
        this.socBroadAvatorContentType = socBroadAvatorContentType;
    }

    public String getSocBroadDescription() {
        return socBroadDescription;
    }

    public SocBroad socBroadDescription(String socBroadDescription) {
        this.socBroadDescription = socBroadDescription;
        return this;
    }

    public void setSocBroadDescription(String socBroadDescription) {
        this.socBroadDescription = socBroadDescription;
    }

    public String getSocBroadURL() {
        return socBroadURL;
    }

    public SocBroad socBroadURL(String socBroadURL) {
        this.socBroadURL = socBroadURL;
        return this;
    }

    public void setSocBroadURL(String socBroadURL) {
        this.socBroadURL = socBroadURL;
    }

    public String getSocBroadPreviewImage() {
        return socBroadPreviewImage;
    }

    public SocBroad socBroadPreviewImage(String socBroadPreviewImage) {
        this.socBroadPreviewImage = socBroadPreviewImage;
        return this;
    }

    public void setSocBroadPreviewImage(String socBroadPreviewImage) {
        this.socBroadPreviewImage = socBroadPreviewImage;
    }

    public SocMinor getSocMinor() {
        return socMinor;
    }

    public SocBroad socMinor(SocMinor socMinor) {
        this.socMinor = socMinor;
        return this;
    }

    public void setSocMinor(SocMinor socMinor) {
        this.socMinor = socMinor;
    }

    public Set<SocSpecific> getSocspecifics() {
        return socspecifics;
    }

    public SocBroad socspecifics(Set<SocSpecific> socSpecifics) {
        this.socspecifics = socSpecifics;
        return this;
    }

    public SocBroad addSocspecific(SocSpecific socSpecific) {
        this.socspecifics.add(socSpecific);
        socSpecific.setSocBroad(this);
        return this;
    }

    public SocBroad removeSocspecific(SocSpecific socSpecific) {
        this.socspecifics.remove(socSpecific);
        socSpecific.setSocBroad(null);
        return this;
    }

    public void setSocspecifics(Set<SocSpecific> socSpecifics) {
        this.socspecifics = socSpecifics;
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
        SocBroad socBroad = (SocBroad) o;
        if (socBroad.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), socBroad.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SocBroad{" +
            "id=" + getId() +
            ", socBroadName='" + getSocBroadName() + "'" +
            ", socBroadCode='" + getSocBroadCode() + "'" +
            ", socBroadAvator='" + getSocBroadAvator() + "'" +
            ", socBroadAvatorContentType='" + getSocBroadAvatorContentType() + "'" +
            ", socBroadDescription='" + getSocBroadDescription() + "'" +
            ", socBroadURL='" + getSocBroadURL() + "'" +
            ", socBroadPreviewImage='" + getSocBroadPreviewImage() + "'" +
            "}";
    }
}
