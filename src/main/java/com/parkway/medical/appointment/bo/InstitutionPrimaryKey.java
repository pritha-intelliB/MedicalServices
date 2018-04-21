/**
 * 
 */
package com.parkway.medical.appointment.bo;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * @author nandita
 *
 */
@Embeddable
public class InstitutionPrimaryKey implements Serializable {

	private static final long serialVersionUID = 1L;
	private String country_code;
	private String inst_cd;
	private String language_code;

	/**
	 * @return the country_code
	 */
	public String getCountry_code() {
		return country_code;
	}

	/**
	 * @param country_code
	 *            the country_code to set
	 */
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country_code == null) ? 0 : country_code.hashCode());
		result = prime * result + ((inst_cd == null) ? 0 : inst_cd.hashCode());
		result = prime * result + ((language_code == null) ? 0 : language_code.hashCode());
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
		InstitutionPrimaryKey other = (InstitutionPrimaryKey) obj;
		if (country_code == null) {
			if (other.country_code != null)
				return false;
		} else if (!country_code.equals(other.country_code))
			return false;
		if (inst_cd == null) {
			if (other.inst_cd != null)
				return false;
		} else if (!inst_cd.equals(other.inst_cd))
			return false;
		if (language_code == null) {
			if (other.language_code != null)
				return false;
		} else if (!language_code.equals(other.language_code))
			return false;
		return true;
	}

	/**
	 * @return the inst_cd
	 */
	public String getInst_cd() {
		return inst_cd;
	}

	/**
	 * @param inst_cd
	 *            the inst_cd to set
	 */
	public void setInst_cd(String inst_cd) {
		this.inst_cd = inst_cd;
	}

	/**
	 * @return the language_code
	 */
	public String getLanguage_code() {
		return language_code;
	}

	/**
	 * @param language_code
	 *            the language_code to set
	 */
	public void setLanguage_code(String language_code) {
		this.language_code = language_code;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InstitutionPrimaryKey [country_code=" + country_code + ", inst_cd=" + inst_cd + ", language_code="
				+ language_code + "]";
	}

}
