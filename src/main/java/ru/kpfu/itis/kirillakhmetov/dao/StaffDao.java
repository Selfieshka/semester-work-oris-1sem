package ru.kpfu.itis.kirillakhmetov.dao;


import ru.kpfu.itis.kirillakhmetov.entity.Employee;
import ru.kpfu.itis.kirillakhmetov.mapper.EmployeeMapper;
import ru.kpfu.itis.kirillakhmetov.util.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StaffDao extends BaseDao<Employee> {
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

    @Override
    public List<Employee> findAll() {
        try (Connection connection = ConnectionProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_GET_ALL)) {

            List<Employee> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(mapper.mapRow(resultSet));
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Can't get all employee");
        }
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Employee employee) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SAVE)) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getPatronymic());
            statement.setDate(4, Date.valueOf(employee.getEffectiveDate()));
            statement.setString(5, employee.getPosition());
            statement.setInt(6, employee.getSalary());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Can't save employee");
        }
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
