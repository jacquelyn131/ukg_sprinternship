package com.example.ukgtime;

import com.example.ukgtime.Company.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CompanyDao implements CorporateEventDao<Company> {
    private static Logger logger = LoggerFactory.getLogger(CompanyDao.class);
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Company> rowMapper = (rs, rowNum) -> {
        Company company = new Company();
        company.setCompanyId(rs.getLong("company_id"));
        company.setCompanyName(rs.getString("company_name"));
        company.setHeadquartersId(rs.getLong("headquarters_id"));
        return company;

    };

    @Autowired
    public CompanyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean add(Company company) {
        String sql = "INSERT INTO company (company_id, company_name, headquarters_id) " +
                "VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, company.getCompanyId(), company.getCompanyName(),
                company.getHeadquartersId());
        logger.info("inserted a company successfully.");
        return true;
    }

    @Override
    public boolean find(long id) {
        String sql = "SELECT COUNT(*) FROM company WHERE company_id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        if (count >0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Company> list() {
        String sql = "SELECT company_id, company_name, headquarters_id FROM company";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional get(long id) {
        String sql = "SELECT company_id, company_name, headquarters_id FROM company " +
                "WHERE company_id = ?";
        Company company = null;
        try {
            company = jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);

        } catch (DataAccessException e) {
            logger.info("Company not found.");
        }
        return Optional.ofNullable(company);
    }



    public void update(Company company, long id) {
        String sql = "UPDATE company SET company_id = ?, company_name = ?, headquarters_id = ? " +
                "WHERE company_id = ?";
        int update = jdbcTemplate.update(sql, company.getCompanyId(), company.getCompanyName(),
                company.getHeadquartersId(), id);
        if (update == 1) {
            logger.info("Company information updated");
        }

    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM company WHERE company_id = " + id;
        jdbcTemplate.execute(sql);

        return false;
    }
}
