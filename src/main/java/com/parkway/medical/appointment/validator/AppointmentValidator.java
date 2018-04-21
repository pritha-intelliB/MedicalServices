/**
 * 
 */
package com.parkway.medical.appointment.validator;

import com.parkway.medical.appointment.util.MedicalAppointmentResponse;

/**
 * @author ManjunathShivashimpi
 *
 */
public class AppointmentValidator {

	/**
	 * validateAppointmentRequest
	 * 
	 * @param c3msJSONString
	 * @return PatientAppointmentResponse
	 */
	public static MedicalAppointmentResponse validateAppointmentRequest(String c3msJSONString) {
		MedicalAppointmentResponse response = new MedicalAppointmentResponse();
		if(c3msJSONString == null || c3msJSONString.isEmpty()) {
			response.setErrorMessage("JSON string in request is empty");
			response.setStatusDescription("FAILURE");
			response.setResponseCode("001");
			return response;
		}
		return response;
	}
}
