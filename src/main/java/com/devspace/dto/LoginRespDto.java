/**
 * 
 */
package com.devspace.dto;

/**
 * 
 */
public class LoginRespDto {

	private String token;

	public LoginRespDto(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
