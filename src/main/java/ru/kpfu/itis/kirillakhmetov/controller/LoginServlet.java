package ru.kpfu.itis.kirillakhmetov.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.kirillakhmetov.dto.OwnerDto;
import ru.kpfu.itis.kirillakhmetov.dto.SignInOwnerDto;
import ru.kpfu.itis.kirillakhmetov.service.OwnerService;
import ru.kpfu.itis.kirillakhmetov.service.SecurityService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private SecurityService securityService;
    private OwnerService ownerService;

    @Override
    public void init() throws ServletException {
        securityService = (SecurityService) getServletContext().getAttribute("securityService");
        ownerService = (OwnerService) getServletContext().getAttribute("ownerService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        boolean signInAttempt = securityService.signIn(new SignInOwnerDto(email, password));

        if (signInAttempt) {
            Optional<OwnerDto> ownerFromBd = ownerService.getProfileInfo(email);
            if (ownerFromBd.isPresent()) {
                OwnerDto owner = ownerFromBd.get();
                req.getSession().setAttribute("id", ownerService.getOwnerIdByEmail(email));
                req.getSession().setAttribute("owner", owner);
            }
            resp.sendRedirect(getServletContext().getContextPath() + "/main");
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
        }
    }
}
