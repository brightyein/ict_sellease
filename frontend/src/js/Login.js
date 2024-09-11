import React, { useState } from 'react';
import axios from 'axios';
import '../css/Login.css';

const Login = () => {
  // 상태 관리 (React state)
  const [formData, setFormData] = useState({
    username: '',
    password: ''
  });

  const [errorMessage, setErrorMessage] = useState('');

  // 입력값 변경 핸들러
  const handleChange = (e) => {
    const { id, value } = e.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [id]: value
    }));
  };

  // 로그인 버튼 클릭 시 호출되는 함수
  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post('http://localhost:8080/login', formData);
      console.log('Login successful:', response);
      // 로그인 성공 시 리디렉션 또는 다른 동작 수행
      const token = response.data.token;
      localStorage.setItem('token', token);
      window.location.href = '/shop';

    } catch (error) {
      console.error('Login failed:', error);
      setErrorMessage('로그인에 실패했습니다. 입력 정보를 확인하세요.');
    }
  };

  return (
      <div id="login-page">
      <div className="login-container">
        <h1>로그인</h1>
        <form id="loginForm" onSubmit={handleLogin}>
          <div className="input-group">
            <label htmlFor="username">사용자 이름:</label>
            <input
                type="text"
                id="username"
                value={formData.username}
                onChange={handleChange}
                required
                autocomplete="username" // 사용자 이름 자동 완성 설정
            />
          </div>
          <div className="input-group">
            <label htmlFor="password">비밀번호:</label>
            <input
                type="password"
                id="password"
                value={formData.password}
                onChange={handleChange}
                required
                autocomplete="password" // 비밀번호 자동 완성 설정
            />
          </div>
          <div className="button-group">
            <button type="submit">로그인</button>
          </div>
        </form>
        {/* 에러 메시지 */}
        {errorMessage && <div style={{ color: 'red' }}>{errorMessage}</div>}
      </div>
      </div>
  );
};

export default Login;
