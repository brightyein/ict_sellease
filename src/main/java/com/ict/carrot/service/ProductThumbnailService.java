package com.ict.carrot.service;

import com.ict.carrot.model.Product;
import com.ict.carrot.model.ProductThumbnail;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ProductThumbnailService {

  List<ProductThumbnail> uploadThumbnail(Product product, List<MultipartFile> images);
}
