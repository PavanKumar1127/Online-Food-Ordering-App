package com.pavan.foodie.onlineordering.response;

import lombok.Data;

@Data
public class MessageResponse {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
