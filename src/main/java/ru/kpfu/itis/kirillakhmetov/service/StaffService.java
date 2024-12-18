package ru.kpfu.itis.kirillakhmetov.service;

import lombok.RequiredArgsConstructor;
import ru.kpfu.itis.kirillakhmetov.dao.StaffDao;
import ru.kpfu.itis.kirillakhmetov.dto.EmployeeDto;
import ru.kpfu.itis.kirillakhmetov.entity.Employee;

import java.util.List;

@RequiredArgsConstructor
public class StaffService {

    private final StaffDao staffDao;

    public List<Employee> getStaff() {
        return staffDao.findAll();
    }

    public void addEmployee(EmployeeDto employee) {
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
