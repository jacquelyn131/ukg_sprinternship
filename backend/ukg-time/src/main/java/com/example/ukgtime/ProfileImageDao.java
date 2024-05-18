package com.example.ukgtime;

import com.example.ukgtime.Company.CompanyAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProfileImageDao implements CorporateEventDao<ProfileImage>{
    private static Logger logger = LoggerFactory.getLogger(CompanyAddressDao.class);
    private JdbcTemplate jdbcTemplate;
    private RowMapper<ProfileImage> rowMapper = (rs, rowNum) -> {
        ProfileImage profileImage = new ProfileImage();
        profileImage.seteId(rs.getLong("eId"));
        Blob blob = rs.getBlob("profile_image");
        profileImage.setProfileImage(handleBlob(blob));
        return profileImage;
    };

    @Autowired
    public ProfileImageDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean add(ProfileImage profileImage) { // TODO: use the actual way to insert a blob
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
        String sql = "SELECT e_id, profile_image FROM profile_image";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<ProfileImage> get(long id) {
        String sql = "SELECT e_id, profile_image FROM profile_image WHERE e_id = ?";
        ProfileImage profileImage = null;
        try {
            profileImage = jdbcTemplate.queryForObject(sql, new Object[] {id}, rowMapper);
        } catch(DataAccessException e) {
            logger.info("ProfileImage not found" + id);
        }
        return Optional.empty();
    }

    @Override
    public void update(ProfileImage profileImage, long id) { // TODO: use the actual way to get a blob
        String sql = "UPDATE profile_image SET e_id = ?, profile_image = ? WHERE e_id = ?";
        int update = jdbcTemplate.update(sql, profileImage.geteId(),
                profileImage.getProfileImage(), id);
        if (update == 1) {
            logger.info("ProfileImage updated: " + id);
        }
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM profile_image WHERE e_id = " + id;
        jdbcTemplate.update(sql);
    }
    public byte[] handleBlob(Blob blob) throws SQLException {
        if (blob == null) {
            return null;
        }
        InputStream is = null;
        try {
            is = blob.getBinaryStream();
            if (is == null) {
                return null;
            }
            byte[] data = new byte[(int)blob.length()];
            if (data.length == 0) {
                return null;
            }
            is.read(data);
            return data;
        } catch(IOException e) {
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {throw new RuntimeException(e);}
            }
        }
        return null;
    }
}
