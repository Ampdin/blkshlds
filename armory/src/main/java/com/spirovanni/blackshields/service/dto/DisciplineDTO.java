package com.spirovanni.blackshields.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Discipline entity.
 */
public class DisciplineDTO implements Serializable {

    private Long id;

    @NotNull
    private String disciplineName;

    @Lob
    private byte[] disciplineAvator;
    private String disciplineAvatorContentType;

    private String disciplineDescription;

    private Long disciplinePrice;

    private Set<ProgramDTO> programs = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public byte[] getDisciplineAvator() {
        return disciplineAvator;
    }

    public void setDisciplineAvator(byte[] disciplineAvator) {
        this.disciplineAvator = disciplineAvator;
    }

    public String getDisciplineAvatorContentType() {
        return disciplineAvatorContentType;
    }

    public void setDisciplineAvatorContentType(String disciplineAvatorContentType) {
        this.disciplineAvatorContentType = disciplineAvatorContentType;
    }

    public String getDisciplineDescription() {
        return disciplineDescription;
    }

    public void setDisciplineDescription(String disciplineDescription) {
        this.disciplineDescription = disciplineDescription;
    }

    public Long getDisciplinePrice() {
        return disciplinePrice;
    }

    public void setDisciplinePrice(Long disciplinePrice) {
        this.disciplinePrice = disciplinePrice;
    }

    public Set<ProgramDTO> getPrograms() {
        return programs;
    }

    public void setPrograms(Set<ProgramDTO> programs) {
        this.programs = programs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DisciplineDTO disciplineDTO = (DisciplineDTO) o;
        if(disciplineDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), disciplineDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DisciplineDTO{" +
            "id=" + getId() +
            ", disciplineName='" + getDisciplineName() + "'" +
            ", disciplineAvator='" + getDisciplineAvator() + "'" +
            ", disciplineDescription='" + getDisciplineDescription() + "'" +
            ", disciplinePrice=" + getDisciplinePrice() +
            "}";
    }
}
