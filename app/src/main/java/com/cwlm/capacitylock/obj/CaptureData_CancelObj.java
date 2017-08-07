package com.cwlm.capacitylock.obj;

/**
 * 
 * @author Administrator
 *
 */
public class CaptureData_CancelObj {
	private String statusCode;//返回到前端的状态码
	private String mess;//返货的错误信息
	private CancelDataObj object;//返回到前端的对象

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}


	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}

	public CancelDataObj getObject() {
		return object;
	}

	public void setObject(CancelDataObj object) {
		this.object = object;
	}
}
