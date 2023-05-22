package com.example.demoJPA.Repositories;

import com.example.demoJPA.Models.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Component
public class PublisherRepository implements CrudRepository<Publisher, Long> {

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
        return null;
    }

    @Override
    public Optional<Publisher> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Publisher> findAll() {
        return null;
    }

    @Override
    public Iterable<Publisher> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Publisher entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Publisher> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
