/**
 * 
 */
package com.parkway.medical.appointment.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.parkway.medical.appointment.bo.ApptMain;



/**
 * @author nandita
 *
 */
@Repository
public interface ApptMainRepository extends CrudRepository<ApptMain,BigInteger> {
  
	@Query(value = "SELECT * FROM TX_APPT_MAIN WHERE TX_APPT_MAIN.SRS_SYS_CD = ?1 and TX_APPT_MAIN.INST_CD =?2 and TX_APPT_MAIN.CASE_NO=?3 and TX_APPT_MAIN.SDH_REQUEST_NO =?4", nativeQuery = true)
	public ApptMain findUniqueAppointment(String srs_sys_cd, String inst_cd, String case_no, String sdh_request_no);

	@Query(value = "SELECT * FROM tx_appt_main WHERE srs_sys_cd =?1 AND id_no = ?2 AND dob = ?3 AND salutation =?4", nativeQuery = true)
	public List<ApptMain> findAppointmentsList(String srs_sys_cd,String id_no, String dob, String salutation);
	
	@Query(value = "SELECT * FROM TX_APPT_MAIN WHERE ID_NO =?1 AND srs_sys_cd =?2", nativeQuery = true)
	public List<ApptMain> findUsingId_noANDSrs_sys_cd(String id_no, String srs_sys_cd);
	
	@Query(value = "SELECT * FROM TX_APPT_MAIN WHERE ID_NO =?1 AND salutation =?2", nativeQuery = true)
	public List<ApptMain> findUsingId_noAndSalutation(String id_no, String salutation);

	@Query(value = "SELECT * FROM TX_APPT_MAIN WHERE ID_NO =?1 AND dob =?2", nativeQuery = true)
	public List<ApptMain> findUsingId_noANDDob(String id_no, String dob);
	
	@Query(value = "SELECT * FROM TX_APPT_MAIN WHERE ID_NO =?1 And srs_sys_cd =?2 AND salutation =?3", nativeQuery = true)
	public List<ApptMain> findUsingId_noANDSrs_sys_cdAndSalutation(String id_no, String srs_sys_cd, String salutation);

	@Query(value = "SELECT * FROM TX_APPT_MAIN WHERE ID_NO =?1 And srs_sys_cd =?2 AND dob =?3", nativeQuery = true)
	public List<ApptMain> findUsingId_noANDSrs_sys_cdAndDob(String id_no, String srs_sys_cd, String dob);

	@Query(value = "SELECT * FROM TX_APPT_MAIN WHERE ID_NO =?1 AND salutation =?2 And dob =?3", nativeQuery = true)
	public List<ApptMain> findUsingId_noANDSalutationAndDob(String id_no, String salutation, String dob);

	@Query(value = "SELECT * FROM TX_APPT_MAIN WHERE ID_NO =?1", nativeQuery = true)
	public List<ApptMain> findUsingId_no(String id_no);

	
}
