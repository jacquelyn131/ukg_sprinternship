package com.example.ukgtime;

import com.example.ukgtime.Company.CompanyLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public class CompanyLocationDao implements CorporateEventDao<CompanyLocation> {

    private static Logger logger = LoggerFactory.getLogger(CompanyLocationDao.class);
    private JdbcTemplate jdbcTemplate;
    private RowMapper<CompanyLocation> rowMapper = (rs, rowNum) -> {
        CompanyLocation companyLocation = new CompanyLocation();
        companyLocation.setCompanyOfficeId(rs.getLong("company_office_id"));
        Point point = rs.getObject("location", Point.class);
        double[] location = new double[2];
        location[0] = point.getX();
        location[1] = point.getY();
        companyLocation.setLocation(location);
        companyLocation.setRadius(rs.getDouble("radius"));
        return companyLocation;
    };

    @Autowired
    public CompanyLocationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean add(CompanyLocation companyLocation) {
        String sql = "INSERT INTO company_location (company_office_id, location, radius) " +
                "VALUES (?, POINT(?, ?),? )";
        jdbcTemplate.update(sql, companyLocation.getCompanyOfficeId(),
                companyLocation.getLocation()[0], companyLocation.getLocation()[1],
                companyLocation.getRadius());
        return true;
    }

    @Override
    public boolean find(long id) {
        String sql = "SELECT COUNT(*) FROM company_location WHERE company_office_id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return (count > 0);
    }

    @Override
    public List<CompanyLocation> list() {
        String sql = "SELECT company_office_id, location, radius FROM company_location";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<CompanyLocation> get(long id) {
        String sql = "SELECT company_office_id, location, radius FROM company_location " +
                "WHERE company_office_id = ?";
        CompanyLocation companyLocation = null;
        try {
            companyLocation = jdbcTemplate.queryForObject(sql, new Object[] {id}, rowMapper);
        } catch (DataAccessException e) {
            logger.info("CompanyLocation not found: " + id);
        }
        return Optional.ofNullable(companyLocation);
    }

    @Override
    public void update(CompanyLocation companyLocation, long id) {
        String sql = "UPDATE company_location SET company_office_id = ?, location = POINT(?, ?), " +
                "radius = ? WHERE company_office_id = ?";
        int update = jdbcTemplate.update(sql, companyLocation.getCompanyOfficeId(),
                companyLocation.getLocation()[0], companyLocation.getLocation()[1],
                companyLocation.getRadius(), id);
        if (update == 1) {
            logger.info("CompanyLocation updated successfully: " + id);
        }
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM company_location WHERE company_office_id = id";
        jdbcTemplate.execute(sql);
        return false;
    }
}
