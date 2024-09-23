package com.ict.carrot.controller;

import com.ict.carrot.dto.SaveResponseDto;
import com.ict.carrot.model.Cart;
import com.ict.carrot.service.CartService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    return ResponseEntity.ok(cartService.saveCart(productId, Optional.empty()));
  }

  /* 장바구니 조회 */
  @GetMapping("/{userId}")
  public ResponseEntity<Optional<Cart>> getCart(@PathVariable Long userId) {
    return ResponseEntity.ok(cartService.getCart(userId, Optional.empty()));
  }

}
