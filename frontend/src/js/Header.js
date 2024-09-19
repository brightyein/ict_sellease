import React, { useEffect, useState } from 'react';
import '../css/Header.css';

const Header = () => {
  const [username, setUsername] = useState(null);

  // 컴포넌트가 마운트될 때 로컬 스토리지에서 username 가져오기
  useEffect(() => {
    const storedUsername = localStorage.getItem('username');
    setUsername(storedUsername); // userName 상태에 저장
  }, []);

  return (
      <header id="header-page">
        <div className="container">
          <div className="logo">
            <a href="/" className="logo-link">
              <img src="/images/logo-dark.svg" className="logo-image" alt="Logo" />
            </a>
          </div>
          <nav>
            <ul>
              <li><a href="/"><i className="bi bi-house-fill"></i></a></li>
              <li><a href="/about"><i className="bi bi-shop"></i></a></li>
              <li><a href="/services"><i className="bi bi-cart-fill"></i></a></li>
              <li><a href="/contact"><i className="bi bi-headset"></i></a></li>
              <li>|</li>
              {username ? (
                  <li><span>{username} 님</span></li>
                ) : (
                <li><a href="/login">Login</a></li>
                )}
            </ul>
          </nav>
        </div>
      </header>
  );
};

export default Header;
