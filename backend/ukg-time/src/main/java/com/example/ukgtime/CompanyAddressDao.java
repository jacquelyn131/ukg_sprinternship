package com.example.ukgtime;

import com.example.ukgtime.Company.CompanyAddress;
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
public class CompanyAddressDao implements CorporateEventDao<CompanyAddress>{
    private static Logger logger = LoggerFactory.getLogger(CompanyAddressDao.class);
    private JdbcTemplate jdbcTemplate;
    private RowMapper<CompanyAddress> rowMapper = (rs, rowNum) -> {
        CompanyAddress companyAddress = new CompanyAddress();
        companyAddress.setCompanyId(rs.getLong("company_id"));
        companyAddress.setCompanyOfficeId(rs.getLong("company_office_id"));
        companyAddress.setStreet(rs.getString("street"));
        companyAddress.setZip(rs.getString("zip"));
        companyAddress.setCountry(rs.getString("country"));

        return companyAddress;
    };

    @Autowired
    public CompanyAddressDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean add(CompanyAddress companyAddress) {
        String sql = "INSERT INTO company_address (company_id, company_office_id, street, zip, " +
                "country) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, companyAddress.getCompanyId(), companyAddress.getCompanyOfficeId(),
                companyAddress.getStreet(), companyAddress.getZip(), companyAddress.getCountry());
        return true;
    }

    @Override
    public boolean find(long id) {
        String sql = "SELECT COUNT(*) FROM company_address WHERE company_office_id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return (count > 0);
    }

    @Override
    public List<CompanyAddress> list() {
        String sql = "SELECT company_id, company_office_id, street, zip, country FROM company_address";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<CompanyAddress> get(long id) {
        String sql = "SELECT company_id, company_office_id, street, zip, country FROM company_address " +
                "WHERE company_office_id = ?";
        CompanyAddress companyAddress = null;
        try {
            companyAddress = jdbcTemplate.queryForObject(sql, new Object[] {id}, rowMapper);
        } catch (DataAccessException e) {
            logger.info("CompanyAddress not found" + id);
        }
        return Optional.ofNullable(companyAddress);
    }

    @Override
    public void update(CompanyAddress companyAddress, long id) {
        String sql = "UPDATE company_address SET company_id = ?, company_office_id = ?, street = ?, " +
                "zip = ?, country = ? WHERE company_office_id = ?";
        int update = jdbcTemplate.update(sql, companyAddress.getCompanyId(),
                companyAddress.getCompanyOfficeId(), companyAddress.getStreet(), companyAddress.getZip(),
                companyAddress.getCountry(), id);
        if (update == 1) {
            logger.info("CompanyAddress updated: " + id);
        }
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM company_address WHERE company_office_id = " + id;
        jdbcTemplate.execute(sql);
        return false;
    }
}
