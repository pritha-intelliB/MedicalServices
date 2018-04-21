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
@Table(name ="tx_msg")
public class Msg {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private BigInteger seq_no;
	private String srs_sys_cd;
	private String src_msg_id;
	private Timestamp src_msg_tm;
	private String message_type;
	private String message;
	private Timestamp process_tm;
	
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
	 * @return the src_msg_id
	 */
	public String getSrc_msg_id() {
		return src_msg_id;
	}
	/**
	 * @param src_msg_id the src_msg_id to set
	 */
	public void setSrc_msg_id(String src_msg_id) {
		this.src_msg_id = src_msg_id;
	}
	/**
	 * @return the src_msg_tm
	 */
	public Timestamp getSrc_msg_tm() {
		return src_msg_tm;
	}
	/**
	 * @param src_msg_tm the src_msg_tm to set
	 */
	public void setSrc_msg_tm(Timestamp src_msg_tm) {
		this.src_msg_tm = src_msg_tm;
	}
	/**
	 * @return the message_type
	 */
	public String getMessage_type() {
		return message_type;
	}
	/**
	 * @param message_type the message_type to set
	 */
	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the process_tm
	 */
	public Timestamp getProcess_tm() {
		return process_tm;
	}
	/**
	 * @param process_tm the process_tm to set
	 */
	public void setProcess_tm(Timestamp process_tm) {
		this.process_tm = process_tm;
	}
	
	
	
}
