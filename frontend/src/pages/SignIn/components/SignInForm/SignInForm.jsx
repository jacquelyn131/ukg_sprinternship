import './SignInForm.module.css';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import SignInButton from './SignInButton';
import Container from 'react-bootstrap/Container';
import { useState, useEffect } from 'react';
import Endpoints from '../../../../endpoints/Endpoints';
import { useNavigate } from 'react-router-dom';
const SignInForm = () => {
    const navigate = useNavigate();

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [missingInfoMessage, setMissingInfoMessage] = useState("");
	const [badCredentialsMessage, setBadCredentialsMessage] = useState("");

    useEffect(() => {
		if (sessionStorage.getItem("login_token")) {
            // Navigate to the correct URL
			// navigate("/dashboard");
		}
	});

    const handleLogin = async (e) => {
        e.preventDefault();
        
        if (!email || !password) {
            setMissingInfoMessage('Please fill out all the fields!');
            return;
        }
        
        const user = {
            email: email,
            password: password // TODO: hash the password, do not send in plaintext
        };
        
        // HTTP post to server
        const response = await Endpoints.loginUser(user);
        
        if (response && response.uid) {
            localStorage.setItem('login_token', response.uid);
            console.log(sessionStorage.getItem('login_token'))
        }
        //console.log(userToken.userEmail);
    };    

    return (
        <>
            <Container fluid="sm">
                <Form onSubmit={handleLogin}>
                    <Form.Group className="mb-3" controlId="formBasicEmail">
                        <Form.Label>Email address</Form.Label>
                        <Form.Control 
                            type="email" 
                            placeholder="Enter email" 
                            value = {email}
                            onChange={(e) => setEmail(e.target.value)}    
                        />
                        <Form.Text className="text-muted">
                        We'll never share your email with anyone else.
                        </Form.Text>
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="formBasicPassword">
                        <Form.Label>Password</Form.Label>
                        <Form.Control 
                            type="password" 
                            placeholder="Password" 
                            value = {password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                    </Form.Group>
                    <SignInButton>
                        Sign In
                    </SignInButton>
                </Form>
            </Container>
        </>
    )
}

export default SignInForm;