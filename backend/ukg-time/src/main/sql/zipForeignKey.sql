ALTER TABLE company_address
ADD CONSTRAINT zip_foreign_key FOREIGN KEY (zip) REFERENCES zip (zip);