package com.spirovanni.blackshields.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;
import com.spirovanni.blackshields.domain.enumeration.Group;

/**
 * A DTO for the SocSpecific entity.
 */
public class SocSpecificDTO implements Serializable {

    private Long id;

    private String socSpecificName;

    private String socSpecificCode;

    @Lob
    private byte[] socSpecificAvator;
    private String socSpecificAvatorContentType;

    private String socSpecificDescription;

    private String socSpecificURL;

    private String socSpecificPreviewImage;

    private Group group;

    private Integer totEmp;

    private Long empPrse;

    private Long hourlyMean;

    private Long annualMean;

    private Long meanPrse;

    private Long hrPctHighest;

    private Long hrPctHigh;

    private Long hourlyMedian;

    private Long hrPctBelow;

    private Long hrPctLowest;

    private Long anPctHighest;

    private Long anPctHigh;

    private Long annualMedian;

    private Long anPctBelow;

    private Long anPctLowest;

    private Long socBroadId;

    private Long skillsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSocSpecificName() {
        return socSpecificName;
    }

    public void setSocSpecificName(String socSpecificName) {
        this.socSpecificName = socSpecificName;
    }

    public String getSocSpecificCode() {
        return socSpecificCode;
    }

    public void setSocSpecificCode(String socSpecificCode) {
        this.socSpecificCode = socSpecificCode;
    }

    public byte[] getSocSpecificAvator() {
        return socSpecificAvator;
    }

    public void setSocSpecificAvator(byte[] socSpecificAvator) {
        this.socSpecificAvator = socSpecificAvator;
    }

    public String getSocSpecificAvatorContentType() {
        return socSpecificAvatorContentType;
    }

    public void setSocSpecificAvatorContentType(String socSpecificAvatorContentType) {
        this.socSpecificAvatorContentType = socSpecificAvatorContentType;
    }

    public String getSocSpecificDescription() {
        return socSpecificDescription;
    }

    public void setSocSpecificDescription(String socSpecificDescription) {
        this.socSpecificDescription = socSpecificDescription;
    }

    public String getSocSpecificURL() {
        return socSpecificURL;
    }

    public void setSocSpecificURL(String socSpecificURL) {
        this.socSpecificURL = socSpecificURL;
    }

    public String getSocSpecificPreviewImage() {
        return socSpecificPreviewImage;
    }

    public void setSocSpecificPreviewImage(String socSpecificPreviewImage) {
        this.socSpecificPreviewImage = socSpecificPreviewImage;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Integer getTotEmp() {
        return totEmp;
    }

    public void setTotEmp(Integer totEmp) {
        this.totEmp = totEmp;
    }

    public Long getEmpPrse() {
        return empPrse;
    }

    public void setEmpPrse(Long empPrse) {
        this.empPrse = empPrse;
    }

    public Long getHourlyMean() {
        return hourlyMean;
    }

    public void setHourlyMean(Long hourlyMean) {
        this.hourlyMean = hourlyMean;
    }

    public Long getAnnualMean() {
        return annualMean;
    }

    public void setAnnualMean(Long annualMean) {
        this.annualMean = annualMean;
    }

    public Long getMeanPrse() {
        return meanPrse;
    }

    public void setMeanPrse(Long meanPrse) {
        this.meanPrse = meanPrse;
    }

    public Long getHrPctHighest() {
        return hrPctHighest;
    }

    public void setHrPctHighest(Long hrPctHighest) {
        this.hrPctHighest = hrPctHighest;
    }

    public Long getHrPctHigh() {
        return hrPctHigh;
    }

    public void setHrPctHigh(Long hrPctHigh) {
        this.hrPctHigh = hrPctHigh;
    }

    public Long getHourlyMedian() {
        return hourlyMedian;
    }

    public void setHourlyMedian(Long hourlyMedian) {
        this.hourlyMedian = hourlyMedian;
    }

    public Long getHrPctBelow() {
        return hrPctBelow;
    }

    public void setHrPctBelow(Long hrPctBelow) {
        this.hrPctBelow = hrPctBelow;
    }

    public Long getHrPctLowest() {
        return hrPctLowest;
    }

    public void setHrPctLowest(Long hrPctLowest) {
        this.hrPctLowest = hrPctLowest;
    }

    public Long getAnPctHighest() {
        return anPctHighest;
    }

    public void setAnPctHighest(Long anPctHighest) {
        this.anPctHighest = anPctHighest;
    }

    public Long getAnPctHigh() {
        return anPctHigh;
    }

    public void setAnPctHigh(Long anPctHigh) {
        this.anPctHigh = anPctHigh;
    }

    public Long getAnnualMedian() {
        return annualMedian;
    }

    public void setAnnualMedian(Long annualMedian) {
        this.annualMedian = annualMedian;
    }

    public Long getAnPctBelow() {
        return anPctBelow;
    }

    public void setAnPctBelow(Long anPctBelow) {
        this.anPctBelow = anPctBelow;
    }

    public Long getAnPctLowest() {
        return anPctLowest;
    }

    public void setAnPctLowest(Long anPctLowest) {
        this.anPctLowest = anPctLowest;
    }

    public Long getSocBroadId() {
        return socBroadId;
    }

    public void setSocBroadId(Long socBroadId) {
        this.socBroadId = socBroadId;
    }

    public Long getSkillsId() {
        return skillsId;
    }

    public void setSkillsId(Long skillsId) {
        this.skillsId = skillsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SocSpecificDTO socSpecificDTO = (SocSpecificDTO) o;
        if(socSpecificDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), socSpecificDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SocSpecificDTO{" +
            "id=" + getId() +
            ", socSpecificName='" + getSocSpecificName() + "'" +
            ", socSpecificCode='" + getSocSpecificCode() + "'" +
            ", socSpecificAvator='" + getSocSpecificAvator() + "'" +
            ", socSpecificDescription='" + getSocSpecificDescription() + "'" +
            ", socSpecificURL='" + getSocSpecificURL() + "'" +
            ", socSpecificPreviewImage='" + getSocSpecificPreviewImage() + "'" +
            ", group='" + getGroup() + "'" +
            ", totEmp=" + getTotEmp() +
            ", empPrse=" + getEmpPrse() +
            ", hourlyMean=" + getHourlyMean() +
            ", annualMean=" + getAnnualMean() +
            ", meanPrse=" + getMeanPrse() +
            ", hrPctHighest=" + getHrPctHighest() +
            ", hrPctHigh=" + getHrPctHigh() +
            ", hourlyMedian=" + getHourlyMedian() +
            ", hrPctBelow=" + getHrPctBelow() +
            ", hrPctLowest=" + getHrPctLowest() +
            ", anPctHighest=" + getAnPctHighest() +
            ", anPctHigh=" + getAnPctHigh() +
            ", annualMedian=" + getAnnualMedian() +
            ", anPctBelow=" + getAnPctBelow() +
            ", anPctLowest=" + getAnPctLowest() +
            "}";
    }
}
