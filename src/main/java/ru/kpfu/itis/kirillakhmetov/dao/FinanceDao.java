package ru.kpfu.itis.kirillakhmetov.dao;

import ru.kpfu.itis.kirillakhmetov.entity.Finance;
import ru.kpfu.itis.kirillakhmetov.entity.Profitability;
import ru.kpfu.itis.kirillakhmetov.util.ConnectionProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FinanceDao extends BaseDao<Finance> {
    //language=sql
    private static final String SQL_GET_ALL = "SELECT * FROM finance";
    //language=sql
    private static final String SQL_SAVE = "";
    //language=sql
    private static final String SQL_CALCULATE_PROFITABILITY_FOR_EACH_YEAR = """
            SELECT SUM(value) AS value, EXTRACT(YEAR FROM date) AS date
            FROM finance
            GROUP BY EXTRACT(YEAR FROM date)
            ORDER BY date
            """;

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
