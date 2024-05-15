import styles from './MyAttendance.module.css';
import AttendanceCard from '../EmployeeDashboard/components/attendanceCard/AttendanceCard';
import {VerticalTimeline, VerticalTimelineElement} from "react-vertical-timeline-component";
import "react-vertical-timeline-component/style.min.css";

const MyAttendance = () => {
    const dotStyles = {background: "#005151"}
    return (
        <>

 <div className={styles.backButton}>
      <button type="button">Back </button>
      <img src="./././public/images/BackArrow.svg" className={styles.backArrow} />
    </div>
<h1>My Attendance</h1>
            <div>

                            <div className={styles.attendanceCard1}>
                                 <AttendanceCard />
                            </div>
                            <div className={styles.attendanceCard1}>
                                                             <AttendanceCard />
                                                        </div>
                                                        <div className={styles.attendanceCard1}>
                                                                                         <AttendanceCard />
                                                                                    </div>
                                                                                    <div className={styles.attendanceCard1}>
                                                                                                                     <AttendanceCard />
                                                                                                                </div>



            </div>

            <div>
                  <button type="button">View all</button>
                </div>

       </>
    )
}

export default MyAttendance;