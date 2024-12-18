package ru.kpfu.itis.kirillakhmetov.dao;

import ru.kpfu.itis.kirillakhmetov.entity.Finance;
import ru.kpfu.itis.kirillakhmetov.mapper.FinanceMapper;
import ru.kpfu.itis.kirillakhmetov.util.ConnectionProvider;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FinanceDao extends BaseDao<Finance> {
    //language=sql
    private static final String SQL_GET_ALL = "SELECT * FROM finance";
    //language=sql
    private static final String SQL_SAVE = """
            INSERT INTO finance (owner_id, type, amount, category, date)
            VALUES (?, ?, ?, ?, ?)
            """;
    //language=sql
    private static final String SQL_SUM_ALL_REVENUE = """
            SELECT SUM(amount) AS amount
            FROM finance
            WHERE owner_id = ? AND category = 'Выручка'
            """;

    //language=sql
    private static final String SQL_SUM_ALL_EXPENSE = """
            SELECT SUM(amount) AS amount
            FROM finance
            WHERE owner_id = ? AND type = 'Расход'
            """;

    //language=sql
    private static final String SQL_PROFIT_ANALYSIS_BY_OWNER_ID = """
            WITH revenue AS (
                SELECT
                    DATE_TRUNC('month', date) AS month,
                    SUM(amount) AS total_revenue
                FROM finance
                WHERE owner_id = ? AND category = 'Выручка' AND date >= CURRENT_DATE - INTERVAL '5 months'
                GROUP BY month
            ),
                 expenses AS (
                     SELECT
                         DATE_TRUNC('month', date) AS month,
                         SUM(amount) AS total_expenses
                     FROM finance
                     WHERE owner_id = ? AND type = 'Расход' AND date >= CURRENT_DATE - INTERVAL '5 months'
                     GROUP BY month
                 )
            SELECT
                months.month AS date,
                COALESCE(r.total_revenue, 0) - COALESCE(e.total_expenses, 0) AS amount
            FROM
                (SELECT DISTINCT month FROM revenue UNION SELECT DISTINCT month FROM expenses) AS months
                    LEFT JOIN revenue r ON months.month = r.month
                    LEFT JOIN expenses e ON months.month = e.month
            ORDER BY months.month ASC
            LIMIT 5
            """;

    //language=sql
    private static final String SQL_EXPENSE_ANALYSIS_BY_OWNER_ID = """
            SELECT category, SUM(amount) AS amount
            FROM finance
            WHERE owner_id = ? AND type = 'Расход'
                AND date >= DATE_TRUNC('month', CURRENT_DATE)
                AND date < DATE_TRUNC('month', CURRENT_DATE) + INTERVAL '1 month'
            GROUP BY category
            ORDER BY category
            """;

    //language=sql
    private static final String SQL_GET_PART_REVENUES_AND_EXPENSES = """
            SELECT * FROM finance
            WHERE owner_id = ?
            ORDER BY date DESC
            LIMIT ?
            OFFSET ?
            """;

    //language=sql
    private static final String SQL_COUNT_REVENUES_AND_EXPENSES_BY_OWNER_ID = """
            SELECT COUNT(*) FROM finance
            WHERE owner_id = ?
            """;

    //language=sql
    private static final String SQL_GET_SUM_REVENUES_MONTH_BY_ID = """
            SELECT SUM(amount) as amount
            FROM finance
            WHERE EXTRACT(MONTH FROM date) = EXTRACT(MONTH FROM CURRENT_DATE)
                AND EXTRACT(YEAR FROM date) = EXTRACT(YEAR FROM CURRENT_DATE)
                AND owner_id = ?
                AND category = 'Выручка';
            """;

    //language=sql
    private static final String SQL_GET_SUM_EXPENSES_MONTH_BY_ID = """
            SELECT SUM(amount) as amount
            FROM finance
            WHERE EXTRACT(MONTH FROM date) = EXTRACT(MONTH FROM CURRENT_DATE)
                AND EXTRACT(YEAR FROM date) = EXTRACT(YEAR FROM CURRENT_DATE)
                AND owner_id = ?
                AND type = 'Расход';
            """;

    //language=sql
    private static final String SQL_GET_MONTH_PROFIT_BY_ID = """
            SELECT (SUM(CASE WHEN type = 'Доход' THEN amount ELSE 0 END) -
                     SUM(CASE WHEN type = 'Расход' THEN amount ELSE 0 END)) AS amount
            FROM finance
            WHERE EXTRACT(MONTH FROM date) = ?
                AND EXTRACT(YEAR FROM date) = ?
                AND owner_id = ?
            GROUP BY owner_id;
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

    public double sumAllRevenue(Long id) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SUM_ALL_REVENUE)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("amount");
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public double sumAllExpense(Long id) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SUM_ALL_EXPENSE)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("amount");
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Finance> profitAnalysisByOwnerid(Long id) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_PROFIT_ANALYSIS_BY_OWNER_ID)) {
            statement.setLong(1, id);
            statement.setLong(2, id);
            ResultSet resultSet = statement.executeQuery();
            List<Finance> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(Finance.builder()
                        .amount(resultSet.getDouble("amount"))
                        .date(resultSet.getDate("date").toLocalDate())
                        .build());
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Finance> expenseAnalysisByOwnerid(Long id) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_EXPENSE_ANALYSIS_BY_OWNER_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<Finance> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(Finance.builder()
                        .amount(resultSet.getDouble("amount"))
                        .category(resultSet.getString("category"))
                        .build());
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Finance> getPartRevenuesAndExpenses(Long id, int limit, int offset) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_PART_REVENUES_AND_EXPENSES)) {
            statement.setLong(1, id);
            statement.setInt(2, limit);
            statement.setInt(3, offset);
            ResultSet resultSet = statement.executeQuery();
            List<Finance> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(mapper.mapRow(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int countRevenuesAndExpensesByOwnerId(Long id) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_COUNT_REVENUES_AND_EXPENSES_BY_OWNER_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("count");
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public double getSumRevenuesMonthById(Long id) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_SUM_REVENUES_MONTH_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("amount");
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public double getSumExpensesMonthById(Long id) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_SUM_EXPENSES_MONTH_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("amount");
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public double getMonthProfitById(Long id, LocalDate localDate) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_MONTH_PROFIT_BY_ID)) {
            statement.setInt(1, localDate.getMonthValue());
            statement.setInt(2, localDate.getYear());
            statement.setLong(3, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("amount");
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
