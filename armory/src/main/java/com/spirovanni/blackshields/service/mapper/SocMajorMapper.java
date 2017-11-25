package com.spirovanni.blackshields.service.mapper;

import com.spirovanni.blackshields.domain.*;
import com.spirovanni.blackshields.service.dto.SocMajorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SocMajor and its DTO SocMajorDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SocMajorMapper extends EntityMapper<SocMajorDTO, SocMajor> {

    

    @Mapping(target = "socminors", ignore = true)
    SocMajor toEntity(SocMajorDTO socMajorDTO);

    default SocMajor fromId(Long id) {
        if (id == null) {
            return null;
        }
        SocMajor socMajor = new SocMajor();
        socMajor.setId(id);
        return socMajor;
    }
}
