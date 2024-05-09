import React from 'react';
import ReactDOM from 'react-dom/client';
import {
  createBrowserRouter,
  RouterProvider,
} from 'react-router-dom';
import App from './App.jsx';
import Root from './routes/Root.jsx';
import ErrorPage from './ErrorPage.jsx';
import SignUp from './components/SignUp.jsx';

const router = createBrowserRouter([
  {
    path: '/',
    element: <Root />,
    errorElement: <ErrorPage />,
    children: [
      { index: true, element: <App /> },
      { path: '*', element: <ErrorPage />, error: true, message: 'Not Found'},
      { path: '/signup', element: <SignUp />}
    ],
  },
]);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
