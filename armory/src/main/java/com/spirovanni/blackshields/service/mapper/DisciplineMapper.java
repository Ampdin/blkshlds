package com.spirovanni.blackshields.service.mapper;

import com.spirovanni.blackshields.domain.*;
import com.spirovanni.blackshields.service.dto.DisciplineDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Discipline and its DTO DisciplineDTO.
 */
@Mapper(componentModel = "spring", uses = {ProgramMapper.class})
public interface DisciplineMapper extends EntityMapper<DisciplineDTO, Discipline> {

    

    @Mapping(target = "resources", ignore = true)
    @Mapping(target = "employees", ignore = true)
    Discipline toEntity(DisciplineDTO disciplineDTO);

    default Discipline fromId(Long id) {
        if (id == null) {
            return null;
        }
        Discipline discipline = new Discipline();
        discipline.setId(id);
        return discipline;
    }
}
