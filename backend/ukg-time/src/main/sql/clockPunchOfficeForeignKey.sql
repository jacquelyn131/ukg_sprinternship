ALTER TABLE clock_punch
ADD CONSTRAINT fk_office FOREIGN KEY (office_id) REFERENCES company_address (office_id);