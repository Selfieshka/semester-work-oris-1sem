package ru.kpfu.itis.kirillakhmetov.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.kirillakhmetov.service.FinanceService;

import java.io.IOException;

@WebServlet("/finance")
public class FinanceServlet extends HttpServlet {
    private FinanceService financeService;

    @Override
    public void init() throws ServletException {
        financeService = (FinanceService) getServletContext().getAttribute("financeService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().setAttribute("info", financeService.getInfo());
        getServletContext().getRequestDispatcher("/WEB-INF/view/finance.jsp").forward(req, resp);
    }
}
