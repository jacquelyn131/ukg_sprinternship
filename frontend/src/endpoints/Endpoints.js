class Endpoints {
    // loginUser = async (user) => {
    //     try {
    //         const response = await fetch('http://localhost:8080/api/auth/login', {
    //             method: 'POST',
    //             headers: {
    //                 'Accept': 'application/json',
    //                 'Content-Type': 'application/json'
    //             },
    //             body: JSON.stringify(user)
    //         });
    //         return response.json();
    //     } catch (error) {
    //         console.error('There was some kind of issue!', error);
    //     }
    //     console.log(response.json())
    // }

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

    userTimeStamp = async (timeStamp) => {
        try {
            const response = await fetch('http://localhost:8080/api/user/location', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(timeStamp)
            });
            return response.json();
        } catch (error) {
            console.log('There was some kind of issue!', error);
        }
    }
    
    addTimestamp = async (recordTime) => {
        try {
            const bodyData = {
                employeeId: recordTime.employeeId,
                dateTime: recordTime.time,
                type: recordTime.timeStampType // TYPES: (IN/OUT/BREAK-IN/BREAKOUT)
            };
    
            const response = await fetch('http://localhost:8080/api/add/timestamp', {
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
}

const endpoints = new Endpoints();
export default endpoints;
