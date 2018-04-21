package com.parkway.medical.appointment.bo;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Embeddable;

@Embeddable
public class ApptAlertLinkPrimaryKey implements Serializable {

	private static final long serialVersionUID = 1L;

	private BigInteger appt_alert_seq_no;

	private BigInteger msg_seq_no;

	public BigInteger getAppt_alert_seq_no() {
		return appt_alert_seq_no;
	}

	public void setAppt_alert_seq_no(BigInteger appt_alert_seq_no) {
		this.appt_alert_seq_no = appt_alert_seq_no;
	}

	public BigInteger getMsg_seq_no() {
		return msg_seq_no;
	}

	public void setMsg_seq_no(BigInteger msg_seq_no) {
		this.msg_seq_no = msg_seq_no;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appt_alert_seq_no == null) ? 0 : appt_alert_seq_no.hashCode());
		result = prime * result + ((msg_seq_no == null) ? 0 : msg_seq_no.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApptAlertLinkPrimaryKey other = (ApptAlertLinkPrimaryKey) obj;
		if (appt_alert_seq_no == null) {
			if (other.appt_alert_seq_no != null)
				return false;
		} else if (!appt_alert_seq_no.equals(other.appt_alert_seq_no))
			return false;
		if (msg_seq_no == null) {
			if (other.msg_seq_no != null)
				return false;
		} else if (!msg_seq_no.equals(other.msg_seq_no))
			return false;
		return true;
	}

}
