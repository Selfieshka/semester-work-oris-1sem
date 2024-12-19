package ru.kpfu.itis.kirillakhmetov.dao;

import ru.kpfu.itis.kirillakhmetov.entity.BankAccount;
import ru.kpfu.itis.kirillakhmetov.mapper.BankAccountMapper;
import ru.kpfu.itis.kirillakhmetov.util.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccountDao extends BaseDao<BankAccount> {
    //language=sql
    private static final String SQL_SAVE = """
            INSERT INTO bank_account(owner_id, bank_name, amount)
            VALUES (?, ?, ?)
            """;

    //language=sql
    private static final String SQL_SUM_ALL_AMOUNT = """
            SELECT SUM(amount) AS amount
            FROM bank_account
            WHERE owner_id = ?
            """;


    public BankAccountDao() {
        this.mapper = new BankAccountMapper();
    }

    @Override
    public void save(BankAccount bankAccount) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SAVE)) {
            statement.setLong(1, bankAccount.getOwnerId());
            statement.setString(2, bankAccount.getBankName());
            statement.setDouble(3, bankAccount.getAmount());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public double sumAllAmount(Long id) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SUM_ALL_AMOUNT)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getDouble("amount");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
