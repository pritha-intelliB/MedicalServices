package com.parkway.medical.appointment.util;

import java.util.List;

import org.hl7.fhir.dstu3.model.StringType;

public class FacilityAddress {

	private List<StringType> line;
	private String city;
	private String district;
	private String state;
	private String postalCode;
	
	public List<StringType> getLine() {
		return line;
	}
	public void setLine(List<StringType> line) {
		this.line = line;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	@Override
	public String toString() {
		String result = "";
		
		if(line != null)
			result = " Line "+line;
		if(city != null)
			result = result + ", City :"+city;
		if(district != null)
			result = result + ", District :"+district;
		if(state != null)
			result = result +", State :"+state;
		if(postalCode !=null)
			result = result +", Postalcode :"+postalCode;
		if(result.equals(""))
			result = "No Address";
		
		return result+".";	
		
	}
	
}
