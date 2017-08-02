package com.cwlm.capacitylock.model;

import java.util.List;


/**
 * 父类的数据模型
 */
public class BaseModel {
	protected String mess;// 错误信息
	protected String statusCode;// 错误码
	protected int infCode;// 接口变量

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public int getInfCode() {
		return infCode;
	}

	public void setInfCode(int infCode) {
		this.infCode = infCode;
	}
}
