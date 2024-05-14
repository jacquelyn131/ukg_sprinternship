class Endpoints {
    loginUser = async (user) => {
        try {
            const response = await fetch('http://localhost:8080/api/auth/login', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(user)
            });
            return response.json();
        } catch (error) {
            console.error('There was some kind of issue!', error);
        }
        console.log(response.json())
    }

    userInfo = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/user/info', {
                method: 'GET', // Keep method as GET
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
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
    
}

const endpoints = new Endpoints();
export default endpoints;
