package com.ict.carrot.controller;

import com.ict.carrot.model.Product;
import com.ict.carrot.security.dto.DeleteResponse;
import com.ict.carrot.service.ItemService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@Controller
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

  private final ItemService itemService;

  /* 상품 등록 */
  @PostMapping
  public ResponseEntity<Product> addItem(@ModelAttribute Product product, @RequestParam("images") List<MultipartFile> images) {
    Product saveProduct = itemService.saveItem(product, images);
    return ResponseEntity.ok(saveProduct);
  }

  /* 상품 상세 조회 */
  @GetMapping("/{itemId}")
  public ResponseEntity<Optional<Product>> getItemDetails(@PathVariable Long itemId) {
    Optional<Product> itemDetails = itemService.getItemDetails(itemId);
    return ResponseEntity.ok(itemDetails);
  }

  /* 상품 목록 조회 */
  @GetMapping
  public ResponseEntity<List<Product>> getItems() {
    List<Product> products = itemService.getItems();
    return ResponseEntity.ok(products);
  }

  /* 상품 삭제 */
  @DeleteMapping("/{itemId}")
  public ResponseEntity<DeleteResponse> deleteItem(@PathVariable Long itemId) {
    return ResponseEntity.ok(itemService.deleteItem(itemId));
  }

}
