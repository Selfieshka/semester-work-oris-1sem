package ru.kpfu.itis.kirillakhmetov.mapper;

import ru.kpfu.itis.kirillakhmetov.entity.MonetaryAccounting;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MonetaryAccountingMapper implements RowMapper<MonetaryAccounting> {
    @Override
    public MonetaryAccounting mapRow(ResultSet resultSet) throws SQLException {
        return new MonetaryAccounting(
        );
    }
}
