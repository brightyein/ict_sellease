package com.ict.carrot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table
public class ItemThumbnail extends BaseTimeEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String imagePath;

  @ManyToOne
  @JoinColumn(name = "item_id", nullable = false)
  @JsonIgnore
  private Product product;

  public ItemThumbnail(Product product, String imagePath) {
    this.product = product;
    this.imagePath = imagePath;
  }
}
