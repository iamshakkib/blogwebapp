package com.shakkib.bloggingwebapp.controllers;


import com.shakkib.bloggingwebapp.entities.User;
import com.shakkib.bloggingwebapp.exceptions.ApiException;
import com.shakkib.bloggingwebapp.helpers.DTOs.UserDTO;
import com.shakkib.bloggingwebapp.helpers.Request.JwtAuthRequest;
import com.shakkib.bloggingwebapp.helpers.Response.JwtAuthResponse;
import com.shakkib.bloggingwebapp.repositories.UserRepository;
import com.shakkib.bloggingwebapp.security.JwtTokenHelper;
import com.shakkib.bloggingwebapp.services.UserService;
import com.shakkib.bloggingwebapp.utilities.AidacsMailSender;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/auth/")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private AidacsMailSender aidacsMailSender;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
		this.authenticate(request.getUsername(), request.getPassword());
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenHelper.generateToken(userDetails);

		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		response.setUser(this.mapper.map((User) userDetails, UserDTO.class));
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);

		try {

			this.authenticationManager.authenticate(authenticationToken);

		} catch (BadCredentialsException e) {
			System.out.println("Invalid Detials !!");
			throw new ApiException("Invalid username or password !!");
		}

	}

	// register new user api

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@Valid @RequestBody UserDTO userDto) {
		String userGotRegisteredOrNot=null;
		try {
			userGotRegisteredOrNot = this.userService.registerNewUser(userDto);
			String email = userDto.getEmail();
			aidacsMailSender.sendMail(email,"Thanks for registering to AIDACS", "Registration Completed");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}catch (Exception e){
			System.out.println("User already exists");
			throw new RuntimeException(e);
		}
		return new ResponseEntity<String>(userGotRegisteredOrNot, HttpStatus.CREATED);
	}

	// get loggedin user data
	@GetMapping("/current-user/")
	public ResponseEntity<UserDTO> getUser(@RequestParam String email) {
		User user = this.userRepository.findByEmail(email).get();
		return new ResponseEntity<UserDTO>(this.mapper.map(user, UserDTO.class), HttpStatus.OK);
	}
}
