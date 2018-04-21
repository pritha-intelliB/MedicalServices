/**
 * 
 */
package com.parkway.medical.appointment.bo;

import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author nandita
 *
 */
@Entity
@Table(name ="tx_appt_main")
public class ApptMain {
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private BigInteger seq_no;
	private String srs_sys_cd;
	private String inst_cd;
	private String case_no;
	private String sdh_request_no;
	private String patient_first_name; //first_name in db_spec doc
	private String patient_last_name;  //last_name in db_spec doc
	private String dob;
	private String nationality;
	private String id_no;
	private String id_type;
	private String email_address;
	private String mobile_no_country_code;
	private String mobile_no;
	private Timestamp creation_time;
	private Timestamp last_modified_time;
	private Timestamp appointment_time;
	private String doctor_first_name;
	private String doctor_last_name;
	private String status;
	private String facility_cd;
	private String facility_name;
	private String facility_address;
	private String facility_contact_no;
	private String appointment_type;
	private String salutation; // extra column
	/**
	 * @return the seq_no
	 */
	public BigInteger getSeq_no() {
		return seq_no;
	}
	/**
	 * @param seq_no the seq_no to set
	 */
	public void setSeq_no(BigInteger seq_no) {
		this.seq_no = seq_no;
	}
	/**
	 * @return the srs_sys_cd
	 */
	public String getSrs_sys_cd() {
		return srs_sys_cd;
	}
	/**
	 * @param srs_sys_cd the srs_sys_cd to set
	 */
	public void setSrs_sys_cd(String srs_sys_cd) {
		this.srs_sys_cd = srs_sys_cd;
	}
	/**
	 * @return the inst_cd
	 */
	public String getInst_cd() {
		return inst_cd;
	}
	/**
	 * @param inst_cd the inst_cd to set
	 */
	public void setInst_cd(String inst_cd) {
		this.inst_cd = inst_cd;
	}
	/**
	 * @return the case_no
	 */
	public String getCase_no() {
		return case_no;
	}
	/**
	 * @param case_no the case_no to set
	 */
	public void setCase_no(String case_no) {
		this.case_no = case_no;
	}
	/**
	 * @return the sdh_request_no
	 */
	public String getSdh_request_no() {
		return sdh_request_no;
	}
	/**
	 * @param sdh_request_no the sdh_request_no to set
	 */
	public void setSdh_request_no(String sdh_request_no) {
		this.sdh_request_no = sdh_request_no;
	}
	/**
	 * @return the patient_first_name
	 */
	public String getPatient_first_name() {
		return patient_first_name;
	}
	/**
	 * @param patient_first_name the patient_first_name to set
	 */
	public void setPatient_first_name(String patient_first_name) {
		this.patient_first_name = patient_first_name;
	}
	/**
	 * @return the patient_last_name
	 */
	public String getPatient_last_name() {
		return patient_last_name;
	}
	/**
	 * @param patient_last_name the patient_last_name to set
	 */
	public void setPatient_last_name(String patient_last_name) {
		this.patient_last_name = patient_last_name;
	}
	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}
	/**
	 * @param dob the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}
	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}
	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	/**
	 * @return the id_no
	 */
	public String getId_no() {
		return id_no;
	}
	/**
	 * @param id_no the id_no to set
	 */
	public void setId_no(String id_no) {
		this.id_no = id_no;
	}
	/**
	 * @return the id_type
	 */
	public String getId_type() {
		return id_type;
	}
	/**
	 * @param id_type the id_type to set
	 */
	public void setId_type(String id_type) {
		this.id_type = id_type;
	}
	/**
	 * @return the email_address
	 */
	public String getEmail_address() {
		return email_address;
	}
	/**
	 * @param email_address the email_address to set
	 */
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}
	/**
	 * @return the mobile_no_country_code
	 */
	public String getMobile_no_country_code() {
		return mobile_no_country_code;
	}
	/**
	 * @param mobile_no_country_code the mobile_no_country_code to set
	 */
	public void setMobile_no_country_code(String mobile_no_country_code) {
		this.mobile_no_country_code = mobile_no_country_code;
	}
	/**
	 * @return the mobile_no
	 */
	public String getMobile_no() {
		return mobile_no;
	}
	/**
	 * @param mobile_no the mobile_no to set
	 */
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	/**
	 * @return the creation_time
	 */
	public Timestamp getCreation_time() {
		return creation_time;
	}
	/**
	 * @param creation_time the creation_time to set
	 */
	public void setCreation_time(Timestamp creation_time) {
		this.creation_time = creation_time;
	}
	/**
	 * @return the last_modified_time
	 */
	public Timestamp getLast_modified_time() {
		return last_modified_time;
	}
	/**
	 * @param last_modified_time the last_modified_time to set
	 */
	public void setLast_modified_time(Timestamp last_modified_time) {
		this.last_modified_time = last_modified_time;
	}
	/**
	 * @return the appointment_time
	 */
	public Timestamp getAppointment_time() {
		return appointment_time;
	}
	/**
	 * @param appointment_time the appointment_time to set
	 */
	public void setAppointment_time(Timestamp appointment_time) {
		this.appointment_time = appointment_time;
	}
	/**
	 * @return the doctor_first_name
	 */
	public String getDoctor_first_name() {
		return doctor_first_name;
	}
	/**
	 * @param doctor_first_name the doctor_first_name to set
	 */
	public void setDoctor_first_name(String doctor_first_name) {
		this.doctor_first_name = doctor_first_name;
	}
	/**
	 * @return the doctor_last_name
	 */
	public String getDoctor_last_name() {
		return doctor_last_name;
	}
	/**
	 * @param doctor_last_name the doctor_last_name to set
	 */
	public void setDoctor_last_name(String doctor_last_name) {
		this.doctor_last_name = doctor_last_name;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the facility_cd
	 */
	public String getFacility_cd() {
		return facility_cd;
	}
	/**
	 * @param facility_cd the facility_cd to set
	 */
	public void setFacility_cd(String facility_cd) {
		this.facility_cd = facility_cd;
	}
	/**
	 * @return the facility_name
	 */
	public String getFacility_name() {
		return facility_name;
	}
	/**
	 * @param facility_name the facility_name to set
	 */
	public void setFacility_name(String facility_name) {
		this.facility_name = facility_name;
	}
	/**
	 * @return the facility_address
	 */
	public String getFacility_address() {
		return facility_address;
	}
	/**
	 * @param facility_address the facility_address to set
	 */
	public void setFacility_address(String facility_address) {
		this.facility_address = facility_address;
	}
	/**
	 * @return the facility_contact_no
	 */
	public String getFacility_contact_no() {
		return facility_contact_no;
	}
	/**
	 * @param facility_contact_no the facility_contact_no to set
	 */
	public void setFacility_contact_no(String facility_contact_no) {
		this.facility_contact_no = facility_contact_no;
	}
	/**
	 * @return the appointment_type
	 */
	public String getAppointment_type() {
		return appointment_type;
	}
	/**
	 * @param appointment_type the appointment_type to set
	 */
	public void setAppointment_type(String appointment_type) {
		this.appointment_type = appointment_type;
	}
	/**
	 * @return the salutation
	 */
	public String getSalutation() {
		return salutation;
	}
	/**
	 * @param salutation the salutation to set
	 */
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
	
	
	
}
