package com.ict.carrot.service.Impl;

import com.ict.carrot.dto.SaveResponseDto;
import com.ict.carrot.model.Cart;
import com.ict.carrot.model.Product;
import com.ict.carrot.repository.CartRepository;
import com.ict.carrot.repository.ProductRepository;
import com.ict.carrot.security.service.UserService;
import com.ict.carrot.security.utils.AuthenticatedUser;
import com.ict.carrot.service.CartService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

  private final CartRepository cartRepository;

  private final ProductRepository productRepository;

  private final UserService userService;

  @Override
  @AuthenticatedUser
  public SaveResponseDto saveCart(Long productId) {

    Cart cart = new Cart();
    List<Product> productList = new ArrayList<>();
    productList.add(productRepository.findById(productId).get());
    cart.setProducts(productList);

    return null;
  }
}
