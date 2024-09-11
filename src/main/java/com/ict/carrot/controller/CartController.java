package com.ict.carrot.controller;

import com.ict.carrot.dto.SaveResponseDto;
import com.ict.carrot.model.Cart;
import com.ict.carrot.model.Product;
import com.ict.carrot.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

  private CartService cartService;

  /* 장바구니 추가 */
  @PostMapping
  public ResponseEntity<SaveResponseDto> addCart(@RequestBody Long productId) {
    return ResponseEntity.ok(cartService.saveCart(productId));
  }

}
