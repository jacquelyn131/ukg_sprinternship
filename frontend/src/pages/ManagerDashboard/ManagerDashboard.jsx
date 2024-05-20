const handleSubmit = (e) => {
    e.preventDefault();
    // Handle form submission logic here
    console.log(formData);
    // Reset form data after submission
    setFormData({
        firstName: '',
        lastName: '',
        socialSecurity: '',
        dob: '',
        employeeEmail: '',
        managerId: '' // Include managerId here
    });
    setShowModal(false);
};
