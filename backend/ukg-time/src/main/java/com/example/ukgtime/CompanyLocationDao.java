package com.example.ukgtime;

import com.example.ukgtime.Company.CompanyAddress;
import com.example.ukgtime.Company.CompanyLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

public class CompanyLocationDao implements CorporateEventDao<CompanyLocation> {

    private static Logger logger = LoggerFactory.getLogger(CompanyLocationDao.class);
    private JdbcTemplate jdbcTemplate;
    private RowMapper<CompanyLocation> rowMapper = (rs, rowNum) -> {
        CompanyLocation companyLocation = new CompanyLocation();
        return companyLocation;
    };

    @Autowired
    public CompanyLocationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean add(CompanyLocation companyLocation) {
        return false;
    }

    @Override
    public boolean find(long id) {
        return false;
    }

    @Override
    public List<CompanyLocation> list() {
        return List.of();
    }

    @Override
    public Optional<CompanyLocation> get(long id) {
        return Optional.empty();
    }

    @Override
    public void update(CompanyLocation companyLocation, long id) {

    }

    @Override
    public void delete(long id) {

    }
}
