package ru.kpfu.itis.kirillakhmetov.dao;


import ru.kpfu.itis.kirillakhmetov.entity.Employee;
import ru.kpfu.itis.kirillakhmetov.mapper.EmployeeMapper;
import ru.kpfu.itis.kirillakhmetov.util.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StaffDao extends BaseDao<Employee> {
    //language=sql
    private final static String SQL_FIND_ALL = """
            SELECT owner_id, first_name, last_name, patronymic, effective_date, salary, employee_id,
                   STRING_AGG(name, ',') AS positions
            FROM employee
            INNER JOIN employee_position USING(employee_id)
            INNER JOIN position USING(position_id)
            WHERE owner_id = ?
            GROUP BY employee_id, effective_date
            ORDER BY effective_date;
            """;
    //language=sql
    private final static String SQL_SAVE_EMPLOYEE = """
            INSERT INTO employee(owner_id, first_name, last_name, patronymic, effective_date, salary)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

    //language=sql
    private final static String SQL_FIND_POSITION = """
            SELECT position_id
            FROM position
            WHERE name = ?
            """;

    //language=sql
    private final static String SQL_SAVE_EMPLOYEE_POSITION = """
            INSERT INTO employee_position(employee_id, position_id)
            VALUES (?, ?)
            """;

    //language=sql
    private final static String SQL_DELETE_EMPLOYEE_BY_ID = """
            DELETE FROM employee
            WHERE employee_id = ?
            """;


    public StaffDao() {
        this.mapper = new EmployeeMapper();
    }

    @Override
    public void save(Employee employee) {
        Connection connection = ConnectionProvider.getConnection();

        try (PreparedStatement statementEmployee = connection.prepareStatement(SQL_SAVE_EMPLOYEE, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement statementPosition = connection.prepareStatement(SQL_FIND_POSITION);
             PreparedStatement statementEmployeePosition = connection.prepareStatement(SQL_SAVE_EMPLOYEE_POSITION)) {

            connection.setAutoCommit(false);

            statementEmployee.setLong(1, employee.getOwnerId());
            statementEmployee.setString(2, employee.getFirstName());
            statementEmployee.setString(3, employee.getLastName());
            statementEmployee.setString(4, employee.getPatronymic());
            statementEmployee.setDate(5, Date.valueOf(employee.getEffectiveDate()));
            statementEmployee.setInt(6, employee.getSalary());
            statementEmployee.executeUpdate();

            ResultSet resultSet = statementEmployee.getGeneratedKeys();
            if (resultSet.next()) {
                long employeeId = resultSet.getLong(1);
                List<Long> positionsId = new ArrayList<>();
                for (String position : employee.getPosition()) {
                    statementPosition.setString(1, position);
                    try (ResultSet resultSetPosition = statementPosition.executeQuery()) {
                        if (resultSetPosition.next()) {
                            positionsId.add(resultSetPosition.getLong("position_id"));
                        }
                    }
                }

                if (!positionsId.isEmpty()) {
                    for (Long positionId : positionsId) {
                        statementEmployeePosition.setLong(1, employeeId);
                        statementEmployeePosition.setLong(2, positionId);
                        statementEmployeePosition.addBatch();
                    }
                    statementEmployeePosition.executeBatch();
                }
            }

            connection.commit();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public List<Employee> findAll(Long id) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<Employee> result = new ArrayList<>();
            while (resultSet.next()) {
                Employee employee = mapper.mapRow(resultSet);
                String positionsString = resultSet.getString("positions");
                List<String> positions = Arrays.asList(positionsString.split(","));
                employee.setPosition(positions);
                result.add(employee);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Can't find all employee", e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_EMPLOYEE_BY_ID)) {
            statement.setLong(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Can't find all employee", e);
        }
    }
}
