import React from 'react';
import styles from './EmployeeCard.module.css';

const EmployeeCard = (props) => {
    return (
        <button className={styles.employeeCard}>
            <h3 className={styles.employeeName}>{props.employeeName}</h3>
            <img src={props.imageUrl} alt="Employee Picture" />
        </button>
    );
};

export default EmployeeCard;
