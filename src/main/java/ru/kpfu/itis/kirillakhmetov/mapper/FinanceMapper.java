package ru.kpfu.itis.kirillakhmetov.mapper;

import ru.kpfu.itis.kirillakhmetov.entity.Finance;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FinanceMapper implements RowMapper<Finance> {
    @Override
    public Finance mapRow(ResultSet resultSet) throws SQLException {
        return Finance.builder()
                .id(resultSet.getLong("finance_id"))
                .owner_id(resultSet.getLong("owner_id"))
                .type(resultSet.getString("type"))
                .amount(resultSet.getDouble("amount"))
                .category(resultSet.getString("category"))
                .date(resultSet.getDate("date").toLocalDate())
                .build();
    }
}
