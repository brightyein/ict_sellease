package com.ict.carrot.service.Impl;

import static com.ict.carrot.exception.ErrorCode.ITEM_NOT_FOUND;

import com.ict.carrot.exception.ApiException;
import com.ict.carrot.model.Product;
import com.ict.carrot.model.User;
import com.ict.carrot.repository.ProductRepository;
import com.ict.carrot.repository.UserRepository;
import com.ict.carrot.security.dto.DeleteResponse;
import com.ict.carrot.service.ItemService;
import com.ict.carrot.service.ItemThumbnailService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor // final 이 붙은 필드 생성자 자동 주입
public class ItemServiceImpl implements ItemService {

  private final ProductRepository productRepository;
  private final UserRepository userRepository;

  private final ItemThumbnailService itemThumbnailService;

  /* 상품 등록 */
  @Override
  public Product saveItem(Product product, List<MultipartFile> images) {
    // 인증된 사용자 정보 추출
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();

    // 추출한 정보로 User 객체 조회
    User user = userRepository.findByUsername(userDetails.getUsername());
    product.setCreator(user);

    // 상품 등록
    Product saveProduct = productRepository.save(product);

    // 이미지 저장
    saveProduct.setItemThumbnails(itemThumbnailService.uploadThumbnail(saveProduct, images));

    return saveProduct;
  }

  /* 상품 상세 조회 */
  @Override
  public Optional<Product> getItemDetails(Long itemId) {
    return Optional.ofNullable(productRepository.findById(itemId)
        .orElseThrow(() -> new ApiException(ITEM_NOT_FOUND)));
  }

  /* 상품 목록 조회 */
  @Override
  public List<Product> getItems() {
    return productRepository.findAll();
  }

  /* 상품 삭제 */
  @Override
  public DeleteResponse deleteItem(Long itemId) {

    DeleteResponse deleteResponse = new DeleteResponse();
    deleteResponse.setData(productRepository.findById(itemId));

    try {
      productRepository.deleteById(itemId);
      deleteResponse.setResult(true);
      deleteResponse.setMessage("상품이 삭제되었습니다.");

    } catch (EmptyResultDataAccessException e) {
      deleteResponse.setResult(false);
      deleteResponse.setMessage(ITEM_NOT_FOUND.getMessage());

    } catch (Exception e) {
      deleteResponse.setResult(false);
      deleteResponse.setMessage(e.getMessage());
    }

    return deleteResponse;
  }

}
