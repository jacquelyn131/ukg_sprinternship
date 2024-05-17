import Button from 'react-bootstrap/Button';
import { useState, useEffect } from "react";
import styles from './ClockInOutWidget.module.css';
import endpoints from '../../../../endpoints/Endpoints';
import { useUser } from '../../../../UserContext.jsx';
import Utils from '../../../../Utils.js';
const ClockInOutWidget = () => {
    const { userInfo } = useUser();


    // Initialize current time state
    const [currentTime, setCurrentTime] = useState("");
    const [currentLocation, setCurrentLocation] = useState("");
    const [clockedIn, setClockedIn] = useState(false); // State variable to track whether the user is clocked in
    const [onBreak, setOnBreak] = useState(false); // State variable to track whether the user is on a break

    const [withinReach, setWithinReach] = useState('true');
    const [clockInStartTime, setClockInStartTime] = useState(null); // State variable to track the start time of clocking in
    const [breakStartTime, setBreakStartTime] = useState(null); // State variable to track the start time of break
    const [clockInElapsedTime, setClockInElapsedTime] = useState(0); // State variable to track the elapsed time for clocking in
    const [breakElapsedTime, setBreakElapsedTime] = useState(0); // State variable to track the elapsed time for break

    const [dateTime, setDateTime] = useState("")

    const getDateTime = () => {
        return new Date().toISOString().slice(0, 19).replace('T', ' ');
    } 

    // const userTimeStamp = {
    //     employeeId: userInfo.employeeId,
    //     timeStampType: 
    //     timeStamp:

    // }

    // Update current time every minute
    useEffect(() => {
        const interval = setInterval(() => {
            setCurrentTime(getFormattedTime());
        }, 1000); // Update every second
        return () => clearInterval(interval);
    }, []);

    // Function to get formatted time (hour, minute, and AM/PM)
    function getFormattedTime() {
        const now = new Date();
        const hour = String(now.getHours() % 12 || 12).padStart(2, '0'); // Convert to 12-hour format
        const minute = String(now.getMinutes()).padStart(2, '0'); // Add leading zero if needed
        const amOrPm = now.getHours() >= 12 ? 'PM' : 'AM'; // Determine AM or PM
        return `${hour}:${minute} ${amOrPm}`; // Include AM/PM indicator
    }

    const geolocation = async (e) => {
        e.preventDefault();
        console.log(getDateTime())
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

            setWithinReach(locationResponse);
        };

        // Request the user's current position
        navigator.geolocation.getCurrentPosition(showPosition);
    }

    const handleClockIn = (e) => {
        const timeStampInfo = {
            employeeId: userInfo.employeeId,
            type: "IN",
            time: getDateTime(),
        };
        console.log(timeStampInfo);
        endpoints.addTimestamp(timeStampInfo);
        geolocation(e);
        e.preventDefault(); // Prevent default form submission behavior
        // endpoints.addTimestamp(timeStamp)
        setClockedIn(true); // Set clockedIn state to true when the user clocks in
        setClockInStartTime(new Date()); // Record the start time
        setCurrentTime(""); // Remove current time when clocked in
    };

    const handleClockOut = () => {
        const timeStampInfo = {
            employeeId: userInfo.employeeId,
            type: "OUT",
            time: getDateTime(),
        };
        endpoints.addTimestamp(timeStampInfo);
        setClockedIn(false); // Set clockedIn state back to false when the user clocks out
        setClockInElapsedTime(0); // Reset elapsed time for clocking in
        setOnBreak(false); // Reset break state
        setCurrentTime(getFormattedTime()); // Show current time when clocked out
    };

    const handleBreak = () => {
        const timeStampInfo = {
            employeeId: userInfo.employeeId,
            type: "BREAK-IN",
            time: getDateTime(),
        };
        endpoints.addTimestamp(timeStampInfo);
        setOnBreak(true); // Set onBreak state to true when the user takes a break
        setBreakStartTime(new Date()); // Record the start time of break
        setClockInElapsedTime((prevElapsedTime) => {
            // Store the current elapsed time when the break starts
            setBreakElapsedTime(prevElapsedTime);
            return prevElapsedTime;
        });
    };

    const handleEndBreak = () => {
        const timeStampInfo = {
            employeeId: userInfo.employeeId,
            type: "BREAK-OUT",
            time: getDateTime(),
        };
        endpoints.addTimestamp(timeStampInfo);
        setOnBreak(false); // Set onBreak state to false when the user ends the break
        setBreakElapsedTime(0); // Reset break elapsed time
        setClockInStartTime((prevStartTime) => {
            // Adjust clock in start time by subtracting break duration
            const breakDuration = new Date() - breakStartTime;
            return new Date(prevStartTime.getTime() + breakDuration);
        });
    };


    // Calculate elapsed time when clocked in
    useEffect(() => {
        let clockInInterval;
        let breakInterval;

        if (clockedIn && !onBreak) {
            clockInInterval = setInterval(() => {
                const currentTime = new Date();
                const elapsedMilliseconds = currentTime - clockInStartTime;
                setClockInElapsedTime(elapsedMilliseconds);
            }, 1000); // Update every second
        } else {
            clearInterval(clockInInterval);
        }

        if (onBreak) {
            breakInterval = setInterval(() => {
                const currentTime = new Date();
                const elapsedMilliseconds = currentTime - breakStartTime;
                setBreakElapsedTime(elapsedMilliseconds);
            }, 1000); // Update every second
        } else {
            clearInterval(breakInterval);
        }

        return () => {
            clearInterval(clockInInterval);
            clearInterval(breakInterval);
        };
    }, [clockedIn, onBreak, clockInStartTime, breakStartTime]);

    // Format elapsed time to display hours, minutes, and seconds
    function formatElapsedTime(milliseconds) {
        const hours = Math.floor(milliseconds / 3600000);
        const minutes = Math.floor((milliseconds % 3600000) / 60000);
        const seconds = Math.floor((milliseconds % 60000) / 1000);
        return `${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
    }

    return (
        <>
            <div className={styles.clockinComponents}>
                {!clockedIn && (
                    <div className={styles.clock}>
                        <h2>Clock</h2>
                        {currentTime && <h1>{currentTime}</h1>}
                    </div>
                )}
                {!clockedIn && (
                    <div className={styles.personal}>
                        <h5>Sunny day, bright vibes</h5>
                    </div>
                )}

                {!clockedIn && (
                    <div className={styles.clockLocation}>
                        <div className={styles.buttonContainer}>
                            <Button className={styles.button} type="button" onClick={handleClockIn}>
                                Clock In
                            </Button>
                        </div>
                        <div className={styles.location}>
                            <img src="././././public/images/location-sign.svg" className={styles.locationIcon} alt="" />
                            <h6 className={styles.officeReach}>You are within office reach</h6>
                        </div>
                    </div>
                )}

                {(onBreak || clockedIn) && (
                    <div className={styles.timerBreak}>
                        <h6>On Break:</h6>
                        <h4 className={styles.breakTimer}>{onBreak ? formatElapsedTime(breakElapsedTime) : "00:00:00"}</h4>
                    </div>
                )}

                {clockedIn && (
                    <div className={styles.clockLocation}>
                        <div className={styles.timer}>
                            <h6>Clocked In:</h6>
                            <h1>{formatElapsedTime(clockInElapsedTime)}</h1>
                        </div>
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

                        {withinReach ? (
                            <div className={styles.location}>
                                <img src="././././public/images/location-sign.svg" className={styles.locationIcon} alt="" />
                                <h6>You are within office reach</h6>
                            </div>
                        ) : (
                            <div className={styles.location}>
                                <img src="././././public/images/location-sign.svg" className={styles.locationIcon} alt="" />
                                <h6>You are not within office reach</h6>
                            </div>
                        )}

                        <div className={styles.location}>
                            <img src="././././public/images/location-sign.svg" className={styles.locationIcon} alt="" />
                            <h6 className ={styles.officeReach}>You are within office reach</h6>
                        </div>
                    </div>
                )}
            </div>
        </>
    );
}

export default ClockInOutWidget;
