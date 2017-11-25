package com.spirovanni.blackshields.repository;

import com.spirovanni.blackshields.domain.SocSpecific;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the SocSpecific entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SocSpecificRepository extends JpaRepository<SocSpecific, Long> {

}
