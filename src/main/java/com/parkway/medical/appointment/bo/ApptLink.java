/**
 * 
 */
package com.parkway.medical.appointment.bo;

import java.sql.Timestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author nandita
 *
 */
@Entity
@Table(name ="tx_appt_link")
public class ApptLink {

	
	
	@EmbeddedId
	ApptLinkPrimaryKey apptLinkPrimaryKey;
	private Timestamp msg_reception_tm;
	/**
	 * @return the apptLinkPrimaryKey
	 */
	public ApptLinkPrimaryKey getApptLinkPrimaryKey() {
		return apptLinkPrimaryKey;
	}
	/**
	 * @param apptLinkPrimaryKey the apptLinkPrimaryKey to set
	 */
	public void setApptLinkPrimaryKey(ApptLinkPrimaryKey apptLinkPrimaryKey) {
		this.apptLinkPrimaryKey = apptLinkPrimaryKey;
	}
	/**
	 * @return the msg_reception_tm
	 */
	public Timestamp getMsg_reception_tm() {
		return msg_reception_tm;
	}
	/**
	 * @param msg_reception_tm the msg_reception_tm to set
	 */
	public void setMsg_reception_tm(Timestamp msg_reception_tm) {
		this.msg_reception_tm = msg_reception_tm;
	}
	
	
}
