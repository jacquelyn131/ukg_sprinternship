package com.example.ukgtime;

import java.io.FileInputStream;
import java.io.IOException;
import com.example.ukgtime.Employee.Employee;
import com.example.ukgtime.Company.Company;
import com.example.ukgtime.Employee.EmployeeCompany;
import com.example.ukgtime.Company.CompanyAddress;
import com.example.ukgtime.Employee.EmployeeController;
import com.example.ukgtime.Company.CompanyLocation;
import com.example.ukgtime.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Arrays;

@SpringBootApplication
public class UkgTimeApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(UkgTimeApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(UkgTimeApplication.class, args);
		JdbcCorporateEventDao jdbcCorporateEventDao = new JdbcCorporateEventDao();

		log.info("found employee: " + jdbcCorporateEventDao.find(1));

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

		log.info("creating company_location table...");
		jdbcTemplate.execute("DROP TABLE IF EXISTS company_location");
		jdbcTemplate.execute("CREATE TABLE company_location (" +
				"company_office_id INTEGER NOT NULL PRIMARY KEY, " +
				"location POINT, " +
				"radius FLOAT(3,3))");
		// insert values into company_location
		CompanyLocation ukgLoc = new CompanyLocation(3, new double[]{26.089835, -80.366919}, 0.005);
		CompanyLocation santasWorkshopLoc = new CompanyLocation(4, new double[]{84.999983, -135.000971}, 0.005);
		CompanyLocation kittenIncLoc = new CompanyLocation(5, new double[]{37.779585, -122.483034}, 0.005);
		log.info("" + kittenIncLoc.getLocation()[0]);
		jdbcTemplate.update("INSERT INTO company_location (company_office_id, location, radius) VALUES " +
				"(?, POINT(?, ?), ?), (?, POINT(?, ?), ?), (?, POINT(?, ?), ?)", ukgLoc.getCompanyOfficeId(), ukgLoc.getLocation()[0], ukgLoc.getLocation()[1],
				ukgLoc.getRadius(), santasWorkshopLoc.getCompanyOfficeId(), santasWorkshopLoc.getLocation()[0], santasWorkshopLoc.getLocation()[1],
				santasWorkshopLoc.getRadius(), kittenIncLoc.getCompanyOfficeId(), kittenIncLoc.getLocation()[0], kittenIncLoc.getLocation()[1],
				kittenIncLoc.getRadius());

		log.info("creating profile_image table");

		jdbcTemplate.execute("DROP TABLE IF EXISTS profile_image");
		jdbcTemplate.execute("CREATE TABLE profile_image (" +
				"e_id INTEGER NOT NULL," +
				"profile_image MEDIUMBLOB)");
		// insert rows into profile image
		ProfileImage johnProfImage = new ProfileImage(1, null);
		ProfileImage jeffProfImage = new ProfileImage(2, null);
		ProfileImage joshBProfImage = new ProfileImage(3, null);
		jdbcTemplate.update("INSERT INTO profile_image (e_id, profile_image) " +
				"VALUES (?,?), (?,?), (?,?)", johnProfImage.geteId(), johnProfImage.getProfileImage(),
				jeffProfImage.geteId(), jeffProfImage.getProfileImage(), joshBProfImage.geteId(),
				joshBProfImage.getProfileImage());

		// create employee_company
		log.info("creating employee_company table...");
		jdbcTemplate.execute("DROP TABLE IF EXISTS employee_company");
		jdbcTemplate.execute("CREATE TABLE employee_company (" +
				"e_id INTEGER NOT NULL, " +
				"company_id INTEGER NOT NULL)");
		EmployeeCompany johnCompany = new EmployeeCompany(1, 1);
		EmployeeCompany jeffCompany = new EmployeeCompany(2, 2);
		EmployeeCompany joshBCompany = new EmployeeCompany(3, 3);
		jdbcTemplate.update("INSERT INTO employee_company (e_id, company_id) " +
						"VALUES (?, ?), (?, ?), (?, ?)",
				johnCompany.geteId(), johnCompany.getCompanyId(), jeffCompany.geteId(),
				jeffCompany.getCompanyId(), joshBCompany.geteId(), joshBCompany.getCompanyId());

		log.info("creating zip table...");
		jdbcTemplate.execute("DROP TABLE IF EXISTS zip");
		jdbcTemplate.execute("CREATE TABLE zip (" +
				"city VARCHAR(255) NOT NULL, " +
				"state CHAR(2) NOT NULL, " +
				"zip CHAR(5) NOT NULL)");
		// insert rows into zip
		Zip ukgZip = new Zip("Weston", "FL", "33326");
		Zip santasWorkshopZip = new Zip("North Pole", "AK", "12345");
		Zip kittensZip = new Zip("San Francisco", "CA", "55555" );
		jdbcTemplate.update("INSERT INTO zip (city, state, zip) " +
				"VALUES (?, ?, ?), (?, ?, ?), (?, ?, ?)", ukgZip.getCity(), ukgZip.getState(), ukgZip.getZip(),
				santasWorkshopZip.getCity(), santasWorkshopZip.getState(), santasWorkshopZip.getZip(),
				kittensZip.getCity(), kittensZip.getState(), kittensZip.getZip());

		log.info("creating clock_punch table...");
		jdbcTemplate.execute("DROP TABLE IF EXISTS clock_punch");
		jdbcTemplate.execute("CREATE TABLE clock_punch (" +
				"date_time DATETIME NOT NULL, " +
				"punch_id SERIAL NOT NULL PRIMARY KEY, " +
				"employee_id INTEGER NOT NULL, " +
				"office_id INTEGER NOT NULL, " +
				"type VARCHAR(9) NOT NULL, " +
				"valid BOOLEAN, " +
				"comments TEXT(280), " +
				"CONSTRAINT CHK_type CHECK (type IN ('IN', 'OUT', 'BREAK-IN', 'BREAK-OUT')))");
		// insert rows into clock_punch
		ClockPunch johnPunch = new ClockPunch("2024-05-13 14:05:00", 1, 1, "IN", false, "nice weather today");
		ClockPunch jeffPunch = new ClockPunch("2024-05-13 14:06:30", 2, 2, "OUT", false, "what a beautiful day");
		ClockPunch joshBPunch = new ClockPunch("2024-05-13 14:06:38", 3, 3, "BREAK-IN", false, "clear blue skies");
		jdbcTemplate.update("INSERT INTO clock_punch (date_time, employee_id, office_id, type, valid, comments) " +
						"VALUES (?, ?, ?, ?, ?, ?), (?, ?, ?, ?, ?, ?), (?, ?, ?, ?, ?, ?)",
				johnPunch.getDateTime(), johnPunch.getEmployeeId(), johnPunch.getOfficeId(), johnPunch.getType(), johnPunch.getValid(),
				johnPunch.getComments(), jeffPunch.getDateTime(), jeffPunch.getEmployeeId(), jeffPunch.getOfficeId(), jeffPunch.getType(),
				jeffPunch.getValid(), jeffPunch.getComments(), joshBPunch.getDateTime(), joshBPunch.getEmployeeId(), joshBPunch.getOfficeId(),
				joshBPunch.getType(), joshBPunch.getValid(), joshBPunch.getComments());

	}


}
