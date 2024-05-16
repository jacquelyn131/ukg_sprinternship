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
            return response.json();
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
                body: JSON.stringify(userLoc)
            });
            return response.json();
        } catch (error) {
            console.log('There was some kind of issue!', error);
        }
    }
    
}

const endpoints = new Endpoints();
export default endpoints;
