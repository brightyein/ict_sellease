package com.ict.carrot.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// rimmel asghar
@Getter
@RequiredArgsConstructor
public class RegistrationException extends RuntimeException {

	private final String errorMessage;

}
