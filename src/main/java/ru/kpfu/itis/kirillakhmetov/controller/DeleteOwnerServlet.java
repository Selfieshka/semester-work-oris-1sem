package ru.kpfu.itis.kirillakhmetov.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.kirillakhmetov.dto.OwnerDto;
import ru.kpfu.itis.kirillakhmetov.service.OwnerService;
import ru.kpfu.itis.kirillakhmetov.service.SecurityService;

import java.io.IOException;

@WebServlet("/profile/delete")
public class DeleteOwnerServlet extends HttpServlet {
    private SecurityService securityService;
    private OwnerService ownerService;

    @Override
    public void init() throws ServletException {
        securityService = (SecurityService) getServletContext().getAttribute("securityService");
        ownerService = (OwnerService) getServletContext().getAttribute("ownerService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ownerService.deleteOwner(((OwnerDto) req.getSession().getAttribute("owner")).email());
        securityService.signOut(req);
        resp.sendRedirect(getServletContext().getContextPath() + "/login");
    }
}
