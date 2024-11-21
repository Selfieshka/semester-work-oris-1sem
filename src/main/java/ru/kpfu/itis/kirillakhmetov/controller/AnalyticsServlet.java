package ru.kpfu.itis.kirillakhmetov.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.kirillakhmetov.entity.Dot;

import java.io.IOException;
import java.util.*;

@WebServlet("/getAnalyticsData")
public class AnalyticsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Dot> dots = new ArrayList<>();
        dots.add(new Dot("2010", 1000));
        dots.add(new Dot("2011", 20));
        dots.add(new Dot("2012", 39));
        dots.add(new Dot("2013", 1));
        dots.add(new Dot("2015", 36));
        String jsonObject = new ObjectMapper().writeValueAsString(dots);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonObject);
    }
}
