package com.ict.carrot.controller;

import com.ict.carrot.security.dto.LoginRequest;
import com.ict.carrot.security.dto.LoginResponse;
import com.ict.carrot.security.jwt.JwtTokenService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

	private final JwtTokenService jwtTokenService;

	@PostMapping
	public ResponseEntity<LoginResponse> loginRequest(@Valid @RequestBody LoginRequest loginRequest) {
		final LoginResponse loginResponse = jwtTokenService.getLoginResponse(loginRequest);
		return ResponseEntity.ok(loginResponse);
	}

}
