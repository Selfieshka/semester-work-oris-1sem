package ru.kpfu.itis.kirillakhmetov.controller.api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.kirillakhmetov.service.BankAccountService;
import ru.kpfu.itis.kirillakhmetov.service.FinanceService;

import java.io.IOException;

@WebServlet("/api/v1/stats/*")
public class FinanceApiServlet extends HttpServlet {
    private FinanceService financeService;
    private BankAccountService bankAccountService;
    private static final String SERVLET_BASE_PATH = "/api/v1/stats";

    @Override
    public void init() throws ServletException {
        financeService = (FinanceService) getServletContext().getAttribute("financeService");
        bankAccountService = (BankAccountService) getServletContext().getAttribute("bankAccountService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI().substring(getServletContext().getContextPath().length());
        Long idOwner = (Long) req.getSession().getAttribute("id");
        switch (path) {
            case SERVLET_BASE_PATH + "/revenue":
                resp.getWriter().write(financeService.calculateRevenue(idOwner));
                break;
            case SERVLET_BASE_PATH + "/expense":
                resp.getWriter().write(financeService.calculateExpense(idOwner));
                break;
            case SERVLET_BASE_PATH + "/money":
                resp.getWriter().write(bankAccountService.calculateAllAmount(idOwner));
                break;
            case SERVLET_BASE_PATH + "/profit":
                resp.getWriter().write(financeService.calculateProfit(idOwner));
                break;
            case SERVLET_BASE_PATH + "/profit-analytics":
                resp.getWriter().write(financeService.analyzeProfit(idOwner));
                break;
            case SERVLET_BASE_PATH + "/expense-analytics":
                resp.getWriter().write(financeService.analyzeExpense(idOwner));
                break;
            case SERVLET_BASE_PATH + "/revenues-expenses/count":
                resp.getWriter().write(financeService.getCountItems(idOwner));
                break;
            case SERVLET_BASE_PATH + "/revenues-expenses/items":
                resp.getWriter().write(financeService.getPage(idOwner, Integer.parseInt(req.getParameter("page"))));
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
        resp.setContentType("application/json");
    }
}
