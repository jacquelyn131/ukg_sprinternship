package com.example.ukgtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.ukgtime.Company.*;
import com.example.ukgtime.Employee.*;


import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Arrays;

public class DBAdmin implements CommandLineRunner{
    public static final Logger log = LoggerFactory.getLogger(UkgTimeApplication.class);

    @Autowired
    JdbcTemplate jdbcTemplate;
    public void setUpTables() throws Exception {

        log.info("setting up tables...");

        jdbcTemplate.execute("DROP TABLE IF EXISTS company");
        jdbcTemplate.execute("CREATE TABLE company(" +
                "company_id SERIAL PRIMARY KEY NOT NULL, company_name String, headquarters_id INTEGER)");
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


    }
    @Override
    public void run(String... strings) throws Exception {
        log.info("setting up tables...");

        jdbcTemplate.execute("DROP TABLE IF EXISTS company");
        jdbcTemplate.execute("CREATE TABLE company(" +
                "company_id SERIAL PRIMARY KEY NOT NULL, company_name String, headquarters_id INTEGER)");
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
    }
}
