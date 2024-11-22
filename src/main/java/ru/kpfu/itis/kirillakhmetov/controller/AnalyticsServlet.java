package ru.kpfu.itis.kirillakhmetov.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.kirillakhmetov.entity.Profitability;
import ru.kpfu.itis.kirillakhmetov.service.AnalyticsService;

import java.io.IOException;
import java.util.*;

@WebServlet("/getAnalyticsData")
public class AnalyticsServlet extends HttpServlet {
    private AnalyticsService analyticsService;

    @Override
    public void init() throws ServletException {
        analyticsService = (AnalyticsService) getServletContext().getAttribute("analyticsService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonResult = analyticsService.getBusinessProfitability();
        List<Profitability> dots = new ArrayList<>();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonResult);
    }
}
