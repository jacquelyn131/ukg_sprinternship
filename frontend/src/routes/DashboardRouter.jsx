import React, { useState, useEffect } from 'react';
import Utils from '../Utils.js';
import { useUser } from '../UserContext';
import Endpoints from '../endpoints/Endpoints.js';

import EmployeeDashboard from '../pages/EmployeeDashboard/EmployeeDashboard.jsx';
import ManagerDashboard from '../pages/ManagerDashboard/ManagerDashboard.jsx';

const DashboardRouter = () => {
    const [isManager, setIsManager] = useState(null);
    const { userInfo, setUserInfo, isLoading, loginToken } = useUser();

    useEffect(() => {
        const fetchUserInfo = async () => {
            if (loginToken && !userInfo) {
                try {
                    const data = await Endpoints.userInfo(loginToken);
                    setUserInfo(data);
                } catch (error) {
                    console.error('Failed to fetch user info', error);
                }
            }
        };

        fetchUserInfo();
    }, [loginToken, userInfo, setUserInfo]);

    useEffect(() => {
        const checkIfManager = async () => {
            if (userInfo) {
                try {
                    const managerStatus = await Utils.isManager(userInfo.employeeId);
                    setIsManager(managerStatus);
                    console.log(`Is user ${userInfo.employeeId} a manager? ${managerStatus}`);
                } catch (error) {
                    console.error('Failed to check if user is a manager', error);
                }
            }
        };

        if (!isLoading && userInfo) {
            checkIfManager();
        }
    }, [userInfo, isLoading]);



    return (
        <>
            {!isManager ? <EmployeeDashboard /> : <ManagerDashboard />}
        </>
    );
};

export default DashboardRouter;
