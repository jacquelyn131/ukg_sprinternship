import ClockInOutWidget from './components/clockInOutWidget/ClockInOutWidget.jsx'
import AttendanceCard from './components/attendanceCard/AttendanceCard.jsx'
import GreetingMessage from './components/greetingMessage/GreetingMessage.jsx'
import CustomDate from './components/customDate/CustomDate.jsx'
import styles from "./EmployeeDashboard.module.css";
import { Alert } from 'react-bootstrap';

import { useNavigate } from 'react-router-dom';

import { useState, useEffect } from 'react';

import { useUser } from '../../UserContext';

const EmployeeDashboard = () => {
    const [showAlert, setShowAlert] = useState(false);
    const [alertMessage, setAlertMessage] = useState('');
    const [alertType, setAlertType] = useState('success');


    const toggleShowAlert = () => setShowAlert(!showAlert);

        useEffect(() => {
            let timer;
            if (showAlert && alertType !== 'danger') {
                timer = setTimeout(() => {
                    toggleShowAlert();
                }, 10000); // 10 seconds
            }

            return () => {
                clearTimeout(timer);
            };
        }, [showAlert]);


    const { userInfo } = useUser();

    const navigate = useNavigate();
    const navigateToMyAttendance = (e) => {
        e.preventDefault();
        console.log('My Attendance button clicked')
        navigate('/attendance');
    }

    return (
        <>
            <div className={styles.dashboardWrapper}>
                <div className={styles.dashboardGreeting}>
                    {userInfo && <GreetingMessage firstName={userInfo.firstName} />}
                    <CustomDate />
                </div>
                <div className={styles.dashboardContent}>
                    <div className={styles.clockInOutWidget}>
                        <ClockInOutWidget setShowAlert={setShowAlert} setAlertMessage={setAlertMessage} setAlertType={setAlertType} />
                    </div>
                    <div className={styles.attendanceSection}>
                        <div className={styles.attendanceHeader}>
                            <h2>My Attendance</h2>
                            <button type="button" onClick={navigateToMyAttendance} className={styles.forwardButtonContainer}><img src="./././public/images/ForwardArrow.svg" className={styles.forwardButton} /></button>
                        </div>
                        <AttendanceCard punchDate="10/1/24" totalHours="8" punchTimeIn="4:00pm" punchTimeOut="8:00pm" />
                        <AttendanceCard punchDate="10/1/24" totalHours="8" punchTimeIn="4:00pm" punchTimeOut="8:00pm" />
                        <AttendanceCard punchDate="10/1/24" totalHours="8" punchTimeIn="4:00pm" punchTimeOut="8:00pm" />
                    </div>
                </div>
            </div>

                  {showAlert && (
                           <Alert variant={alertType} className={styles.alert} onClose={toggleShowAlert} dismissible>
                               {alertMessage}
                           </Alert>
                           )
                           }
        </>
    );
}

export default EmployeeDashboard;
