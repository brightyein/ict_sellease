package com.ict.carrot.security.service;

import com.ict.carrot.exception.ApiException;
import com.ict.carrot.exception.ErrorCode;
import com.ict.carrot.model.User;
import com.ict.carrot.model.UserRole;
import com.ict.carrot.service.UserValidationService;
import com.ict.carrot.utils.GeneralMessageAccessor;
import com.ict.carrot.security.dto.AuthenticatedUserDto;
import com.ict.carrot.security.dto.RegistrationRequest;
import com.ict.carrot.security.dto.RegistrationResponse;
import com.ict.carrot.security.mapper.UserMapper;
import com.ict.carrot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

// rimmel asghar
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private static final String REGISTRATION_SUCCESSFUL = "registration_successful";

	private final UserRepository userRepository;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	private final UserValidationService userValidationService;

	private final GeneralMessageAccessor generalMessageAccessor;

	@Override
	public User findByUsername(String username) {

		return userRepository.findByUsername(username);
	}

	@Override
	public RegistrationResponse registration(RegistrationRequest registrationRequest) {

		userValidationService.validateUser(registrationRequest);

		final User user = UserMapper.INSTANCE.convertToUser(registrationRequest);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setUserRole(UserRole.USER);

		userRepository.save(user);

		final String username = registrationRequest.getUsername();
		final String registrationSuccessMessage = generalMessageAccessor.getMessage(null, REGISTRATION_SUCCESSFUL, username);

		log.info("{} registered successfully!", username);

		return new RegistrationResponse(registrationSuccessMessage);
	}

	@Override
	public AuthenticatedUserDto findAuthenticatedUserByUsername(String username) {

		final User user = findByUsername(username);

		return UserMapper.INSTANCE.convertToAuthenticatedUserDto(user);
	}

	@Override
	public User getAuthenticatedUser() {
		// 인증된 사용자 정보 추출
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()) {
			throw new ApiException(ErrorCode.UNAUTHORIZED);
		}

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String username = userDetails.getUsername();

		// 추출한 정보로 User 객체 조회
		User user = userRepository.findByUsername(username);

		if (user == null) {
			throw new ApiException(ErrorCode.USER_NOT_FOUND);
		}

		return user;
	}
}
