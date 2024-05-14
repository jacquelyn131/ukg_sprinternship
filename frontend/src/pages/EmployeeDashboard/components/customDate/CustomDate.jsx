import styles from './CustomDate.module.css';
import { useState, useEffect } from "react";

function CustomDateFormat() {
    const [newDate, setNewDate] = useState('');

    useEffect(() => {
        let todayDate = new Date();
        let options = { weekday: 'short', month: 'short', day: 'numeric', year: 'numeric' };
        let formattedDate = todayDate.toLocaleDateString('en-US', options);
        console.log(formattedDate);
        setNewDate(formattedDate);
    }, []);

    return (
        <div className={styles.date}>
            <h4>{newDate}</h4>
        </div>
    );
}

export default CustomDateFormat;
