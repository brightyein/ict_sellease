package com.ict.carrot.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
  private List<Product> products;

  private int totalAmount; // 상품 총 갯수

  private int totalPrice; // 상품 총액


}
