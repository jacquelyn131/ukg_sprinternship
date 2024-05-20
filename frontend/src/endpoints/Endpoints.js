class Endpoints {

    loginUser = async (user) => {
        try {
            const bodyData = {
                email: user.email,
                employeeId: user.employeeId
            };

            const response = await fetch('http://localhost:8080/api/auth/login', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(bodyData)
            });
            return response.json();
        } catch (error) {
            console.error('There was some kind of issue!', error);
        }
    }

    userInfo = async (userId) => {
        try {
            const bodyData = {
                email: userId.email,
                employeeId: userId.employeeId
            };
    
            const response = await fetch('http://localhost:8080/api/user/info', {
                method: 'POST', 
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(bodyData)
            });
            const jsonResponse = await response.json()
            return jsonResponse;
            
        } catch (error) {
            console.error('There was some kind of issue!', error);
        }
    }    

    addUser = async (emp) => {
        try {
            const bodyData = {
                email: emp.email,
                employeeId: emp.employeeId
            };

            const response = await fetch('http://localhost:8080/api/user/add', {
                method: 'POST', 
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(bodyData)
            }) 
            const jsonResponse = await response.json()
            return jsonResponse;
        } catch (error) {
            console.error('There was some kind of issue!', error)
        }
    }

    deleteUser = async (emp) => {
        try {
            const bodyData = {
                email: emp.email,
                employeeId: emp.employeeId
            };

            const response = await fetch('http://localhost:8080/api/user/delete', {
                method: 'POST', 
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(bodyData)
            }) 
            const jsonResponse = await response.json()
            return jsonResponse;
        } catch (error) {
            console.error('There was some kind of issue!', error)
        }
    }

    viewRecentShift = async (userId) => {
        try {
            const bodyData = {
                email: userId.email,
                employeeId: userId.employeeId
            };
    
            const response = await fetch('http://localhost:8080/api/user/viewRecentShift', {
                method: 'POST', 
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(bodyData)
            });
            const jsonResponse = await response.json()
            return jsonResponse;
            
        } catch (error) {
            console.error('There was some kind of issue!', error);
        }
    }

    locationChecker = async (userLoc) => {
        try {
            const response = await fetch('http://localhost:8080/api/user/location', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(userLoc)
            });
            return response.json();
        } catch (error) {
            console.error('There was some kind of issue!', error);
        }
    }
    
    addTimestamp = async (recordTime) => {
        try {
            // const bodyData = {
            //     employeeId: recordTime.employeeId,
            //     dateTime: recordTime.time,
            //     type: recordTime.timeStampType, // TYPES: (IN/OUT/BREAK-IN/BREAKOUT)
            //     comments: recordTime.comments
            // };
            const response = await fetch('http://localhost:8080/api/add/timestamp', {
                method: 'POST', 
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(recordTime)
            });
            const jsonResponse = await response.json()
            return jsonResponse;
            
        } catch (error) {
            console.error('There was some kind of issue!', error);
        }
    }  
    
    viewRecentPunch = async (employeeId) => {
        try {
            const response = await fetch(`http://localhost:8080/api/user/viewRecentPunch?id=${employeeId}`);
    
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
    
            const jsonResponse = await response.json();
    
        } catch (error) {
            console.error('There was an error!', error);
        }
    }
    
    viewRecentPunchList = async (employeeId) => {
        try {
            const response = await fetch(`http://localhost:8080/api/user/viewRecentPunchList?id=${employeeId}`);
    
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
    
            const jsonResponse = await response.json();
    
            return jsonResponse;
        } catch (error) {
            console.error('There was an error!', error);
        }
    }


}

const endpoints = new Endpoints();
export default endpoints;
