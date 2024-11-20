package ru.kpfu.itis.kirillakhmetov.service;

import ru.kpfu.itis.kirillakhmetov.dao.StaffDao;
import ru.kpfu.itis.kirillakhmetov.dto.EmployeeRecordDto;
import ru.kpfu.itis.kirillakhmetov.entity.Employee;

import java.util.List;

public class StaffService {

    private final StaffDao staffDao;

    public StaffService() {
        this.staffDao = new StaffDao();
    }

    public List<Employee> getStaff() {
        return staffDao.getAll();
    }

    public void addEmployee(EmployeeRecordDto employee) {
        staffDao.save(new Employee(
                employee.firstName(),
                employee.lastName(),
                employee.patronymic(),
                employee.effectiveDate(),
                employee.position(),
                employee.salary()
        ));
    }
}
