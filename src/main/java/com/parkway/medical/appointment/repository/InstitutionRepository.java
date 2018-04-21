/**
 * 
 */
package com.parkway.medical.appointment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.parkway.medical.appointment.bo.Institution;
import com.parkway.medical.appointment.bo.InstitutionPrimaryKey;

/**
 * @author nandita
 *
 */
@Repository
public interface InstitutionRepository extends CrudRepository<Institution, InstitutionPrimaryKey> {

	@Query(value = "SELECT * FROM CD_INSTITUTION WHERE INST_NAME =?1", nativeQuery = true)
	public List<Institution> findUsingIntitutionName(String institution_name);
}
