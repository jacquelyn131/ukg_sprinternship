package com.example.ukgtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class ClockPunchDao implements CorporateEventDao<ClockPunch>{
    private static Logger logger = LoggerFactory.getLogger(ClockPunchDao.class);
    private static SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
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
    // insert a 'IN' punch for the given employee
    public boolean employeeClockIn(long id) {
        // check if a clock in is allowed
        // can only insert clock in when most recent is clock out
        if (true) {
            return false;
        }
        return true;
    }
    // return recent punch type given employee id
    public Optional getRecentPunchType(long id) {
        String punchType = null;
        ClockPunch result = null;
        String sql = "SELECT type FROM clock_punch " +
                    "WHERE employee_id = ? " +
                    "ORDER BY date_time DESC " +
                    "LIMIT 1";
        try {
            punchType = jdbcTemplate.queryForObject(sql, String.class, id);

        } catch (DataAccessException e) {
            logger.info("No recent punches for employee: " + id);
        }
        return Optional.ofNullable(punchType);
    }
    // return recent punch time given employee id
    public Optional getRecentPunchTime(long id) {
        String dateTime = null;
        ClockPunch result = null;
        String sql = "SELECT date_time FROM clock_punch " +
                "WHERE employee_id = ? " +
                "ORDER BY date_time DESC " +
                "LIMIT 1";
        try {
            dateTime = jdbcTemplate.queryForObject(sql, String.class, id);

        } catch (DataAccessException e) {
            logger.info("No recent punches for employee: " + id);
        }
        return Optional.ofNullable(dateTime);
    }

    // return recent punch time given employee id and punch type
    public Optional getRecentPunchTime(long id, String punchType) {
        String dateTime = null;
        ClockPunch result = null;
        String sql = "SELECT date_time FROM clock_punch " +
                "WHERE employee_id = ? AND type = ?" +
                "ORDER BY date_time DESC " +
                "LIMIT 1";
        try {
            dateTime = jdbcTemplate.queryForObject(sql, String.class, id, punchType);

        } catch (DataAccessException e) {
            logger.info("No recent punches for employee: " + id);
        }
        return Optional.ofNullable(dateTime);
    }
    // return shift length given two punch id's
    public float getShiftLength(long startId, long endId) throws ParseException {
        float shiftLength = (float)0.0;
        String sql = "SELECT date_time FROM clock_punch WHERE punch_id = ?";
        String d1 = null;
        String d2 = null;
        try {
            d1 = jdbcTemplate.queryForObject(sql, String.class, startId);
            d2 = jdbcTemplate.queryForObject(sql, String.class, endId);
        } catch (DataAccessException e) {
            logger.info("no punch for given id: " + startId + " " + endId);
        }
        Date date1 = sdf.parse(d1);
        Date date2 = sdf.parse(d2);
        shiftLength = date2.getTime() - date1.getTime();
        return shiftLength;
    }
    // return break length given two punch id's
    public float getBreakLength(long startId, long endId) {
        return (float) 0.0;
    }

}
