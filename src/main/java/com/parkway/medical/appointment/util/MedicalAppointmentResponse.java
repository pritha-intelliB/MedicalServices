package com.parkway.medical.appointment.util;

import java.util.List;

/**
 * 
 * @author ManjunathShivashimpi
 *
 */
public class MedicalAppointmentResponse {

	private String statusDescription;
	private String responseCode;
	private String errorMessage;
	private List<ApptMainJson> appMainObjList;
	
	
	/**
	 * @return the statusDescription
	 */
	public String getStatusDescription() {
		return statusDescription;
	}

	/**
	 * @param statusDescription
	 *            the statusDescription to set
	 */
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	/**
	 * @return the responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}

	/**
	 * @param responseCode
	 *            the responseCode to set
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the appMainObjList
	 */
	public List<ApptMainJson> getAppMainObjList() {
		return appMainObjList;
	}

	/**
	 * @param appMainObjList the appMainObjList to set
	 */
	public void setAppMainObjList(List<ApptMainJson> appMainObjList) {
		this.appMainObjList = appMainObjList;
	}

	/**
	 * @return the appMainObj
	 */
	
}
