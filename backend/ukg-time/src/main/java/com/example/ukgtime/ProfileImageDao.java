package com.example.ukgtime;

import com.example.ukgtime.Company.CompanyAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Blob;
import java.util.List;
import java.util.Optional;

public class ProfileImageDao implements CorporateEventDao<ProfileImage>{
    private static Logger logger = LoggerFactory.getLogger(CompanyAddressDao.class);
    private JdbcTemplate jdbcTemplate;
    private RowMapper<ProfileImage> rowMapper = (rs, rowNum) -> {
        ProfileImage profileImage = new ProfileImage();
        profileImage.seteId(rs.getLong("eId"));
        Blob blob = rs.getBlob("profile_image");
        handleBlob(blob)
        profileImage.setProfileImage(handleBlob(blob));
        return profileImage;
    };

    @Autowired
    public ProfileImageDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean add(ProfileImage profileImage) {
        String sql = "INSERT INTO profile_image (e_id, profile_image) VALUES (?, ?)";
        jdbcTemplate.update(sql, profileImage.geteId(), profileImage.getProfileImage());
        return false;
    }

    @Override
    public boolean find(long id) {
        String sql = "SELECT COUNT(*) FROM profile_image WHERE e_id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return (count > 0);
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
    public byte[] handleBlob(Blob blob) {

    }
}
