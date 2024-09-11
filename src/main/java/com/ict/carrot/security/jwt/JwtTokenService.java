package com.ict.carrot.security.jwt;

import com.ict.carrot.model.User;
import com.ict.carrot.security.dto.LoginRequest;
import com.ict.carrot.security.mapper.UserMapper;
import com.ict.carrot.security.service.UserService;
import com.ict.carrot.security.dto.AuthenticatedUserDto;
import com.ict.carrot.security.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

// rimmel asghar
@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenService {

	private final UserService userService;
	private final JwtTokenManager jwtTokenManager; // 인증된 사용자 정보 기반 JWT 토큰 생성
	private final AuthenticationManager authenticationManager; // 사용자의 인증 요청을 처리

	public LoginResponse getLoginResponse(LoginRequest loginRequest) {

		final String username = loginRequest.getUsername();
		final String password = loginRequest.getPassword();

		// UsernamePasswordAuthenticationToken : Spring Security에서 제공하는 인증(Authentication) 객체
		// 인증 전에 사용자의 자격 증명(아이디, 비밀번호) 를 담는다. 이때 authentication = false
		final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);

		// authenticationManager 에게 인증을 요청
		// 인증 실패 시 예외 발생
		authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		// 인증된 사용자 Dto 에 담는다
		final AuthenticatedUserDto authenticatedUserDto = userService.findAuthenticatedUserByUsername(username);

		final User user = UserMapper.INSTANCE.convertToUser(authenticatedUserDto);
		final String token = jwtTokenManager.generateToken(user);

		log.info("{} has successfully logged in!", user.getUsername());

		return new LoginResponse(token);
	}

}
