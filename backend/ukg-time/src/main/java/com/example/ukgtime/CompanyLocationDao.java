package com.example.ukgtime;

import com.example.ukgtime.Company.CompanyLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;
import java.util.Optional;
@Component
public class CompanyLocationDao implements CorporateEventDao<CompanyLocation> {

    private static Logger logger = LoggerFactory.getLogger(CompanyLocationDao.class);
    private JdbcTemplate jdbcTemplate;
    private RowMapper<CompanyLocation> rowMapper = (rs, rowNum) -> {
        CompanyLocation companyLocation = new CompanyLocation();
        companyLocation.setCompanyOfficeId(rs.getLong("company_office_id"));
        //Point point = rs.getObject("location", Point.class);
        String wktString = rs.getString("location");
        logger.info("wktstring: " + wktString);
        double[] location;
        location = convertWKTStringToDouble(wktString);
        logger.info("location array: " + location.toString());
        logger.info("location array: " + location[0] +", " +  location[1]);
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
        String sql = "SELECT company_office_id, ST_ASTEXT(location) AS location, radius FROM company_location";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<CompanyLocation> get(long id) {
        String sql = "SELECT company_office_id, ST_ASTEXT(location) AS location, radius FROM company_location " +
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
    // helper method to convert wkt format string to double array
    public double[] convertWKTStringToDouble(String wktString) {
        //System.out.println("inside convert");
        char ch;
        int index = 0;
        int count = 0;
        double[] arr = new double[2];
        String num = ""; // track the current number
        while (index <= wktString.length()) {
            // check if space or comma => push the number onto the array, and reset num

            ch = wktString.charAt(index);
            if (ch == ')') {
                if (num != "") {
                    arr[count] = Double.parseDouble(num);
                    count++;
                    num = "";
                }
                break;
            }
            if (ch == ' ' || ch == ',') {
                if (count == 0 && num != "") {
                    arr[count] = Double.parseDouble(num);
                    count++;
                    num = "";
                } else if (arr[0] != 0 && num != "") {
                    arr[count] = Double.parseDouble(num);
                    count++;
                    num = "";
                }
            }
            // check if current character is digit or minus sign or decimal
            if ( (ch >=48 && ch <= 57) || ch == '-' || ch == '.' ) {
                num = num + ch;
            }
            index++;
//            System.out.println("index " + index);
//            System.out.println("count " + count);
//            System.out.println("num " + num);
            // otherwise continue to next character
        }
        // logger.info("arr: " + arr[0] + " " + arr[1]);
        //System.out.println("arr" + arr.toString());
        return arr;
    }
}
