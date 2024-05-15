ALTER TABLE company
ADD CONSTRAINT fk_headquarters FOREIGN KEY (headquarters_id) REFERENCES company_address (company_office_id);