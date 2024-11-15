package ru.kpfu.itis.kirillakhmetov.dao;


import ru.kpfu.itis.kirillakhmetov.entity.Employee;
import ru.kpfu.itis.kirillakhmetov.exception.CreateConnectionDBException;
import ru.kpfu.itis.kirillakhmetov.util.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDao {
    private final ConnectionProvider connectionProvider;

    public StaffDao(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public List<Employee> getAllEmployees() throws CreateConnectionDBException, SQLException {
        // language=sql
        String sqlQueryForGetAllEmployees = """
                SELECT * FROM employee
                INNER JOIN position USING(position_id)
                """;
        ResultSet resultSet;
        Statement statement = connectionProvider.getConnection().createStatement();
        resultSet = statement.executeQuery(sqlQueryForGetAllEmployees);

        List<Employee> result = new ArrayList<>();

        while (resultSet.next()) {
            result.add(new Employee(
                    resultSet.getInt("employee_id"),
                    resultSet.getString("firstname"),
                    resultSet.getString("lastname"),
                    resultSet.getString("patronymic"),
                    resultSet.getString("effectiveDate"),
                    resultSet.getString("position_name"),
                    resultSet.getInt("salary")
            ));
        }

        return result;
    }

    public void addEmployee(String firstname, String lastname, String patronymic, String effectiveDate, String position, Integer salary) throws CreateConnectionDBException, SQLException {
        PreparedStatement statement = connectionProvider.getConnection()
                .prepareStatement(
                        "INSERT INTO employee(firstname, lastname, patronymic, effectiveDate, position_id, salary) " +
                                "VALUES (?, ?, ?, ?, (SELECT position_id FROM position WHERE position_name = ?), ?)"
                );
        statement.setString(1, firstname);
        statement.setString(2, lastname);
        statement.setString(3, patronymic);
        statement.setDate(4, Date.valueOf(effectiveDate));
        statement.setString(5, position);
        statement.setInt(6, salary);
        statement.execute();
    }
}
