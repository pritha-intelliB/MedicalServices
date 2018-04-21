package com.parkway.medical.appointment.util;

public class SpecialityAppointmentResponse {

	private String statusCode;
	private String statusDescription;
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusDescription() {
		return statusDescription;
	}
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	@Override
	public String toString() {
		return "SpecialityAppointmentResponse [statusCode=" + statusCode + ", statusDescription=" + statusDescription
				+ "]";
	}
	
}
