import React, { createContext, useState, useContext, useEffect } from 'react';
import Endpoints from './endpoints/Endpoints';

const UserContext = createContext(null);

export const UserProvider = ({ children }) => {
    const [loginToken, setLoginToken] = useState(localStorage.getItem('login_token'));
    const [userInfo, setUserInfo] = useState(null);

    useEffect(() => {
        const token = localStorage.getItem('login_token');
        if (token) {
            setLoginToken(token);

            Endpoints.userInfo(token).then(data => {
                setUserInfo(data);
            }).catch(error => console.error('Failed to fetch user info', error));
        }
    }, []);

    return (
        <UserContext.Provider value={{ loginToken, userInfo, setLoginToken, setUserInfo }}>
            {children}
        </UserContext.Provider>
    );
};

export const useUser = () => {
    const context = useContext(UserContext);
    if (context === undefined) {
        throw new Error('useUser must be used within a UserProvider');
    }
    return context;
};
