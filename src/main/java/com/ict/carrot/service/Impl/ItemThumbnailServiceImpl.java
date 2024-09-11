package com.ict.carrot.service.Impl;

import com.ict.carrot.model.Product;
import com.ict.carrot.model.ItemThumbnail;
import com.ict.carrot.repository.ItemThumbnailRepository;
import com.ict.carrot.service.ItemThumbnailService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ItemThumbnailServiceImpl implements ItemThumbnailService {

  private final ItemThumbnailRepository itemThumbnailRepository;

  /* thumbnail 객체 db 저장 */
  @Override
  public List<ItemThumbnail> uploadThumbnail(Product product, List<MultipartFile> images) {
    List<ItemThumbnail> itemThumbnails = new ArrayList<>();
    try {
      // 이미지 저장 경로
      String uploadsDir = "src/main/front/public/images/itemThumbnail/";

      // db 에 thumbnail 객체 저장
      for(MultipartFile image : images) {
        String dbFilePath = saveImage(image, uploadsDir);
        ItemThumbnail thumbnail = new ItemThumbnail(product, dbFilePath);
        itemThumbnails.add(itemThumbnailRepository.save(thumbnail));
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    return itemThumbnails;
  }

  /* 이미지 저장 */
  private String saveImage(MultipartFile image, String uploadsDir) throws IOException {
    // 저장할 파일명 생성
    String fileName = UUID.randomUUID().toString().replace("-","") + "_" + image.getOriginalFilename();
    // 경로 생성
    String filePath = uploadsDir + fileName;
    // db에 저장할 경로
    String dbFilePath = "/images/itemThumbnail/" + fileName;

    // Path 객체 생성
    Path path = Paths.get(filePath);
    // 디렉토리 생성
    Files.createDirectories(path.getParent());
    // 디렉토리에 파일 저장
    Files.write(path, image.getBytes());

    return dbFilePath;
  }
}
