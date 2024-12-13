package ru.kpfu.itis.kirillakhmetov.mapper;

import ru.kpfu.itis.kirillakhmetov.entity.BankAccount;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccountMapper implements RowMapper<BankAccount> {
    @Override
    public BankAccount mapRow(ResultSet resultSet) throws SQLException {
        return null;
    }
}
