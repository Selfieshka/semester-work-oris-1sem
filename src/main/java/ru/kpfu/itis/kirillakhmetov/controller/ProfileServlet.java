package ru.kpfu.itis.kirillakhmetov.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.kirillakhmetov.dto.OwnerDto;
import ru.kpfu.itis.kirillakhmetov.service.SecurityService;

import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private SecurityService securityService;

    @Override
    public void init() throws ServletException {
        securityService = (SecurityService) getServletContext().getAttribute("securityService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OwnerDto owner = securityService.getProfileInfo((String) req.getSession().getAttribute("email"))
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        req.getSession().setAttribute("owner", owner);
        getServletContext().getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OwnerDto updatedOwnerDto = securityService.changePersonalData(new OwnerDto(
                req.getParameter("firstName"),
                req.getParameter("lastName"),
                req.getParameter("patronymic"),
                Integer.parseInt(req.getParameter("age")),
                (String) req.getSession().getAttribute("email"),
                req.getParameter("phoneNumber")
        ));
        req.getSession().removeAttribute("owner");
        req.getSession().setAttribute("owner", updatedOwnerDto);
        System.out.println(updatedOwnerDto);
        resp.sendRedirect(getServletContext().getContextPath() + "/profile");
    }
}
