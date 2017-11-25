package com.spirovanni.blackshields.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Skills entity.
 */
public class SkillsDTO implements Serializable {

    private Long id;

    private String socSpecificSkName;

    private String socSpecificSkCode;

    private Long anDaInLv;

    private Long anDaInIm;

    private Long asCaOthLv;

    private Long asCaOthIm;

    private Long coDevOthLv;

    private Long coDevOthIm;

    private Long coPerOutOrgLv;

    private Long coPerOutOrgIm;

    private Long coSupPeSubLv;

    private Long coSupPeSubIm;

    private Long conMaProLv;

    private Long conMaProIm;

    private Long cooWorActOthLv;

    private Long cooWorActOthIm;

    private Long devBuildTeamsLv;

    private Long devBuildTeamsIm;

    private Long devObjStratLv;

    private Long devObjStratIm;

    private Long docRecInfoLv;

    private Long docRecInfoIm;

    private Long drLayOutSpecLv;

    private Long drLayOutSpecIm;

    private Long estMaIntRelLv;

    private Long estMaIntRelIm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSocSpecificSkName() {
        return socSpecificSkName;
    }

    public void setSocSpecificSkName(String socSpecificSkName) {
        this.socSpecificSkName = socSpecificSkName;
    }

    public String getSocSpecificSkCode() {
        return socSpecificSkCode;
    }

    public void setSocSpecificSkCode(String socSpecificSkCode) {
        this.socSpecificSkCode = socSpecificSkCode;
    }

    public Long getAnDaInLv() {
        return anDaInLv;
    }

    public void setAnDaInLv(Long anDaInLv) {
        this.anDaInLv = anDaInLv;
    }

    public Long getAnDaInIm() {
        return anDaInIm;
    }

    public void setAnDaInIm(Long anDaInIm) {
        this.anDaInIm = anDaInIm;
    }

    public Long getAsCaOthLv() {
        return asCaOthLv;
    }

    public void setAsCaOthLv(Long asCaOthLv) {
        this.asCaOthLv = asCaOthLv;
    }

    public Long getAsCaOthIm() {
        return asCaOthIm;
    }

    public void setAsCaOthIm(Long asCaOthIm) {
        this.asCaOthIm = asCaOthIm;
    }

    public Long getCoDevOthLv() {
        return coDevOthLv;
    }

    public void setCoDevOthLv(Long coDevOthLv) {
        this.coDevOthLv = coDevOthLv;
    }

    public Long getCoDevOthIm() {
        return coDevOthIm;
    }

    public void setCoDevOthIm(Long coDevOthIm) {
        this.coDevOthIm = coDevOthIm;
    }

    public Long getCoPerOutOrgLv() {
        return coPerOutOrgLv;
    }

    public void setCoPerOutOrgLv(Long coPerOutOrgLv) {
        this.coPerOutOrgLv = coPerOutOrgLv;
    }

    public Long getCoPerOutOrgIm() {
        return coPerOutOrgIm;
    }

    public void setCoPerOutOrgIm(Long coPerOutOrgIm) {
        this.coPerOutOrgIm = coPerOutOrgIm;
    }

    public Long getCoSupPeSubLv() {
        return coSupPeSubLv;
    }

    public void setCoSupPeSubLv(Long coSupPeSubLv) {
        this.coSupPeSubLv = coSupPeSubLv;
    }

    public Long getCoSupPeSubIm() {
        return coSupPeSubIm;
    }

    public void setCoSupPeSubIm(Long coSupPeSubIm) {
        this.coSupPeSubIm = coSupPeSubIm;
    }

    public Long getConMaProLv() {
        return conMaProLv;
    }

    public void setConMaProLv(Long conMaProLv) {
        this.conMaProLv = conMaProLv;
    }

    public Long getConMaProIm() {
        return conMaProIm;
    }

    public void setConMaProIm(Long conMaProIm) {
        this.conMaProIm = conMaProIm;
    }

    public Long getCooWorActOthLv() {
        return cooWorActOthLv;
    }

    public void setCooWorActOthLv(Long cooWorActOthLv) {
        this.cooWorActOthLv = cooWorActOthLv;
    }

    public Long getCooWorActOthIm() {
        return cooWorActOthIm;
    }

    public void setCooWorActOthIm(Long cooWorActOthIm) {
        this.cooWorActOthIm = cooWorActOthIm;
    }

    public Long getDevBuildTeamsLv() {
        return devBuildTeamsLv;
    }

    public void setDevBuildTeamsLv(Long devBuildTeamsLv) {
        this.devBuildTeamsLv = devBuildTeamsLv;
    }

    public Long getDevBuildTeamsIm() {
        return devBuildTeamsIm;
    }

    public void setDevBuildTeamsIm(Long devBuildTeamsIm) {
        this.devBuildTeamsIm = devBuildTeamsIm;
    }

    public Long getDevObjStratLv() {
        return devObjStratLv;
    }

    public void setDevObjStratLv(Long devObjStratLv) {
        this.devObjStratLv = devObjStratLv;
    }

    public Long getDevObjStratIm() {
        return devObjStratIm;
    }

    public void setDevObjStratIm(Long devObjStratIm) {
        this.devObjStratIm = devObjStratIm;
    }

    public Long getDocRecInfoLv() {
        return docRecInfoLv;
    }

    public void setDocRecInfoLv(Long docRecInfoLv) {
        this.docRecInfoLv = docRecInfoLv;
    }

    public Long getDocRecInfoIm() {
        return docRecInfoIm;
    }

    public void setDocRecInfoIm(Long docRecInfoIm) {
        this.docRecInfoIm = docRecInfoIm;
    }

    public Long getDrLayOutSpecLv() {
        return drLayOutSpecLv;
    }

    public void setDrLayOutSpecLv(Long drLayOutSpecLv) {
        this.drLayOutSpecLv = drLayOutSpecLv;
    }

    public Long getDrLayOutSpecIm() {
        return drLayOutSpecIm;
    }

    public void setDrLayOutSpecIm(Long drLayOutSpecIm) {
        this.drLayOutSpecIm = drLayOutSpecIm;
    }

    public Long getEstMaIntRelLv() {
        return estMaIntRelLv;
    }

    public void setEstMaIntRelLv(Long estMaIntRelLv) {
        this.estMaIntRelLv = estMaIntRelLv;
    }

    public Long getEstMaIntRelIm() {
        return estMaIntRelIm;
    }

    public void setEstMaIntRelIm(Long estMaIntRelIm) {
        this.estMaIntRelIm = estMaIntRelIm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SkillsDTO skillsDTO = (SkillsDTO) o;
        if(skillsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), skillsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SkillsDTO{" +
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
