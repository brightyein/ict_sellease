package com.ict.carrot.controller;

import com.ict.carrot.model.Product;
import com.ict.carrot.security.dto.DeleteResponse;
import com.ict.carrot.service.ProductService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

  private final ProductService productService;

  /* 상품 등록 */
  @PostMapping
  public ResponseEntity<Product> addProduct(@ModelAttribute Product item, @RequestParam("images") List<MultipartFile> images) {
    return ResponseEntity.ok(productService.saveProduct(item, images));
  }

  /* 상품 상세 조회 */
  @GetMapping("/{productId}")
  public ResponseEntity<Optional<Product>> getProductDetails(@PathVariable Long productId) {
    Optional<Product> productDetails = productService.getProductDetails(productId);
    return ResponseEntity.ok(productDetails);
  }

  /* 상품 목록 조회 */
  @GetMapping
  public ResponseEntity<List<Product>> getProducts() {
    List<Product> products = productService.getProducts();
    return ResponseEntity.ok(products);
  }

  /* 상품 삭제 */
  @DeleteMapping("/{productId}")
  public ResponseEntity<DeleteResponse> deleteProduct(@PathVariable Long productId) {
    return ResponseEntity.ok(productService.deleteProduct(productId));
  }

  /* 상품 수정 */
  @PutMapping("/{productId}")
  public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product product, @RequestParam("images") List<MultipartFile> images) {
    Product udpateProduct = productService.updateProduct(productId,product,images);
    return ResponseEntity.ok(udpateProduct);
  }

}
