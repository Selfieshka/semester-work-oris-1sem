package ru.kpfu.itis.kirillakhmetov.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.kirillakhmetov.service.AnalyticsService;

import java.io.IOException;

@WebServlet("")
public class MainServlet extends HttpServlet {
    private AnalyticsService analyticsService;

    @Override
    public void init() throws ServletException {
        analyticsService = (AnalyticsService) getServletContext().getAttribute("analyticsService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
    }
}
