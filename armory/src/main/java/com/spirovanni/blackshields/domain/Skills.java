package com.spirovanni.blackshields.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * A Skills.
 */
@Entity
@Table(name = "skills")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "skills")
public class Skills implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "soc_specific_sk_name")
    private String socSpecificSkName;

    @Column(name = "soc_specific_sk_code")
    private String socSpecificSkCode;

    @Column(name = "an_da_in_lv")
    private Long anDaInLv;

    @Column(name = "an_da_in_im")
    private Long anDaInIm;

    @Column(name = "as_ca_oth_lv")
    private Long asCaOthLv;

    @Column(name = "as_ca_oth_im")
    private Long asCaOthIm;

    @Column(name = "co_dev_oth_lv")
    private Long coDevOthLv;

    @Column(name = "co_dev_oth_im")
    private Long coDevOthIm;

    @Column(name = "co_per_out_org_lv")
    private Long coPerOutOrgLv;

    @Column(name = "co_per_out_org_im")
    private Long coPerOutOrgIm;

    @Column(name = "co_sup_pe_sub_lv")
    private Long coSupPeSubLv;

    @Column(name = "co_sup_pe_sub_im")
    private Long coSupPeSubIm;

    @Column(name = "con_ma_pro_lv")
    private Long conMaProLv;

    @Column(name = "con_ma_pro_im")
    private Long conMaProIm;

    @Column(name = "coo_wor_act_oth_lv")
    private Long cooWorActOthLv;

    @Column(name = "coo_wor_act_oth_im")
    private Long cooWorActOthIm;

    @Column(name = "dev_build_teams_lv")
    private Long devBuildTeamsLv;

    @Column(name = "dev_build_teams_im")
    private Long devBuildTeamsIm;

    @Column(name = "dev_obj_strat_lv")
    private Long devObjStratLv;

    @Column(name = "dev_obj_strat_im")
    private Long devObjStratIm;

    @Column(name = "doc_rec_info_lv")
    private Long docRecInfoLv;

    @Column(name = "doc_rec_info_im")
    private Long docRecInfoIm;

    @Column(name = "dr_lay_out_spec_lv")
    private Long drLayOutSpecLv;

    @Column(name = "dr_lay_out_spec_im")
    private Long drLayOutSpecIm;

    @Column(name = "est_ma_int_rel_lv")
    private Long estMaIntRelLv;

    @Column(name = "est_ma_int_rel_im")
    private Long estMaIntRelIm;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSocSpecificSkName() {
        return socSpecificSkName;
    }

    public Skills socSpecificSkName(String socSpecificSkName) {
        this.socSpecificSkName = socSpecificSkName;
        return this;
    }

    public void setSocSpecificSkName(String socSpecificSkName) {
        this.socSpecificSkName = socSpecificSkName;
    }

    public String getSocSpecificSkCode() {
        return socSpecificSkCode;
    }

    public Skills socSpecificSkCode(String socSpecificSkCode) {
        this.socSpecificSkCode = socSpecificSkCode;
        return this;
    }

    public void setSocSpecificSkCode(String socSpecificSkCode) {
        this.socSpecificSkCode = socSpecificSkCode;
    }

    public Long getAnDaInLv() {
        return anDaInLv;
    }

    public Skills anDaInLv(Long anDaInLv) {
        this.anDaInLv = anDaInLv;
        return this;
    }

    public void setAnDaInLv(Long anDaInLv) {
        this.anDaInLv = anDaInLv;
    }

    public Long getAnDaInIm() {
        return anDaInIm;
    }

    public Skills anDaInIm(Long anDaInIm) {
        this.anDaInIm = anDaInIm;
        return this;
    }

    public void setAnDaInIm(Long anDaInIm) {
        this.anDaInIm = anDaInIm;
    }

    public Long getAsCaOthLv() {
        return asCaOthLv;
    }

    public Skills asCaOthLv(Long asCaOthLv) {
        this.asCaOthLv = asCaOthLv;
        return this;
    }

    public void setAsCaOthLv(Long asCaOthLv) {
        this.asCaOthLv = asCaOthLv;
    }

    public Long getAsCaOthIm() {
        return asCaOthIm;
    }

    public Skills asCaOthIm(Long asCaOthIm) {
        this.asCaOthIm = asCaOthIm;
        return this;
    }

    public void setAsCaOthIm(Long asCaOthIm) {
        this.asCaOthIm = asCaOthIm;
    }

    public Long getCoDevOthLv() {
        return coDevOthLv;
    }

    public Skills coDevOthLv(Long coDevOthLv) {
        this.coDevOthLv = coDevOthLv;
        return this;
    }

    public void setCoDevOthLv(Long coDevOthLv) {
        this.coDevOthLv = coDevOthLv;
    }

    public Long getCoDevOthIm() {
        return coDevOthIm;
    }

    public Skills coDevOthIm(Long coDevOthIm) {
        this.coDevOthIm = coDevOthIm;
        return this;
    }

    public void setCoDevOthIm(Long coDevOthIm) {
        this.coDevOthIm = coDevOthIm;
    }

    public Long getCoPerOutOrgLv() {
        return coPerOutOrgLv;
    }

    public Skills coPerOutOrgLv(Long coPerOutOrgLv) {
        this.coPerOutOrgLv = coPerOutOrgLv;
        return this;
    }

    public void setCoPerOutOrgLv(Long coPerOutOrgLv) {
        this.coPerOutOrgLv = coPerOutOrgLv;
    }

    public Long getCoPerOutOrgIm() {
        return coPerOutOrgIm;
    }

    public Skills coPerOutOrgIm(Long coPerOutOrgIm) {
        this.coPerOutOrgIm = coPerOutOrgIm;
        return this;
    }

    public void setCoPerOutOrgIm(Long coPerOutOrgIm) {
        this.coPerOutOrgIm = coPerOutOrgIm;
    }

    public Long getCoSupPeSubLv() {
        return coSupPeSubLv;
    }

    public Skills coSupPeSubLv(Long coSupPeSubLv) {
        this.coSupPeSubLv = coSupPeSubLv;
        return this;
    }

    public void setCoSupPeSubLv(Long coSupPeSubLv) {
        this.coSupPeSubLv = coSupPeSubLv;
    }

    public Long getCoSupPeSubIm() {
        return coSupPeSubIm;
    }

    public Skills coSupPeSubIm(Long coSupPeSubIm) {
        this.coSupPeSubIm = coSupPeSubIm;
        return this;
    }

    public void setCoSupPeSubIm(Long coSupPeSubIm) {
        this.coSupPeSubIm = coSupPeSubIm;
    }

    public Long getConMaProLv() {
        return conMaProLv;
    }

    public Skills conMaProLv(Long conMaProLv) {
        this.conMaProLv = conMaProLv;
        return this;
    }

    public void setConMaProLv(Long conMaProLv) {
        this.conMaProLv = conMaProLv;
    }

    public Long getConMaProIm() {
        return conMaProIm;
    }

    public Skills conMaProIm(Long conMaProIm) {
        this.conMaProIm = conMaProIm;
        return this;
    }

    public void setConMaProIm(Long conMaProIm) {
        this.conMaProIm = conMaProIm;
    }

    public Long getCooWorActOthLv() {
        return cooWorActOthLv;
    }

    public Skills cooWorActOthLv(Long cooWorActOthLv) {
        this.cooWorActOthLv = cooWorActOthLv;
        return this;
    }

    public void setCooWorActOthLv(Long cooWorActOthLv) {
        this.cooWorActOthLv = cooWorActOthLv;
    }

    public Long getCooWorActOthIm() {
        return cooWorActOthIm;
    }

    public Skills cooWorActOthIm(Long cooWorActOthIm) {
        this.cooWorActOthIm = cooWorActOthIm;
        return this;
    }

    public void setCooWorActOthIm(Long cooWorActOthIm) {
        this.cooWorActOthIm = cooWorActOthIm;
    }

    public Long getDevBuildTeamsLv() {
        return devBuildTeamsLv;
    }

    public Skills devBuildTeamsLv(Long devBuildTeamsLv) {
        this.devBuildTeamsLv = devBuildTeamsLv;
        return this;
    }

    public void setDevBuildTeamsLv(Long devBuildTeamsLv) {
        this.devBuildTeamsLv = devBuildTeamsLv;
    }

    public Long getDevBuildTeamsIm() {
        return devBuildTeamsIm;
    }

    public Skills devBuildTeamsIm(Long devBuildTeamsIm) {
        this.devBuildTeamsIm = devBuildTeamsIm;
        return this;
    }

    public void setDevBuildTeamsIm(Long devBuildTeamsIm) {
        this.devBuildTeamsIm = devBuildTeamsIm;
    }

    public Long getDevObjStratLv() {
        return devObjStratLv;
    }

    public Skills devObjStratLv(Long devObjStratLv) {
        this.devObjStratLv = devObjStratLv;
        return this;
    }

    public void setDevObjStratLv(Long devObjStratLv) {
        this.devObjStratLv = devObjStratLv;
    }

    public Long getDevObjStratIm() {
        return devObjStratIm;
    }

    public Skills devObjStratIm(Long devObjStratIm) {
        this.devObjStratIm = devObjStratIm;
        return this;
    }

    public void setDevObjStratIm(Long devObjStratIm) {
        this.devObjStratIm = devObjStratIm;
    }

    public Long getDocRecInfoLv() {
        return docRecInfoLv;
    }

    public Skills docRecInfoLv(Long docRecInfoLv) {
        this.docRecInfoLv = docRecInfoLv;
        return this;
    }

    public void setDocRecInfoLv(Long docRecInfoLv) {
        this.docRecInfoLv = docRecInfoLv;
    }

    public Long getDocRecInfoIm() {
        return docRecInfoIm;
    }

    public Skills docRecInfoIm(Long docRecInfoIm) {
        this.docRecInfoIm = docRecInfoIm;
        return this;
    }

    public void setDocRecInfoIm(Long docRecInfoIm) {
        this.docRecInfoIm = docRecInfoIm;
    }

    public Long getDrLayOutSpecLv() {
        return drLayOutSpecLv;
    }

    public Skills drLayOutSpecLv(Long drLayOutSpecLv) {
        this.drLayOutSpecLv = drLayOutSpecLv;
        return this;
    }

    public void setDrLayOutSpecLv(Long drLayOutSpecLv) {
        this.drLayOutSpecLv = drLayOutSpecLv;
    }

    public Long getDrLayOutSpecIm() {
        return drLayOutSpecIm;
    }

    public Skills drLayOutSpecIm(Long drLayOutSpecIm) {
        this.drLayOutSpecIm = drLayOutSpecIm;
        return this;
    }

    public void setDrLayOutSpecIm(Long drLayOutSpecIm) {
        this.drLayOutSpecIm = drLayOutSpecIm;
    }

    public Long getEstMaIntRelLv() {
        return estMaIntRelLv;
    }

    public Skills estMaIntRelLv(Long estMaIntRelLv) {
        this.estMaIntRelLv = estMaIntRelLv;
        return this;
    }

    public void setEstMaIntRelLv(Long estMaIntRelLv) {
        this.estMaIntRelLv = estMaIntRelLv;
    }

    public Long getEstMaIntRelIm() {
        return estMaIntRelIm;
    }

    public Skills estMaIntRelIm(Long estMaIntRelIm) {
        this.estMaIntRelIm = estMaIntRelIm;
        return this;
    }

    public void setEstMaIntRelIm(Long estMaIntRelIm) {
        this.estMaIntRelIm = estMaIntRelIm;
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
        Skills skills = (Skills) o;
        if (skills.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), skills.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Skills{" +
            "id=" + getId() +
            ", socSpecificSkName='" + getSocSpecificSkName() + "'" +
            ", socSpecificSkCode='" + getSocSpecificSkCode() + "'" +
            ", anDaInLv=" + getAnDaInLv() +
            ", anDaInIm=" + getAnDaInIm() +
            ", asCaOthLv=" + getAsCaOthLv() +
            ", asCaOthIm=" + getAsCaOthIm() +
            ", coDevOthLv=" + getCoDevOthLv() +
            ", coDevOthIm=" + getCoDevOthIm() +
            ", coPerOutOrgLv=" + getCoPerOutOrgLv() +
            ", coPerOutOrgIm=" + getCoPerOutOrgIm() +
            ", coSupPeSubLv=" + getCoSupPeSubLv() +
            ", coSupPeSubIm=" + getCoSupPeSubIm() +
            ", conMaProLv=" + getConMaProLv() +
            ", conMaProIm=" + getConMaProIm() +
            ", cooWorActOthLv=" + getCooWorActOthLv() +
            ", cooWorActOthIm=" + getCooWorActOthIm() +
            ", devBuildTeamsLv=" + getDevBuildTeamsLv() +
            ", devBuildTeamsIm=" + getDevBuildTeamsIm() +
            ", devObjStratLv=" + getDevObjStratLv() +
            ", devObjStratIm=" + getDevObjStratIm() +
            ", docRecInfoLv=" + getDocRecInfoLv() +
            ", docRecInfoIm=" + getDocRecInfoIm() +
            ", drLayOutSpecLv=" + getDrLayOutSpecLv() +
            ", drLayOutSpecIm=" + getDrLayOutSpecIm() +
            ", estMaIntRelLv=" + getEstMaIntRelLv() +
            ", estMaIntRelIm=" + getEstMaIntRelIm() +
            "}";
    }
}
