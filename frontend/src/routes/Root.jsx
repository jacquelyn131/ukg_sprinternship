import React from 'react';
import { Outlet, Link } from "react-router-dom";

const Root = () => {
  return (
   <>
    <nav>
      <Link to="/">Home</Link>
    </nav>
    <Outlet />
   </>
  );
}

export default Root;