import React from 'react';
import styles from './EmployeeCard.module.css';
import { useNavigate } from 'react-router-dom';

const EmployeeCard = (props) => {

    const navigate = useNavigate();

    const handleClick = (e) => {
        e.preventDefault();
        navigate(`/employee/${props.employeeId}`);
        }

    return (
        <button className={styles.employeeCard} onClick={handleClick}>
            <h3 className={styles.employeeName}>{props.employeeName}</h3>
            <img src={props.imageUrl} alt="Employee Picture" />
        </button>
    );
};

export default EmployeeCard;
