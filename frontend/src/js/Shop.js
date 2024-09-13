import React, { useState, useEffect } from 'react';
import '../css/Shop.css';
import axios from 'axios';
import Header from './Header';
import { useNavigate } from 'react-router-dom';

// 상품 등록 모달 컴포넌트
const AddProductModal = ({ isOpen, onClose }) => {
  const [name, setName] = useState('');
  const [price, setPrice] = useState('');
  const [description, setDescription] = useState('');
  const [images, setImages] = useState([]);
  const [previewImages, setPreviewImages] = useState([]);

  if (!isOpen) return null;

  const handleImageChange = (e) => {
    const selectedFiles = Array.from(e.target.files); // 선택된 파일을 배열로 변환

    // 최대 10개의 이미지 제한
    if (selectedFiles.length + images.length > 10) {
      alert("최대 10개의 이미지만 업로드할 수 있습니다.");
      return;
    }

    const newImages = [...images, ...selectedFiles]; // 기존 이미지 + 새로 선택한 이미지 병합
    setImages(newImages);

    // 새로 선택한 파일들의 미리보기 URL 생성
    const newPreviews = selectedFiles.map((file) => URL.createObjectURL(file));

    // 기존 미리보기 + 새로운 미리보기를 합쳐서 업데이트
    setPreviewImages((prev) => [...prev, ...newPreviews]);
  };

  const handleRemoveImage = (indexToRemove) => {
    const newImages = images.filter((_, index) => index !== indexToRemove);
    const newPreviews = previewImages.filter((_, index) => index !== indexToRemove);

    setImages(newImages);
    setPreviewImages(newPreviews);
  }

  const handleSubmit = async (e) => {
    e.preventDefault();
    const formData = new FormData();
    const token = localStorage.getItem('token');
    formData.append('name', name);
    formData.append('price', price);
    formData.append('description', description);
    images.forEach((image) => formData.append('images', image));

    try {
      await axios.post('http://localhost:8080/product', formData, {
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'multipart/form-data',
        },
      });
      alert('상품이 성공적으로 등록되었습니다.');
      onClose(); // 모달 닫기
      window.location.href = '/shop';
    } catch (error) {
      if (error.response && error.response.status === 404) {
        alert('로그인이 필요합니다.');
      } else {
        alert('상품 등록에 실패했습니다.');
      }
      console.error(error);
    }
  };

  return (
      <div className="modal-overlay">
        <div className="modal-content">
          <button className="modal-close-button" onClick={onClose}><i
              className="bi bi-x-square-fill"></i></button>
          <div className="modal-header">
            <h2>상품 등록</h2>
          </div>
          <div className="modal-body">
            <form onSubmit={handleSubmit}>
              <div className="form-group">
                <label htmlFor="images">이미지 업로드</label>
                <div className="upload-box" onClick={() => document.getElementById('images').click()}>
                  <i className="bi bi-camera"></i>
                  <p><span style={{ color: 'blue' }}>{images.length}</span>/10</p> {/* 이미지 개수 표시 */}
                </div>
                <input
                    type="file"
                    id="images"
                    multiple
                    onChange={handleImageChange}
                    style={{ display: 'none' }} // 실제 파일 선택 버튼은 숨깁니다
                />
              </div>
              {/* 미리보기 이미지 표시 */}
              <div className="image-preview-container">
                {previewImages.length > 0 && (
                    <div className="preview-grid">
                      {previewImages.map((image, index) => (
                          <div key={index} className="preview-product">
                            <img src={image} alt={`preview-${index}`} className="preview-image" />
                            <button className="remove-button" onClick={() => handleRemoveImage(index)}>X</button>
                          </div>
                      ))}
                    </div>
                )}
              </div>
              <div className="form-group">
                <label htmlFor="name">제목</label>
                <textarea
                    id="name"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
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
        </div>
      </div>
  );
};

const MainContent = () => {
  const [products, setProducts] = useState([]);
  const [visibleProducts, setVisibleProducts] = useState(9);
  const [isModalOpen, setIsModalOpen] = useState(false);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await axios.get('http://localhost:8080/product');
        setProducts(response.data);
      } catch (error) {
        console.error('상품 데이터를 가져오는 중 오류 발생: ' + error);
      }
    };
    fetchProducts();
  }, []);

  const handleLoadMore = () => {
    setVisibleProducts((prevVisibleProducts) => prevVisibleProducts + 6);
  };

  const openModal = () => {
    console.log("Modal Opened");
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
  };

  const navigate = useNavigate();
  const getProductDetail = (id) => {
    // 클릭 시 아이템의 상세 페이지로 이동
    //window.location.href = `/item/${id}`;
    navigate(`/item/${id}`);
  };


  return (
      <main id="main-content">
        <div className="content-header">
          <h1>Main Content Area</h1>
          <p>기업에서 판매를 위해 게시한 상품입니다.</p>
          <button className="add-product-button" onClick={openModal}>
            상품 등록하기
          </button>
        </div>
        <div className="product-grid">
          {products.map(product => (
              <div key={product.id} className="product-card"  onClick={() => getProductDetail(product.id)}>
                <img src={product.productThumbnails[0]?.imagePath} alt={product.name} className="product-image" />
                <h2 className="product-name">{product.name}</h2>
                <p>{product.price}원</p>
              </div>
          ))}
        </div>
        <div className="load-more-container">
          <button onClick={handleLoadMore} className="load-more-button">더보기</button>
        </div>

        {/* 상품 등록 모달 */}
        {isModalOpen && (
            <AddProductModal isOpen={isModalOpen} onClose={closeModal} />
        )}
      </main>
  );
};

const Shop = () => {
  return (
      <div id="shop-page">
        <Header />
        <MainContent />
      </div>
  );
};

export default Shop;
