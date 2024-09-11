package com.ict.carrot.security.utils;

import com.ict.carrot.model.Product;
import com.ict.carrot.model.User;
import com.ict.carrot.security.service.UserService;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@AllArgsConstructor
public class AuthAspect {

  private final UserService userService;

  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.RUNTIME)
  public @interface AuthenticatedUser {
  }

  @Around("@annotation(AuthenticatedUser)")
  public Object setAuthenticatedUser(ProceedingJoinPoint joinPoint) throws Throwable {
    // 인증된 사용자 정보 추출
    User user = userService.getAuthenticatedUser();

    // 대상 메서드의 인자로 `User`를 설정
    Object[] args = joinPoint.getArgs();
    for (int i = 0; i < args.length; i++) {
      if (args[i] instanceof Product) {
        ((Product) args[i]).setCreator(user);
      }
    }

    return joinPoint.proceed(args);
  }
}
