import React from 'react';
import ReactDOM from 'react-dom/client';
import {
  createBrowserRouter,
  RouterProvider,
} from 'react-router-dom';
import App from './App.jsx';
import Layout from './layout/Layout.jsx';
import ErrorPage from './pages/ErrorPage/ErrorPage.jsx';


import SignInPage from './pages/SignIn/SignInPage.jsx';

const router = createBrowserRouter([
  {
    path: '/',
    element: <Layout />,
    errorElement: <ErrorPage />,
    children: [
      { index: true, element: <App /> },
      { path: '*', element: <ErrorPage />, error: true, message: 'Not Found'},
      { path: '/signin', element: <SignInPage />},
    ],
  },
]);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
