import React from "react";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import SplashPage from './components/homepage/homepage.js'
// import ErrorPage from "./Components/ErrorPage/ErrorPage.js"

const App = () => {

  return (
    <Router>
      <Routes>
        <Route path="/" element={<SplashPage />} />
      </Routes>
    </Router>
      
  );
};

export default App;
