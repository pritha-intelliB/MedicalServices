package com.parkway.medical.appointment.util;

import java.sql.Date;
import java.sql.Time;

public class SpecialityAppointmentRequest {

	private String source;
	private String appointmentType;
	private String doctor;
	private String hospital;
	private String appointmentDate;
	private String startTime;
	private String endTime;
	private String gender;
	private String nationality;
	private String firstName;
	private String lastName;
	private Date dob;
	private String countryOfResidence;
	private String idType;
	private String idNo;
	private String email;
	private String mobileNoCountryCode;
	private String mobileNo;
	private String currentDiognosis;
	private String ehsPackage;

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getCountryOfResidence() {
		return countryOfResidence;
	}

	public void setCountryOfResidence(String countryOfResidence) {
		this.countryOfResidence = countryOfResidence;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNoCountryCode() {
		return mobileNoCountryCode;
	}

	public void setMobileNoCountryCode(String mobileNoCountryCode) {
		this.mobileNoCountryCode = mobileNoCountryCode;
	}

	public String getCurrentDiognosis() {
		return currentDiognosis;
	}

	public void setCurrentDiognosis(String currentDiognosis) {
		this.currentDiognosis = currentDiognosis;
	}

	public String getEhsPackage() {
		return ehsPackage;
	}

	public void setEhsPackage(String ehsPackage) {
		this.ehsPackage = ehsPackage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SpecialityAppointmentRequest [source=" + source + ", appointmentType=" + appointmentType + ", doctor="
				+ doctor + ", hospital=" + hospital + ", appointmentDate=" + appointmentDate + ", startTime="
				+ startTime + ", endTime=" + endTime + ", gender=" + gender + ", nationality=" + nationality
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + ", countryOfResidence="
				+ countryOfResidence + ", idType=" + idType + ", idNo=" + idNo + ", email=" + email
				+ ", mobileNoCountryCode=" + mobileNoCountryCode + ", mobileNo=" + mobileNo + ", currentDiognosis="
				+ currentDiognosis + ", ehsPackage=" + ehsPackage + "]";
	}

}
