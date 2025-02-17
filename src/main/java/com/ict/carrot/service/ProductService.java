package com.ict.carrot.service;

import com.ict.carrot.model.Product;
import com.ict.carrot.model.User;
import com.ict.carrot.security.dto.DeleteResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

  Product updateProduct(Long productId, Product product, List<MultipartFile> images);

  Product saveProduct(Product product, List<MultipartFile> images, Optional<User> user);

  Optional<Product> getProductDetails(Long productId);

  List<Product> getProducts();

  DeleteResponse deleteProduct(Long productId);
}
