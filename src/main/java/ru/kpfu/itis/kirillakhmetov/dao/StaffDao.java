package ru.kpfu.itis.kirillakhmetov.dao;


import ru.kpfu.itis.kirillakhmetov.entity.Employee;
import ru.kpfu.itis.kirillakhmetov.exception.CreateConnectionDBException;
import ru.kpfu.itis.kirillakhmetov.util.ConnectionProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffDao {
    private final ConnectionProvider connectionProvider;

    public StaffDao(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public List<Employee> getAllEmployees() throws CreateConnectionDBException, SQLException {
        ResultSet resultSet = connectionProvider.getConnection().createStatement().executeQuery("SELECT * FROM employee");
        List<Employee> result = new ArrayList<>();

        while (resultSet.next()) {
            result.add(new Employee(
                    resultSet.getInt("id"),
                    resultSet.getString("firstname"),
                    resultSet.getString("lastname"),
                    resultSet.getString("patronymic"),
                    resultSet.getString("effectiveDate")
            ));
        }

        return result;
    }
}
