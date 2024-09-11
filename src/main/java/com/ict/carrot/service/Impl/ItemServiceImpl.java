package com.ict.carrot.service.Impl;

import static com.ict.carrot.exception.ErrorCode.ITEM_NOT_FOUND;
import static com.ict.carrot.exception.ErrorCode.UNAUTHORIZED;
import static com.ict.carrot.exception.ErrorCode.USER_NOT_FOUND;

import com.ict.carrot.exception.ApiException;
import com.ict.carrot.exception.ApiExceptionResponse;
import com.ict.carrot.exception.ErrorCode;
import com.ict.carrot.model.Item;
import com.ict.carrot.model.User;
import com.ict.carrot.repository.ItemRepository;
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

  private final ItemRepository itemRepository;
  private final UserRepository userRepository;

  private final ItemThumbnailService itemThumbnailService;

  /* 상품 등록 */
  @Override
  public Item saveItem(Item item, List<MultipartFile> images) {
    try {
      // 인증된 사용자 정보 추출
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

      // 인증되지 않았을 때 NullPointerException을 방지하기 위해 확인
      if (authentication == null || !authentication.isAuthenticated()) {
        throw new ApiException(UNAUTHORIZED);
      }

      UserDetails userDetails = (UserDetails) authentication.getPrincipal();

      // 추출한 정보로 User 객체 조회
      User user = userRepository.findByUsername(userDetails.getUsername());

      if (user == null) {
        throw new ApiException(USER_NOT_FOUND);
      }

      item.setCreator(user);

      // 상품 등록
      Item saveItem = itemRepository.save(item);

      // 이미지 저장
      saveItem.setItemThumbnails(itemThumbnailService.uploadThumbnail(saveItem, images));

      return saveItem;
    } catch (ClassCastException e) {
      throw new ApiException(USER_NOT_FOUND);
    } catch (Exception e) {
      // 다른 예외에 대한 처리
      throw new RuntimeException("상품 등록 중 문제가 발생했습니다.", e);
    }
  }

  /* 상품 상세 조회 */
  @Override
  public Optional<Item> getItemDetails(Long itemId) {
    return Optional.ofNullable(itemRepository.findById(itemId)
        .orElseThrow(() -> new ApiException(ITEM_NOT_FOUND)));
  }

  /* 상품 목록 조회 */
  @Override
  public List<Item> getItems() {
    return itemRepository.findAll();
  }

  /* 상품 삭제 */
  @Override
  public DeleteResponse deleteItem(Long itemId) {

    DeleteResponse deleteResponse = new DeleteResponse();
    deleteResponse.setData(itemRepository.findById(itemId));

    try {
      itemRepository.deleteById(itemId);
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
