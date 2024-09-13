import React, { useEffect,useState } from 'react';
import axios from 'axios';
import '../css/GetItem.css';
import Header from './Header';
import { useParams } from 'react-router-dom';

const MainContent = () => {
  const { id } = useParams();  // URL에서 id 파라미터 추출
  const [item, setItem] = useState(null);

  useEffect(() => {
    // 아이템 상세 정보를 가져오는 API 요청
    const fetchItem = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/item/${id}`);
        setItem(response.data);
      } catch (error) {
        console.error('아이템 상세 정보 조회 오류:', error);
      }
    };

    fetchItem();
  }, [id]);

  if (!item) return <p>Loading...</p>;

  const editItem = (id) => {
    // 클릭 시 아이템의 상세 페이지로 이동
    window.location.href = `/item/${id}`;
  };

  const deleteItem = (id) => {
    // 클릭 시 아이템의 상세 페이지로 이동
    window.location.href = `/item/${id}`;
  };

  const listItem = () => {
    // 클릭 시 shop  페이지로 이동
    window.location.href = '/shop';
  };

  return (
      <div className="item-detail">
        <label>상품 이름</label>
        <h1>{item.itemName}</h1>

        {item.itemThumbnails.map(thumbnail => (
            <div className="item-image-div">
              <img src={thumbnail.imagePath} alt={item.itemName}
                   className="item-image"/>
            </div>
        ))}

        <label>가격</label>
        <p>{item.price}원</p>

        <label>내용</label>
        <textarea readOnly={true}>{item.description}</textarea>

        <div>
          <button className="edit-button"  onClick={() => editItem(item.id)}>수정하기</button>
          <button className="delete-button"  onClick={() => deleteItem(item.id)}>삭제하기</button>
          <button className="list-button"  onClick={() => listItem()}>목록보기</button>
        </div>
      </div>

  );

};

const ProductDetails = () => {
  return (
      <div id="detail-item-page">
        <Header />
        <MainContent />
      </div>
  );
};

export default ProductDetails;