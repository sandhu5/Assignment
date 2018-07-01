package com.example.assignment.common.utility;

public enum ServiceStatusType {

	ACTIVE("ACTIVE"), INACTIVE("INACTIVE") , ALL("ALL");

	private String value;

	ServiceStatusType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
