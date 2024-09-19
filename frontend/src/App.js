import React, {useEffect, useState} from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { Helmet } from 'react-helmet';
import Shop from './js/Shop';
import './css/App.css';
import Login from "./js/Login";
import Home from "./js/Home";
import Register from "./js/Register";
import axios from "axios";


function App() {
  // App.js 에서 useEffect 로 한 번만 토큰 유효성을 검사하고, 이를 전역 상태로 관리
  const [username, setUsername] = useState(null);

  useEffect(() => {
    const token = localStorage.getItem('token');
    const storedUsername = localStorage.getItem('username');

    if (token) {
      axios.post('http://localhost:8080/auth/validate-token', { token, username: storedUsername })
      .then(response => {
        if (response.data.isValid) {
          setUsername(storedUsername);
        } else {
          localStorage.removeItem('token');
          localStorage.removeItem('username');
          setUsername(null);
        }
      })
      .catch(error => {
        console.error('Token validation error:', error);
        localStorage.removeItem('token');
        localStorage.removeItem('username');
        setUsername(null);
      });
    }
  }, []);

  return (
      <>
        {/* Helmet을 통해 메타 태그 추가 */}
        <Helmet>
          <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        </Helmet>

        <Router>
          <Routes>
            <Route path="/shop" element={<Shop />} />
            <Route path="/login" element={<Login />} />
            <Route path="/" element={<Home />} />
            <Route path="/register" element={<Register />} />
            {/* <Route path="/product/:id" element={<ProductDetails />} /> */}
            {/* 다른 라우트도 여기에 계속 추가 */}
          </Routes>
        </Router>
      </>
  );
}

export default App;
