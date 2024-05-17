package com.example.ukgtime;

import com.example.ukgtime.Company.CompanyAddress;
import com.example.ukgtime.Employee.EmployeeCompany;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

public class EmployeeCompanyDao implements CorporateEventDao<EmployeeCompany>{
    private static Logger logger = LoggerFactory.getLogger(CompanyAddressDao.class);
    private JdbcTemplate jdbcTemplate;
    private RowMapper<EmployeeCompany> rowMapper = (rs, rowNum) -> {
        EmployeeCompany employeeCompany = new EmployeeCompany();
        return employeeCompany;
    };

    @Autowired
    public EmployeeCompanyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean add(EmployeeCompany employeeCompany) {
        return false;
    }

    @Override
    public boolean find(long id) {
        return false;
    }

    @Override
    public List<EmployeeCompany> list() {
        return List.of();
    }

    @Override
    public Optional<EmployeeCompany> get(long id) {
        return Optional.empty();
    }

    @Override
    public void update(EmployeeCompany employeeCompany, long id) {

    }

    @Override
    public void delete(long id) {

    }
}
