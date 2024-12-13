package ru.kpfu.itis.kirillakhmetov.dao;

import ru.kpfu.itis.kirillakhmetov.entity.Finance;
import ru.kpfu.itis.kirillakhmetov.entity.Profitability;
import ru.kpfu.itis.kirillakhmetov.mapper.FinanceMapper;
import ru.kpfu.itis.kirillakhmetov.util.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FinanceDao extends BaseDao<Finance> {
    //language=sql
    private static final String SQL_GET_ALL = "SELECT * FROM finance";
    //language=sql
    private static final String SQL_SAVE = """
            INSERT INTO finance (owner_id, type, amount, category, date)
            VALUES (?, ?, ?, ?, ?)
            """;
    //language=sql
    private static final String SQL_CALCULATE_PROFITABILITY_FOR_EACH_YEAR = """
            SELECT SUM(value) AS value, EXTRACT(YEAR FROM date) AS date
            FROM finance
            GROUP BY EXTRACT(YEAR FROM date)
            ORDER BY date
            """;

    public FinanceDao() {
        this.mapper = new FinanceMapper();
    }

    @Override
    public List<Finance> findAll() {
        try (Connection connection = ConnectionProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_GET_ALL)) {

            List<Finance> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(mapper.mapRow(resultSet));
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Can't get all finance");
        }
    }

    @Override
    public Optional<Finance> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Finance finance) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SAVE)) {
            statement.setLong(1, finance.getOwner_id());
            statement.setString(2, finance.getType());
            statement.setDouble(3, finance.getAmount());
            statement.setString(4, finance.getCategory());
            statement.setDate(5, Date.valueOf(finance.getDate()));
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    public List<Profitability> calculateProfitabilityForEachYear() {
        try (Connection connection = ConnectionProvider.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_CALCULATE_PROFITABILITY_FOR_EACH_YEAR);
            List<Profitability> result = new ArrayList<>();

            while (resultSet.next()) {
                result.add(new Profitability(
                        resultSet.getInt("date"),
                        resultSet.getDouble("value")
                ));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
