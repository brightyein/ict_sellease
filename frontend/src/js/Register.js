import React, { useState } from 'react';
import axios from 'axios';
import Header from './Header';  // Header를 import
import '../css/Register.css';

const Register = () => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    username: '',
    password: ''
  });
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setSuccess('');
    try {
      const response = await axios.post('/register', formData);
      console.log('회원가입 성공:', response.data);
      setSuccess('회원가입이 성공적으로 완료되었습니다.');
      // 2초 후 로그인 페이지로 리다이렉트
      setTimeout(() => {
        window.location.href = '/login';
      }, 2000);
    } catch (error) {
      console.error('회원가입 실패:', error.response.data);
      setError(error.response.data.message || '회원가입 중 오류가 발생했습니다.');
    }
  };

  return (
      <>
        {/* 헤더 컴포넌트 추가 */}
        <Header />

        <div className="form-container signup-form">
          <h1>회원가입</h1>
          {error && <p className="error-message">{error}</p>}
          {success && <p className="success-message">{success}</p>}
          <form id="signupForm" onSubmit={handleSubmit}>
            <div className="input-group">
              <label htmlFor="name">이름</label>
              <input
                  type="text"
                  id="name"
                  name="name"
                  value={formData.name}
                  onChange={handleChange}
                  required
              />
            </div>
            <div className="input-group">
              <label htmlFor="email">이메일</label>
              <input
                  type="email"
                  id="email"
                  name="email"
                  value={formData.email}
                  onChange={handleChange}
                  required
              />
            </div>
            <div className="input-group">
              <label htmlFor="username">아이디</label>
              <input
                  type="text"
                  id="username"
                  name="username"
                  value={formData.username}
                  onChange={handleChange}
                  required
              />
            </div>
            <div className="input-group">
              <label htmlFor="password">비밀번호</label>
              <input
                  type="password"
                  id="password"
                  name="password"
                  value={formData.password}
                  onChange={handleChange}
                  required
              />
            </div>
            <div className="button-group">
              <button type="submit">가입</button>
            </div>
          </form>
        </div>
      </>
  );
};

export default Register;
