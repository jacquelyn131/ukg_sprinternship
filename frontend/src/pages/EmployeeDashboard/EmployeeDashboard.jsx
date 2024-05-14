import ClockInOutWidget from './components/clockInOutWidget/ClockInOutWidget.jsx'
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
            <GreetingMessage />
            <CustomDate />
            </div>

                <div className={styles.contentWrapper}>
        <ClockInOutWidget />
        </div>
        </div>
        </div>
        </>)}

        export default EmployeeDashboard;

