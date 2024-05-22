import React, { useState, useEffect } from 'react';
import utils from '../../../../Utils';
import AttendanceCard from './AttendanceCard';
const PunchList = ({ employeeId }) => {
    const [punchesByDay, setPunchesByDay] = useState({});
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const data = await utils.getPunchByDay(employeeId);
                setPunchesByDay(data);
            } catch (err) {
                setError(err.message);
            } finally {
                setLoading(false);
            }
        };

        fetchData();
    }, [employeeId]);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <div>
            
            {Object.entries(punchesByDay).map(([date, punches]) => (
                <div key={date}>
                    <h3>Date: {date}</h3>
                    <ul>
                        {punches.map(punch => (
                            <AttendanceCard key="punch.punchId" punchDate={punch.dateTime.split(" ")[1]}  totalHours={punch.type} punchTimeIn={punch.comments} punchTimeOut="8:00pm" />
                            // <li key={punch.punchId}>
                            //     <div>Time: {punch.dateTime.split(" ")[1]}</div>
                            //     <div>Punch ID: {punch.punchId}</div>
                            //     <div>Employee ID: {punch.employeeId}</div>
                            //     <div>Office ID: {punch.officeId}</div>
                            //     <div>Type: {punch.type}</div>
                            //     <div>Comments: {punch.comments}</div>
                            //     <div>Valid: {punch.valid ? 'Yes' : 'No'}</div>
                            // </li>
                        ))}
                    </ul>
                </div>
            ))}
        </div>
    );
};

export default PunchList;
