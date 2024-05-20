import { useState, useEffect } from "react";
import styles from './ClockInOutWidget.module.css';
import endpoints from '../../../../endpoints/Endpoints';

import { useUser } from '../../../../UserContext.jsx'
import { Button } from 'react-bootstrap';
import { Offcanvas } from 'react-bootstrap';
import offcanvasStyles from './ClockOutOffcanvasStyles.module.css'

import Utils from '../../../../Utils.js';

const ClockInOutWidget = () => {
    const { userInfo } = useUser();

    const [showOffcanvas, setShowOffcanvas] = useState(false);
    const [totalHours, setTotalHours] = useState(0);
    const [currentTime, setCurrentTime] = useState("");
    const [currentLocation, setCurrentLocation] = useState("");
    const [clockedIn, setClockedIn] = useState(false);
    const [onBreak, setOnBreak] = useState(false);
    const [withinReach, setWithinReach] = useState('true');
    const [clockInStartTime, setClockInStartTime] = useState(null);
    const [breakStartTime, setBreakStartTime] = useState(null);
    const [clockOutTime, setClockOutTime] = useState(null);
    const [clockInElapsedTime, setClockInElapsedTime] = useState(0);
    const [breakElapsedTime, setBreakElapsedTime] = useState(0);
    const [dateTime, setDateTime] = useState("");
    const getDateTime = () => {
        return new Date().toISOString().slice(0, 19).replace('T', ' ');
    }
    useEffect(() => {
        const interval = setInterval(() => {
            setCurrentTime(getFormattedTime());
        }, 1000);
        return () => clearInterval(interval);
    }, []);
    function getFormattedTime() {
        const now = new Date();
        const hour = String(now.getHours() % 12 || 12).padStart(2, '0');
        const minute = String(now.getMinutes()).padStart(2, '0');
        const amOrPm = now.getHours() >= 12 ? 'PM' : 'AM';
        return `${hour}:${minute} ${amOrPm}`;
    }
    const geolocation = async (e) => {
        e.preventDefault();
        const showPosition = async (position) => {
            const lat = position.coords.latitude;
            const lon = position.coords.longitude;
            const userLoc = { latitude: lat, longitude: lon };
            const locationResponse = await endpoints.locationChecker(userLoc);
            setWithinReach(locationResponse);
        };
        navigator.geolocation.getCurrentPosition(showPosition);
    }
    const handleClockIn = async (e) => {
        e.preventDefault();
        
        const emp = {
            email: "john@gmail.com",
            employeeId: 4
        };
        // console.log(await endpoints.deleteUser(emp));
        // console.log(await endpoints.viewRecentShift(emp))
        const timeStampInfo = {
            employeeId: userInfo.employeeId,
            type: 'IN',
            dateTime: getDateTime(),
        };
        endpoints.addTimestamp(timeStampInfo);
        geolocation(e);
        setClockedIn(true);
        setClockInStartTime(new Date());
        setCurrentTime("");
    };
    const handleClockOut = (e) => {
        e.preventDefault();
        let comments = document.getElementById('comments').value;
        if (comments === "") {
            comments = null;
        }
        const timeStampInfo = {
            employeeId: userInfo.employeeId,
            type: 'OUT',
            dateTime: getDateTime(),
            comments: comments,
        };
        endpoints.addTimestamp(timeStampInfo);
        setClockedIn(false);
        setClockInElapsedTime(0);
        setOnBreak(false);
        setCurrentTime(getFormattedTime());
        setShowOffcanvas(false);
    };
    const handleBreak = (e) => {
        e.preventDefault();
        const timeStampInfo = {
            employeeId: userInfo.employeeId,
            dateTime: getDateTime(),
            type: 'BREAK-IN',
        };
        endpoints.addTimestamp(timeStampInfo);
        setOnBreak(true);
        setBreakStartTime(new Date());
        setClockInElapsedTime((prevElapsedTime) => {
            setBreakElapsedTime(prevElapsedTime);
            return prevElapsedTime;
        });
    };
    const handleEndBreak = (e) => {
        e.preventDefault();
        const timeStampInfo = {
            employeeId: userInfo.employeeId,
            type: 'BREAK-OUT',
            dateTime: getDateTime(),
        };
        endpoints.addTimestamp(timeStampInfo);
        setOnBreak(false);
        setBreakElapsedTime(0);
        setClockInStartTime((prevStartTime) => {
            const breakDuration = new Date() - breakStartTime;
            return new Date(prevStartTime.getTime() + breakDuration);
        });
    };
    useEffect(() => {
        let clockInInterval;
        let breakInterval;
        if (clockedIn && !onBreak && !showOffcanvas) {
            clockInInterval = setInterval(() => {
                const currentTime = new Date();
                const elapsedMilliseconds = currentTime - clockInStartTime;
                setClockInElapsedTime(elapsedMilliseconds);
            }, 1000);
        } else {
            clearInterval(clockInInterval);
        }
        if (onBreak) {
            breakInterval = setInterval(() => {
                const currentTime = new Date();
                const elapsedMilliseconds = currentTime - breakStartTime;
                setBreakElapsedTime(elapsedMilliseconds);
            }, 1000);
        } else {
            clearInterval(breakInterval);
        }
        return () => {
            clearInterval(clockInInterval);
            clearInterval(breakInterval);
        };
    }, [clockedIn, onBreak, clockInStartTime, breakStartTime, showOffcanvas]);
    const handleOffcanvasShow = () => {
        setTotalHours(clockInElapsedTime);
        console.log("clockin: " + clockInStartTime);
        setClockOutTime(new Date());
        clockOutTime && console.log("clockout" + clockOutTime);
        setShowOffcanvas(true);
    };
    const handleOffcanvasClose = () => setShowOffcanvas(false);
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
                            <Button className={styles.clockoutButton} onClick={handleOffcanvasShow}>
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
 <Offcanvas placement="bottom" show={showOffcanvas} onHide={handleOffcanvasClose} className={offcanvasStyles.clockOutOffcanvas}>
        <Offcanvas.Header closeButton className={offcanvasStyles.confirmClockOutOffcanvasHeader}>
          <Offcanvas.Title className={offcanvasStyles.offcanvasTitle}>Confirm Clockout</Offcanvas.Title>
        </Offcanvas.Header>
       <Offcanvas.Body className={offcanvasStyles.offcanvasBody}>
         {clockOutTime != null && (
           <div>
             <div className={offcanvasStyles.totalHoursContainer}>
               <span className={offcanvasStyles.totalHoursTitle}>
                   <h2>Today's Total Hours</h2>
                   </span>
                <span className={offcanvasStyles.totalHoursValue}>
                   {formatElapsedTime(clockInElapsedTime)}</span>
                   </div>
                   <div className={offcanvasStyles.punchesContainer}>
                       <div className={offcanvasStyles.clockIn}>
               <span className={offcanvasStyles.punchLabel}><h3>Clock In Time</h3></span>
<span className={offcanvasStyles.punchValue}>
               {clockInStartTime.toLocaleTimeString()}
               </span>
               </div>
             <div className={offcanvasStyles.clockOut}>
              <span className={offcanvasStyles.punchLabel}><h3>Clock Out Time:</h3></span>
               <span className={offcanvasStyles.punchValue}>
               {clockOutTime.toLocaleTimeString()}
               </span>
             </div></div>
           </div>
         )}
         <div className={offcanvasStyles.commentsContainer}>
             <label htmlFor="comments" className={offcanvasStyles.commentsLabel}>Add Notes (Optional)</label>
                      <textarea placeholder="Write a note here..." name="comments" id="comments" className={offcanvasStyles.commentsInput} rows="3"></textarea>
</div>
         <div className={offcanvasStyles.buttonContainer}>
             <button type="button" onClick={handleOffcanvasClose} className={offcanvasStyles.cancelButton}>Cancel
                 </button>
             <button type="button" onClick={handleClockOut} className={offcanvasStyles.clockOutButton}>
                        Confirm Clock Out
                      </button>
             </div>
       </Offcanvas.Body>
      </Offcanvas>
        </>
    );
}
export default ClockInOutWidget;