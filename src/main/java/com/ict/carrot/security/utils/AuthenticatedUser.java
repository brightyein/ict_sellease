package com.ict.carrot.security.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)  // 파라미터에 적용할 수 있게 지정
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthenticatedUser {

}
