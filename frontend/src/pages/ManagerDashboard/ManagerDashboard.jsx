import React, { useState } from 'react';
import { Modal, Button, Form } from 'react-bootstrap';
import GreetingMessage from '../../pages/EmployeeDashboard/components/greetingMessage/GreetingMessage.jsx';
import CustomDate from '../../pages/EmployeeDashboard/components/customDate/CustomDate.jsx';
import EmployeeCard from '../../pages/ManagerDashboard/components/employeeCard/EmployeeCard.jsx';

import styles from '../../pages/ManagerDashboard/ManagerDashboard.module.css';

import { useUser } from '../../UserContext';
import endpoints from '../../endpoints/Endpoints.js';
import utils from '../../Utils.js';

const ManagerDashboard = () => {
    const { userInfo } = useUser();
    const [showModal, setShowModal] = useState(false);
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        ssn: '',
        dob: '',
        email: '',
        managerId: ''
    });

    const handleCloseModal = () => setShowModal(false);
    const handleShowModal = () => setShowModal(true);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        // Handle form submission logic here
        console.log(formData);
        
        console.log(await endpoints.addUser(formData))
        // Reset form data after submission
        setFormData({
            firstName: '',
            lastName: '',
            ssn: '',
            dob: '',
            email: '',
            managerId: '' // Include managerId here
        });
        setShowModal(false);
    };

    return (
        <div className={styles.dashboardWrapper}>
            <div className={styles.dashboardGreeting}>
                <div className={styles.greetingContainer}>
                    <h2 className={styles.customGreeting}>Good Morning, Jesse!</h2>
                    <CustomDate className={styles.customDate} />
                </div>
            </div>

            <section className={styles.managerDashboard}>
                <h2 className={styles.dashboardTitle}>My Employees</h2>
                <div className={styles.employeeCardContainer}>
                    <EmployeeCard employeeName="John Woo" imageUrl="./images/John Woo - Profile.jpg" />
                    <EmployeeCard employeeName="Jeff Dean" imageUrl="./images/Jeff Dean - Profile.jpg" />
                    <EmployeeCard employeeName="Josh Bloke" imageUrl="./images/Josh Bloke - Profile.jpg" />
                    <EmployeeCard employeeName="Josh Long" imageUrl="./images/Josh Long - Profile.jpg" />
                    <EmployeeCard employeeName="Emily Stone" imageUrl="./images/Emily Stone - Profile.jpg" />
                    <EmployeeCard employeeName="Jane Doe" imageUrl="./images/Jane Doe - Profile.jpg" />
                    <EmployeeCard employeeName="John Smith" imageUrl="./images/John Smith - Profile.jpg" />
                    <EmployeeCard employeeName="Michael Roe" imageUrl="./images/Michael Roe - Profile.jpg" />
                    
                </div>
            </section>

            <div className={styles.divider}></div>

            <div className={styles.bottomButton}>
                <Button className={styles.addEmployee} variant="primary" onClick={handleShowModal}>Add Employee</Button>

                <section>
                    <Modal className={styles.modal} show={showModal} onHide={handleCloseModal}>
                        <Modal.Header className={styles.modalHeader} closeButton>
                            <Modal.Title className={styles.modalTitle}>Add Employee</Modal.Title>
                        </Modal.Header>
                        <Modal.Body className={styles.modalBody}>
                            <Form className={styles.form} onSubmit={handleSubmit}>
                                <Form.Group controlId="firstName">
                                    <Form.Label>First Name</Form.Label>
                                    <Form.Control type="text" name="firstName" placeholder="John" value={formData.firstName} onChange={handleChange} required />
                                </Form.Group>
                                <Form.Group controlId="lastName">
                                    <Form.Label>Last Name</Form.Label>
                                    <Form.Control type="text" name="lastName" placeholder="Doe" value={formData.lastName} onChange={handleChange} required />
                                </Form.Group>
                                <Form.Group controlId="ssn">
                                    <Form.Label>Social Security</Form.Label>
                                    <Form.Control type="text" name="ssn" placeholder="123-45-6789" value={formData.ssn} onChange={handleChange} required />
                                </Form.Group>
                                <Form.Group controlId="dob">
                                    <Form.Label>Date of Birth</Form.Label>
                                    <Form.Control type="date" name="dob" value={formData.dob} onChange={handleChange} required />
                                </Form.Group>
                                <Form.Group controlId="email">
                                    <Form.Label>Employee Email</Form.Label>
                                    <Form.Control type="email" name="email" placeholder="JohnDoe@gmail.com" value={formData.email} onChange={handleChange} required />
                                </Form.Group>
                                <Form.Group controlId="managerId">
                                    <Form.Label>Manager ID</Form.Label>
                                    <Form.Control type="text" name="managerId" value={formData.managerId} onChange={handleChange} required />
                                </Form.Group>
                                <Button className={styles.submitEmployee} variant="primary" type="submit">Submit</Button>
                            </Form>
                        </Modal.Body>
                    </Modal>
                </section>
            </div>
        </div>
    );
};

export default ManagerDashboard;
