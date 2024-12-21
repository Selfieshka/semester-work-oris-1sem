package ru.kpfu.itis.kirillakhmetov.dao;

import ru.kpfu.itis.kirillakhmetov.dto.InvoiceDto;
import ru.kpfu.itis.kirillakhmetov.dto.ProductDto;
import ru.kpfu.itis.kirillakhmetov.entity.Invoice;
import ru.kpfu.itis.kirillakhmetov.mapper.InvoiceMapper;
import ru.kpfu.itis.kirillakhmetov.util.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDao extends BaseDao<Invoice> {
    //language=sql
    private static final String SQL_SAVE_INVOICE_AND_RETURN_ID = """
            INSERT INTO invoice(owner_id, number, date)
            VALUES (?, ?, ?)
            RETURNING invoice_id
            """;

    //language=sql
    private static final String SQL_SAVE_ALL_PRODUCTS = """
            INSERT INTO product(invoice_id, name, measurement_unit, quantity, unit_price)
            VALUES (?, ?, ?, ?, ?)
            """;

    //language=sql
    private static final String SQL_FIND_ALL_LAZY_BY_OWNER_ID = """
            SELECT * FROM invoice
            WHERE owner_id = ?
            """;
    //language=sql
    private static final String SQL_DELETE_INVOICE_BY_ID = """
            DELETE FROM invoice
            WHERE invoice_id = ?
            """;


    public InvoiceDao() {
        this.mapper = new InvoiceMapper();
    }

    public void saveInvoiceWithProducts(InvoiceDto invoiceDto, List<ProductDto> products) {
        Connection connection = ConnectionProvider.getConnection();
        try (connection;
             PreparedStatement statementForInvoice = connection.prepareStatement(SQL_SAVE_INVOICE_AND_RETURN_ID);
             PreparedStatement statementForProduct = connection.prepareStatement(SQL_SAVE_ALL_PRODUCTS)) {
            connection.setAutoCommit(false);
            statementForInvoice.setLong(1, invoiceDto.ownerId());
            statementForInvoice.setString(2, invoiceDto.number());
            statementForInvoice.setDate(3, Date.valueOf(invoiceDto.date()));
            ResultSet resultSet = statementForInvoice.executeQuery();

            if (resultSet.next()) {
                long invoiceId = resultSet.getLong("invoice_id");
                for (ProductDto product : products) {
                    statementForProduct.setLong(1, invoiceId);
                    statementForProduct.setString(2, product.name());
                    statementForProduct.setString(3, product.measurementUnit());
                    statementForProduct.setInt(4, product.quantity());
                    statementForProduct.setDouble(5, product.costPerUnit());
                    statementForProduct.addBatch();
                }
                statementForProduct.executeBatch();
                connection.commit();
            }
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public List<Invoice> findAllLazyByOwnerId(Long id) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_LAZY_BY_OWNER_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<Invoice> invoices = new ArrayList<>();
            while (resultSet.next()) {
                invoices.add(mapper.mapRow(resultSet));
            }
            return invoices;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_INVOICE_BY_ID)) {
            statement.setLong(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
