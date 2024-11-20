package ru.kpfu.itis.kirillakhmetov.service;

import ru.kpfu.itis.kirillakhmetov.dao.StaffDao;
import ru.kpfu.itis.kirillakhmetov.entity.Employee;
import ru.kpfu.itis.kirillakhmetov.exception.CreateConnectionDBException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class StaffService {

    private final StaffDao staffDao;

    public StaffService() {
        this.staffDao = new StaffDao();
    }

    public List<Employee> getAllEmployees() throws CreateConnectionDBException, SQLException {
        return staffDao.getAll();
    }

    public void addEmployee(String firstName, String lastName, String patronymic, LocalDate effectiveDate, String position, Integer salary) {
        try {
            staffDao.save(new Employee(null, firstName, lastName, patronymic, effectiveDate, position, salary));
        } catch (CreateConnectionDBException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
