package ru.kpfu.itis.kirillakhmetov.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.kirillakhmetov.service.FinanceService;

import java.io.IOException;

@WebServlet("/getAnalyticsData")
public class AnalyticsServlet extends HttpServlet {
    private FinanceService financeService;

    @Override
    public void init() throws ServletException {
        financeService = (FinanceService) getServletContext().getAttribute("financeService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonResult = financeService.getBusinessProfitability();
        resp.setContentType("application/json");
        resp.getWriter().write(jsonResult);
    }
}
