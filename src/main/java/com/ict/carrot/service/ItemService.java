package com.ict.carrot.service;

import com.ict.carrot.model.Item;
import com.ict.carrot.security.dto.DeleteResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

public interface ItemService {

  Item saveItem(Item item, List<MultipartFile> images);

  Optional<Item> getItemDetails(Long itemId);

  List<Item> getItems();

  DeleteResponse deleteItem(Long itemId);
}
