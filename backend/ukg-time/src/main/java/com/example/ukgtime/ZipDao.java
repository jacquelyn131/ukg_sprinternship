package com.example.ukgtime;

import com.example.ukgtime.Company.CompanyAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

public class ZipDao implements CorporateEventDao<Zip>{
    private static Logger logger = LoggerFactory.getLogger(CompanyAddressDao.class);
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Zip> rowMapper = (rs, rowNum) -> {
        Zip zip = new Zip();
        return zip;
    };

    @Autowired
    public ZipDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean add(Zip zip) {
        return false;
    }

    @Override
    public boolean find(long id) {
        return false;
    }

    @Override
    public List<Zip> list() {
        return List.of();
    }

    @Override
    public Optional<Zip> get(long id) {
        return Optional.empty();
    }

    @Override
    public void update(Zip zip, long id) {

    }

    @Override
    public void delete(long id) {

    }
}
