package com.bpbbank.pasqyra.servisi;

public class ClientReportInfo {

	private String number;
	
	private String label;
	
	private String value;
	
	private String occurance;
	
	private String parameterName;
	
	public ClientReportInfo() {
		
	}
	
	public ClientReportInfo(String number, String label, String value, String occurance, String parameterName) {
		super();
		this.number = number;
		this.label = label;
		this.value = value;
		this.occurance = occurance;
		this.parameterName = parameterName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getOccurance() {
		return occurance;
	}

	public void setOccurance(String occurance) {
		this.occurance = occurance;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
}
