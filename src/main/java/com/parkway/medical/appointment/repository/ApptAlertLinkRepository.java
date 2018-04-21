/**
 * 
 */
package com.parkway.medical.appointment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.parkway.medical.appointment.bo.ApptAlertLink;
import com.parkway.medical.appointment.bo.ApptAlertLinkPrimaryKey;

/**
 * @author nandita
 *
 */
@Repository
public interface ApptAlertLinkRepository extends CrudRepository<ApptAlertLink, ApptAlertLinkPrimaryKey> {

}
