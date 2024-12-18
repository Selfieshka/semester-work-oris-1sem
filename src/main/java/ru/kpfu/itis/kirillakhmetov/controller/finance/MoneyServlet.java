package ru.kpfu.itis.kirillakhmetov.controller.finance;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.kirillakhmetov.dto.BankAccountDto;
import ru.kpfu.itis.kirillakhmetov.exception.ValidationException;
import ru.kpfu.itis.kirillakhmetov.service.BankAccountService;

import java.io.IOException;

@WebServlet("/finance/money")
public class MoneyServlet extends HttpServlet {
    private BankAccountService bankAccountService;

    @Override
    public void init() throws ServletException {
        bankAccountService = (BankAccountService) getServletContext().getAttribute("bankAccountService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            bankAccountService.addAccount(new BankAccountDto(
                    (Long) req.getSession().getAttribute("id"),
                    req.getParameter("bankName"),
                    Double.parseDouble(req.getParameter("amount"))
            ));
            resp.sendRedirect(getServletContext().getContextPath() + "/finance");
        } catch (ValidationException e) {
            req.setAttribute("errors", e.getErrors());
            req.getRequestDispatcher("/WEB-INF/view/finance.jsp").forward(req, resp);
        }
    }
}
