package com.ict.carrot.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Orders extends BaseTimeEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(mappedBy = "orders")
  private Pay pay;

  @ManyToOne
  @JoinColumn(name = "buyer_id")
  private User user;

  @Enumerated(EnumType.STRING)
  private OrderAndPayStatus orderAndPayStatus;

  private int totalAmount; // 상품 총 갯수

  private int totalPrice; // 상품 총액
}
