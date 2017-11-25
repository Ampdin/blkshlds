package com.spirovanni.blackshields.service.mapper;

import com.spirovanni.blackshields.domain.*;
import com.spirovanni.blackshields.service.dto.SocMinorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SocMinor and its DTO SocMinorDTO.
 */
@Mapper(componentModel = "spring", uses = {SocMajorMapper.class})
public interface SocMinorMapper extends EntityMapper<SocMinorDTO, SocMinor> {

    @Mapping(source = "socMajor.id", target = "socMajorId")
    SocMinorDTO toDto(SocMinor socMinor); 

    @Mapping(source = "socMajorId", target = "socMajor")
    @Mapping(target = "socbroads", ignore = true)
    SocMinor toEntity(SocMinorDTO socMinorDTO);

    default SocMinor fromId(Long id) {
        if (id == null) {
            return null;
        }
        SocMinor socMinor = new SocMinor();
        socMinor.setId(id);
        return socMinor;
    }
}
