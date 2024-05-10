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
    public void setUpTables() {
        log.info("setting up tables...");
    }
}
