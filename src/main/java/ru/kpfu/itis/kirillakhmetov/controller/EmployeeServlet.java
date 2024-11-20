package ru.kpfu.itis.kirillakhmetov.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.kirillakhmetov.dto.EmployeeRecordDto;
import ru.kpfu.itis.kirillakhmetov.service.StaffService;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/staff/add")
public class EmployeeServlet extends HttpServlet {
    private StaffService staffService;

    @Override
    public void init() throws ServletException {
        super.init();
        staffService = (StaffService) getServletContext().getAttribute("staffService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/view/staff/addEmployee.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        staffService.addEmployee(
                new EmployeeRecordDto(
                        req.getParameter("firstName"),
                        req.getParameter("lastName"),
                        req.getParameter("patronymic"),
                        LocalDate.parse(req.getParameter("effectiveDate")),
                        req.getParameter("position"),
                        Integer.valueOf(req.getParameter("salary"))
                )
        );
        resp.sendRedirect(req.getContextPath() + "/staff");
    }
}
