package com.ict.carrot.security.dto;

import com.ict.carrot.model.UserRole;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticatedUserDto {

	private Long id;
	private String name;
	private String username;
	private String password;
	private UserRole userRole;

}
