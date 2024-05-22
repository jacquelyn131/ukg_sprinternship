import React, { Component, useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";

import AttendanceCard from "../../../EmployeeDashboard/components/attendanceCard/AttendanceCard.jsx"
import Utils from "../../../../Utils";
import Endpoints from "../../../../endpoints/Endpoints";
import PunchList from "../../../EmployeeDashboard/components/attendanceCard/PunchList.jsx";


const EmployeeDetailPage = () => {

    let { id } = useParams();
    const [employees, setEmployees] = useState([]);
    const [employeeInfo, setEmployeeInfo] = useState(null);

    const navigate = useNavigate();


    useEffect(() => {
        const fetchEmployee = async () => {
            try {
                const employees = await Utils.listusers();
                setEmployees(employees);
                console.log(employees);

                const matchingEmployee = employees.find(employee => employee.employeeId == id);

                if (matchingEmployee) {
                    setEmployeeInfo(matchingEmployee);
                }

            } catch (error) {
                console.error(error);
            }
        };

        fetchEmployee();
                    console.log(employeeInfo);

    }, [id]);

    employees.filter((employee) => employee.employeeId === id).map((employee) => {
        setEmployeeInfo(employee);
        console.log(employeeInfo);
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
                employeeId: id
            };

            const response = await Endpoints.deleteUser(employee);
            console.log(response);

            } catch (error) {
                console.error(error);
            }
        }

    return (
        <>
        <button onClick={() => navigate(-1)}>Back</button>
        <h1>Employee Detail Page</h1>
        <p>Employee ID: {id}</p>
        <p>First Name: {employeeInfo.firstName}</p>
        <p>Last Name: {employeeInfo.lastName}</p>
        <p>Email: {employeeInfo.email}</p>
        <p>Date of Birth: {employeeInfo.dob}</p>

        <button onClick={handleDelete}>Delete Employee</button>

        <PunchList employeeId={id} />

        </>
        );
    }

export default EmployeeDetailPage;