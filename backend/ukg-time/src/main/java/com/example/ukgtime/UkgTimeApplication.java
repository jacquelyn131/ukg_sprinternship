package com.example.ukgtime;

import java.io.FileInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Arrays;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

@SpringBootApplication
public class UkgTimeApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(UkgTimeApplication.class);
	public static void main(String[] args) throws IOException, FirebaseAuthException {
		SpringApplication.run(UkgTimeApplication.class, args);


		// FirebaseOptions options = FirebaseOptions.builder()
		// .setCredentials(GoogleCredentials.getApplicationDefault())
		// .setDatabaseUrl("https://<DATABASE_NAME>.firebaseio.com/")
		// .build();

		// FileInputStream serviceAccount = new FileInputStream("keys\\firebaseKeys.json");

		// @SuppressWarnings("deprecation")
		// FirebaseOptions options = new FirebaseOptions.Builder()
		// .setCredentials(GoogleCredentials.fromStream(serviceAccount))
		// .build();

		// FirebaseApp app = FirebaseApp.initializeApp(options);
		// FirebaseAuth defaultAuth = FirebaseAuth.getInstance(app);


	}
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... strings) throws Exception {

		log.info("Creating tables");

		jdbcTemplate.execute("DROP TABLE IF EXISTS employees");
		jdbcTemplate.execute("CREATE TABLE employees(" +
				"employee_id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255), ssn CHAR(9), " +
				"dob DATE, manager_id INTEGER, email VARCHAR(255))");
		// split up the array of whole names into an array of first/last names
		List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
				.map(name-> name.split(" "))
				.collect(Collectors.toList());
		// use a java 8 stream to print out each tuple
		splitUpNames.forEach(name->
				log.info(String.format("Inserting employee record for %s %s", name[0], name[1])));

		// use JdbcTemplate's batchUpdate operation to bulk load data
		jdbcTemplate.batchUpdate("INSERT INTO employees(first_name, last_name) " +
				"VALUES (?,?)", splitUpNames);

		log.info("Querying for employee records where first_name = 'Josh':");
		jdbcTemplate.query(
				"SELECT employee_id, first_name, last_name FROM employees WHERE first_name = ?",
				(rs, rowNum) -> new Employee(rs.getLong("employee_id"),
						rs.getString("first_name"), rs.getString("last_name")), "Josh")
				.forEach(employee-> log.info(employee.toString()));
		log.info("setting up tables...");

		jdbcTemplate.execute("DROP TABLE IF EXISTS company");
		jdbcTemplate.execute("CREATE TABLE company(" +
				"company_id SERIAL PRIMARY KEY NOT NULL, company_name VARCHAR(255), headquarters_id INTEGER)");
		// insert example companies into table: UKG, Santa's Workshop, Kittens Inc.
		//  using batch update with a list of objects
		Company ukg = new Company(1, "UKG", 12);
		Company santasWorkshop = new Company(2, "Santa's Workshop", 13);
		Company kittensInc = new Company(3, "Kittens Inc.", 14);
		List<Company> companies = Arrays.asList(ukg, santasWorkshop, kittensInc);
		companies.forEach(company->
				log.info(String.format("Inserting company record for %s", company.getCompanyName())));

		// use jdbctemplate batchupdate to insert multiple
		// insert statements to insert multiple
		jdbcTemplate.update(
				"INSERT INTO company (company_id, company_name, headquarters_id) " +
						"VALUES (?, ?, ?), " +
						"(?, ?, ?), " +
						"(?, ?, ?)", ukg.getCompanyId(), ukg.getCompanyName(), ukg.getHeadquartersId(),
				santasWorkshop.getCompanyId(), santasWorkshop.getCompanyName(), santasWorkshop.getHeadquartersId(),
				kittensInc.getCompanyId(), kittensInc.getCompanyName(), kittensInc.getHeadquartersId());

		log.info("creating company_address table...");
		jdbcTemplate.execute("DROP TABLE IF EXISTS company_address");
		jdbcTemplate.execute("CREATE TABLE company_address (" +
				"company_id INTEGER, " +
				"company_office_id INTEGER, " +
				"street VARCHAR(255), " +
				"zip CHAR(5), " +
				"country CHAR(3))");
		// insert values into company address
		CompanyAddress ukgAddr = new CompanyAddress(1, 3, "2500 N Commerce Pkwy", "33326", "USA");
		CompanyAddress santasWorkshopAddr = new CompanyAddress(2, 4, "1 N 1st Lane", "12345", "USA");
		CompanyAddress kittensIncAddr = new CompanyAddress(3, 5, "123 Meow Blvd", "55555", "USA");

		jdbcTemplate.update("INSERT INTO company_address (company_id, company_office_id, street, zip, country)" +
				" VALUES (?, ?, ?, ?, ?), " +
				"(?, ?, ?, ?, ?), " +
				"(?, ?, ?, ?, ?)", ukgAddr.getCompanyId(), ukgAddr.getCompanyOfficeId(), ukgAddr.getStreet(), ukgAddr.getZip(), ukgAddr.getCountry(),
				santasWorkshopAddr.getCompanyId(), santasWorkshopAddr.getCompanyOfficeId(), santasWorkshopAddr.getStreet(), santasWorkshopAddr.getZip(),
					santasWorkshopAddr.getCountry(), kittensIncAddr.getCompanyId(), kittensIncAddr.getCompanyOfficeId(), kittensIncAddr.getStreet(),
				kittensIncAddr.getZip(), kittensIncAddr.getCountry()
				);


	}

}
