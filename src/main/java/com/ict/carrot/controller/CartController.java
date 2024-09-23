package com.ict.carrot.controller;

import com.ict.carrot.dto.SaveResponseDto;
import com.ict.carrot.service.CartService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

  private final CartService cartService;

  /* 장바구니 추가 */
  @PostMapping("/add/{productId}")
  public ResponseEntity<SaveResponseDto> addCart(@PathVariable Long productId) {
    System.out.println("장바구니 추가 컨트롤러 도착!");
    System.out.println("productId : " + productId);
    return ResponseEntity.ok(cartService.saveCart(productId, Optional.empty()));
  }

}
