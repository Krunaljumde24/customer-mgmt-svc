/**
 * 
 */
package com.devspace.service;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devspace.dto.LoginReqDto;
import com.devspace.dto.LoginRespDto;
import com.devspace.dto.SignupReqDto;
import com.devspace.dto.SignupRespDto;
import com.devspace.entity.User;
import com.devspace.repository.UserRepository;

import jakarta.validation.Valid;

/**
 * 
 */
@Service
public class AuthService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserRepository userRepository;

	public ResponseEntity<SignupRespDto> saveUserDetails(final SignupReqDto req) {
		Optional<User> user = userRepository.findByUsername(req.getEmailId());
		ResponseEntity<SignupRespDto> resp = null;
		if (user.isPresent()) {
			String msg = "User with the enail id " + req.getEmailId() + " already exists.";
			LOGGER.info(msg);
			resp = new ResponseEntity<>(new SignupRespDto(msg), HttpStatus.BAD_REQUEST);
		} else {
			User savedUser = userRepository.save(from(req));
			if (savedUser != null) {
				resp = new ResponseEntity<>(new SignupRespDto("User details saved."), HttpStatus.CREATED);
			} else {
				resp = new ResponseEntity<>(new SignupRespDto("User details saved."), HttpStatus.CREATED);
			}
		}
		return resp;
	}

	public ResponseEntity<LoginRespDto> login(@Valid LoginReqDto req) {

		return null;
	}

	public User from(SignupReqDto req) {
		User user = new User();
		user.setFirstName(req.getFirstName());
		user.setLastName(req.getLastName());
		user.setUsername(req.getEmailId());
		String password = encoder.encode(req.getPassword());
		user.setPassword(password);
		user.setCreatedAt(new Date());
		user.setCreatedBy("PORTAL");
		user.setRole("USER");
		return user;
	}

}
