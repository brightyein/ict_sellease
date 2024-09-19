package com.ict.carrot.security.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* 현재 인증된 사용자 정보를 자동으로 설정 */
@Target(ElementType.METHOD)  // 파라미터에 적용할 수 있게 지정
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthenticatedUser {

}
