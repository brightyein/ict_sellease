package com.ict.carrot.security.service;

import com.ict.carrot.model.User;
import com.ict.carrot.security.dto.AuthenticatedUserDto;
import com.ict.carrot.security.dto.RegistrationRequest;
import com.ict.carrot.security.dto.RegistrationResponse;

// rimmel asghar
public interface UserService {

	User findByUsername(String username);

	RegistrationResponse registration(RegistrationRequest registrationRequest);

	AuthenticatedUserDto findAuthenticatedUserByUsername(String username);

}
