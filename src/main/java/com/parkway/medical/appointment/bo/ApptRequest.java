package com.parkway.medical.appointment.bo;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TX_APPT_REQUEST")
public class ApptRequest {

	@Id
	private String sdh_request_no;
	private String first_name;
	private String last_name;
	private Date dob;
	private String nationality;
	private String id_type;
	private String id_no;
	private String country_code_residency;
	private String gender;
	private String email_address;
	private String phone_no_country_code;
	private String phone_no_area_code;
	private String phone_no;
	private String phone_no_extension;
	private String appointement_type;
	private String appointment_date;
	private String appointment_start_time;
	private String appointment_end_time;
	private String facility_cd;
	private String facility_name;
	private String facility_address;
	private String facility_contact_no;
	private String specialty_cd;
	private String specialty_desc;
	private String doctor_cd;
	private String doctor_name;
	private String diagonsis;
	private String status;
	private Timestamp creation_tm;
	private String created_by;
	private Timestamp last_modified_time;

	/**
	 * @return the sdh_request_no
	 */
	public String getSdh_request_no() {
		return sdh_request_no;
	}

	/**
	 * @param sdh_request_no
	 *            the sdh_request_no to set
	 */
	public void setSdh_request_no(String sdh_request_no) {
		this.sdh_request_no = sdh_request_no;
	}

	/**
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}

	/**
	 * @param first_name
	 *            the first_name to set
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	/**
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}

	/**
	 * @param last_name
	 *            the last_name to set
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	/**
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * @param dob
	 *            the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * @param nationality
	 *            the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * @return the id_type
	 */
	public String getId_type() {
		return id_type;
	}

	/**
	 * @param id_type
	 *            the id_type to set
	 */
	public void setId_type(String id_type) {
		this.id_type = id_type;
	}

	/**
	 * @return the id_no
	 */
	public String getId_no() {
		return id_no;
	}

	/**
	 * @param id_no
	 *            the id_no to set
	 */
	public void setId_no(String id_no) {
		this.id_no = id_no;
	}

	/**
	 * @return the country_code_residency
	 */
	public String getCountry_code_residency() {
		return country_code_residency;
	}

	/**
	 * @param country_code_residency
	 *            the country_code_residency to set
	 */
	public void setCountry_code_residency(String country_code_residency) {
		this.country_code_residency = country_code_residency;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the email_address
	 */
	public String getEmail_address() {
		return email_address;
	}

	/**
	 * @param email_address
	 *            the email_address to set
	 */
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}

	/**
	 * @return the phone_no_country_code
	 */
	public String getPhone_no_country_code() {
		return phone_no_country_code;
	}

	/**
	 * @param phone_no_country_code
	 *            the phone_no_country_code to set
	 */
	public void setPhone_no_country_code(String phone_no_country_code) {
		this.phone_no_country_code = phone_no_country_code;
	}

	/**
	 * @return the phone_no_area_code
	 */
	public String getPhone_no_area_code() {
		return phone_no_area_code;
	}

	/**
	 * @param phone_no_area_code
	 *            the phone_no_area_code to set
	 */
	public void setPhone_no_area_code(String phone_no_area_code) {
		this.phone_no_area_code = phone_no_area_code;
	}

	/**
	 * @return the phone_no
	 */
	public String getPhone_no() {
		return phone_no;
	}

	/**
	 * @param phone_no
	 *            the phone_no to set
	 */
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	/**
	 * @return the phone_no_extension
	 */
	public String getPhone_no_extension() {
		return phone_no_extension;
	}

	/**
	 * @param phone_no_extension
	 *            the phone_no_extension to set
	 */
	public void setPhone_no_extension(String phone_no_extension) {
		this.phone_no_extension = phone_no_extension;
	}

	/**
	 * @return the appointement_type
	 */
	public String getAppointement_type() {
		return appointement_type;
	}

	/**
	 * @param appointement_type
	 *            the appointement_type to set
	 */
	public void setAppointement_type(String appointement_type) {
		this.appointement_type = appointement_type;
	}

	/**
	 * @return the appointment_date
	 */
	public String getAppointment_date() {
		return appointment_date;
	}

	/**
	 * @param appointment_date
	 *            the appointment_date to set
	 */
	public void setAppointment_date(String appointment_date) {
		this.appointment_date = appointment_date;
	}

	/**
	 * @return the facility_cd
	 */
	public String getFacility_cd() {
		return facility_cd;
	}

	/**
	 * @param facility_cd
	 *            the facility_cd to set
	 */
	public void setFacility_cd(String facility_cd) {
		this.facility_cd = facility_cd;
	}

	/**
	 * @return the appointment_start_time
	 */
	public String getAppointment_start_time() {
		return appointment_start_time;
	}

	/**
	 * @param appointment_start_time
	 *            the appointment_start_time to set
	 */
	public void setAppointment_start_time(String appointment_start_time) {
		this.appointment_start_time = appointment_start_time;
	}

	/**
	 * @return the appointment_end_time
	 */
	public String getAppointment_end_time() {
		return appointment_end_time;
	}

	/**
	 * @param appointment_end_time
	 *            the appointment_end_time to set
	 */
	public void setAppointment_end_time(String appointment_end_time) {
		this.appointment_end_time = appointment_end_time;
	}

	/**
	 * @return the facility_name
	 */
	public String getFacility_name() {
		return facility_name;
	}

	/**
	 * @param facility_name
	 *            the facility_name to set
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
	 * @param facility_address
	 *            the facility_address to set
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
	 * @param facility_contact_no
	 *            the facility_contact_no to set
	 */
	public void setFacility_contact_no(String facility_contact_no) {
		this.facility_contact_no = facility_contact_no;
	}

	/**
	 * @return the doctor_cd
	 */
	public String getDoctor_cd() {
		return doctor_cd;
	}

	/**
	 * @param doctor_cd
	 *            the doctor_cd to set
	 */
	public void setDoctor_cd(String doctor_cd) {
		this.doctor_cd = doctor_cd;
	}

	/**
	 * @return the doctor_name
	 */
	public String getDoctor_name() {
		return doctor_name;
	}

	/**
	 * @param doctor_name
	 *            the doctor_name to set
	 */
	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	/**
	 * @return the diagonsis
	 */
	public String getDiagonsis() {
		return diagonsis;
	}

	/**
	 * @param diagonsis
	 *            the diagonsis to set
	 */
	public void setDiagonsis(String diagonsis) {
		this.diagonsis = diagonsis;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the creation_tm
	 */
	public Timestamp getCreation_tm() {
		return creation_tm;
	}

	/**
	 * @param creation_tm
	 *            the creation_tm to set
	 */
	public void setCreation_tm(Timestamp creation_tm) {
		this.creation_tm = creation_tm;
	}

	/**
	 * @return the created_by
	 */
	public String getCreated_by() {
		return created_by;
	}

	/**
	 * @param created_by
	 *            the created_by to set
	 */
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	/**
	 * @return the specialty_cd
	 */
	public String getSpecialty_cd() {
		return specialty_cd;
	}

	/**
	 * @param specialty_cd
	 *            the specialty_cd to set
	 */
	public void setSpecialty_cd(String specialty_cd) {
		this.specialty_cd = specialty_cd;
	}

	/**
	 * @return the specialty_desc
	 */
	public String getSpecialty_desc() {
		return specialty_desc;
	}

	/**
	 * @param specialty_desc
	 *            the specialty_desc to set
	 */
	public void setSpecialty_desc(String specialty_desc) {
		this.specialty_desc = specialty_desc;
	}

	/**
	 * @return the last_modified_time
	 */
	public Timestamp getLast_modified_time() {
		return last_modified_time;
	}

	/**
	 * @param last_modified_time
	 *            the last_modified_time to set
	 */
	public void setLast_modified_time(Timestamp last_modified_time) {
		this.last_modified_time = last_modified_time;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ApptRequest [sdh_request_no=" + sdh_request_no + ", first_name=" + first_name + ", last_name="
				+ last_name + ", dob=" + dob + ", nationality=" + nationality + ", id_type=" + id_type + ", id_no="
				+ id_no + ", country_code_residency=" + country_code_residency + ", gender=" + gender
				+ ", email_address=" + email_address + ", phone_no_country_code=" + phone_no_country_code
				+ ", phone_no_area_code=" + phone_no_area_code + ", phone_no=" + phone_no + ", phone_no_extension="
				+ phone_no_extension + ", appointement_type=" + appointement_type + ", appointment_date="
				+ appointment_date + ", appointment_start_time=" + appointment_start_time + ", appointment_end_time="
				+ appointment_end_time + ", facility_cd=" + facility_cd + ", facility_name=" + facility_name
				+ ", facility_address=" + facility_address + ", facility_contact_no=" + facility_contact_no
				+ ", specialty_cd=" + specialty_cd + ", specialty_desc=" + specialty_desc + ", doctor_cd=" + doctor_cd
				+ ", doctor_name=" + doctor_name + ", diagonsis=" + diagonsis + ", status=" + status + ", creation_tm="
				+ creation_tm + ", created_by=" + created_by + ", last_modified_time=" + last_modified_time + "]";
	}

}