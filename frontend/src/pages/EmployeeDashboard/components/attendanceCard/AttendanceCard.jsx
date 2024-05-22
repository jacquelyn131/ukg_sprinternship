import styles from './AttendanceCard.module.css'

const AttendanceCard = (props) => {

    return (
        <>

<div className={styles.attendanceCardContainer}>
        <div className={styles.attendanceBlock}>
            <div className={styles.date}>
                {props.punchDate}
            </div>

           <div className={styles.punchContainer}>

               <div className={styles.totalHoursContainer}>
                     <span className={styles.totalHours}>
                        {props.totalHours}
                     </span>
                   <span className={styles.totalHoursLabel}>Type</span>
                   </div>

                    <div className={styles.punch}>
                        <span className={styles.punchTime}>
                            {props.punchTimeIn}
                        </span>
                    <span className={styles.punchType}>Clock In</span>
                    </div>
                      <div className={styles.punch}>
                            <span className={styles.punchTime}>
                                {props.punchTimeOut}
                            </span>
                      <span className={styles.punchType}>Clock Out</span>
                    </div>

               </div>
           </div>
        </div>
</>)}

        export default AttendanceCard;