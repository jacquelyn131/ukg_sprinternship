package com.example.ukgtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

public class ZipDao implements CorporateEventDao<Zip>{
    private static Logger logger = LoggerFactory.getLogger(CompanyAddressDao.class);
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Zip> rowMapper = (rs, rowNum) -> {
        Zip zip = new Zip();
        zip.setZip(rs.getString("zip"));
        zip.setCity(rs.getString("city"));
        zip.setState(rs.getString("state"));
        return zip;
    };

    @Autowired
    public ZipDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean add(Zip zip) {
        String sql = "INSERT INTO zip (city, state, zip) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, zip.getCity(), zip.getState(), zip.getZip());
        return true;
    }

    @Override
    public boolean find(long id) {
        String sql = "SELECT COUNT(*) FROM zip WHERE zip = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return (count > 0);
    }

    @Override
    public List<Zip> list() {
        String sql = "SELECT zip, city, state FROM zip";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<Zip> get(long id) {
        String sql = "SELECT zip, city, state FROM zip WHERE zip = ?";
        Zip zip = null;
        try {
            zip = jdbcTemplate.queryForObject(sql, new Object[] {id}, rowMapper);
        } catch (DataAccessException e) {
            logger.info("Zip not found: " + id);
        }
        return Optional.ofNullable(zip);
    }

    @Override
    public void update(Zip zip, long id) {
        String sql = "UPDATE zip SET zip = ?, city = ?, state = ? WHERE zip = ?";
        int update = jdbcTemplate.update(sql, zip.getZip(), zip.getCity(), zip.getState(), id);
        if (update == 1) {
            logger.info("Zip updated: " + id);
        }
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM zip WHERE zip = " + id;
        jdbcTemplate.execute(sql);
        return false;
    }
}
