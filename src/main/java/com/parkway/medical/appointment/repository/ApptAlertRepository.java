/**
 * 
 */
package com.parkway.medical.appointment.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.parkway.medical.appointment.bo.ApptAlert;



/**
 * @author nandita
 *
 */
@Repository
public interface ApptAlertRepository extends CrudRepository<ApptAlert, BigInteger> {

	@Query(value = "SELECT * FROM TX_APPT_ALERT WHERE SRS_SYS_CD = ?1 AND INST_CD = ?2 AND CASE_NO =?3 AND SDH_REQUEST_NO =?4 AND COMM_TYPE =?5", nativeQuery = true)
	public ApptAlert findUniqueApptAlert(String srs_sys_cdValue, String inst_cdValue, String case_noValue, String sdh_request_noValue, String comm_type);
}
