import React, { useEffect, useState } from 'react';
import EmployeeCard from './EmployeeCard';
import styles from './EmployeeCard.module.css'
import utils from '../../../../Utils.js';

const EmployeeList = () => {
    const [listUsers, setListUsers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchUsers = async () => {
            try {
                const users = await utils.listusers();
                setListUsers(users);
                setLoading(false);
            } catch (error) {
                setError(error);
                setLoading(false);
            }
        };

        fetchUsers();
    }, []); // Empty dependency array ensures this runs once after the initial render

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error.message}</div>;
    }

    return (
        <div className={styles.employeeList}>
            {listUsers.map((user) => {
                const employeeName = `${user.firstName || ''} ${user.lastName || ''}`.trim();
                const imageUrl =
//                  user.firstName && user.lastName
//                                     ? `./images/${user.firstName} ${user.lastName} - Profile.jpg`
//                                     :
                            './images/default-icon.svg';
                return (
                    <EmployeeCard
                        key={user.employeeId}
                        employeeName={employeeName}
                        employeeId={user.employeeId}
                        imageUrl={imageUrl}
                    />
                );
            })}
        </div>
    );
};

export default EmployeeList;
