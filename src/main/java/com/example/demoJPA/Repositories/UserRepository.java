package com.example.demoJPA.Repositories;

import com.example.demoJPA.Models.User;
import com.example.demoJPA.RowMappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserRepository implements CrudRepository<User, UUID> {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public <S extends User> S save(S entity) {
        return null;
    }

    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override//https://sec.okta.com/articles/2020/12/sql-injection-java-practices-avoid
    public Optional<User> findById(UUID uuid) {

        return Optional.empty();
    }

    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    @Override
    public Iterable<User> findAll() {
        return null;
    }

    @Override
    public Iterable<User> findAllById(Iterable<UUID> uuids) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(UUID uuid) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {

    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {

    }

    @Override
    public void deleteAll() {

    }

    public boolean Login(String email,String hash){

        var sql = "select * from public.AppUser where email='"+email+"' and hash='"+hash+"'";
        //var stmt = jdbcTemplate.
        return (long) jdbcTemplate.query(sql, new UserRowMapper()).size() > 0;
    }
}
