package com.shakkib.bloggingwebapp.helpers.Request;

import lombok.Data;

@Data
public class JwtAuthRequest {

	private String username;
	
	private String password;
	
}
