package com.ict.carrot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/* 서버에서 커스텀한 HttpStatus 가 클라이언트에 전달되기 위한 전역 예외 처리기 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ApiException.class)
  public ResponseEntity<ErrorResponse> handleApiException(ApiException ex) {
    ErrorCode errorCode = ex.getErrorCode();
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류입니다.");
    return new ResponseEntity<>(errorResponse, errorCode.getStatus());
  }

}
