package com.ict.carrot.service;

import com.ict.carrot.dto.SaveResponseDto;
import com.ict.carrot.model.User;
import java.util.Optional;

public interface CartService {

  SaveResponseDto saveCart(Long productId, Optional<User> user);
}