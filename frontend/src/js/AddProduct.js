import React, { useState } from 'react';
import axios from 'axios';
import '../css/AddProduct.css';
import Header from './Header';

const AddItemForm = () => {  // 이름을 변경
  const [itemName, setItemName] = useState('');
  const [price, setPrice] = useState('');
  const [description, setDescription] = useState('');
  const [images, setImages] = useState([]);

  // 이미지 업로드 핸들러
  const handleImageChange = (e) => {
    setImages([...e.target.files]);
  };

  // 상품 등록 핸들러
  const handleSubmit = async (e) => {
    e.preventDefault();

    const formData = new FormData();
    formData.append('itemName', itemName);
    formData.append('price', price);
    formData.append('description', description);

    // 이미지 파일 추가
    images.forEach((image) => formData.append('images', image));

    try {
      const response = await axios.post('http://localhost:8080/product', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });
      alert('상품이 성공적으로 등록되었습니다.');
      console.log(response.data);
    } catch (error) {
      console.error('상품 등록 중 오류 발생:', error);
      alert('상품 등록에 실패했습니다.');
    }
  };

  return (
      <div id="add-product-page" className="add-product-container">
        <h2>상품 등록</h2>
        <form onSubmit={handleSubmit} className="add-product-form">
          <div className="form-group">
            <label htmlFor="images">이미지 업로드</label>
            <input
                type="file"
                id="images"
                multiple
                onChange={handleImageChange}
            />
          </div>
          <div className="form-group">
            <label htmlFor="itemName">상품 이름</label>
            <input
                type="text"
                id="itemName"
                value={itemName}
                onChange={(e) => setItemName(e.target.value)}
                required
            />
          </div>
          <div className="form-group">
            <label htmlFor="price">가격</label>
            <input
                type="number"
                id="price"
                value={price}
                onChange={(e) => setPrice(e.target.value)}
                required
            />
          </div>
          <div className="form-group">
            <label htmlFor="description">설명</label>
            <textarea
                id="description"
                value={description}
                onChange={(e) => setDescription(e.target.value)}
                required
            />
          </div>
          <button type="submit" className="submit-button">상품 등록</button>
        </form>
      </div>
  );
};

const AddItemPage = () => {  // 상위 컴포넌트 이름을 변경
  return (
      <div id="add-product-page">
        <Header />
        <AddItemPage />  {/* 변경된 컴포넌트 이름 사용 */}
      </div>
  );
};

export default AddItemPage;
