package com.parkway.medical.appointment.bo;

import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="tx_enq_alert")
public class EnqAlert {
	
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
	public String getSrs_sys_cd() {
		return srs_sys_cd;
	}
	public void setSrs_sys_cd(String srs_sys_cd) {
		this.srs_sys_cd = srs_sys_cd;
	}
	public String getInst_cd() {
		return inst_cd;
	}
	public void setInst_cd(String inst_cd) {
		this.inst_cd = inst_cd;
	}
	public String getCase_no() {
		return case_no;
	}
	public void setCase_no(String case_no) {
		this.case_no = case_no;
	}
	public String getSdh_request_no() {
		return sdh_request_no;
	}
	public void setSdh_request_no(String sdh_request_no) {
		this.sdh_request_no = sdh_request_no;
	}
	public String getAlert_type() {
		return alert_type;
	}
	public void setAlert_type(String alert_type) {
		this.alert_type = alert_type;
	}
	public String getComm_type() {
		return comm_type;
	}
	public void setComm_type(String comm_type) {
		this.comm_type = comm_type;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Timestamp getCreation_time() {
		return creation_time;
	}
	public void setCreation_time(Timestamp creation_time) {
		this.creation_time = creation_time;
	}
	public Timestamp getLast_modified_time() {
		return last_modified_time;
	}
	public void setLast_modified_time(Timestamp last_modified_time) {
		this.last_modified_time = last_modified_time;
	}
	
}
