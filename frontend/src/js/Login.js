import React, { useState } from 'react';
import axios from 'axios';
import '../css/Login.css';
import Header from './Header';
import { Link } from 'react-router-dom';

const Login = () => {
  const [formData, setFormData] = useState({
    username: '',
    password: ''
  });

  const [errorMessage, setErrorMessage] = useState('');

  const handleChange = (e) => {
    const { id, value } = e.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [id]: value
    }));
  };

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post('http://localhost:8080/login', formData);
      console.log('Login successful:', response);
      const token = response.data.token;
      const username = response.data.username;
      localStorage.setItem('token', token);
      localStorage.setItem('username', username);
      window.location.href = '/shop';

    } catch (error) {
      console.error('Login failed:', error);
      setErrorMessage('로그인에 실패했습니다. 입력 정보를 확인하세요.');
    }
  };

  return (
      <>
        <Header />
        <div id="login-page">
          <div className="login-container">
            <h1>로그인</h1>
            <form id="loginForm" onSubmit={handleLogin}>
              <div className="input-group">
                <label htmlFor="username">아이디:</label>
                <input
                    type="text"
                    id="username"
                    value={formData.username}
                    onChange={handleChange}
                    required
                    autoComplete="username"
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
                    autoComplete="current-password"
                />
              </div>
              <div className="button-group">
                <button type="submit">로그인</button>
              </div>
            </form>
            {errorMessage && <div style={{ color: 'red' }}>{errorMessage}</div>}
            <div className="signup-prompt">
              <span>아직 계정이 없으신가요?</span>
              <Link to="/register">회원가입</Link>
            </div>
          </div>
        </div>
      </>
  );
};

export default Login;
