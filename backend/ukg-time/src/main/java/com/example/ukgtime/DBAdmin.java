package com.example.ukgtime;

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

public class DBAdmin {
    public static final Logger log = LoggerFactory.getLogger(UkgTimeApplication.class);

    @Autowired
    JdbcTemplate jdbcTemplate;
    public void setUpTables() throws Exception {

        log.info("setting up tables...");

        jdbcTemplate.execute("DROP TABLE IF EXISTS company");
        jdbcTemplate.execute("CREATE TABLE company(" +
                "company_id SERIAL PRIMARY KEY NOT NULL, company_name String, headquarters_id INTEGER)");
        // insert example companies into table: UKG, Santa's Workshop, Kittens Inc.
        List<Object[]> companies = Arrays.asList("UKG", 1)
    }
}
