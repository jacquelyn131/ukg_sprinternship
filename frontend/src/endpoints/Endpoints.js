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
            console.log("Success!");
            return response.json();
        } catch (error) {
            console.error('There was some kind of issue!', error);
        }
    }
}

const endpoints = new Endpoints();
export default endpoints;
