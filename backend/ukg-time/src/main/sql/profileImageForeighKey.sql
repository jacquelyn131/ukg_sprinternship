ALTER TABLE profile_image
ADD CONSTRAINT fk_employee_id FOREIGN KEY (e_id) REFERENCES employees (employee_id);