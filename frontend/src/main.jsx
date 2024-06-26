import React from 'react';
import ReactDOM from 'react-dom/client';
import {
  createBrowserRouter,
  RouterProvider,
} from 'react-router-dom';
import App from './App.jsx';
import Layout from './layout/Layout.jsx';
import ErrorPage from './pages/ErrorPage/ErrorPage.jsx';
import DashboardRouter from './routes/DashboardRouter.jsx';

import EmployeeDashboard from './pages/EmployeeDashboard/EmployeeDashboard.jsx'
import MyAttendance from './pages/MyAttendance/MyAttendance.jsx'

import ManagerDashboard from './pages/ManagerDashboard/ManagerDashboard.jsx'
import EmployeeDetailPage from './pages/ManagerDashboard/components/employeeDetailPage/EmployeeDetailPage.jsx'

import SignInPage from './pages/SignIn/SignInPage.jsx';
import { UserProvider } from './UserContext.jsx';
import PrivateRoutes from './PrivateRoutes.jsx';

const router = createBrowserRouter([
  {
    path: '/',
    element: <Layout />,
    errorElement: <ErrorPage />,
    children: [
      { index: true, element: <App /> },
      { path: '*', element: <ErrorPage />, error: true, message: 'Not Found'},
      { path: '/signin', element: <SignInPage />},
      { path: '/attendance', element: <PrivateRoutes><MyAttendance />
       </PrivateRoutes>},
      { path: '/dashboard', element: <PrivateRoutes>
        <DashboardRouter />
          </PrivateRoutes>},
          { path: '/employee/:id', element:
            <PrivateRoutes>
                <EmployeeDetailPage />
                </PrivateRoutes>
                },

    ],
  },
]);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(

  <React.StrictMode>
      <UserProvider>
    <RouterProvider router={router} />
 </UserProvider>
  </React.StrictMode>
);
