package com.ict.carrot.security.jwt;

import com.ict.carrot.security.dto.TokenValidationResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
  private final JwtTokenManager jwtTokenManager;

  @PostMapping("/validate-token")
  public ResponseEntity<?> validateToken(@RequestBody TokenRequest tokenRequest) {
    System.out.println("auth/validate-token 도착");
    boolean isValid = jwtTokenManager.validateToken(tokenRequest.getToken(), tokenRequest.username);

    if (isValid) {
      System.out.println("isValid : " + isValid);
      return ResponseEntity.ok().body(new TokenValidationResponse(true));
    } else {
      System.out.println("isValid : " + isValid);
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new TokenValidationResponse(false));
    }
  }

  @Getter
  @Setter
  public static class TokenRequest {
    private String token;
    private String username;
  }
}
