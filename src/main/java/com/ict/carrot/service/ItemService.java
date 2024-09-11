package com.ict.carrot.service;

import com.ict.carrot.model.Product;
import com.ict.carrot.security.dto.DeleteResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

public interface ItemService {

  Product saveItem(Product product, List<MultipartFile> images);

  Optional<Product> getItemDetails(Long itemId);

  List<Product> getItems();

  DeleteResponse deleteItem(Long itemId);
}
