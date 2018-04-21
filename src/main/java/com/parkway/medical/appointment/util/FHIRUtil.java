/**
 * 
 */
package com.parkway.medical.appointment.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hl7.fhir.dstu3.model.Address;
import org.hl7.fhir.dstu3.model.Appointment;
import org.hl7.fhir.dstu3.model.AppointmentResponse;
import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.CommunicationRequest;
import org.hl7.fhir.dstu3.model.CommunicationRequest.CommunicationRequestPayloadComponent;
import org.hl7.fhir.dstu3.model.ContactPoint;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.Identifier;
import org.hl7.fhir.dstu3.model.Location;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Practitioner;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.StringType;
import org.hl7.fhir.exceptions.FHIRException;

import com.parkway.medical.appointment.bo.ApptAlert;
import com.parkway.medical.appointment.bo.ApptMain;
import com.parkway.medical.appointment.bo.EnqAlert;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;

/**
 * @author ManjunathShivashimpi
 *
 */
public class FHIRUtil {

	private static final String srssyscd = "C3MS";
	private static final String instcd = "C3MS Call Center";
	private static final String c3MSCaseNo = "C3MSCASENUMBER";
	private static final String phone = "PHONE";
	private static final String sms = "SMS";
	private static final String email = "EMAIL";

	public static void parseAppointmentString(String c3msString, ApptMain apptMain, List<ApptAlert> apptAlertList)
			throws ParseException {

		//ApptMainPrimaryKey apptMainPrimaryKey = new ApptMainPrimaryKey();
		apptMain.setSrs_sys_cd(srssyscd);
		apptMain.setInst_cd(instcd);

		String caseNo = null;
		FhirContext fhirContext = FhirContext.forDstu3();
		IParser parser = fhirContext.newJsonParser();
		Appointment appointment = parser.parseResource(Appointment.class, c3msString);
		List<Resource> resources = appointment.getContained();

		List<CodeableConcept> lisOfCodableConcept = appointment.getSpecialty();
		for (CodeableConcept codeableConcept : lisOfCodableConcept) {
			for (Coding coding : codeableConcept.getCoding()) {
				apptMain.setAppointment_type(coding.getDisplay());
			}
		}

		for (Resource resource : resources) {
			List<Identifier> identifiers = appointment.getIdentifier();

			for (Iterator<Identifier> iterator = identifiers.iterator(); iterator.hasNext();) {

				Identifier identifier = iterator.next();
				if (identifier.getType().getText().equalsIgnoreCase(c3MSCaseNo)) {
					caseNo = identifier.getId();
					apptMain.setCase_no(caseNo);
				}
				apptMain.setSdh_request_no("");
			}
			//apptMain.setApptMainPrimaryKey(apptMainPrimaryKey);

			if (resource instanceof Patient) {
				Patient patient = (Patient) resource;
				List<HumanName> patientNames = patient.getName();
				for (Iterator<HumanName> iterator = patientNames.iterator(); iterator.hasNext();) {
					HumanName patientName = iterator.next();
					apptMain.setPatient_first_name(patientName.getGivenAsSingleString());
					apptMain.setPatient_last_name(patientName.getFamily());
				}
				List<ContactPoint> listOfContactPoint = patient.getTelecom();
				for (ContactPoint contactPoint : listOfContactPoint) {
					if (contactPoint.getSystem().toString().equalsIgnoreCase(phone)) {

						String PhoneAndContryCode = contactPoint.getValueElement().getValue().trim();
						int firstIndex = PhoneAndContryCode.indexOf('(');
						int lastIndex = PhoneAndContryCode.indexOf(')');
						String contryCode = null;
						try {
							contryCode = PhoneAndContryCode.substring(firstIndex + 1, lastIndex);
						} catch (IndexOutOfBoundsException iobe) {
							contryCode = PhoneAndContryCode.substring(0, 2);
							lastIndex = 1;
						}
						String phoneNo = PhoneAndContryCode.substring(lastIndex + 1, PhoneAndContryCode.length());
						apptMain.setMobile_no_country_code(contryCode.trim());
						apptMain.setMobile_no(phoneNo.trim());
					} else if (contactPoint.getSystem().toString().equalsIgnoreCase(email)) {
						String email = contactPoint.getValue();
						apptMain.setEmail_address(email.trim());
					}
				}

				apptMain.setDob(convertDateToString(patient.getBirthDate().toString()));

				List<Identifier> pidentifiers = patient.getIdentifier();
				for (Iterator<Identifier> iterator = pidentifiers.iterator(); iterator.hasNext();) {

					Identifier identifier = iterator.next();
					if (identifier.getType().getText().equalsIgnoreCase("NRIC")) {
						apptMain.setId_no(identifier.getId());
						apptMain.setId_type(identifier.getType().getText());
					}
				}
				if (patient.getGender().toString().equalsIgnoreCase("Male")) {
					apptMain.setSalutation("Mr.");
				} else if (patient.getGender().toString().equalsIgnoreCase("Female")) {
					apptMain.setSalutation("Miss.");
				} else {
					apptMain.setSalutation("");
				}

			} else if (resource instanceof Practitioner) {
				Practitioner practitioner = (Practitioner) resource;

				List<HumanName> practitionerName = practitioner.getName();
				for (Iterator<HumanName> iterator = practitionerName.iterator(); iterator.hasNext();) {
					HumanName humanName = iterator.next();
					List<StringType> firstAndLastName = humanName.getGiven();
					if (firstAndLastName.size() > 1) {
						apptMain.setDoctor_first_name(firstAndLastName.get(0).toString());
						apptMain.setDoctor_last_name(firstAndLastName.get(1).toString());
					} else {
						apptMain.setDoctor_first_name(firstAndLastName.get(0).toString());
					}
				}

			} else if (resource instanceof Location) {
				Location location = (Location) resource;

				apptMain.setFacility_name(location.getName());
				apptMain.setFacility_address(location.getAddress().getText());
				List<ContactPoint> listOfContactPoint = location.getTelecom();

				for (ContactPoint contactPoint : listOfContactPoint) {
					if (contactPoint.getSystem().toString().equalsIgnoreCase(phone)) {
						if(contactPoint.getValue().indexOf('(') == -1) {
							apptMain.setFacility_contact_no('('+contactPoint.getValue().substring(0, 2)+')'+contactPoint.getValue().substring(2, contactPoint.getValue().length()-1));
						}else {
							apptMain.setFacility_contact_no(contactPoint.getValue());
						}
					}
				}

				List<Identifier> listOfIdentifier = location.getIdentifier();
				for (Identifier identifier : listOfIdentifier) {
					String hospString = identifier.getType().getText();
					if (hospString.contains("Hospital") || hospString.contains("Clinic code")) {
						apptMain.setFacility_cd(identifier.getId());
					}
				}
				
				Address address = location.getAddress();
				FacilityAddress facilityAddress = new FacilityAddress();
				facilityAddress.setCity(address.getCity());
				facilityAddress.setDistrict(address.getDistrict());
				facilityAddress.setLine(address.getLine());
				facilityAddress.setPostalCode(address.getPostalCode());
				facilityAddress.setState(address.getState());
				apptMain.setFacility_address(facilityAddress.toString());


			}
		}
		final Timestamp creationTime = new Timestamp(System.currentTimeMillis());

		apptMain.setCreation_time(creationTime);
		apptMain.setLast_modified_time(new Timestamp(System.currentTimeMillis()));
		apptMain.setStatus("Booked");
		apptMain.setNationality("Singaporian");
		Timestamp sttimestamp = new Timestamp(appointment.getStart().getTime());
		apptMain.setAppointment_time(sttimestamp);

		String descriptionString = appointment.getDescription();
		if (descriptionString != null) {
			appAlertEntry(apptAlertList, descriptionString, caseNo, "", "CONFIRMED");
		}

	}

	/**
	 * parseAppoitmentResponseString
	 * 
	 * @param c3msString
	 * @param apptAlertList
	 */
	public static ApptMain parseAppoitmentResponseString(String c3msString, List<ApptAlert> apptAlertList) {
		FhirContext fhirContext = FhirContext.forDstu3();
		IParser parser = fhirContext.newJsonParser();
		AppointmentResponse appointmentResponse = parser.parseResource(AppointmentResponse.class, c3msString);

		ApptMain apptMain = new ApptMain();
		//ApptMainPrimaryKey apptMainPrimaryKey = new ApptMainPrimaryKey();
		apptMain.setSrs_sys_cd(srssyscd);
		apptMain.setInst_cd(instcd);
		apptMain.setSdh_request_no("");
		String caseNo = null;

		List<Identifier> identifiers = appointmentResponse.getIdentifier();

		for (Iterator<Identifier> iterator = identifiers.iterator(); iterator.hasNext();) {
			Identifier identifier = iterator.next();
			if (identifier.getType().getText().equalsIgnoreCase(c3MSCaseNo)) {
				caseNo = identifier.getId();
				apptMain.setCase_no(identifier.getId());
			}
		}

		//apptMain.setApptMainPrimaryKey(apptMainPrimaryKey);
		apptMain.setStatus(appointmentResponse.getParticipantStatus().toString());

		// apptAlertList

		String commentString = appointmentResponse.getComment();
		if (commentString != null) {
			appAlertEntry(apptAlertList, commentString, caseNo, "", "CANCELLED");
		}
		return apptMain;
	}

	/**
	 * parseCommunicationRequestString
	 * 
	 * @param c3msString
	 * @param apptAlertList2
	 * @throws FHIRException
	 */
	public static String parseCommunicationRequestString(String c3msString, List<ApptAlert> apptAlertList,
			List<EnqAlert> enqAlertList) {
		String SDHValue = null, C3MSCaseNumberValue = null, contentString = null;
		FhirContext fhirContext = FhirContext.forDstu3();
		IParser parser = fhirContext.newJsonParser();
		CommunicationRequest communicationRequest = parser.parseResource(CommunicationRequest.class, c3msString);

		List<CommunicationRequestPayloadComponent> communicationRequestPayloadComponents = communicationRequest
				.getPayload();
		for (CommunicationRequestPayloadComponent communicationRequestPayloadComponent : communicationRequestPayloadComponents) {

			contentString = communicationRequestPayloadComponent.getContent().toString();
			if (contentString == null || contentString.isEmpty()) {
				return "";
			}
		}

		Identifier identifierGroup = communicationRequest.getGroupIdentifier();
		String type = identifierGroup.getType().getText();
		if (type.equalsIgnoreCase("Appointment")) {
			List<Identifier> listOfIdentifier = communicationRequest.getIdentifier();
			for (Identifier identifier : listOfIdentifier) {
				if (identifier.getType().getText().equalsIgnoreCase("SDHAppointmentId")) {
					SDHValue = identifier.getId();
				}
				if (identifier.getType().getText().equalsIgnoreCase("C3MSCaseNumber")) {
					C3MSCaseNumberValue = identifier.getId();
				}
			}
			appAlertEntry(apptAlertList, contentString, C3MSCaseNumberValue, SDHValue, "REMINDER");
		}

		if (type.equalsIgnoreCase("SelfHelp")) {
			List<Identifier> listOfIdentifier = communicationRequest.getIdentifier();
			for (Identifier identifier : listOfIdentifier) {
				if (identifier.getType().getText().equalsIgnoreCase("SDHSelfHelpRequestId")) {
					SDHValue = identifier.getId();
				}
				if (identifier.getType().getText().equalsIgnoreCase("C3MSSelfHelpRequestId")) {
					C3MSCaseNumberValue = identifier.getId();
				}
			}
			enqAlertEntry(enqAlertList, contentString, C3MSCaseNumberValue, SDHValue, "REMINDER");
		}

		/*
		 * List<Identifier> identifiers = communicationRequest.getIdentifier(); for
		 * (Iterator<Identifier> iterator = identifiers.iterator(); iterator.hasNext();)
		 * { Identifier identifier = iterator.next(); if
		 * (identifier.getType().getText().equalsIgnoreCase(c3MSCaseNo)) { caseNo
		 * =identifier.getId(); } }
		 */
		return type;
	}

	// Uncomment Below Code for local testing or Remove as required public static
	/*
	 * private static String getFileToString() throws IOException { //String
	 * file_name =
	 * "C:\\Users\\IBM_ADMIN\\Desktop\\Parkway\\Work_doc\\FHIR\\FHIR-Appointment.txt";
	 * String file_name =
	 * "C:\\Users\\IBM_ADMIN\\Desktop\\Parkway\\Work_doc\\FHIR\\FHIR-CommunicationRequest.txt";
	 * 
	 * String file_content = ""; String file_detail = "";
	 * 
	 * File f = new File(file_name);
	 * 
	 * BufferedReader br = new BufferedReader(new FileReader(f));
	 * 
	 * String readLine = "";
	 * 
	 * while ((readLine = br.readLine()) != null) { file_content = file_content +
	 * readLine; file_detail = file_content.toString(); } br.close();
	 * //System.out.println(file_detail); return file_detail; }
	 * 
	 * public static void main(String[] args) throws IOException, ParseException {
	 * //ApptMain apptMain = new ApptMain();
	 * //parseAppointmentString(getFileToString(), apptMain);
	 * //parseCommunicationRequestString(getFileToString()); }
	 * 
	 */
	private static String convertDateToString(String birthDate) throws ParseException {

		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		Date date = null;
		date = (Date) formatter.parse(birthDate);

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String formatedDate = cal.get(Calendar.DATE) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
				+ cal.get(Calendar.YEAR);

		return formatedDate;
	}

	public static String convertTimeToString(Timestamp timestampinmilisec) throws ParseException {
		Date date = new Date(timestampinmilisec.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		return sdf.format(date);
	}

	private static void appAlertEntry(List<ApptAlert> apptAlertList, String commentString, String caseNo,
			String sdhRequestNo, String alertType) {

		String[] params = commentString.split("\\|");
		HashMap<String, String> hMap = new HashMap<String, String>();
		for (String p : params) {
			String[] subParam = p.split("\\:");
			hMap.put(subParam[0], subParam[1]);
		}

		Integer smsCount = Integer.parseInt(hMap.get(sms));
		if (smsCount > 0) {
			ApptAlert apptAlertSMS = new ApptAlert();
			apptAlertSMS.setSrs_sys_cd(srssyscd);
			apptAlertSMS.setInst_cd(instcd);
			apptAlertSMS.setCase_no(caseNo);
			apptAlertSMS.setAlert_type(alertType);
			apptAlertSMS.setSdh_request_no(sdhRequestNo);
			apptAlertSMS.setComm_type(sms);
			apptAlertSMS.setSender(hMap.get("SMSSENDER"));
			apptAlertSMS.setReceiver(hMap.get("SMSRECEIVER"));
			// apptAlert.setSubject("SMSSUBJECT");
			apptAlertSMS.setText(hMap.get("SMSTEXT"));
			apptAlertSMS.setCreation_time(new Timestamp(System.currentTimeMillis()));
			apptAlertSMS.setLast_modified_time(new Timestamp(System.currentTimeMillis()));
			apptAlertList.add(apptAlertSMS);
			smsCount--;
		}
		Integer emailCount = Integer.parseInt(hMap.get(email));
		if (emailCount > 0) {
			ApptAlert apptAlertEMAIL = new ApptAlert();
			apptAlertEMAIL.setSrs_sys_cd(srssyscd);
			apptAlertEMAIL.setInst_cd(instcd);
			apptAlertEMAIL.setCase_no(caseNo);
			apptAlertEMAIL.setAlert_type(alertType);
			apptAlertEMAIL.setSdh_request_no(sdhRequestNo);
			apptAlertEMAIL.setComm_type(email);
			apptAlertEMAIL.setSender(hMap.get("EMAILSENDER"));
			apptAlertEMAIL.setReceiver(hMap.get("EMAILRECEIVER"));
			apptAlertEMAIL.setSubject(hMap.get("EMAILSUBJECT"));
			apptAlertEMAIL.setText(hMap.get("EMAILTEXT"));
			apptAlertEMAIL.setCreation_time(new Timestamp(System.currentTimeMillis()));
			apptAlertEMAIL.setLast_modified_time(new Timestamp(System.currentTimeMillis()));
			apptAlertList.add(apptAlertEMAIL);
			emailCount--;
		}
		Integer pnCount = Integer.parseInt(hMap.get("PN"));
		if (pnCount > 0) {
			ApptAlert apptAlertPN = new ApptAlert();
			apptAlertPN.setSrs_sys_cd(srssyscd);
			apptAlertPN.setInst_cd(instcd);
			apptAlertPN.setCase_no(caseNo);
			apptAlertPN.setAlert_type(alertType);
			apptAlertPN.setSdh_request_no(sdhRequestNo);
			apptAlertPN.setComm_type("PN");
			apptAlertPN.setSender(hMap.get("PNSENDER"));
			// apptAlert.setReceiver(hMap.get("PNRECEIVER"));
			apptAlertPN.setSubject(hMap.get("PNSUBJECT"));
			apptAlertPN.setText(hMap.get("PNTEXT"));
			apptAlertPN.setCreation_time(new Timestamp(System.currentTimeMillis()));
			apptAlertPN.setLast_modified_time(new Timestamp(System.currentTimeMillis()));
			apptAlertList.add(apptAlertPN);
			pnCount--;
		}

	}

	private static void enqAlertEntry(List<EnqAlert> enqAlertList, String commentString, String caseNo,
			String sdhRequestNo, String alertType) {

		String[] params = commentString.split("\\|");
		HashMap<String, String> hMap = new HashMap<String, String>();
		for (String p : params) {
			String[] subParam = p.split("\\:");
			hMap.put(subParam[0], subParam[1]);
		}

		Integer smsCount = Integer.parseInt(hMap.get(sms));
		if (smsCount > 0) {
			EnqAlert enqAlertSMS = new EnqAlert();
			enqAlertSMS.setSrs_sys_cd(srssyscd);
			enqAlertSMS.setInst_cd(instcd);
			enqAlertSMS.setCase_no(caseNo);
			/*
			 * if(appreqtype.equalsIgnoreCase("createAppointment")) {
			 * apptAlertSMS.setAlert_type("CONFIRMED"); }else
			 * if(appreqtype.equalsIgnoreCase("updateAppointment")) {
			 * apptAlertSMS.setAlert_type("CANCELLED"); }else
			 * if(appreqtype.equalsIgnoreCase("notifyAppointment")) {
			 * apptAlertSMS.setAlert_type("REMINDER"); }
			 */
			enqAlertSMS.setAlert_type(alertType);
			enqAlertSMS.setSdh_request_no(sdhRequestNo);
			enqAlertSMS.setComm_type(sms);
			enqAlertSMS.setSender(hMap.get("SMSSENDER"));
			enqAlertSMS.setReceiver(hMap.get("SMSRECEIVER"));
			// apptAlert.setSubject("SMSSUBJECT");
			enqAlertSMS.setText(hMap.get("SMSTEXT"));
			enqAlertSMS.setCreation_time(new Timestamp(System.currentTimeMillis()));
			enqAlertSMS.setLast_modified_time(new Timestamp(System.currentTimeMillis()));
			enqAlertList.add(enqAlertSMS);
			smsCount--;
		}
		Integer emailCount = Integer.parseInt(hMap.get(email));
		if (emailCount > 0) {
			EnqAlert enqAlertEMAIL = new EnqAlert();
			enqAlertEMAIL.setSrs_sys_cd(srssyscd);
			enqAlertEMAIL.setInst_cd(instcd);
			enqAlertEMAIL.setCase_no(caseNo);
			enqAlertEMAIL.setAlert_type(alertType);
			enqAlertEMAIL.setSdh_request_no(sdhRequestNo);
			enqAlertEMAIL.setComm_type(email);
			enqAlertEMAIL.setSender(hMap.get("EMAILSENDER"));
			enqAlertEMAIL.setReceiver(hMap.get("EMAILRECEIVER"));
			enqAlertEMAIL.setSubject(hMap.get("EMAILSUBJECT"));
			enqAlertEMAIL.setText(hMap.get("EMAILTEXT"));
			enqAlertEMAIL.setCreation_time(new Timestamp(System.currentTimeMillis()));
			enqAlertEMAIL.setLast_modified_time(new Timestamp(System.currentTimeMillis()));
			enqAlertList.add(enqAlertEMAIL);
			emailCount--;
		}
		Integer pnCount = Integer.parseInt(hMap.get("PN"));
		if (pnCount > 0) {
			EnqAlert enqAlertPN = new EnqAlert();
			enqAlertPN.setSrs_sys_cd(srssyscd);
			enqAlertPN.setInst_cd(instcd);
			enqAlertPN.setCase_no(caseNo);
			enqAlertPN.setAlert_type(alertType);
			enqAlertPN.setSdh_request_no(sdhRequestNo);
			enqAlertPN.setComm_type("PN");
			enqAlertPN.setSender(hMap.get("PNSENDER"));
			// apptAlert.setReceiver(hMap.get("PNRECEIVER"));
			enqAlertPN.setSubject(hMap.get("PNSUBJECT"));
			enqAlertPN.setText(hMap.get("PNTEXT"));
			enqAlertPN.setCreation_time(new Timestamp(System.currentTimeMillis()));
			enqAlertPN.setLast_modified_time(new Timestamp(System.currentTimeMillis()));
			enqAlertList.add(enqAlertPN);
			pnCount--;
		}

	}
	
}
