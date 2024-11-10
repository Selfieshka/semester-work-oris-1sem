package ru.kpfu.itis.kirillakhmetov.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.kirillakhmetov.exception.CreateConnectionDBException;
import ru.kpfu.itis.kirillakhmetov.service.StaffService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/staff")
public class StaffServlet extends HttpServlet {
    private StaffService staffService;

    @Override
    public void init() throws ServletException {
        super.init();
        staffService = (StaffService) getServletContext().getAttribute("staffDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("staff", staffService.getAllEmployees());
        } catch (CreateConnectionDBException | SQLException e) {
            throw new RuntimeException(e);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/view/staff.jsp").forward(req, resp);
    }
}
