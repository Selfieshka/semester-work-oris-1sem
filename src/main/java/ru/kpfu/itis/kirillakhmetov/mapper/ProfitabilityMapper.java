package ru.kpfu.itis.kirillakhmetov.mapper;

import ru.kpfu.itis.kirillakhmetov.entity.Profitability;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfitabilityMapper implements RowMapper<Profitability> {
    @Override
    public Profitability mapRow(ResultSet resultSet) throws SQLException {
        return new Profitability(
                resultSet.getLong("profitability_id"),
                resultSet.getDouble("profitability_value"),
                resultSet.getDate("profitability_date").toLocalDate()
        );
    }
}
