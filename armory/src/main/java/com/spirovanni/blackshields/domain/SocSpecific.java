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

import com.spirovanni.blackshields.domain.enumeration.Group;


/**
 * A SocSpecific.
 */
@Entity
@Table(name = "soc_specific")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "socspecific")
public class SocSpecific implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "soc_specific_name")
    private String socSpecificName;

    @Column(name = "soc_specific_code")
    private String socSpecificCode;

    @Lob
    @Column(name = "soc_specific_avator")
    private byte[] socSpecificAvator;

    @Column(name = "soc_specific_avator_content_type")
    private String socSpecificAvatorContentType;

    @Column(name = "soc_specific_description")
    private String socSpecificDescription;

    @Column(name = "soc_specific_url")
    private String socSpecificURL;

    @Column(name = "soc_specific_preview_image")
    private String socSpecificPreviewImage;

    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_group")
    private Group group;

    @Column(name = "tot_emp")
    private Integer totEmp;

    @Column(name = "emp_prse")
    private Long empPrse;

    @Column(name = "hourly_mean")
    private Long hourlyMean;

    @Column(name = "annual_mean")
    private Long annualMean;

    @Column(name = "mean_prse")
    private Long meanPrse;

    @Column(name = "hr_pct_highest")
    private Long hrPctHighest;

    @Column(name = "hr_pct_high")
    private Long hrPctHigh;

    @Column(name = "hourly_median")
    private Long hourlyMedian;

    @Column(name = "hr_pct_below")
    private Long hrPctBelow;

    @Column(name = "hr_pct_lowest")
    private Long hrPctLowest;

    @Column(name = "an_pct_highest")
    private Long anPctHighest;

    @Column(name = "an_pct_high")
    private Long anPctHigh;

    @Column(name = "annual_median")
    private Long annualMedian;

    @Column(name = "an_pct_below")
    private Long anPctBelow;

    @Column(name = "an_pct_lowest")
    private Long anPctLowest;

    @ManyToOne
    private SocBroad socBroad;

    @OneToOne
    @JoinColumn(unique = true)
    private Skills skills;

    @OneToMany(mappedBy = "socSpecific")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Employee> employees = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSocSpecificName() {
        return socSpecificName;
    }

    public SocSpecific socSpecificName(String socSpecificName) {
        this.socSpecificName = socSpecificName;
        return this;
    }

    public void setSocSpecificName(String socSpecificName) {
        this.socSpecificName = socSpecificName;
    }

    public String getSocSpecificCode() {
        return socSpecificCode;
    }

    public SocSpecific socSpecificCode(String socSpecificCode) {
        this.socSpecificCode = socSpecificCode;
        return this;
    }

    public void setSocSpecificCode(String socSpecificCode) {
        this.socSpecificCode = socSpecificCode;
    }

    public byte[] getSocSpecificAvator() {
        return socSpecificAvator;
    }

    public SocSpecific socSpecificAvator(byte[] socSpecificAvator) {
        this.socSpecificAvator = socSpecificAvator;
        return this;
    }

    public void setSocSpecificAvator(byte[] socSpecificAvator) {
        this.socSpecificAvator = socSpecificAvator;
    }

    public String getSocSpecificAvatorContentType() {
        return socSpecificAvatorContentType;
    }

    public SocSpecific socSpecificAvatorContentType(String socSpecificAvatorContentType) {
        this.socSpecificAvatorContentType = socSpecificAvatorContentType;
        return this;
    }

    public void setSocSpecificAvatorContentType(String socSpecificAvatorContentType) {
        this.socSpecificAvatorContentType = socSpecificAvatorContentType;
    }

    public String getSocSpecificDescription() {
        return socSpecificDescription;
    }

    public SocSpecific socSpecificDescription(String socSpecificDescription) {
        this.socSpecificDescription = socSpecificDescription;
        return this;
    }

    public void setSocSpecificDescription(String socSpecificDescription) {
        this.socSpecificDescription = socSpecificDescription;
    }

    public String getSocSpecificURL() {
        return socSpecificURL;
    }

    public SocSpecific socSpecificURL(String socSpecificURL) {
        this.socSpecificURL = socSpecificURL;
        return this;
    }

    public void setSocSpecificURL(String socSpecificURL) {
        this.socSpecificURL = socSpecificURL;
    }

    public String getSocSpecificPreviewImage() {
        return socSpecificPreviewImage;
    }

    public SocSpecific socSpecificPreviewImage(String socSpecificPreviewImage) {
        this.socSpecificPreviewImage = socSpecificPreviewImage;
        return this;
    }

    public void setSocSpecificPreviewImage(String socSpecificPreviewImage) {
        this.socSpecificPreviewImage = socSpecificPreviewImage;
    }

    public Group getGroup() {
        return group;
    }

    public SocSpecific group(Group group) {
        this.group = group;
        return this;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Integer getTotEmp() {
        return totEmp;
    }

    public SocSpecific totEmp(Integer totEmp) {
        this.totEmp = totEmp;
        return this;
    }

    public void setTotEmp(Integer totEmp) {
        this.totEmp = totEmp;
    }

    public Long getEmpPrse() {
        return empPrse;
    }

    public SocSpecific empPrse(Long empPrse) {
        this.empPrse = empPrse;
        return this;
    }

    public void setEmpPrse(Long empPrse) {
        this.empPrse = empPrse;
    }

    public Long getHourlyMean() {
        return hourlyMean;
    }

    public SocSpecific hourlyMean(Long hourlyMean) {
        this.hourlyMean = hourlyMean;
        return this;
    }

    public void setHourlyMean(Long hourlyMean) {
        this.hourlyMean = hourlyMean;
    }

    public Long getAnnualMean() {
        return annualMean;
    }

    public SocSpecific annualMean(Long annualMean) {
        this.annualMean = annualMean;
        return this;
    }

    public void setAnnualMean(Long annualMean) {
        this.annualMean = annualMean;
    }

    public Long getMeanPrse() {
        return meanPrse;
    }

    public SocSpecific meanPrse(Long meanPrse) {
        this.meanPrse = meanPrse;
        return this;
    }

    public void setMeanPrse(Long meanPrse) {
        this.meanPrse = meanPrse;
    }

    public Long getHrPctHighest() {
        return hrPctHighest;
    }

    public SocSpecific hrPctHighest(Long hrPctHighest) {
        this.hrPctHighest = hrPctHighest;
        return this;
    }

    public void setHrPctHighest(Long hrPctHighest) {
        this.hrPctHighest = hrPctHighest;
    }

    public Long getHrPctHigh() {
        return hrPctHigh;
    }

    public SocSpecific hrPctHigh(Long hrPctHigh) {
        this.hrPctHigh = hrPctHigh;
        return this;
    }

    public void setHrPctHigh(Long hrPctHigh) {
        this.hrPctHigh = hrPctHigh;
    }

    public Long getHourlyMedian() {
        return hourlyMedian;
    }

    public SocSpecific hourlyMedian(Long hourlyMedian) {
        this.hourlyMedian = hourlyMedian;
        return this;
    }

    public void setHourlyMedian(Long hourlyMedian) {
        this.hourlyMedian = hourlyMedian;
    }

    public Long getHrPctBelow() {
        return hrPctBelow;
    }

    public SocSpecific hrPctBelow(Long hrPctBelow) {
        this.hrPctBelow = hrPctBelow;
        return this;
    }

    public void setHrPctBelow(Long hrPctBelow) {
        this.hrPctBelow = hrPctBelow;
    }

    public Long getHrPctLowest() {
        return hrPctLowest;
    }

    public SocSpecific hrPctLowest(Long hrPctLowest) {
        this.hrPctLowest = hrPctLowest;
        return this;
    }

    public void setHrPctLowest(Long hrPctLowest) {
        this.hrPctLowest = hrPctLowest;
    }

    public Long getAnPctHighest() {
        return anPctHighest;
    }

    public SocSpecific anPctHighest(Long anPctHighest) {
        this.anPctHighest = anPctHighest;
        return this;
    }

    public void setAnPctHighest(Long anPctHighest) {
        this.anPctHighest = anPctHighest;
    }

    public Long getAnPctHigh() {
        return anPctHigh;
    }

    public SocSpecific anPctHigh(Long anPctHigh) {
        this.anPctHigh = anPctHigh;
        return this;
    }

    public void setAnPctHigh(Long anPctHigh) {
        this.anPctHigh = anPctHigh;
    }

    public Long getAnnualMedian() {
        return annualMedian;
    }

    public SocSpecific annualMedian(Long annualMedian) {
        this.annualMedian = annualMedian;
        return this;
    }

    public void setAnnualMedian(Long annualMedian) {
        this.annualMedian = annualMedian;
    }

    public Long getAnPctBelow() {
        return anPctBelow;
    }

    public SocSpecific anPctBelow(Long anPctBelow) {
        this.anPctBelow = anPctBelow;
        return this;
    }

    public void setAnPctBelow(Long anPctBelow) {
        this.anPctBelow = anPctBelow;
    }

    public Long getAnPctLowest() {
        return anPctLowest;
    }

    public SocSpecific anPctLowest(Long anPctLowest) {
        this.anPctLowest = anPctLowest;
        return this;
    }

    public void setAnPctLowest(Long anPctLowest) {
        this.anPctLowest = anPctLowest;
    }

    public SocBroad getSocBroad() {
        return socBroad;
    }

    public SocSpecific socBroad(SocBroad socBroad) {
        this.socBroad = socBroad;
        return this;
    }

    public void setSocBroad(SocBroad socBroad) {
        this.socBroad = socBroad;
    }

    public Skills getSkills() {
        return skills;
    }

    public SocSpecific skills(Skills skills) {
        this.skills = skills;
        return this;
    }

    public void setSkills(Skills skills) {
        this.skills = skills;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public SocSpecific employees(Set<Employee> employees) {
        this.employees = employees;
        return this;
    }

    public SocSpecific addEmployee(Employee employee) {
        this.employees.add(employee);
        employee.setSocSpecific(this);
        return this;
    }

    public SocSpecific removeEmployee(Employee employee) {
        this.employees.remove(employee);
        employee.setSocSpecific(null);
        return this;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
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
        SocSpecific socSpecific = (SocSpecific) o;
        if (socSpecific.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), socSpecific.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SocSpecific{" +
            "id=" + getId() +
            ", socSpecificName='" + getSocSpecificName() + "'" +
            ", socSpecificCode='" + getSocSpecificCode() + "'" +
            ", socSpecificAvator='" + getSocSpecificAvator() + "'" +
            ", socSpecificAvatorContentType='" + getSocSpecificAvatorContentType() + "'" +
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
