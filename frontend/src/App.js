import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Shop from './js/Shop';
import './css/App.css';
import Login from "./js/Login";
import Home from "./js/Home";
import AddItem from "./js/AddItem";
import ItemDetails from "./js/ItemDetails";

function App() {
  return (
      <Router>
        <Routes>
          <Route path="/shop" element={<Shop />} />
          <Route path="/login" element={<Login />} />
          <Route path="/" element={<Home />} />
          <Route path="/item/:id" element={<ItemDetails />} />
          {/* 다른 라우트도 여기에 계속 추가 */}
        </Routes>
      </Router>
  );
}

export default App;