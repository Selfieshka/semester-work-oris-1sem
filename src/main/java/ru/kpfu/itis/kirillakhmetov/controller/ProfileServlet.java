package ru.kpfu.itis.kirillakhmetov.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.kirillakhmetov.dto.OwnerDto;
import ru.kpfu.itis.kirillakhmetov.service.OwnerService;

import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private OwnerService ownerService;

    @Override
    public void init() throws ServletException {
        ownerService = (OwnerService) getServletContext().getAttribute("ownerService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OwnerDto owner = ownerService.getProfileInfo(((OwnerDto) req.getSession().getAttribute("owner")).email())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        req.getSession().setAttribute("owner", owner);
        getServletContext().getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = ((OwnerDto) req.getSession().getAttribute("owner")).email();
        ownerService.changePersonalData(new OwnerDto(
                req.getParameter("firstName"),
                req.getParameter("lastName"),
                req.getParameter("patronymic"),
                Integer.parseInt(req.getParameter("age")),
                email,
                req.getParameter("phoneNumber"),
                null
        ));
        OwnerDto owner = ownerService.getProfileInfo(email).orElseThrow();
        req.getSession().setAttribute("owner", owner);
        resp.sendRedirect(getServletContext().getContextPath() + "/profile");
//        resp.setContentType("application/json");
//        resp.getWriter().write("{\"redirectUrl\": \"%s\"}".formatted(getServletContext().getContextPath() + "/profile"));
    }
}
