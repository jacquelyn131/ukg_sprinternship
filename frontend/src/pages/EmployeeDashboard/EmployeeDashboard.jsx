import ClockInOutWidget from './components/clockInOutWidget/ClockInOutWidget.jsx'
import AttendanceCard from './components/attendanceCard/AttendanceCard.jsx'
import GreetingMessage from './components/greetingMessage/GreetingMessage.jsx'
import CustomDate from './components/customDate/CustomDate.jsx'
import styles from "./EmployeeDashboard.module.css";

import { useState } from 'react';


const EmployeeDashboard = () =>

{
    return (
        <>

        <div className={styles.dashboardWrapper}>
            <div className={styles.dashboardBackground}>
                <div className={styles.dashboardGreeting}>


       {/*} <div>Test</div>
        <ClockInOutWidget />*/}

<div className={styles.dashboardWidgets}>
        <div>
        <div>
            <GreetingMessage />
            <CustomDate />
        </div>


        <div className={styles.contentWrapper}>
            <ClockInOutWidget />
        </div>
        </div>

        <div className={styles.attendanceWrapper}>
            <div className={styles.attendanceTitle}><h2>My Attendance</h2></div>
            <AttendanceCard test="Hello World" />
            <AttendanceCard test="Hello World" />
            <AttendanceCard test="Hello World" />

        </div>
</div>

        </div>
            </div>
                </div>

        </>);}

        export default EmployeeDashboard;

