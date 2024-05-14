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
                     <div className={styles.num8}>08:00<br /></div>
                   <h3>Total Hours</h3>
                   </div>
                    <div className={styles.clockIn}>
                        <div className={styles.num6}>06:00<br/></div>
                    <h4>Clock In</h4>
                    </div>
                      <div className={styles.clockOut}>
                          <div className={styles.num2}>02:00<br/></div>
                      <h4>Clock out</h4>
                    </div>

               </div>
           </div>
        </div>
</>)}

        export default AttendanceCard;