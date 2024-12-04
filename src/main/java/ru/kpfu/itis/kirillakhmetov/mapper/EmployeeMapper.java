package ru.kpfu.itis.kirillakhmetov.mapper;

import ru.kpfu.itis.kirillakhmetov.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet resultSet) throws SQLException {
        return new Employee(
                resultSet.getLong("employee_id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("patronymic"),
                resultSet.getDate("effective_date").toLocalDate(),
                resultSet.getString("name"),
                resultSet.getInt("salary")
        );
    }
}
