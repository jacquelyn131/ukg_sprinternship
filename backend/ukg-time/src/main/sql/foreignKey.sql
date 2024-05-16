ALTER TABLE employee
ADD CONSTRAINT fk_manager_id FOREIGN KEY (manager_id) REFERENCES employee (employee_id);