package com.ict.carrot.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Pay extends BaseTimeEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String paymentType;

  private int paymentPrice;

  @OneToOne
  @JoinColumn(name = "orders_id", referencedColumnName = "id")
  private Orders orders;

  @Enumerated(EnumType.STRING)
  private OrderAndPayStatus orderAndPayStatus;

}
