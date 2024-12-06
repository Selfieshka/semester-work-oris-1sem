package ru.kpfu.itis.kirillakhmetov.dao;

import ru.kpfu.itis.kirillakhmetov.entity.Product;

import java.util.List;
import java.util.Optional;

public class InvoiceDao extends BaseDao<Product> {

    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
