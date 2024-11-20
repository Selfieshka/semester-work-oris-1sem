package ru.kpfu.itis.kirillakhmetov.dao;


import ru.kpfu.itis.kirillakhmetov.entity.Employee;
import ru.kpfu.itis.kirillakhmetov.exception.CreateConnectionDBException;
import ru.kpfu.itis.kirillakhmetov.mapper.EmployeeMapper;
import ru.kpfu.itis.kirillakhmetov.mapper.RowMapper;
import ru.kpfu.itis.kirillakhmetov.util.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDao {
    private final RowMapper<Employee> mapper;
    //language=sql
    private final static String SQL_GET_ALL = """
            SELECT * FROM employee
                INNER JOIN position USING(position_id)
            """;

    //language=sql
    private final static String SQL_SAVE = """
            INSERT INTO employee(first_name, last_name, patronymic, effective_date, position_id, salary)
            VALUES (?, ?, ?, ?, (SELECT position_id FROM position WHERE position_name = ?), ?)
            """;

    public StaffDao() {
        this.mapper = new EmployeeMapper();
    }

    public List<Employee> getAll() throws CreateConnectionDBException, SQLException {
        Statement statement = ConnectionProvider.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(SQL_GET_ALL);

        List<Employee> result = new ArrayList<>();

        while (resultSet.next()) {
            result.add(mapper.mapRow(resultSet));
        }

        return result;
    }

    public void save(Employee employee) throws CreateConnectionDBException, SQLException {
        PreparedStatement statement = ConnectionProvider.getConnection()
                .prepareStatement(SQL_SAVE);
        statement.setString(1, employee.getFirstName());
        statement.setString(2, employee.getLastName());
        statement.setString(3, employee.getPatronymic());
        statement.setDate(4, Date.valueOf(employee.getEffectiveDate()));
        statement.setString(5, employee.getPosition());
        statement.setInt(6, employee.getSalary());
        statement.execute();
    }
}
