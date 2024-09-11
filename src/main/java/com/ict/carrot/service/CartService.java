package com.ict.carrot.service;

import com.ict.carrot.dto.SaveResponseDto;

public interface CartService {

  SaveResponseDto saveCart(Long productId);
}
