package com.cs.ecom.model.helping;

public class RestResponse {

	
	private String status;
	private String code;
	private String message;
	private Object result;

	public RestResponse() {
	}

	public RestResponse(String status, String code, String message, Object result) {
		this.status = status;
		this.code = code;
		this.message = message;
		this.result = result;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "RestResponse [status=" + status + ", code=" + code + ", message=" + message + ", result=" + result
				+ "]";
	}
	
	
}
