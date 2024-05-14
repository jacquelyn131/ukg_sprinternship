package com.example.ukgtime;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.ukgtime.Employee.Employee;

@Component
public class JdbcCorporateEventDao implements CorporateEventDao<Employee>{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcCorporateEventDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public boolean add(Employee employee) {
        // TODO: finish
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        jdbcTemplate.update("INSERT INTO employees(first_name, last_name) " +
                "VALUES (?,?)", employee.getFirstName(), employee.getLastName());
        System.out.println("inserted an employee successfully");
        return true;
    }
    public boolean find(long eId) {
        int count = this.jdbcTemplate.queryForObject("SELECT count(*) FROM employees WHERE employee_id = " + eId, Integer.class);
        return count>0;
    }
}
