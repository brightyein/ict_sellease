package com.ict.carrot.service;

import com.ict.carrot.model.Item;
import com.ict.carrot.model.ItemThumbnail;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ItemThumbnailService {

  List<ItemThumbnail> uploadThumbnail(Item item, List<MultipartFile> images);
}
