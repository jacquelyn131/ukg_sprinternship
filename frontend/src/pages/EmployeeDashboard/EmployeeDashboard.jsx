import ClockInOutWidget from './components/clockInOutWidget/ClockInOutWidget.jsx'
import AttendanceCard from './components/attendanceCard/AttendanceCard.jsx'
import GreetingMessage from './components/greetingMessage/GreetingMessage.jsx'
import CustomDate from './components/customDate/CustomDate.jsx'
import styles from "./EmployeeDashboard.module.css";

import { useState } from 'react';

import { useUser } from '../../UserContext';


const EmployeeDashboard = () =>

{
    const { userInfo } = useUser();

    return (
        <>

        <div className={styles.dashboardWrapper}>
            <div className={styles.dashboardGreeting}>
                <GreetingMessage firstName={userInfo.firstName} />
                <CustomDate />
                </div>
                <div className={styles.dashboardContent}>
                    <div className={styles.clockInOutWidget}>
                        <ClockInOutWidget />
                        </div>
                        <div className={styles.attendanceSection}>
                            <h2>My Attendance</h2>
                                        <AttendanceCard test="Hello World" />
                                        <AttendanceCard test="Hello World" />
                                        <AttendanceCard test="Hello World" />
                            </div>
                    </div>

            </div>

        </>);}

        export default EmployeeDashboard;

