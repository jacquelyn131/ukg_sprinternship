import styles from './AttendanceCard.module.css'

const AttendanceCard = (props) => {

    return (
        <>

<div className={styles.all}>
        <div className={styles.attendanceBlock}>
            <div className={styles.date}>
                May 6, 2024
                </div>

           <div className={styles.punchContainer}>
               <div className={styles.totalHours}>
                   08:00<br />
                   Total Hours
                   </div>
                    <div className={styles.clockIn}>06:00<br />
                    Clock In</div>
                      <div className={styles.clockOut}>02:00<br />
                      Clock out</div>

               </div>
</div>
</div>


        </>)}

        export default AttendanceCard;