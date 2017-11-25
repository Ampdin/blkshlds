package com.spirovanni.blackshields.repository;

import com.spirovanni.blackshields.domain.SocMajor;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the SocMajor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SocMajorRepository extends JpaRepository<SocMajor, Long> {

}
