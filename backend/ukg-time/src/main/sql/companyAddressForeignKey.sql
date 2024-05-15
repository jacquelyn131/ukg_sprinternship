ALTER TABLE company_address
ADD CONSTRAINT fk_company_address FOREIGN KEY (company_id) REFERENCES company (company_id);