/**
 * 
 */
package com.parkway.medical.appointment.bo;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Embeddable;

/**
 * @author nandita
 *
 */
@Embeddable
public class ApptLinkPrimaryKey implements Serializable{

	private static final long serialVersionUID = 1L;
	private BigInteger appt_seq_no;
	private BigInteger msg_seq_no;
	/**
	 * @return the appt_seq_no
	 */
	public BigInteger getAppt_seq_no() {
		return appt_seq_no;
	}
	/**
	 * @param appt_seq_no the appt_seq_no to set
	 */
	public void setAppt_seq_no(BigInteger appt_seq_no) {
		this.appt_seq_no = appt_seq_no;
	}
	/**
	 * @return the msg_seq_no
	 */
	public BigInteger getMsg_seq_no() {
		return msg_seq_no;
	}
	/**
	 * @param msg_seq_no the msg_seq_no to set
	 */
	public void setMsg_seq_no(BigInteger msg_seq_no) {
		this.msg_seq_no = msg_seq_no;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appt_seq_no == null) ? 0 : appt_seq_no.hashCode());
		result = prime * result + ((msg_seq_no == null) ? 0 : msg_seq_no.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApptLinkPrimaryKey other = (ApptLinkPrimaryKey) obj;
		if (appt_seq_no == null) {
			if (other.appt_seq_no != null)
				return false;
		} else if (!appt_seq_no.equals(other.appt_seq_no))
			return false;
		if (msg_seq_no == null) {
			if (other.msg_seq_no != null)
				return false;
		} else if (!msg_seq_no.equals(other.msg_seq_no))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ApptLinkPrimaryKey [appt_seq_no=" + appt_seq_no + ", msg_seq_no=" + msg_seq_no + "]";
	}

	
}
