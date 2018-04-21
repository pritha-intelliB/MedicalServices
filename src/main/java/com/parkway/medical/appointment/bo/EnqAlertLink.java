package com.parkway.medical.appointment.bo;

import java.sql.Timestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tx_enq_alert_link")
public class EnqAlertLink {

	@EmbeddedId
	private EnqAlertLinkPrimaryKey enqAlertLinkPrimaryKey;
	private Timestamp msg_reception_tm;
	public EnqAlertLinkPrimaryKey getEnqAlertLinkPrimaryKey() {
		return enqAlertLinkPrimaryKey;
	}
	public void setEnqAlertLinkPrimaryKey(EnqAlertLinkPrimaryKey enqAlertLinkPrimaryKey) {
		this.enqAlertLinkPrimaryKey = enqAlertLinkPrimaryKey;
	}
	public Timestamp getMsg_reception_tm() {
		return msg_reception_tm;
	}
	public void setMsg_reception_tm(Timestamp msg_reception_tm) {
		this.msg_reception_tm = msg_reception_tm;
	}
	
	
	
}
