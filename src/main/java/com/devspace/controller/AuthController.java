/**
 * 
 */
package com.devspace.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devspace.dto.LoginReqDto;
import com.devspace.dto.LoginRespDto;
import com.devspace.dto.SignupReqDto;
import com.devspace.dto.SignupRespDto;
import com.devspace.service.AuthService;
import com.devspace.util.JwtUtil;

import jakarta.validation.Valid;

/**
 * 
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthService authService;

	@Autowired
	private JwtUtil jwtUtil;

	AuthController(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid LoginReqDto req) {
		try {
			Authentication auth = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
			if (auth.isAuthenticated()) {
				String token = jwtUtil.generateToken(req.getUsername());
				return ResponseEntity.ok(new LoginRespDto(token));
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
			}

		} catch (BadCredentialsException exception) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		}
	}

	@PostMapping("/signup")
	public ResponseEntity<SignupRespDto> signup(@RequestBody @Valid SignupReqDto req) {
		LOGGER.info("Recieved sign up request with details :: {}", req.toString());
		return authService.saveUserDetails(req);
	}
}
