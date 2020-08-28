package com.learning.bean;

import java.io.Serializable;

import com.google.gson.Gson;

public class ResultObj implements Serializable {
	
	private Integer status =0;
	private String error="SUCCESS";
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

}
