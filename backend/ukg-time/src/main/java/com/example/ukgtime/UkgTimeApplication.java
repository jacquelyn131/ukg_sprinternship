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

@SpringBootApplication
public class UkgTimeApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(UkgTimeApplication.class);
	public static void main(String[] args) {
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
				"id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255), ssn CHAR(9), company_id INTEGER, " +
				"dob DATE, profile_image MEDIUMBLOB)");
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
				"SELECT id, first_name, last_name FROM employees WHERE first_name = ?",
				(rs, rowNum) -> new Employee(rs.getLong("id"),
						rs.getString("first_name"), rs.getString("last_name")), "Josh")
				.forEach(employee-> log.info(employee.toString()));

	}

}
