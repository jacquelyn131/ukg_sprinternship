import Button from 'react-bootstrap/Button';
import { useState } from "react";
import styles from './ClockInOutWidget.module.css'

const ClockInOutWidget = () => {
    // Initialize current time state
    const [currentTime, setCurrentTime] = useState(getFormattedTime());

    // Update current time every minute
    setInterval(() => {
        setCurrentTime(getFormattedTime());
    }, 60000); // Update every 60 seconds (1 minute)

    // Function to get formatted time (hour and minutes)
    function getFormattedTime() {
        const now = new Date();
        const hour = String(now.getHours() % 12 || 12).padStart(2, '0'); // Convert to 12-hour format
        const minute = String(now.getMinutes()).padStart(2, '0'); // Add leading zero if needed
        const amOrPm = now.getHours() >= 12 ? 'PM' : 'AM'; // Determine AM or PM
        return `${hour}:${minute} ${amOrPm}`; // Include AM/PM indicator
    }




    return (
        <>
            <div className={styles.clock}>
                <h2>Clock</h2>
                <h1>{currentTime}</h1>
            </div>

            <div className={styles.personal}>
                <h5>Sunny day, bright vibes</h5>
            </div>

            <div className={styles.buttonContainer}>
                <Button className={styles.button} variant="primary" size="lg">Clock In</Button>{' '}
            </div>
        </>
    );

    }

    export default ClockInOutWidget;