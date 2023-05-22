package com.example.demoJPA.Repositories;

import com.example.demoJPA.Models.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class PublisherRepository implements CrudRepository<Publisher, UUID> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public <S extends Publisher> S save(S entity) {
        var sql =  """
                INSERT INTO public.publisher(
                	name)
                	VALUES (?);
                """;
        jdbcTemplate.update(sql,"Penguin");
        return entity;
    }

    @Override
    public <S extends Publisher> Iterable<S> saveAll(Iterable<S> entities) {
        var sql =  """
                INSERT INTO public.publisher(
                	name)
                	VALUES (?);
                """;
        jdbcTemplate.batchUpdate(sql,
                (ArrayList<Publisher>)entities,
                100,
                (PreparedStatement ps, Publisher p) -> {
                    ps.setString(1, p.getName());
                });

        return entities;
    }

    @Override
    public Optional<Publisher> findById(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    @Override
    public Iterable<Publisher> findAll() {
        return null;
    }

    @Override
    public Iterable<Publisher> findAllById(Iterable<UUID> uuids) {
        return null;
    }

    @Override
    public long count() {
        return this.jdbcTemplate.queryForObject("select count(*) from public.Publisher", Long.class);
    }

    @Override
    public void deleteById(UUID uuid) {

    }

    @Override
    public void delete(Publisher entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {

    }

    @Override
    public void deleteAll(Iterable<? extends Publisher> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
