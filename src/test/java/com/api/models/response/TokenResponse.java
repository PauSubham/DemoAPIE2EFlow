package com.api.models.response;

public class TokenResponse {
	
	private String token;

	public TokenResponse(String token) {
		super();
		this.token = token;
	}

	public TokenResponse() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "TokenResponse [token=" + token + "]";
	}

}
