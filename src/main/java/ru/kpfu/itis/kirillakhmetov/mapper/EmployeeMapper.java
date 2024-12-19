package ru.kpfu.itis.kirillakhmetov.mapper;

import ru.kpfu.itis.kirillakhmetov.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet resultSet) throws SQLException {
        return Employee.builder()
                .id(resultSet.getLong("employee_id"))
                .ownerId(resultSet.getLong("owner_id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .patronymic(resultSet.getString("patronymic"))
                .effectiveDate(resultSet.getDate("effective_date").toLocalDate())
                .salary(resultSet.getInt("salary"))
                .build();
    }
}
