package com.example.demoJPA.RowMappers;

import com.example.demoJPA.Models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User User = new User();
        User.setId(UUID.fromString(rs.getString("id")));
        User.setName(rs.getString("name"));
        User.setEmail(rs.getString("email"));
        User.setPasswordHash(rs.getString("hash"));

        return User;
    }
}
