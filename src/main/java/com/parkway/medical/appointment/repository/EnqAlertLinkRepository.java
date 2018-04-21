/**
 * 
 */
package com.parkway.medical.appointment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.parkway.medical.appointment.bo.EnqAlertLink;
import com.parkway.medical.appointment.bo.EnqAlertLinkPrimaryKey;

/**
 * @author nandita
 *
 */
@Repository
public interface EnqAlertLinkRepository extends CrudRepository<EnqAlertLink, EnqAlertLinkPrimaryKey> {

}
