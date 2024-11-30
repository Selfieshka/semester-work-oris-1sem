package ru.kpfu.itis.kirillakhmetov.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.kirillakhmetov.service.MonetaryAccountingService;

import java.io.IOException;

@WebServlet
public class MonetaryAccountingServlet extends HttpServlet {
    private MonetaryAccountingService monetaryAccountingService;

    @Override
    public void init() throws ServletException {
        monetaryAccountingService = (MonetaryAccountingService) getServletContext().getAttribute("monetaryAccountingService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().setAttribute("info", monetaryAccountingService.getInfo());
        getServletContext().getRequestDispatcher("/WEB-INF/view/monetary-info.jsp").forward(req, resp);
    }
}
