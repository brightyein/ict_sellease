package com.ict.carrot.service;

import com.ict.carrot.model.Product;
import com.ict.carrot.model.ItemThumbnail;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ItemThumbnailService {

  List<ItemThumbnail> uploadThumbnail(Product product, List<MultipartFile> images);
}
