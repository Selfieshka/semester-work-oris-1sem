package ru.kpfu.itis.kirillakhmetov.dao;

import ru.kpfu.itis.kirillakhmetov.entity.Profitability;
import ru.kpfu.itis.kirillakhmetov.exception.CreateConnectionDBException;
import ru.kpfu.itis.kirillakhmetov.mapper.ProfitabilityMapper;
import ru.kpfu.itis.kirillakhmetov.mapper.RowMapper;
import ru.kpfu.itis.kirillakhmetov.util.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnalyticsDao {
    private final RowMapper<Profitability> mapper;
    //language=sql
    private static final String SQL_SAVE = """
            INSERT INTO profitability (profitability_value, profitability_date)
            VALUES (?, ?)
            """;
    //language=sql
    private static final String SQL_GET_ALL = "SELECT * FROM profitability";

    public AnalyticsDao() {
        this.mapper = new ProfitabilityMapper();
    }

    public void save(Profitability profitability) {
        try {
            PreparedStatement statement = ConnectionProvider.getConnection().prepareStatement(SQL_SAVE);
            statement.setDouble(1, profitability.getValue());
            statement.setDate(2, Date.valueOf(profitability.getDate()));
            statement.execute();
        } catch (SQLException | CreateConnectionDBException e) {
            throw new RuntimeException("Can't save profitability");
        }
    }

    public List<Profitability> getAll() {
        try {
            Statement statement = ConnectionProvider.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL);

            List<Profitability> result = new ArrayList<>();
            while(resultSet.next()) {
                result.add(mapper.mapRow(resultSet));
            }
            return result;
        } catch (SQLException | CreateConnectionDBException e) {
            throw new RuntimeException(e);
        }
    }
}
