package com.ict.carrot.security.utils;

import static com.ict.carrot.exception.ErrorCode.USER_NOT_FOUND;

import com.ict.carrot.exception.ApiException;
import com.ict.carrot.exception.ErrorCode;
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
public class AuthAspect { // 인증된 사용자 확인 aspect

  private final UserService userService;

  @Around("@annotation(AuthenticatedUser)")
  public Object setAuthenticatedUser(ProceedingJoinPoint joinPoint) throws Throwable {
    System.out.println("@AuthenticatedUser 이 발동되었다!!! AuthAspect 도착!!!!");

    try {
      // 인증된 사용자 정보 추출
      User user = userService.getAuthenticatedUser();

      if(user == null) {
        throw new ApiException(ErrorCode.USER_NOT_FOUND);
      }

      // 대상 메서드의 인자로 `User`를 설정
      Object[] args = joinPoint.getArgs();
      for (int i = 0; i < args.length; i++) {
        if (args[i] instanceof Product) {
          ((Product) args[i]).setCreator(user);
        }
      }
      return joinPoint.proceed(args);

    } catch (ClassCastException e) {
      throw new ApiException(USER_NOT_FOUND);
    } catch (Exception e) {
      throw new RuntimeException("상품 등록 중 문제가 발생했습니다.", e);
    }
  }
}
