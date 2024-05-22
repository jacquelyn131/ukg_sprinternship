import styles from './SignInForm.module.css';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import SignInButton from './SignInButton';
import Container from 'react-bootstrap/Container';
import { useState, useEffect } from 'react';
import Endpoints from '../../../../endpoints/Endpoints';
import { useNavigate } from 'react-router-dom';

import { useUser } from '../../../../UserContext';
import utils from '../../../../Utils';

const SignInForm = () => {
    const { setLoginToken, setUserInfo, loginToken } = useUser();
    const navigate = useNavigate();

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [missingInfoMessage, setMissingInfoMessage] = useState("");
	const [badCredentialsMessage, setBadCredentialsMessage] = useState("");

    const handleLogin = async (e) => {
        e.preventDefault();
        console.log(await utils.getPunchByDay(1));
        if (!email || !password) {
            setMissingInfoMessage('Please fill out all the fields!');
            return;
        }

        const emp = {
            email: email,
            employeeId: password // TODO: hash the password, do not send in plaintext
        };

        try {
            // HTTP post to server
            const response = await Endpoints.loginUser(emp);
            if (response) {
                const response = await Endpoints.userInfo(emp);
                sessionStorage.setItem('login_token', response.employeeId);
                setLoginToken(response.employeeId);
//                 const userInfo = await Endpoints.userInfo();
                setUserInfo(response);
                navigate("/dashboard");

            }
        } catch (error) {
            console.error('Login failed', error);
            setBadCredentialsMessage('Invalid credentials. Please try again.');
        }
    };

    return (
        <>
            <Container fluid="sm">
                <Form onSubmit={handleLogin} id={styles.signInForm}>
                    <Form.Group className="mb-3" controlId="formBasicEmail">
                        <Form.Control
                            type="email" 
                            placeholder="Email Address"
                            value = {email}
                            onChange={(e) => setEmail(e.target.value)}    
                        />
                        <Form.Text className="text-muted">
                        We'll never share your email with anyone else.
                        </Form.Text>
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="formBasicPassword">
                        <Form.Control
                            type="password" 
                            placeholder="Password" 
                            value = {password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                    </Form.Group>
                    <SignInButton>
                        Log In
                    </SignInButton>
                </Form>
            </Container>
        </>
    )
}

export default SignInForm;