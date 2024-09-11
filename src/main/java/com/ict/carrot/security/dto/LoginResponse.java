package com.ict.carrot.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse { // 로그인 성공 시 클라이언트로 보내는 응답

	private String token;

}
