import styles from './AttendanceCard.module.css'

const AttendanceCard = (props) => {

    return (
        <>

<div className={styles.attendanceCardContainer}>
        <div className={styles.attendanceBlock}>
            <div className={styles.date}>
                May 6, 2024
            </div>

           <div className={styles.punchContainer}>

               <div className={styles.totalHoursContainer}>
                     <span className={styles.totalHours}>08:00</span>
                   <span className={styles.totalHoursLabel}>Total Hours</span>
                   </div>

                    <div className={styles.punch}>
                        <span className={styles.punchTime}>06:00</span>
                    <span className={styles.punchType}>Clock In</span>
                    </div>
                      <div className={styles.punch}>
                          <span className={styles.punchTime}>02:00</span>
                      <span className={styles.punchType}>Clock Out</span>
                    </div>

               </div>
           </div>
        </div>
</>)}

        export default AttendanceCard;