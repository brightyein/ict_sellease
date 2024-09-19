package com.ict.carrot.service.Impl;

import static com.ict.carrot.exception.ErrorCode.PRODUCT_NOT_FOUND;
import static com.ict.carrot.exception.ErrorCode.UNAUTHORIZED;
import static com.ict.carrot.exception.ErrorCode.USER_NOT_FOUND;

import com.ict.carrot.exception.ApiException;
import com.ict.carrot.model.Product;
import com.ict.carrot.model.User;
import com.ict.carrot.repository.ProductRepository;
import com.ict.carrot.repository.UserRepository;
import com.ict.carrot.security.dto.DeleteResponse;
import com.ict.carrot.security.utils.AuthenticatedUser;
import com.ict.carrot.service.ProductService;
import com.ict.carrot.service.ProductThumbnailService;
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
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final UserRepository userRepository;
  private final ProductThumbnailService productThumbnailService;

  /* 상품 등록 */
  @Override
  @AuthenticatedUser
  public Product saveProduct(Product product, List<MultipartFile> images) {

      // 인증된 사용자 정보 추출
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();

      // 추출한 정보로 User 객체 조회
      User user = userRepository.findByUsername(userDetails.getUsername());
      product.setCreator(user);

      // 상품 등록
      Product saveProduct = productRepository.save(product);

      // 이미지 저장
      saveProduct.setProductThumbnails(productThumbnailService.uploadThumbnail(saveProduct, images));

      return saveProduct;
  }

  /* 상품 상세 조회 */
  @Override
  public Optional<Product> getProductDetails(Long productId) {
    return Optional.ofNullable(productRepository.findById(productId)
        .orElseThrow(() -> new ApiException(PRODUCT_NOT_FOUND)));
  }

  /* 상품 목록 조회 */
  @Override
  public List<Product> getProducts() {
    return productRepository.findAll();
  }

  /* 상품 삭제 */
  @Override
  public DeleteResponse deleteProduct(Long productId) {

    DeleteResponse deleteResponse = new DeleteResponse();
    deleteResponse.setData(productRepository.findById(productId));

    try {
      productRepository.deleteById(productId);
      deleteResponse.setResult(true);
      deleteResponse.setMessage("상품이 삭제되었습니다.");

    } catch (EmptyResultDataAccessException e) {
      deleteResponse.setResult(false);
      deleteResponse.setMessage(PRODUCT_NOT_FOUND.getMessage());

    } catch (Exception e) {
      deleteResponse.setResult(false);
      deleteResponse.setMessage(e.getMessage());
    }

    return deleteResponse;
  }

  @Override
  public Product updateProduct(Long productId, Product updatedProduct, List<MultipartFile> images) {
    try {
      // 인증된 사용자 정보 추출
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

      if (authentication == null || !authentication.isAuthenticated()) {
        throw new ApiException(UNAUTHORIZED);
      }

      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      User user = userRepository.findByUsername(userDetails.getUsername());

      if (user == null) {
        throw new ApiException(USER_NOT_FOUND);
      }

      // 기존 상품 조회
      Optional<Product> existingProductOptional = productRepository.findById(productId);
      if (existingProductOptional.isEmpty()) {
        throw new ApiException(PRODUCT_NOT_FOUND);
      }

      Product existingProduct = existingProductOptional.get();

      // 수정할 상품 정보 업데이트
      existingProduct.setName(updatedProduct.getName());
      existingProduct.setPrice(updatedProduct.getPrice());
      existingProduct.setDescription(updatedProduct.getDescription());
      // 필요한 다른 필드들 업데이트

      // 현재 상품의 이미지를 업데이트하거나 새 이미지를 추가
      if (images != null && !images.isEmpty()) {
        existingProduct.setProductThumbnails(productThumbnailService.uploadThumbnail(existingProduct, images));
      }

      // 상품 저장
      return productRepository.save(existingProduct);

    } catch (ClassCastException e) {
      throw new ApiException(USER_NOT_FOUND);
    } catch (Exception e) {
      throw new RuntimeException("상품 수정 중 문제가 발생했습니다.", e);
    }
  }


}
