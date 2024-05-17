import styles from './MyAttendance.module.css';
import AttendanceCard from '../EmployeeDashboard/components/attendanceCard/AttendanceCard';
// import {VerticalTimeline, VerticalTimelineElement} from "react-vertical-timeline-component";
// import "react-vertical-timeline-component/style.min.css";

const MyAttendance = () => {
    return (
        <>
           <div className={styles.myAttendanceWrapper}>

                <button className={styles.backButton} type="button">
                               <img src="./././public/images/BackArrow.svg" className={styles.backArrow} />
                           </button>
                           <div className={styles.myAttendance}>
                                           <h1 className={styles.myAttendanceHeader}>My Attendance </h1>

               </div>
                           <div className={styles.allAttendance}>


                               <div className={styles.attendanceCard1}>
                                   <AttendanceCard className={styles.test} />

                                   <AttendanceCard className={styles.test} />
                                   <AttendanceCard className={styles.test} />
                               </div>
                           </div>

                           <div className={styles.container}>
                               <button className={styles.viewAll}> View All </button>
                           </div>
               </div>
        </>
    )
}

export default MyAttendance;