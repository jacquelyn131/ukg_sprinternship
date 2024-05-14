package com.example.ukgtime;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.ukgtime.Employee.Employee;

@Component
public class JdbcCorporateEventDao implements CorporateEventDao<Employee>{
    private static Logger logger = LoggerFactory.getLogger(JdbcCorporateEventDao.class);
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Employee> rowMapper = (rs, rowNum) -> {
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getLong("employee_id"));
        employee.setSsn(rs.getString("ssn"));
        employee.setEmail(rs.getString("email"));
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setDob(rs.getString("dob"));
        employee.setManagerId(rs.getLong("manager_id"));
        return employee;
    };

    @Autowired
    public JdbcCorporateEventDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public boolean add(Employee employee) {
        String sql = "INSERT INTO employees(first_name, last_name) VALUES (?,?)";
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        jdbcTemplate.update(sql, employee.getFirstName(), employee.getLastName());
        System.out.println("inserted an employee successfully");
        return true;
    }
    public boolean find(long eId) {
        String sql = "SELECT count(*) FROM employees WHERE employee_id = ?";
        int count = this.jdbcTemplate.queryForObject(sql, Integer.class, eId);
        return count>0;
    }

    @Override
    public List<Employee> list() {
        String sql = "SELECT employee_id, first_name, last_name, ssn, dob, manager_id, email " +
                "FROM employees " +
                "";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<Employee> get(long id) {
        return Optional.empty();
    }

    @Override
    public void update(Employee employee, long id) {

    }

    @Override
    public void delete(long id) {

    }
}
