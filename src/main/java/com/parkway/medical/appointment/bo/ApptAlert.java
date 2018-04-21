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
@Table(name ="tx_appt_alert")
public class ApptAlert {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private BigInteger seq_no;
	private String srs_sys_cd;
	private String inst_cd;
	private String case_no;
	private String sdh_request_no;
	private String alert_type;
	private String comm_type;
	private String sender;
	private String receiver;
	private String subject;
	private String text;
	private Timestamp creation_time;
	private Timestamp last_modified_time;
	
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
	 * @return the alert_type
	 */
	public String getAlert_type() {
		return alert_type;
	}
	/**
	 * @param alert_type the alert_type to set
	 */
	public void setAlert_type(String alert_type) {
		this.alert_type = alert_type;
	}
	/**
	 * @return the comm_type
	 */
	public String getComm_type() {
		return comm_type;
	}
	/**
	 * @param comm_type the comm_type to set
	 */
	public void setComm_type(String comm_type) {
		this.comm_type = comm_type;
	}
	/**
	 * @return the sender
	 */
	public String getSender() {
		return sender;
	}
	/**
	 * @param sender the sender to set
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}
	/**
	 * @return the receiver
	 */
	public String getReceiver() {
		return receiver;
	}
	/**
	 * @param receiver the receiver to set
	 */
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
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
	
	
}
