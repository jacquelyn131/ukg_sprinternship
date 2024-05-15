import Button from 'react-bootstrap/Button';
import { useState } from "react";
import styles from './ClockInOutWidget.module.css'
import endpoints from '../../../../endpoints/Endpoints';

const ClockInOutWidget = () => {
    // Initialize current time state
    const [currentTime, setCurrentTime] = useState(getFormattedTime());
    const [currentLocation, setCurrentLocation] = useState("");
    const [clockedIn, setClockedIn] = useState(false); // State variable to track whether the user is clocked in
    const [onBreak, setOnBreak] = useState(false); // State variable to track whether the user is on a break

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

    const geolocation = async (e) => {
        e.preventDefault();

        const showPosition = async (position) => {
            const lat = position.coords.latitude;
            const lon = position.coords.longitude;

            console.log(lat, lon);

            const userLoc = {
                latitude: lat,
                longitude: lon
            };

            const locationResponse = await endpoints.locationChecker(userLoc);
            console.log(locationResponse)
        };

        // Request the user's current position
        navigator.geolocation.getCurrentPosition(showPosition);
    }

    const handleClockIn = (e) => {
        e.preventDefault(); // Prevent default form submission behavior
        setClockedIn(true); // Set clockedIn state to true when the user clocks in
    };

    const handleBreak = () => {
        // Handle break logic here
        setOnBreak(true); // Set onBreak state to true when the user takes a break
    };

    const handleEndBreak = () => {
        // Handle end break logic here
        setOnBreak(false); // Set onBreak state to false when the user ends the break
    };

    const handleClockOut = () => {
        // Handle clock out logic here
        setClockedIn(false); // Set clockedIn state back to false when the user clocks out
    };

    return (
        <>
            <div className={styles.clockinComponents}>
                <div className={styles.clock}>
                    <h2>Clock</h2>
                    <h1>{currentTime}</h1>
                </div>

                <div className={styles.personal}>
                    <h5>Sunny day, bright vibes</h5>
                </div>

                {clockedIn ? ( // Render buttons only if the user is clocked in
                    <div className={styles.clockLocation}>
                        <div className={styles.buttonContainer}>
                            {onBreak ?
                                (
                                <Button className={styles.endbreakButton} onClick={handleEndBreak}>
                                    End Break
                                </Button>
                            ) : (
                                <Button className={styles.breakButton} onClick={handleBreak}>
                                    Break
                                </Button>
                            )}
                            <Button className={styles.clockoutButton} onClick={handleClockOut}>
                                Clock Out
                            </Button>
                        </div>
                        <div className={styles.location}>
                            <img src="././././public/images/location-sign.svg" className={styles.locationIcon} alt="" />
                            <h6 >You are within office reach</h6>
                        </div>
                    </div>
                ) : ( // Render "Clock In" button if the user is not clocked in
                    <div className={styles.clockLocation}>
                        <div className={styles.buttonContainer}>
                            <Button className={styles.button} type="button" onClick={handleClockIn}>
                                Clock In
                            </Button>
                        </div>
                        <div className={styles.location}>
                            <img src="././././public/images/location-sign.svg" className={styles.locationIcon} alt="" />
                            <h6 >You are within office reach</h6>
                        </div>
                    </div>
                )}
            </div>
        </>
    );
}

export default ClockInOutWidget;
