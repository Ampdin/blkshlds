package com.spirovanni.blackshields.repository;

import com.spirovanni.blackshields.domain.SocBroad;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the SocBroad entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SocBroadRepository extends JpaRepository<SocBroad, Long> {

}
