package com.spirovanni.blackshields.service.mapper;

import com.spirovanni.blackshields.domain.*;
import com.spirovanni.blackshields.service.dto.ProgramDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Program and its DTO ProgramDTO.
 */
@Mapper(componentModel = "spring", uses = {CourseMapper.class})
public interface ProgramMapper extends EntityMapper<ProgramDTO, Program> {

    

    @Mapping(target = "resources", ignore = true)
    @Mapping(target = "disciplines", ignore = true)
    Program toEntity(ProgramDTO programDTO);

    default Program fromId(Long id) {
        if (id == null) {
            return null;
        }
        Program program = new Program();
        program.setId(id);
        return program;
    }
}
