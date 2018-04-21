/**
 * 
 */
package com.parkway.medical.appointment.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.sql.rowset.serial.SerialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.parkway.medical.appointment.bo.ApptAlert;
import com.parkway.medical.appointment.bo.ApptAlertLink;
import com.parkway.medical.appointment.bo.ApptAlertLinkPrimaryKey;
import com.parkway.medical.appointment.bo.ApptLink;
import com.parkway.medical.appointment.bo.ApptLinkPrimaryKey;
import com.parkway.medical.appointment.bo.ApptMain;
import com.parkway.medical.appointment.bo.ApptRequest;
import com.parkway.medical.appointment.bo.EnqAlert;
import com.parkway.medical.appointment.bo.EnqAlertLink;
import com.parkway.medical.appointment.bo.EnqAlertLinkPrimaryKey;
import com.parkway.medical.appointment.bo.Institution;
import com.parkway.medical.appointment.bo.Msg;
import com.parkway.medical.appointment.repository.ApptAlertLinkRepository;
import com.parkway.medical.appointment.repository.ApptAlertRepository;
import com.parkway.medical.appointment.repository.ApptLinkRepository;
import com.parkway.medical.appointment.repository.ApptMainRepository;
import com.parkway.medical.appointment.repository.EnqAlertLinkRepository;
import com.parkway.medical.appointment.repository.EnqAlertRepository;
import com.parkway.medical.appointment.repository.InstitutionRepository;
import com.parkway.medical.appointment.repository.MsgRepository;
import com.parkway.medical.appointment.repository.SpecialityAppointmentRepository;
import com.parkway.medical.appointment.util.ApptMainJson;
import com.parkway.medical.appointment.util.FHIRUtil;
import com.parkway.medical.appointment.util.MedicalAppointmentRequest;
import com.parkway.medical.appointment.util.MedicalAppointmentResponse;
import com.parkway.medical.appointment.util.MedicalResponse;
import com.parkway.medical.appointment.util.SpecialityAppointmentRequest;
import com.parkway.medical.appointment.util.SpecialityAppointmentResponse;

import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

/**
 * @author ManjunathShivashimpi
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/hosp/appointment")
@PropertySource(ignoreResourceNotFound = true, value = "classpath:url.properties")
public class AppointmentController {

	@Autowired
	ApptMainRepository apptMainRepository;

	@Autowired
	MsgRepository msgRepository;

	@Autowired
	ApptLinkRepository apptLinkRepository;

	@Autowired
	ApptAlertRepository apptAlertRepository;

	@Autowired
	ApptAlertLinkRepository apptAlertLinkRepository;

	@Autowired
	EnqAlertRepository enqAlertRepository;

	@Autowired
	EnqAlertLinkRepository enqAlertLinkRepository;

	@Autowired
	SpecialityAppointmentRepository specialityAppointmentRepository;

	@Autowired
	InstitutionRepository institutionRepository;

	@Autowired
	private JavaMailSender emailSender;

	@Autowired(required = true)
	@Qualifier(value = "freemarker")
	private Configuration freemarkerConfig;

	@Value("${spring.mail.username}")
	private String FROMADDRESS;

	@Value("${secret.header.value}")
	private String secretKeyValue;

	@Value("${enable.email.send}")
	private Boolean enableEmailSend;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	private static final String C3MS = "C3MS";
	private static final String JSON = "JSON";
	private static final String APPOINTMENT = "Appointment";
	private static final String SELFHELP = "SelfHelp";
	private static final String BLANKSPACE = "";
	private static final String FAILURECODE = "001";
	private static final String SUCCESSCODE = "000";
	private static final String FAILUREDESCRIPTION = "FAILURE";
	private static final String SUCCESSDESCRIPTION = "SUCCESS";

	@RequestMapping(value = "/createAppointment", method = RequestMethod.POST)
	public MedicalResponse createAppointment(@RequestHeader HttpHeaders headers, @RequestBody String C3msJsonString)
			throws MessagingException, IOException, TemplateException {
		logger.debug("createAppointment Service Called");
		MedicalResponse response = new MedicalResponse();
		boolean check = headers.containsKey("secret");
		if (!check) {
			logger.info("NO SECRET KEY FOUND IN HEADER");
			response.setStatusCode(FAILURECODE);
			response.setStatusDiscription("NO SECRET KEY FOUND IN HEADER");
			return response;
		}
		String key = headers.getFirst("secret");
		if (key.equalsIgnoreCase(secretKeyValue.trim())) {
			// Insert New Record to TX_MSG table
			Msg apptMsg = new Msg();
			apptMsg.setSrs_sys_cd(C3MS);
			apptMsg.setMessage_type(JSON);
			apptMsg.setSrc_msg_id(BLANKSPACE);
			apptMsg.setSrc_msg_tm(new Timestamp(System.currentTimeMillis()));

			String message = C3msJsonString.trim();
			/*
			 * try { message = new SerialBlob(C3msJsonString.trim().getBytes());
			 * 
			 * } catch (SQLException e) { response.setStatusCode(FAILURECODE);
			 * response.setStatusDiscription(FAILUREDESCRIPTION); return response; }
			 */

			apptMsg.setMessage(message);
			apptMsg.setProcess_tm(new Timestamp(System.currentTimeMillis()));
			msgRepository.save(apptMsg);

			// Insert Record to TX_APPT_MAIN table
			ApptMain apptMain = new ApptMain();

			List<ApptAlert> apptAlertList = new ArrayList<ApptAlert>();

			try {
				FHIRUtil.parseAppointmentString(C3msJsonString, apptMain, apptAlertList);
			} catch (ParseException ex) {
				logger.error(ex.getMessage());
				response.setStatusCode(FAILURECODE);
				response.setStatusDiscription(FAILUREDESCRIPTION);
				return response;
			}

			ApptMain appAlreayExist = apptMainRepository.findUniqueAppointment(apptMain.getSrs_sys_cd(),
					apptMain.getInst_cd(), apptMain.getCase_no(), apptMain.getSdh_request_no());

			if (appAlreayExist == null) {
				apptMainRepository.save(apptMain);

				// Insert to TX_APPT_LINK
				ApptLink apptLink = new ApptLink();
				ApptLinkPrimaryKey apptLinkPrimaryKey = new ApptLinkPrimaryKey();
				apptLinkPrimaryKey.setAppt_seq_no(apptMain.getSeq_no());
				apptLinkPrimaryKey.setMsg_seq_no(apptMsg.getSeq_no());
				apptLink.setApptLinkPrimaryKey(apptLinkPrimaryKey);
				apptLink.setMsg_reception_tm(new Timestamp(System.currentTimeMillis()));
				apptLinkRepository.save(apptLink);

			} else {

				// update TX_APPT_MAIN
				apptMainRepository.findOne(appAlreayExist.getSeq_no());
				// set all the field and do save
				appAlreayExist.setPatient_first_name(apptMain.getPatient_first_name());
				appAlreayExist.setPatient_last_name(apptMain.getPatient_last_name());
				appAlreayExist.setDob(apptMain.getDob());
				appAlreayExist.setNationality(apptMain.getNationality());
				appAlreayExist.setId_no(apptMain.getId_no());
				appAlreayExist.setId_type(apptMain.getId_type());
				appAlreayExist.setEmail_address(apptMain.getEmail_address());
				appAlreayExist.setMobile_no_country_code(apptMain.getMobile_no_country_code());
				appAlreayExist.setMobile_no(apptMain.getMobile_no());
				appAlreayExist.setCreation_time(apptMain.getCreation_time());
				appAlreayExist.setLast_modified_time(new Timestamp(System.currentTimeMillis())); // last_modified time
																									// is
																									// system time
				appAlreayExist.setAppointment_time(apptMain.getAppointment_time());
				appAlreayExist.setDoctor_first_name(apptMain.getDoctor_first_name());
				appAlreayExist.setDoctor_last_name(apptMain.getDoctor_last_name());
				appAlreayExist.setStatus(apptMain.getStatus());
				appAlreayExist.setFacility_cd(apptMain.getFacility_cd());
				appAlreayExist.setFacility_name(apptMain.getFacility_name());
				appAlreayExist.setFacility_address(apptMain.getFacility_address());
				appAlreayExist.setFacility_contact_no(apptMain.getFacility_contact_no());
				appAlreayExist.setAppointment_type(apptMain.getAppointment_type());
				appAlreayExist.setSalutation(apptMain.getSalutation());

				apptMainRepository.save(appAlreayExist);

				// Insert to TX_APPT_LINK
				ApptLink apptLink = new ApptLink();
				ApptLinkPrimaryKey apptLinkPrimaryKey = new ApptLinkPrimaryKey();
				apptLinkPrimaryKey.setAppt_seq_no(appAlreayExist.getSeq_no());
				apptLinkPrimaryKey.setMsg_seq_no(apptMsg.getSeq_no());
				apptLink.setApptLinkPrimaryKey(apptLinkPrimaryKey);
				apptLink.setMsg_reception_tm(new Timestamp(System.currentTimeMillis()));
				apptLinkRepository.save(apptLink);

			}

			if (!apptAlertList.isEmpty()) {
				for (ApptAlert apptAlert : apptAlertList) {
					ApptAlert appAlertExist = apptAlertRepository.findUniqueApptAlert(apptAlert.getSrs_sys_cd(),
							apptAlert.getInst_cd(), apptAlert.getCase_no(), apptAlert.getSdh_request_no(),
							apptAlert.getComm_type());
					if (appAlertExist == null) {
						apptAlertRepository.save(apptAlert);
					} else {
						appAlertExist.setAlert_type(apptAlert.getAlert_type());
						appAlertExist.setSender(apptAlert.getSender());
						appAlertExist.setReceiver(apptAlert.getReceiver());
						appAlertExist.setSubject(apptAlert.getSubject());
						appAlertExist.setText(apptAlert.getText());
						appAlertExist.setLast_modified_time(new Timestamp(System.currentTimeMillis()));
						apptAlertRepository.save(appAlertExist);

					}
					if ((apptAlert.getComm_type().equalsIgnoreCase("EMAIL")) && (enableEmailSend)) {
						sendMail(apptMain, apptAlert, null, true);
					}
				}
			} else {
				logger.warn("No alert added to TX_APPT_ALERT table");
			}

			response.setStatusCode(SUCCESSCODE);
			response.setStatusDiscription(SUCCESSDESCRIPTION);
		} else {
			response.setStatusCode(FAILURECODE);
			response.setStatusDiscription("SECRET KEY DOES NOT MATCH !!!");
		}
		logger.debug("createAppointment Service Ended");
		return response;

	}

	@RequestMapping(value = "/updateAppointment", method = RequestMethod.POST)
	public MedicalResponse updateAppointment(@RequestHeader HttpHeaders headers,
			@RequestBody String C3msJsonAppointmentResponse) throws MessagingException, IOException, TemplateException {
		logger.debug("updateAppointment Service Called");
		MedicalResponse response = new MedicalResponse();
		boolean check = headers.containsKey("secret");
		if (!check) {
			logger.info("NO SECRET KEY FOUND IN HEADER");
			response.setStatusCode(FAILURECODE);
			response.setStatusDiscription("NO SECRET KEY FOUND IN HEADER");
			return response;
		}
		String key = headers.getFirst("secret");
		if (key.equalsIgnoreCase(secretKeyValue.trim())) {
			// Insert New Record to TX_MSG table
			Msg apptMsg = new Msg();
			apptMsg.setSrs_sys_cd(C3MS);
			apptMsg.setMessage_type(JSON);
			apptMsg.setSrc_msg_id(BLANKSPACE);
			apptMsg.setSrc_msg_tm(new Timestamp(System.currentTimeMillis()));

			String message = C3msJsonAppointmentResponse.trim();
			/*
			 * try { message = new
			 * SerialBlob(C3msJsonAppointmentResponse.trim().getBytes());
			 * 
			 * } catch (SQLException e) { response.setStatusCode(FAILURECODE);
			 * response.setStatusDiscription(FAILUREDESCRIPTION); }
			 */

			apptMsg.setMessage(message);
			apptMsg.setProcess_tm(new Timestamp(System.currentTimeMillis()));
			msgRepository.save(apptMsg);

			List<ApptAlert> apptAlertList = new ArrayList<ApptAlert>();

			ApptMain updateapptMain = FHIRUtil.parseAppoitmentResponseString(C3msJsonAppointmentResponse,
					apptAlertList);

			ApptMain updateappAlreayExist = apptMainRepository.findUniqueAppointment(updateapptMain.getSrs_sys_cd(),
					updateapptMain.getInst_cd(), updateapptMain.getCase_no(), updateapptMain.getSdh_request_no());
			// TO DO : replaced with seq_no - verify/test
			// apptMain = apptMainRepository.findOne(updateappAlreayExist.getSeq_no());
			if (updateappAlreayExist == null) {
				response.setStatusCode(FAILURECODE);
				response.setStatusDiscription(FAILUREDESCRIPTION);
			} else {
				updateappAlreayExist.setStatus(updateapptMain.getStatus());
				updateappAlreayExist.setLast_modified_time(new Timestamp(System.currentTimeMillis()));
				apptMainRepository.save(updateappAlreayExist);
				response.setStatusCode(SUCCESSCODE);
				response.setStatusDiscription(SUCCESSDESCRIPTION);
			}
			if (!apptAlertList.isEmpty()) {
				for (ApptAlert apptAlert : apptAlertList) {
					ApptAlert appAlertExist = apptAlertRepository.findUniqueApptAlert(apptAlert.getSrs_sys_cd(),
							apptAlert.getInst_cd(), apptAlert.getCase_no(), apptAlert.getSdh_request_no(),
							apptAlert.getComm_type());
					if (appAlertExist == null) {
						apptAlertRepository.save(apptAlert);
						// Insert to TX_APPT_LINK
						ApptLink apptLink = new ApptLink();
						ApptLinkPrimaryKey apptLinkPrimaryKey = new ApptLinkPrimaryKey();
						apptLinkPrimaryKey.setAppt_seq_no(updateappAlreayExist.getSeq_no());
						apptLinkPrimaryKey.setMsg_seq_no(apptMsg.getSeq_no());
						apptLink.setApptLinkPrimaryKey(apptLinkPrimaryKey);
						apptLink.setMsg_reception_tm(new Timestamp(System.currentTimeMillis()));
						apptLinkRepository.save(apptLink);
					} else {
						appAlertExist.setAlert_type(apptAlert.getAlert_type());
						appAlertExist.setSender(apptAlert.getSender());
						appAlertExist.setReceiver(apptAlert.getReceiver());
						appAlertExist.setSubject(apptAlert.getSubject());
						appAlertExist.setText(apptAlert.getText());
						appAlertExist.setLast_modified_time(new Timestamp(System.currentTimeMillis()));
						apptAlertRepository.save(appAlertExist);

						// Insert to TX_APPT_LINK
						ApptLink apptLink = new ApptLink();
						ApptLinkPrimaryKey apptLinkPrimaryKey = new ApptLinkPrimaryKey();
						apptLinkPrimaryKey.setAppt_seq_no(appAlertExist.getSeq_no());
						apptLinkPrimaryKey.setMsg_seq_no(apptMsg.getSeq_no());
						apptLink.setApptLinkPrimaryKey(apptLinkPrimaryKey);
						apptLink.setMsg_reception_tm(new Timestamp(System.currentTimeMillis()));
						apptLinkRepository.save(apptLink);
					}
					if ((apptAlert.getComm_type().equalsIgnoreCase("EMAIL")) && (enableEmailSend)) {
						sendMail(updateapptMain, apptAlert, null, false);
					}

				}
			} else {
				logger.warn("No alert added to TX_APPT_ALERT table");
			}

			response.setStatusCode(SUCCESSCODE);
			response.setStatusDiscription(SUCCESSDESCRIPTION);
		} else {
			response.setStatusCode(FAILURECODE);
			response.setStatusDiscription("SECRET KEY DOES NOT MATCH !!!");
		}
		logger.debug("updateAppointment Service Ended" );
		return response;

	}

	@RequestMapping(value = "/notifyAppointment", method = RequestMethod.POST)
	public MedicalResponse notifyAppointment(@RequestHeader HttpHeaders headers,
			@RequestBody String c3msCommunicationString) throws SerialException, Exception {
		logger.debug("notifyAppointment Service Called");
		
		MedicalResponse response = new MedicalResponse();
		boolean check = headers.containsKey("secret");
		if (!check) {
			logger.info("NO SECRET KEY FOUND IN HEADER");
			response.setStatusCode(FAILURECODE);
			response.setStatusDiscription("NO SECRET KEY FOUND IN HEADER");
			return response;
		}
		String key = headers.getFirst("secret");
		if (key.equalsIgnoreCase(secretKeyValue.trim())) {

			List<ApptAlert> apptAlertList = new ArrayList<>();
			List<EnqAlert> enqAlertList = new ArrayList<>();

			Msg messageInfo = new Msg();
			messageInfo.setSrs_sys_cd(C3MS);
			messageInfo.setSrc_msg_id(BLANKSPACE);
			messageInfo.setSrc_msg_tm(new Timestamp(System.currentTimeMillis()));
			messageInfo.setMessage_type(JSON);
			/*
			 * byte[] c3msCommunicationbye = c3msCommunicationString.getBytes(); Blob blob =
			 * new SerialBlob(c3msCommunicationbye);
			 */
			messageInfo.setMessage(c3msCommunicationString);
			messageInfo.setProcess_tm(new Timestamp(System.currentTimeMillis()));
			msgRepository.save(messageInfo);

			String type = FHIRUtil.parseCommunicationRequestString(c3msCommunicationString, apptAlertList,
					enqAlertList);

			if (type.equalsIgnoreCase(APPOINTMENT)) {

				for (ApptAlert apptAlert : apptAlertList) {
					BigInteger seq_no;
					ApptAlert apptAlertObject = apptAlertRepository.findUniqueApptAlert(apptAlert.getSrs_sys_cd(),
							apptAlert.getInst_cd(), apptAlert.getCase_no(), apptAlert.getSdh_request_no(),
							apptAlert.getComm_type());
					if (apptAlertObject != null) {
						apptAlertObject.setCreation_time(new Timestamp(System.currentTimeMillis()));
						apptAlertObject.setAlert_type(apptAlert.getAlert_type());
						apptAlertObject.setSender(apptAlert.getSender());
						apptAlertObject.setReceiver(apptAlert.getReceiver());
						apptAlertObject.setSubject(apptAlert.getSubject());
						apptAlertObject.setText(apptAlert.getText());
						apptAlertObject.setLast_modified_time(new Timestamp(System.currentTimeMillis()));
						seq_no = apptAlertObject.getSeq_no();
						apptAlertRepository.save(apptAlertObject);
					} else {
						apptAlertRepository.save(apptAlert);
						seq_no = apptAlert.getSeq_no();
					}
					ApptAlertLinkPrimaryKey apptAlertLinkPrimaryKey = new ApptAlertLinkPrimaryKey();
					apptAlertLinkPrimaryKey.setAppt_alert_seq_no(seq_no);
					apptAlertLinkPrimaryKey.setMsg_seq_no(messageInfo.getSeq_no());

					ApptAlertLink apptAlertLink = new ApptAlertLink();
					apptAlertLink.setApptAlertLinkPrimaryKey(apptAlertLinkPrimaryKey);
					apptAlertLink.setMsg_reception_tm(new Timestamp(System.currentTimeMillis()));
					apptAlertLinkRepository.save(apptAlertLink);
					if ((apptAlert.getComm_type().equalsIgnoreCase("EMAIL")) && (enableEmailSend)) {
						sendMail(null, apptAlert, null, false);
					}
				}

			} else if (type.equalsIgnoreCase(SELFHELP)) {
				for (EnqAlert enqAlert : enqAlertList) {
					BigInteger seq_no;
					EnqAlert enqAlertObject = enqAlertRepository.findUniqueEnqAlert(enqAlert.getSrs_sys_cd(),
							enqAlert.getInst_cd(), enqAlert.getCase_no(), enqAlert.getSdh_request_no(),
							enqAlert.getComm_type());
					if (enqAlertObject != null) {
						enqAlertObject.setCreation_time(new Timestamp(System.currentTimeMillis()));
						enqAlertObject.setAlert_type(enqAlert.getAlert_type());
						enqAlertObject.setSender(enqAlert.getSender());
						enqAlertObject.setReceiver(enqAlert.getReceiver());
						enqAlertObject.setSubject(enqAlert.getSubject());
						enqAlertObject.setText(enqAlert.getText());
						enqAlertObject.setLast_modified_time(new Timestamp(System.currentTimeMillis()));
						seq_no = enqAlertObject.getSeq_no();
						enqAlertRepository.save(enqAlertObject);
					} else {
						enqAlertRepository.save(enqAlert);
						seq_no = enqAlert.getSeq_no();
					}

					EnqAlertLinkPrimaryKey enqAlertLinkPrimaryKey = new EnqAlertLinkPrimaryKey();
					enqAlertLinkPrimaryKey.setAppt_enq_seq_no(seq_no);
					enqAlertLinkPrimaryKey.setMsg_seq_no(messageInfo.getSeq_no());

					EnqAlertLink enqAlertLink = new EnqAlertLink();
					enqAlertLink.setEnqAlertLinkPrimaryKey(enqAlertLinkPrimaryKey);
					enqAlertLink.setMsg_reception_tm(new Timestamp(System.currentTimeMillis()));

					enqAlertLinkRepository.save(enqAlertLink);
					if ((enqAlert.getComm_type().equalsIgnoreCase("EMAIL")) && (enableEmailSend)) {
						sendMail(null, null, enqAlert, false);
					}

				}
			} else {
				logger.warn("No alert added to TX_APPT_ALERT or TX_ENQ_ALERT table");
				response.setStatusCode(FAILURECODE);
				response.setStatusDiscription(FAILUREDESCRIPTION);
				return response;
			}

			response.setStatusCode(SUCCESSCODE);
			response.setStatusDiscription(SUCCESSDESCRIPTION);
		} else {
			response.setStatusCode(FAILURECODE);
			response.setStatusDiscription("SECRET KEY DOES NOT MATCH !!!");
		}
		logger.debug("notifyAppointment Service Ended");
		return response;

	}

	@RequestMapping(value = "/getAppointments", method = RequestMethod.POST)
	public MedicalAppointmentResponse getAppointment(@RequestBody MedicalAppointmentRequest request)
			throws ParseException {
		logger.debug("getAppointments Service Called");
		MedicalAppointmentResponse response = new MedicalAppointmentResponse();
		List<ApptMain> apptMainList = new ArrayList<ApptMain>();
		if (request.getIdNo() == null) {
			logger.info("Mandatory field id_no does not exist");
			response.setStatusDescription(FAILUREDESCRIPTION);
			response.setResponseCode(FAILURECODE);
			response.setErrorMessage("Mandatory field id_no does not exist..! ");
			return response;

		} else {

			String salutation = null, dob = null, srs_sys_cd = null;

			if (request.getDob() != null) {
				dob = request.getDob();
			}

			if (request.getSourceSystemCd() != null) {
				srs_sys_cd = request.getSourceSystemCd();
			}

			if (request.getGender() != null) {
				if (request.getGender().equalsIgnoreCase("Male")) {
					salutation = "Mr.";
				} else if (request.getGender().equalsIgnoreCase("Female")) {
					salutation = "Miss.";
				}
			}

			if (dob == null && salutation == null && srs_sys_cd == null) {
				apptMainList = apptMainRepository.findUsingId_no(request.getIdNo());
			} else {

				if (salutation != null && srs_sys_cd != null && dob != null) {
					apptMainList = apptMainRepository.findAppointmentsList(srs_sys_cd, request.getIdNo(), dob,
							salutation);
				} else if (dob != null && salutation != null) {
					apptMainList = apptMainRepository.findUsingId_noANDSalutationAndDob(request.getIdNo(), salutation,
							dob);
				} else if (dob != null && srs_sys_cd != null) {
					apptMainList = apptMainRepository.findUsingId_noANDSrs_sys_cdAndDob(request.getIdNo(), srs_sys_cd,
							dob);
				} else if (salutation != null && srs_sys_cd != null) {
					apptMainList = apptMainRepository.findUsingId_noANDSrs_sys_cdAndSalutation(request.getIdNo(),
							srs_sys_cd, salutation);
				} else if (salutation != null) {
					apptMainList = apptMainRepository.findUsingId_noAndSalutation(request.getIdNo(), salutation);
				} else if (srs_sys_cd != null) {
					apptMainList = apptMainRepository.findUsingId_noANDSrs_sys_cd(request.getIdNo(), srs_sys_cd);
				} else if (dob != null) {
					apptMainList = apptMainRepository.findUsingId_noANDDob(request.getIdNo(), dob);
				}
			}

			if (apptMainList.isEmpty()) {
				response.setStatusDescription(FAILUREDESCRIPTION);
				response.setResponseCode(FAILURECODE);
				response.setErrorMessage("No such Appointment found in TX_APPT_MAIN table");
				return response;
			}

			List<ApptMainJson> apptMainJsonList = new ArrayList<>();
			for (ApptMain apptMain : apptMainList) {
				ApptMainJson apptMainObject = new ApptMainJson();
				// Set values to response Object
				apptMainObject.setSrs_sys_cd(apptMain.getSrs_sys_cd());
				apptMainObject.setInst_cd(apptMain.getInst_cd());
				apptMainObject.setCase_no(apptMain.getCase_no());
				apptMainObject.setSdh_request_no(apptMain.getSdh_request_no());
				apptMainObject.setPatient_first_name(apptMain.getPatient_first_name());
				apptMainObject.setPatient_last_name(apptMain.getPatient_last_name());
				apptMainObject.setDob(apptMain.getDob());
				apptMainObject.setId_no(apptMain.getId_no());
				apptMainObject.setId_type(apptMain.getId_type());
				apptMainObject.setEmail_address(apptMain.getEmail_address());
				apptMainObject.setMobile_no_country_code(apptMain.getMobile_no_country_code());
				apptMainObject.setMobile_no(apptMain.getMobile_no());
				apptMainObject.setCreation_time(FHIRUtil.convertTimeToString(apptMain.getCreation_time()));
				apptMainObject.setLast_modified_time(FHIRUtil.convertTimeToString(apptMain.getLast_modified_time()));
				apptMainObject.setStatus(apptMain.getStatus());
				apptMainObject.setSalutation(apptMain.getSalutation());
				apptMainObject.setDoctor_first_name(apptMain.getDoctor_first_name());
				apptMainObject.setDoctor_last_name(apptMain.getDoctor_last_name());
				apptMainObject.setFacility_cd(apptMain.getFacility_cd());
				apptMainObject.setFacility_name(apptMain.getFacility_name());
				apptMainObject.setFacility_address(apptMain.getFacility_address());
				apptMainObject.setFacility_contact_no(apptMain.getFacility_contact_no());
				apptMainObject.setAppointment_type(apptMain.getAppointment_type());
				apptMainObject.setNatioanlity(apptMain.getNationality());
				apptMainObject.setAppointment_time(FHIRUtil.convertTimeToString(apptMain.getAppointment_time()));
				apptMainJsonList.add(apptMainObject);
			}
			logger.debug("getAppointments Service Ended");
			response.setAppMainObjList(apptMainJsonList);
			response.setStatusDescription(SUCCESSDESCRIPTION);
			response.setResponseCode(SUCCESSCODE);
			return response;
		}
	}

	@RequestMapping(value = "/sendEmailStatusForAppointment", method = RequestMethod.POST)
	public SpecialityAppointmentResponse sendEmailStatusForAppointment(
			@RequestBody SpecialityAppointmentRequest specialityAppointmentRequest)
			throws MessagingException, TemplateNotFoundException, MalformedTemplateNameException,
			freemarker.core.ParseException, IOException, TemplateException {
		logger.debug("sendEmailStatusForAppointment Service Called");
		SpecialityAppointmentResponse appointmentResponse = new SpecialityAppointmentResponse();

		String hospitalName = specialityAppointmentRequest.getHospital();
		List<Institution> institutionList = institutionRepository.findUsingIntitutionName(hospitalName);
		if (institutionList.isEmpty()) {
			logger.info("NO INSTITUION ARE AVAILABLE IN DB");
			appointmentResponse.setStatusCode("001");
			appointmentResponse.setStatusDescription("FAILURE : NO INSTITUION ARE AVAILABLE IN DB");
			return appointmentResponse;
		} else {
			ApptRequest apptRequest = new ApptRequest();
			try {
				Institution institution = institutionList.get(0);
				apptRequest.setFacility_name(hospitalName);
				apptRequest.setFacility_cd(institution.getInstitutionPrimaryKey().getInst_cd());
				apptRequest.setFacility_address(institution.getAddress());

			} catch (IndexOutOfBoundsException iobe) {
				logger.error(iobe.getMessage());
				appointmentResponse.setStatusCode("001");
				appointmentResponse.setStatusDescription("FAILURE : HOSPITAL NAME NOT AVAILABLE");
				return appointmentResponse;
			}
			apptRequest.setSdh_request_no(RandomStringUtils.randomAlphanumeric(10));
			apptRequest.setDoctor_name(specialityAppointmentRequest.getDoctor());
			apptRequest.setFirst_name(specialityAppointmentRequest.getFirstName());
			apptRequest.setLast_name(specialityAppointmentRequest.getLastName());
			apptRequest.setAppointement_type(specialityAppointmentRequest.getAppointmentType());
			apptRequest.setCountry_code_residency(specialityAppointmentRequest.getCountryOfResidence());
			apptRequest.setDob(specialityAppointmentRequest.getDob());
			apptRequest.setEmail_address(specialityAppointmentRequest.getEmail());
			apptRequest.setGender(specialityAppointmentRequest.getGender());
			apptRequest.setId_no(specialityAppointmentRequest.getIdNo());
			apptRequest.setId_type(specialityAppointmentRequest.getIdType());
			apptRequest.setPhone_no_country_code(specialityAppointmentRequest.getMobileNoCountryCode());
			apptRequest.setPhone_no(specialityAppointmentRequest.getMobileNo());
			apptRequest.setNationality(specialityAppointmentRequest.getNationality());
			apptRequest.setAppointment_start_time(specialityAppointmentRequest.getStartTime());
			apptRequest.setAppointment_end_time(specialityAppointmentRequest.getEndTime());
			apptRequest.setLast_modified_time(new Timestamp(System.currentTimeMillis()));
			apptRequest.setCreation_tm(new Timestamp(System.currentTimeMillis()));
			apptRequest.setCreated_by(specialityAppointmentRequest.getSource());
			apptRequest.setAppointment_date(specialityAppointmentRequest.getAppointmentDate());
			apptRequest.setDiagonsis(specialityAppointmentRequest.getCurrentDiognosis());

			specialityAppointmentRepository.save(apptRequest);

			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());

			Map<String, String> model = new HashMap<String, String>();
			model.put("subject", apptRequest.getFacility_name());
			model.put("name", "Admin");
			model.put("source", apptRequest.getCreated_by());
			model.put("hospitalName", apptRequest.getFacility_name());
			model.put("patientName", apptRequest.getFirst_name());
			model.put("email", apptRequest.getEmail_address());
			model.put("nationality", apptRequest.getNationality());
			model.put("gender", apptRequest.getGender());
			model.put("dob", apptRequest.getDob().toString());
			model.put("idNo", apptRequest.getId_no());
			model.put("residency", apptRequest.getCountry_code_residency());
			model.put("contactNo", specialityAppointmentRequest.getMobileNo());
			model.put("type", specialityAppointmentRequest.getAppointmentType());
			model.put("doctorName", specialityAppointmentRequest.getDoctor());
			model.put("appointmentDate", specialityAppointmentRequest.getAppointmentDate().toString());
			model.put("startTime", specialityAppointmentRequest.getStartTime());
			model.put("endTime", specialityAppointmentRequest.getEndTime());

			Template t = freemarkerConfig.getTemplate("SpecialityTemplate.ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

			helper.setFrom(FROMADDRESS);
			helper.setTo(specialityAppointmentRequest.getEmail());
			helper.setSubject(model.get("subject"));
			helper.setText(html, true);

			emailSender.send(message);

			appointmentResponse.setStatusCode("000");
			appointmentResponse.setStatusDescription("SUCCESS");
		}
		logger.debug("sendEmailStatusForAppointment Service Ended");
		return appointmentResponse;

	}

	public Timestamp createTimestamp(Date date, Time time) {
		long timestamp = 0;
		try {
			timestamp = date.getTime() + time.getTime();
		} catch (NullPointerException npe) {
			timestamp = System.currentTimeMillis();
		}
		return new Timestamp(timestamp);

	}

	@SuppressWarnings("deprecation")
	public void sendMail(ApptMain apptMain, ApptAlert apptAlert, EnqAlert enqAlert, boolean check) {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = null;
		try {
			helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			helper.setFrom(FROMADDRESS);

			Map<String, String> model = new HashMap<String, String>();
			String html = "";
			if (check) {

				model.put("name", apptMain.getPatient_first_name());
				model.put("appointmentDate", new SimpleDateFormat("yyyy-MM-dd").format(apptMain.getAppointment_time()));
				model.put("appointmentTime",
						String.valueOf(apptMain.getAppointment_time().getHours() + ":"
								+ apptMain.getAppointment_time().getMinutes() + ":"
								+ apptMain.getAppointment_time().getSeconds()));
				model.put("doctorName", apptMain.getDoctor_first_name());
				model.put("clinicAndHospitalName", apptMain.getFacility_name());
				model.put("clinicAddress", apptMain.getFacility_address());

				Template t = null;

				t = freemarkerConfig.getTemplate("FHIRTemplate.ftl");
				html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
				helper.setTo(apptAlert.getReceiver());
				helper.setSubject(apptAlert.getSubject());

			} else {
				Template t = null;
				t = freemarkerConfig.getTemplate("StaticTemplate.ftl");
				if (apptAlert != null) {
					model.put("emailtext", apptAlert.getText());
					html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
					helper.setTo(apptAlert.getReceiver());
					helper.setSubject(apptAlert.getSubject());
				} else {
					model.put("emailtext", enqAlert.getText());
					html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
					helper.setTo(enqAlert.getReceiver());
					helper.setSubject(enqAlert.getSubject());
				}
			}

			helper.setText(html, true);
			
		} catch (IOException | TemplateException | MessagingException e) {
			logger.debug(e.getMessage());
		}

		emailSender.send(message);
	}

}