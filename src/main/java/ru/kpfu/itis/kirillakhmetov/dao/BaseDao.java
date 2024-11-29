package ru.kpfu.itis.kirillakhmetov.dao;

import ru.kpfu.itis.kirillakhmetov.mapper.RowMapper;

import java.util.List;
import java.util.Optional;

public abstract class BaseDao<T> {
    RowMapper<T> mapper;

    public abstract List<T> findAll();

    public abstract Optional<T> findById(Long id);

    public abstract void save(T t);

    public abstract boolean deleteById(Long id);
}
