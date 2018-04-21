/**
 * 
 */
package com.parkway.medical.appointment.util;

/**
 * @author ManjunathShivashimpi
 *
 */
public class MedicalAppointmentRequest {
	/*
	 * 	private BigInteger seq_no;
	 *  private String inst_cd = "C3MS Call Center";
	 * 	private String case_no;
	 *  private String sdh_request_no = "";
	 */

	private String sourceSystemCd = "C3MS";
	private String idNo;
	private String dob;
	private String gender;

	
	public String getSourceSystemCd() {
		return sourceSystemCd;
	}

	public void setSourceSystemCd(String sourceSystemCd) {
		this.sourceSystemCd = sourceSystemCd;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
