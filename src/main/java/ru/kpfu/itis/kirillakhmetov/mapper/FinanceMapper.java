package ru.kpfu.itis.kirillakhmetov.mapper;

import ru.kpfu.itis.kirillakhmetov.entity.Finance;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FinanceMapper implements RowMapper<Finance> {
    @Override
    public Finance mapRow(ResultSet resultSet) throws SQLException {
        System.out.println(resultSet.getDouble("value"));
        return new Finance(
                resultSet.getLong("finance_id"),
                resultSet.getLong("finance_type_id"),
                resultSet.getDouble("value"),
                resultSet.getDate("date").toLocalDate()
        );
    }
}
