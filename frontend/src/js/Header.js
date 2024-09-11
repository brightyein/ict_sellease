import React from 'react';
import '../css/Header.css';

const Header = () => {
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
              <li><a href="/">Home</a></li>
              <li><a href="/about">All</a></li>
              <li><a href="/services">New</a></li>
              <li><a href="/contact">Contact</a></li>
            </ul>
          </nav>
        </div>
      </header>
  );
};

export default Header;