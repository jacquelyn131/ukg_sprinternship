package com.example.ukgtime;

import com.example.ukgtime.Employee.EmployeeCompany;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

public class EmployeeCompanyDao implements CorporateEventDao<EmployeeCompany>{
    private static Logger logger = LoggerFactory.getLogger(CompanyAddressDao.class);
    private JdbcTemplate jdbcTemplate;
    private RowMapper<EmployeeCompany> rowMapper = (rs, rowNum) -> {
        EmployeeCompany employeeCompany = new EmployeeCompany();
        employeeCompany.seteId(rs.getLong("e_id"));
        employeeCompany.setCompanyId(rs.getLong("company_id"));
        return employeeCompany;
    };

    @Autowired
    public EmployeeCompanyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean add(EmployeeCompany employeeCompany) {
        String sql = "INSERT INTO employee_company (e_id, company_id ) VALUES (?, ?)";
        jdbcTemplate.update(sql, employeeCompany.geteId(), employeeCompany.getCompanyId());
        return true;
    }

    @Override
    public boolean find(long id) {
        String sql = "SELECT COUNT(*) FROM employee_company WHERE e_id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return (count >0);
    }

    @Override
    public List<EmployeeCompany> list() {
        String sql = "SELECT e_id, company_id FROM employee_company";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<EmployeeCompany> get(long id) {
        String sql = "SELECT e_id, company_id FROM employee_company WHERE e_id = ?";
        EmployeeCompany employeeCompany = null;
        try {
            employeeCompany = jdbcTemplate.queryForObject(sql, new Object[] {id}, rowMapper);
        } catch (DataAccessException e) {
            logger.info("EmployeeCompany not found: " + id);
        }
        return Optional.ofNullable(employeeCompany);
    }

    @Override
    public void update(EmployeeCompany employeeCompany, long id) {
        String sql = "UPDATE employee_company SET e_id = ?, company_id = ? WHERE e_id = ?";
        int update = jdbcTemplate.update(sql, employeeCompany.geteId(), employeeCompany.getCompanyId(), id);
        if (update == 1) {
            logger.info("EmployeeCompany updated: " + id);
        }
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM employee_company WHERE e_id = " + id;
        jdbcTemplate.execute(sql);
        return false;
    }
}
