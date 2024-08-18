package com.shakkib.bloggingwebapp.helpers.Response;

import com.shakkib.bloggingwebapp.helpers.DTOs.UserDTO;
import lombok.Data;

@Data
public class JwtAuthResponse {

	private String token;
	
	private UserDTO user;
}
