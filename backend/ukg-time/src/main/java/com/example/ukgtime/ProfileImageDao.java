package com.example.ukgtime;

import com.example.ukgtime.Company.CompanyAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

public class ProfileImageDao implements CorporateEventDao<ProfileImage>{
    private static Logger logger = LoggerFactory.getLogger(CompanyAddressDao.class);
    private JdbcTemplate jdbcTemplate;
    private RowMapper<ProfileImage> rowMapper = (rs, rowNum) -> {
        ProfileImage profileImage = new ProfileImage();
        return profileImage;
    };

    @Autowired
    public ProfileImageDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean add(ProfileImage profileImage) {
        return false;
    }

    @Override
    public boolean find(long id) {
        return false;
    }

    @Override
    public List<ProfileImage> list() {
        return List.of();
    }

    @Override
    public Optional<ProfileImage> get(long id) {
        return Optional.empty();
    }

    @Override
    public void update(ProfileImage profileImage, long id) {

    }

    @Override
    public void delete(long id) {

    }
}
