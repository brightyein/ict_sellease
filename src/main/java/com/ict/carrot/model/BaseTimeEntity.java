package com.ict.carrot.model;

import java.time.LocalDateTime;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

  @CreatedDate
  @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
  private LocalDateTime createdDate;

  @LastModifiedDate
  @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
  private LocalDateTime modifiedDate;
}
