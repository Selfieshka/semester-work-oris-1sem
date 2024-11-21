package ru.kpfu.itis.kirillakhmetov.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.kirillakhmetov.entity.Dot;
import ru.kpfu.itis.kirillakhmetov.service.AnalyticsService;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@WebServlet("")
public class MainServlet extends HttpServlet {
    private AnalyticsService analyticsService;

    @Override
    public void init() throws ServletException {
        super.init();
        analyticsService = (AnalyticsService) getServletContext().getAttribute("analyticsService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
    }
}
