package ru.kpfu.itis.kirillakhmetov.dao;

import ru.kpfu.itis.kirillakhmetov.entity.BankAccount;
import ru.kpfu.itis.kirillakhmetov.mapper.BankAccountMapper;
import ru.kpfu.itis.kirillakhmetov.util.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BankAccountDao extends BaseDao<BankAccount> {
    //language=sql
    private static final String SQL_SAVE = """
            INSERT INTO bank_account(owner_id, bank_name, amount)
            VALUES (?, ?, ?)
            """;

    public BankAccountDao() {
        this.mapper = new BankAccountMapper();
    }

    @Override
    public List<BankAccount> findAll() {
        return List.of();
    }

    @Override
    public Optional<BankAccount> findById(Long id) {
        return Optional.empty();
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

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
