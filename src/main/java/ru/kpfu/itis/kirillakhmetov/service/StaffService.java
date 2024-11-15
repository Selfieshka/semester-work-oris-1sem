package ru.kpfu.itis.kirillakhmetov.service;

import ru.kpfu.itis.kirillakhmetov.dao.StaffDao;
import ru.kpfu.itis.kirillakhmetov.entity.Employee;
import ru.kpfu.itis.kirillakhmetov.exception.CreateConnectionDBException;

import java.sql.SQLException;
import java.util.List;

public class StaffService {

    private final StaffDao staffDao;

    public StaffService(StaffDao staffDao) {
        this.staffDao = staffDao;
    }

    public List<Employee> getAllEmployees() throws CreateConnectionDBException, SQLException {
        return staffDao.getAllEmployees();
    }

    public void addEmployee(String firstName, String lastName, String patronymic, String effectiveDate, String position, Integer salary) {
        try {
            staffDao.addEmployee(firstName, lastName, patronymic, effectiveDate, position, salary);
        } catch (CreateConnectionDBException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
