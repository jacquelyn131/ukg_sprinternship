ALTER TABLE company_location
ADD CONSTRAINT fk_company_location FOREIGN KEY (company_office_id) REFERENCES company_address (company_office_id);