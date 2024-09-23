package com.ict.carrot.repository;

import com.ict.carrot.model.Cart;
import com.ict.carrot.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
  Optional<Cart> findByUser(Optional<User> user);
}
