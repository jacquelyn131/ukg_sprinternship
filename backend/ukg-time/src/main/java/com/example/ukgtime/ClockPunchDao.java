package com.example.ukgtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.util.List;
import java.util.Optional;

@Component
public class ClockPunchDao implements CorporateEventDao<ClockPunch>{
    private static Logger logger = LoggerFactory.getLogger(ClockPunchDao.class);
    private JdbcTemplate jdbcTemplate;
    private RowMapper<ClockPunch> rowMapper = (rs, rowNum) -> {
        ClockPunch clockPunch = new ClockPunch();
        clockPunch.setPunchId(rs.getLong("punch_id"));
        clockPunch.setDateTime("" + rs.getDate("date_time"));
        clockPunch.setEmployeeId(rs.getLong("employee_id"));
        clockPunch.setOfficeId(rs.getLong("office_id"));
        clockPunch.setType(rs.getString("type"));
        clockPunch.setValid(rs.getBoolean("valid"));
        clockPunch.setComments(rs.getString("comments"));
        return clockPunch;
    };

    @Autowired
    public ClockPunchDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean add(ClockPunch clockPunch) {
        String sql = "INSERT INTO clock_punch (date_time, punch_id, employee_id, office_id, " +
                "type, valid, comments) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, clockPunch.getDateTime(), clockPunch.getPunchId(),
                clockPunch.getEmployeeId(), clockPunch.getOfficeId(), clockPunch.getType(),
                clockPunch.getValid(), clockPunch.getComments());
        return true;
    }

    @Override
    public boolean find(long id) {
        String sql = "SELECT COUNT(*) FROM clock_punch WHERE punch_id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count > 0;
    }

    @Override
    public List<ClockPunch> list() {
        String sql = "SELECT date_time, punch_id, employee_id, office_id, type, valid, comments " +
                "FROM clock_punch";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional get(long id) {
        String sql = "SELECT date_time, punch_id, employee_id, office_id, type, valid, comments " +
                "FROM clock_punch WHERE punch_id = ?";
        ClockPunch clockPunch = null;
        try {
            clockPunch = jdbcTemplate.queryForObject(sql, new Object[] {id}, rowMapper);
        } catch (DataAccessException e) {
            logger.info("ClockPunch not found: " + id);
        }
        return Optional.ofNullable(clockPunch);
    }

    public void update(ClockPunch clockPunch, long id) {
        String sql = "UPDATE clock_punch SET date_time = ?, punch_id = ?, employee_id = ?, " +
                "office_id = ?, type = ?, valid = ?, comments = ? WHERE punch_id = ?";
        int update = jdbcTemplate.update(sql, clockPunch.getDateTime(), clockPunch.getPunchId(),
                clockPunch.getEmployeeId(), clockPunch.getOfficeId(), clockPunch.getType(),
                clockPunch.getValid(), clockPunch.getComments());
        if (update == 1) {
            logger.info("ClockPunch updated: " + id);
        }
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM clock_punch WHERE punch_id = " + id;
        jdbcTemplate.execute(sql);
    }
}
