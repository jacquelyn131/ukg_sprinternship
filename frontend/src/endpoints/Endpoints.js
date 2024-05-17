class Endpoints {

    // userInfo = async (userId) => {
    //     try {
    //         const bodyData = {
    //             email: userId.email,
    //             employeeId: userId.employeeId
    //         };
    
    //         const response = await fetch('http://localhost:8080/api/user/info', {
    //             method: 'POST', 
    //             headers: {
    //                 'Accept': 'application/json',
    //                 'Content-Type': 'application/json'
    //             },
    //             body: JSON.stringify(bodyData)
    //         });
    //         const jsonResponse = await response.json()
    //         console.log(jsonResponse)
    //         return jsonResponse;
            
    //     } catch (error) {
    //         console.error('There was some kind of issue!', error);
    //     }
    // }

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
        console.log(response.json())
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
            console.log(jsonResponse)
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

    // userTimeStamp = async (timeStamp) => {
        
    //     try {
    //         const response = await fetch('http://localhost:8080/api/user/location', {
    //             method: 'POST',
    //             headers: {
    //                 'Accept': 'application/json',
    //                 'Content-Type': 'application/json'
    //             },
    //             body: JSON.stringify(timeStamp)
    //         });
    //         return response.json();
    //     } catch (error) {
    //         console.log('There was some kind of issue!', error);
    //     }
    // }
    
    addTimestamp = async (recordTime) => {
        console.log(recordTime)
        try {
            // const bodyData = {
            //     employeeId: recordTime.employeeId,
            //     dateTime: recordTime.time,
            //     type: recordTime.timeStampType, // TYPES: (IN/OUT/BREAK-IN/BREAKOUT)
            //     comments: recordTime.comments
            // };
            console.log(recordTime)
            const response = await fetch('http://localhost:8080/api/add/timestamp', {
                method: 'POST', 
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(recordTime)
            });
            const jsonResponse = await response.json()
            console.log(jsonResponse)
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
    
            console.log(jsonResponse);
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
    
            console.log(jsonResponse);
        } catch (error) {
            console.error('There was an error!', error);
        }
    }
}

const endpoints = new Endpoints();
export default endpoints;
