package com.ict.carrot.service;

import com.ict.carrot.model.Product;
import com.ict.carrot.security.dto.DeleteResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

  Product saveProduct(Product product, List<MultipartFile> images);

  Optional<Product> getProductDetails(Long productId);

  List<Product> getProducts();

  DeleteResponse deleteProduct(Long productId);
}
