package ru.kpfu.itis.kirillakhmetov.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.kirillakhmetov.dto.EmployeeDto;
import ru.kpfu.itis.kirillakhmetov.service.StaffService;
import ru.kpfu.itis.kirillakhmetov.util.annotations.SingletonFactory;
import ru.kpfu.itis.kirillakhmetov.validator.CreateEmployeeValidator;
import ru.kpfu.itis.kirillakhmetov.validator.ValidationResult;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/staff")
public class StaffServlet extends HttpServlet {
    private StaffService staffService;
    private final static CreateEmployeeValidator createEmployeeValidator = SingletonFactory.getInstance(CreateEmployeeValidator.class);

    @Override
    public void init() throws ServletException {
        staffService = (StaffService) getServletContext().getAttribute("staffService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("staff", staffService.getStaff((Long) req.getSession().getAttribute("id")));
        getServletContext().getRequestDispatcher("/WEB-INF/view/staff.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] positions = req.getParameterValues("positions");
        EmployeeDto employeeDto = new EmployeeDto(
                (Long) req.getSession().getAttribute("id"),
                req.getParameter("firstName"),
                req.getParameter("patronymic"),
                req.getParameter("patronymic"),
                LocalDate.parse(req.getParameter("effectiveDate")),
                positions != null ? List.of(positions) : List.of(),
                Integer.valueOf(req.getParameter("salary")));

        ValidationResult validationResult = createEmployeeValidator.isValid(employeeDto);
        if (validationResult.isValid()) {
            staffService.saveEmployee(employeeDto);
            resp.sendRedirect(getServletContext().getContextPath() + "/staff");
        } else {
            req.setAttribute("errors", validationResult.getErrors());
            doGet(req, resp);
        }
    }
}
