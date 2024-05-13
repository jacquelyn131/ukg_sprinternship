import ClockInOutWidget from './components/clockInOutWidget/ClockInOutWidget.jsx'
import GreetingMessage from './components/greetingMessage/GreetingMessage.jsx'
import CustomDate from './components/customDate/CustomDate.jsx'

import { useState } from 'react';


const EmployeeDashboard = () =>

{
    return (
        <>
        <div>
            <GreetingMessage />
            <CustomDate />
                </div>
        <ClockInOutWidget />
        </>)}

        export default EmployeeDashboard;