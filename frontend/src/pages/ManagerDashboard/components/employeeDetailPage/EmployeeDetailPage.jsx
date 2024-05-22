
import React, { Component, useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";

import { Alert } from "react-bootstrap";
import styles from "./EmployeeDetailPage.module.css";
import AttendanceCard from "../../../EmployeeDashboard/components/attendanceCard/AttendanceCard.jsx"
import Utils from "../../../../Utils";
import Endpoints from "../../../../endpoints/Endpoints";
import PunchList from "../../../EmployeeDashboard/components/attendanceCard/PunchList.jsx";


const EmployeeDetailPage = () => {

    let { id } = useParams();
    const [employees, setEmployees] = useState([]);
    const [employeeInfo, setEmployeeInfo] = useState(null);

    const navigate = useNavigate();

    const [showAlert, setShowAlert] = useState(false);
    const [alertMessage, setAlertMessage] = useState('');
    const [alertType, setAlertType] = useState('success');

    const toggleShowAlert = () => setShowAlert(!showAlert);

                useEffect(() => {
                    let timer;
                    if (showAlert && alertType !== 'danger') {
                        timer = setTimeout(() => {
                            toggleShowAlert();
                        }, 10000); // 10 seconds
                    }

                    return () => {
                        clearTimeout(timer);
                    };
                }, [showAlert]);


    useEffect(() => {
        const fetchEmployee = async () => {
            try {
                const employees = await Utils.listusers();
                setEmployees(employees);

                const matchingEmployee = employees.find(employee => employee.employeeId === parseInt(id));

                if (matchingEmployee) {
                    setEmployeeInfo(matchingEmployee);
                }

            } catch (error) {
                console.error(error);
            }
        };

        fetchEmployee();

    }, [id]);

    employees.filter((employee) => employee.employeeId === id).map((employee) => {
        setEmployeeInfo(employee);
    });

    if (employeeInfo == null) {
        return (
            <h1>Loading...</h1>
        );
    }

    const handleDelete = async () => {
        try {

            const employee = {
                email: employeeInfo.email,
                employeeId: id,
                firstName: employeeInfo.firstName,
                lastName: employeeInfo.lastName,
                dob: employeeInfo.dob,
                ssn: employeeInfo.ssn,
                managerId: employeeInfo.managerId,
            };

            const response = await Endpoints.deleteUser(employee);


            setAlertMessage('Employee deleted successfully');
            setAlertType('success');
            setShowAlert(true);

            setTimeout(() => {
                navigate(-1);
            }, 2000);


            } catch (error) {
                console.error(error);
            }
        }

    return (
        <>
<button className={styles.backButton} onClick={() => navigate(-1)}><img src="/public/images/BackArrow.svg" className={styles.backArrow} /></button>
        <div className={styles.pageContainer}>

            <div className={styles.employeeInfoContainer}>

<div className={styles.employeeInfo}>
<h1>{employeeInfo.firstName} {employeeInfo.lastName}'s <span className={styles.subtitle}>Attendance List</span></h1>
<p>
    <strong>Employee ID:</strong> {id} <br />
    <strong>Email:</strong> {employeeInfo.email} <br />
    <strong>Date of Birth:</strong> {employeeInfo.dob}
    </p>
    <button onClick={handleDelete} className={styles.deleteButton}>Delete Employee</button>
    </div>
<img src="/images/default-icon.svg" className={styles.employeeImage} />
                </div>
                            <div className={styles.divider}></div>

<div className={styles.attendanceList}>
                            <AttendanceCard />
                            </div>


            </div>


        <PunchList employeeId={id} />

{showAlert && (
                        <Alert variant={alertType} className={styles.alert} onClose={toggleShowAlert} dismissible>
                            {alertMessage}
                           </Alert>
                         )
                        }

        </>
        );
    }

export default EmployeeDetailPage;