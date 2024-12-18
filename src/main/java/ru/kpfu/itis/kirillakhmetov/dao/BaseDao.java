package ru.kpfu.itis.kirillakhmetov.dao;

import ru.kpfu.itis.kirillakhmetov.mapper.RowMapper;

import java.util.List;
import java.util.Optional;

public abstract class BaseDao<T> {
    RowMapper<T> mapper;

    /**
     * @throws UnsupportedOperationException if method not implemented
     */
    public List<T> findAll() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    /**
     * @throws UnsupportedOperationException if method not implemented
     */
    public Optional<T> findById(Long id) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    /**
     * @throws UnsupportedOperationException if method not implemented
     */
    public void save(T t) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    /**
     * @throws UnsupportedOperationException if method not implemented
     */
    public boolean deleteById(Long id) {
        throw new UnsupportedOperationException("Method not implemented");
    }
}
