import ClockInOutWidget from './components/clockInOutWidget/ClockInOutWidget.jsx'
import AttendanceCard from './components/attendanceCard/AttendanceCard.jsx'
import GreetingMessage from './components/greetingMessage/GreetingMessage.jsx'
import CustomDate from './components/customDate/CustomDate.jsx'

import { useState } from 'react';


const EmployeeDashboard = () =>

{
    return (
        <>

       {/*} <div>Test</div>
        <ClockInOutWidget />*/}

        <div><h1>My Attendance</h1></div>
        <AttendanceCard test="Hello World" />

        <div>
            <GreetingMessage />
            <CustomDate />
                </div>
        <ClockInOutWidget />

        </>)}

        export default EmployeeDashboard;