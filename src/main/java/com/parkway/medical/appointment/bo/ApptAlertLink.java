package com.parkway.medical.appointment.bo;

import java.sql.Timestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name ="tx_appt_alert_link")
public class ApptAlertLink {

	@EmbeddedId
	private ApptAlertLinkPrimaryKey apptAlertLinkPrimaryKey;
	
	private Timestamp msg_reception_tm;

	public ApptAlertLinkPrimaryKey getApptAlertLinkPrimaryKey() {
		return apptAlertLinkPrimaryKey;
	}

	public void setApptAlertLinkPrimaryKey(ApptAlertLinkPrimaryKey apptAlertLinkPrimaryKey) {
		this.apptAlertLinkPrimaryKey = apptAlertLinkPrimaryKey;
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
