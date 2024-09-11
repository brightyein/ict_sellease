import React from 'react';
import '../css/Home.css';

const Home = () => {
  return (
      <div id="home-page" className="page-wrap">
        {/* Nav */}
        <nav id="nav">
          <ul>
            <li><a href="/" className="active"><i className="bi bi-house-fill"></i></a></li>
            <li><a href="/login"><i className="bi bi-person-circle"></i></a></li>
            <li><a href="/shop"><i className="bi bi-shop"></i></a></li>
            <li><a href="/"><i className="bi bi-cart-fill"></i></a></li>
          </ul>
        </nav>

        {/* Main */}
        <section id="main">
          <section id="banner">
            <div className="inner">
              <h1>BUSINESS MARKET</h1>
              <p>우리 회사의 자산을 성공적으로 거래해 보세요</p>
              <ul className="actions">
                <li><a href="#galleries" className="button alt scrolly big">구경하기</a></li>
              </ul>
            </div>
          </section>

          {/* Gallery */}
          <section id="galleries">
            <div className="gallery">
              <header className="special">
                <h2>What's New</h2>
              </header>
              <div className="content">
                {['1', '2', '3', '4', '5', '6', '7', '8'].map((num, index) => (
                    <div className="media" key={index}>
                      <a href={`/images/product${num}.jpg`}>
                        <img
                            src={`/images/product${num}.jpg`}
                            alt={`Product ${num}`}
                            title={`Product ${num}`}
                            width="450"
                            height="450"
                        />
                      </a>
                    </div>
                ))}
              </div>
              <footer>
                <a href="/shop" className="button big">Go Shop</a>
              </footer>
            </div>
          </section>

          {/* Contact */}
          <section id="contact">
            <div className="social column">
              <h3>About Me</h3>
              <p>기업 중고 자산 거래 마켓, 셀리즈에 오신것을 환영합니다.</p>
              <h3>Follow Me</h3>
              <ul className="icons">
                <li><a href="#" className="icon"><i className="bi bi-twitter"></i><span className="label">Twitter</span></a></li>
                <li><a href="#" className="icon"><i className="bi bi-facebook"></i><span className="label">Facebook</span></a></li>
                <li><a href="#" className="icon"><i className="bi bi-instagram"></i><span className="label">Instagram</span></a></li>
              </ul>
            </div>

            <div className="column">
              <h3>Get in Touch</h3>
              <form action="#" method="post">
                <div className="field half first">
                  <label htmlFor="name">Name</label>
                  <input name="name" id="name" type="text" placeholder="Name" />
                </div>
                <div className="field half">
                  <label htmlFor="email">Email</label>
                  <input name="email" id="email" type="email" placeholder="Email" />
                </div>
                <div className="field">
                  <label htmlFor="message">Message</label>
                  <textarea name="message" id="message" rows="6" placeholder="Message"></textarea>
                </div>
                <ul className="actions">
                  <li><input value="Send Message" className="button" type="submit" /></li>
                </ul>
              </form>
            </div>
          </section>
        </section>

        <div className="copyright">
          Copyright ⓒ2024 Sellease Inc. All rights reserved.
        </div>

        {/* Scripts */}
        <script src="/js/jquery.min.js"></script>
        <script src="/js/jquery.poptrox.min.js"></script>
        <script src="/js/jquery.scrolly.min.js"></script>
        <script src="/js/skel.min.js"></script>
        <script src="/js/util.js"></script>
        <script src="/js/main.js"></script>
      </div>
  );
};

export default Home;
