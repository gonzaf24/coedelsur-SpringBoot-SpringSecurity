package com.coedelsur.form;

import java.io.Serializable;

public class MessageForm extends ClinicasTemplateForm implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String ERROR_KO = "ERROR-KO";
	public static final String INFO_OK = "INFO-OK";

	private String severity;
	private String message;

	public MessageForm() {
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
