import styles from './MyAttendance.module.css';
import AttendanceCard from '../EmployeeDashboard/components/attendanceCard/AttendanceCard';
import { useNavigate } from 'react-router-dom';


const MyAttendance = () => {

    const navigate = useNavigate();
    const handleBackButton = (e) => {
        e.preventDefault();
        console.log('back button clicked')
        navigate('/dashboard');
    }


    return (
        <>
           <div className={styles.myAttendanceWrapper}>

                <button className={styles.backButton} type="button" onClick={handleBackButton}>
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