package com.ict.carrot.exception;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
  PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 아이템입니다."), // 404
  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."), // 404
  UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증되지 않은 사용자입니다."); // 401

  private final HttpStatus status;
  private final LocalDateTime time;
  private final String message;

  ErrorCode(HttpStatus status, String message) {
    this.status = status;
    this.message = message;
    this.time = LocalDateTime.now(); // 현재 시간 할당
  }

}
