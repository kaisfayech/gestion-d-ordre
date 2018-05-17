package com.example.demo.models;

public class SecurityType {
	protected String externalCode;
	protected String label;
	public SecurityType(String externalCode, String label) {
		this.externalCode=externalCode;
		this.label=label;
	}
	public String getExternalCode() {
		return externalCode;
	}
	public void setExternalCode(String externalCode) {
		this.externalCode = externalCode;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}

}
