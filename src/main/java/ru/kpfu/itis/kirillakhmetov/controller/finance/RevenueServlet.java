package ru.kpfu.itis.kirillakhmetov.controller.finance;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.kirillakhmetov.dto.FinanceDto;
import ru.kpfu.itis.kirillakhmetov.service.FinanceService;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/finance/revenue")
public class RevenueServlet extends HttpServlet {
    private FinanceService financeService;

    @Override
    public void init() throws ServletException {
        financeService = (FinanceService) getServletContext().getAttribute("financeService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        financeService.addRevenue(new FinanceDto(
                (Long) req.getSession().getAttribute("id"),
                Double.parseDouble(req.getParameter("amount")),
                "Выручка",
                LocalDate.parse(req.getParameter("date"))
        ));
        resp.sendRedirect(getServletContext().getContextPath() + "/finance");
    }
}
