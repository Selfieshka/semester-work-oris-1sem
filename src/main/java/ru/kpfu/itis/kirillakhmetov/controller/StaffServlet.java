package ru.kpfu.itis.kirillakhmetov.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.kirillakhmetov.service.StaffService;

import java.io.IOException;

@WebServlet("/staff")
public class StaffServlet extends HttpServlet {
    private StaffService staffService;

    @Override
    public void init() throws ServletException {
        staffService = (StaffService) getServletContext().getAttribute("staffService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("staff", staffService.getStaff());
        getServletContext().getRequestDispatcher("/WEB-INF/view/staff.jsp").forward(req, resp);
    }
}
