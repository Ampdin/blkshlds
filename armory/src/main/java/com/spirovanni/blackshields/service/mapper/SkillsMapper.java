package com.spirovanni.blackshields.service.mapper;

import com.spirovanni.blackshields.domain.*;
import com.spirovanni.blackshields.service.dto.SkillsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Skills and its DTO SkillsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SkillsMapper extends EntityMapper<SkillsDTO, Skills> {

    

    

    default Skills fromId(Long id) {
        if (id == null) {
            return null;
        }
        Skills skills = new Skills();
        skills.setId(id);
        return skills;
    }
}
