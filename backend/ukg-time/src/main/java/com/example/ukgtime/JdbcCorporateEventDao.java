package com.example.ukgtime;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcCorporateEventDao implements CorporateEventDao{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource datasource) {
        this.jdbcTemplate = new JdbcTemplate(datasource);
    }

    public boolean add(Employee employee) {
        // TODO: finish
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
