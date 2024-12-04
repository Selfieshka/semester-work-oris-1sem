package ru.kpfu.itis.kirillakhmetov.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.kirillakhmetov.dto.SignInOwnerDto;
import ru.kpfu.itis.kirillakhmetov.service.SecurityService;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private SecurityService securityService;

    @Override
    public void init() throws ServletException {
        securityService = (SecurityService) getServletContext().getAttribute("securityService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean signInAttempt = securityService.signIn(
                req, new SignInOwnerDto(
                        req.getParameter("email"),
                        req.getParameter("password")
                ));

        if (signInAttempt) {
            req.getSession().setAttribute("email", req.getParameter("email"));
            resp.sendRedirect(getServletContext().getContextPath());
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
        }
    }
}
