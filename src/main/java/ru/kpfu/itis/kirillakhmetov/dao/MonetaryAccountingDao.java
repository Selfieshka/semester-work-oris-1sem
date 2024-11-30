package ru.kpfu.itis.kirillakhmetov.dao;

import ru.kpfu.itis.kirillakhmetov.entity.MonetaryAccounting;
import ru.kpfu.itis.kirillakhmetov.mapper.MonetaryAccountingMapper;
import ru.kpfu.itis.kirillakhmetov.util.ConnectionProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MonetaryAccountingDao extends BaseDao<MonetaryAccounting> {
    //language=sql
    private static final String SQL_GET_ALL = "SELECT * FROM monetary_accouting";

    public MonetaryAccountingDao() {
        this.mapper = new MonetaryAccountingMapper();
    }

    @Override
    public List<MonetaryAccounting> findAll() {
        try (Connection connection = ConnectionProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_GET_ALL)) {

            List<MonetaryAccounting> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(mapper.mapRow(resultSet));
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Can't get all monetary accounting");
        }
    }

    @Override
    public Optional<MonetaryAccounting> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(MonetaryAccounting monetaryAccounting) {

    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
