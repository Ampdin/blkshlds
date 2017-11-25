package com.spirovanni.blackshields.service.dto;


import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Employee entity.
 */
public class EmployeeDTO implements Serializable {

    private Long id;

    private Long employeeId;

    private String playerType;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String badgeNumber;

    private LocalDate startDate;

    private ZonedDateTime memberSince;

    private Long previousSalary;

    private Long currentSalary;

    private Long goalSalary;

    private String pathGoal;

    private String address;

    private String city;

    private Long zip;

    private String state;

    @Lob
    private byte[] employeeAvatar;
    private String employeeAvatarContentType;

    private Long socSpecificId;

    private Long departmentId;

    private Long managerId;

    private Long disciplineId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getPlayerType() {
        return playerType;
    }

    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(String badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getMemberSince() {
        return memberSince;
    }

    public void setMemberSince(ZonedDateTime memberSince) {
        this.memberSince = memberSince;
    }

    public Long getPreviousSalary() {
        return previousSalary;
    }

    public void setPreviousSalary(Long previousSalary) {
        this.previousSalary = previousSalary;
    }

    public Long getCurrentSalary() {
        return currentSalary;
    }

    public void setCurrentSalary(Long currentSalary) {
        this.currentSalary = currentSalary;
    }

    public Long getGoalSalary() {
        return goalSalary;
    }

    public void setGoalSalary(Long goalSalary) {
        this.goalSalary = goalSalary;
    }

    public String getPathGoal() {
        return pathGoal;
    }

    public void setPathGoal(String pathGoal) {
        this.pathGoal = pathGoal;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getZip() {
        return zip;
    }

    public void setZip(Long zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public byte[] getEmployeeAvatar() {
        return employeeAvatar;
    }

    public void setEmployeeAvatar(byte[] employeeAvatar) {
        this.employeeAvatar = employeeAvatar;
    }

    public String getEmployeeAvatarContentType() {
        return employeeAvatarContentType;
    }

    public void setEmployeeAvatarContentType(String employeeAvatarContentType) {
        this.employeeAvatarContentType = employeeAvatarContentType;
    }

    public Long getSocSpecificId() {
        return socSpecificId;
    }

    public void setSocSpecificId(Long socSpecificId) {
        this.socSpecificId = socSpecificId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long employeeId) {
        this.managerId = employeeId;
    }

    public Long getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(Long disciplineId) {
        this.disciplineId = disciplineId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmployeeDTO employeeDTO = (EmployeeDTO) o;
        if(employeeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), employeeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
            "id=" + getId() +
            ", employeeId=" + getEmployeeId() +
            ", playerType='" + getPlayerType() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", badgeNumber='" + getBadgeNumber() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", memberSince='" + getMemberSince() + "'" +
            ", previousSalary=" + getPreviousSalary() +
            ", currentSalary=" + getCurrentSalary() +
            ", goalSalary=" + getGoalSalary() +
            ", pathGoal='" + getPathGoal() + "'" +
            ", address='" + getAddress() + "'" +
            ", city='" + getCity() + "'" +
            ", zip=" + getZip() +
            ", state='" + getState() + "'" +
            ", employeeAvatar='" + getEmployeeAvatar() + "'" +
            "}";
    }
}
