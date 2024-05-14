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
 EmployeeDashboard
        <div className={styles.dashboardWrapper}>
            <div className={styles.dashboardBackground}>
        <div className={styles.dashboardGreeting}>
=======

       {/*} <div>Test</div>
        <ClockInOutWidget />*/}

        

        <div>
 main
            <GreetingMessage />
            <CustomDate />
            </div>

                <div className={styles.contentWrapper}>
        <ClockInOutWidget />
 EmployeeDashboard
        </div>

        <div>
        <div><h1>My Attendance</h1></div>
        <AttendanceCard test="Hello World" />
        </div>

        </div>
        </div>
=======

 main
        </>)}

        export default EmployeeDashboard;

