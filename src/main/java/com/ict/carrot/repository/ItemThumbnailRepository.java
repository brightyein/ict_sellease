package com.ict.carrot.repository;

import com.ict.carrot.model.ProductThumbnail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemThumbnailRepository extends JpaRepository<ProductThumbnail, Long> {

}
