package com.ict.carrot.service.Impl;

import static com.ict.carrot.exception.ErrorCode.PRODUCT_NOT_FOUND;

import com.ict.carrot.dto.SaveResponseDto;
import com.ict.carrot.exception.ApiException;
import com.ict.carrot.model.Cart;
import com.ict.carrot.model.CartItem;
import com.ict.carrot.model.Product;
import com.ict.carrot.model.User;
import com.ict.carrot.repository.CartRepository;
import com.ict.carrot.repository.ProductRepository;
import com.ict.carrot.security.utils.AuthenticatedUser;
import com.ict.carrot.service.CartService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

  private final CartRepository cartRepository;

  private final ProductRepository productRepository;

  @Override
  @AuthenticatedUser
  public SaveResponseDto saveCart(Long productId, Optional<User> user) {

    // 1. 해당 상품이 존재하는지 확인 (Optional 사용)
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ApiException(PRODUCT_NOT_FOUND));

    // 2. 사용자의 기존 장바구니가 있는지 확인
    Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
      Cart newCart = new Cart();
      user.ifPresent( newCart::setUser);
      return cartRepository.save(newCart);
    });

    // 3. 장바구니에 상품 추가 (중복 상품 체크)
    List<CartItem> cartItems = cart.getItems();
    if (cartItems == null) {
      cartItems = new ArrayList<>();  // 빈 리스트로 초기화
    }

    boolean isProductInCart = false; // 중복 상품 체크 플래그

    for (CartItem cartItem : cartItems) {
      if (cartItem.getProduct().equals(product)) {
        isProductInCart = true;
        break;
      }
    }

    if(!isProductInCart) {
      CartItem newCartItem = new CartItem();
      newCartItem.setProduct(product);
      newCartItem.setCart(cart);
      cartItems.add(newCartItem);
      cart.setItems(cartItems);
      cartRepository.save(cart);
      return new SaveResponseDto(cart, true, "Product added to cart successfully");
    } else return new SaveResponseDto(null, false, "Product already in cart");
  }
}
