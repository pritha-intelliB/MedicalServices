/**
 * 
 */
package com.parkway.medical.appointment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.parkway.medical.appointment.bo.ApptLink;
import com.parkway.medical.appointment.bo.ApptLinkPrimaryKey;



/**
 * @author nandita
 *
 */
@Repository
public interface ApptLinkRepository extends CrudRepository<ApptLink,ApptLinkPrimaryKey>{

}
