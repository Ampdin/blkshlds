package com.spirovanni.blackshields.repository;

import com.spirovanni.blackshields.domain.SocMinor;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the SocMinor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SocMinorRepository extends JpaRepository<SocMinor, Long> {

}
