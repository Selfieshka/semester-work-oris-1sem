package ru.kpfu.itis.kirillakhmetov.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.kirillakhmetov.service.StaffService;

import java.io.IOException;

@WebServlet("/staff/*")
public class DeleteEmployeeServlet extends HttpServlet {
    private StaffService staffService;

    @Override
    public void init() throws ServletException {
        staffService = (StaffService) getServletContext().getAttribute("staffService");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        long employeeId;
        try {
            employeeId = Long.parseLong(pathParts[1]);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid employee ID.");
            return;
        }
        staffService.deleteEmployeeById(employeeId);
    }
}
