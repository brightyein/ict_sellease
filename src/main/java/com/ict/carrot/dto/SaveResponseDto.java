package com.ict.carrot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveResponseDto<T> {

  private T data;
  private boolean result;
  private String message;

}
