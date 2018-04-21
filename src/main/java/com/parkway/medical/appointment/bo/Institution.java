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
@Table(name="CD_INSTITUTION")
public class Institution {
	
	@EmbeddedId
	InstitutionPrimaryKey institutionPrimaryKey;
	
	private String inst_name;
	private String display_name;
	private String gst_registration_no;
	private String biz_registration_no;
	private String address;
	private Timestamp last_action_time;
	private String last_action_by;
	private String last_action;
	/**
	 * @return the institutionPrimaryKey
	 */
	public InstitutionPrimaryKey getInstitutionPrimaryKey() {
		return institutionPrimaryKey;
	}
	/**
	 * @param institutionPrimaryKey the institutionPrimaryKey to set
	 */
	public void setInstitutionPrimaryKey(InstitutionPrimaryKey institutionPrimaryKey) {
		this.institutionPrimaryKey = institutionPrimaryKey;
	}
	/**
	 * @return the inst_name
	 */
	public String getInst_name() {
		return inst_name;
	}
	/**
	 * @param inst_name the inst_name to set
	 */
	public void setInst_name(String inst_name) {
		this.inst_name = inst_name;
	}
	/**
	 * @return the display_name
	 */
	public String getDisplay_name() {
		return display_name;
	}
	/**
	 * @param display_name the display_name to set
	 */
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	/**
	 * @return the gst_registration_no
	 */
	public String getGst_registration_no() {
		return gst_registration_no;
	}
	/**
	 * @param gst_registration_no the gst_registration_no to set
	 */
	public void setGst_registration_no(String gst_registration_no) {
		this.gst_registration_no = gst_registration_no;
	}
	/**
	 * @return the biz_registration_no
	 */
	public String getBiz_registration_no() {
		return biz_registration_no;
	}
	/**
	 * @param biz_registration_no the biz_registration_no to set
	 */
	public void setBiz_registration_no(String biz_registration_no) {
		this.biz_registration_no = biz_registration_no;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the last_action_time
	 */
	public Timestamp getLast_action_time() {
		return last_action_time;
	}
	/**
	 * @param last_action_time the last_action_time to set
	 */
	public void setLast_action_time(Timestamp last_action_time) {
		this.last_action_time = last_action_time;
	}
	/**
	 * @return the last_action_by
	 */
	public String getLast_action_by() {
		return last_action_by;
	}
	/**
	 * @param last_action_by the last_action_by to set
	 */
	public void setLast_action_by(String last_action_by) {
		this.last_action_by = last_action_by;
	}
	/**
	 * @return the last_action
	 */
	public String getLast_action() {
		return last_action;
	}
	/**
	 * @param last_action the last_action to set
	 */
	public void setLast_action(String last_action) {
		this.last_action = last_action;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Institution [institutionPrimaryKey=" + institutionPrimaryKey + ", inst_name=" + inst_name
				+ ", display_name=" + display_name + ", gst_registration_no=" + gst_registration_no
				+ ", biz_registration_no=" + biz_registration_no + ", address=" + address + ", last_action_time="
				+ last_action_time + ", last_action_by=" + last_action_by + ", last_action=" + last_action + "]";
	}
	
	

}
