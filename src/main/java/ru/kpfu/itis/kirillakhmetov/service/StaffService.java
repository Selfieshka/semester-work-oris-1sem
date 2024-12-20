package ru.kpfu.itis.kirillakhmetov.service;

import lombok.RequiredArgsConstructor;
import ru.kpfu.itis.kirillakhmetov.dao.StaffDao;
import ru.kpfu.itis.kirillakhmetov.dto.EmployeeDto;
import ru.kpfu.itis.kirillakhmetov.entity.Employee;

import java.util.List;

@RequiredArgsConstructor
public class StaffService {

    private final StaffDao staffDao;

    public List<Employee> getStaff(Long id) {
        return staffDao.findAll(id);
    }

    public void saveEmployee(EmployeeDto employee) {
        staffDao.save(Employee.builder()
                .ownerId(employee.ownerId())
                .firstName(employee.firstName())
                .lastName(employee.lastName())
                .patronymic(employee.patronymic())
                .effectiveDate(employee.effectiveDate())
                .position(employee.position())
                .salary(employee.salary())
                .build());
    }

    public boolean deleteEmployeeById(long employeeId) {
        return staffDao.deleteById(employeeId);
    }
}
