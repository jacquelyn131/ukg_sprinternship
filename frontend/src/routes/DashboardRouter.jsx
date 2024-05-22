import React, { useState, useEffect } from 'react';
import Utils from '../Utils.js';
import { useUser } from '../UserContext';

import EmployeeDashboard from '../pages/EmployeeDashboard/EmployeeDashboard.jsx';
import ManagerDashboard from '../pages/ManagerDashboard/ManagerDashboard.jsx';

const DashboardRouter = () => {

    const [isManager, setIsManager] = useState(null);

    const { userInfo } = useUser();

    useEffect (() => {
        const checkIfManager = async () => {
              if (userInfo) {
                  const managerStatus = await Utils.isManager(userInfo.employeeId);
                    setIsManager(managerStatus);
                    console.log(`Is user ${userInfo.employeeId} a manager? ${managerStatus}`);
                    }
                };

            checkIfManager();

        }, [userInfo]);

    if (isManager === null) {
        return <div>Loading...</div>;
    }

    return (
        <>

        { !isManager ? <EmployeeDashboard /> : <ManagerDashboard /> }

        </>

        )
        }
    export default DashboardRouter;