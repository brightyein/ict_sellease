package com.ict.carrot.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

  private HttpStatus status;
  private String message;
}
