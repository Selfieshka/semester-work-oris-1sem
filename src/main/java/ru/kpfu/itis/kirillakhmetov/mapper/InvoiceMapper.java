package ru.kpfu.itis.kirillakhmetov.mapper;

import ru.kpfu.itis.kirillakhmetov.entity.Invoice;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceMapper implements RowMapper<Invoice> {
    @Override
    public Invoice mapRow(ResultSet resultSet) throws SQLException {
        return Invoice.builder()
                .id(resultSet.getLong("invoice_id"))
                .owner_id(resultSet.getLong("owner_id"))
                .number(resultSet.getString("number"))
                .date(resultSet.getDate("date").toLocalDate())
                .build();
    }
}
