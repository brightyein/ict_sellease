package com.ict.carrot.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Item extends BaseTimeEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String itemName;

  @PositiveOrZero
  @NotNull(message = "가격은 필수 입력 값 입니다.")
  private int price;

  @Size(max = 500)
  private String description;

  // User 와 다대일 관계 (여러 상품들은 한 명의 유저에 의해 생성)
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "creator_id")
  private User creator;

  // ItemThumbnail 과 일대다 관계 (하나의 상품은 여러 이미지를 가질 수 있음)
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ItemThumbnail> itemThumbnails = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "cart_id")
  private Cart cart;

  @ManyToOne
  @JoinColumn(name = "orders_id") // 외래 키 설정
  private Orders orders; // Order와의 관계를 매핑

}
