package ru.kpfu.itis.kirillakhmetov.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import ru.kpfu.itis.kirillakhmetov.dto.OwnerDto;
import ru.kpfu.itis.kirillakhmetov.service.OwnerService;

import java.io.IOException;

@WebServlet("/profile/upload")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024)
public class UploadProfilePhotoServlet extends HttpServlet {
    private OwnerService ownerService;

    @Override
    public void init() throws ServletException {
        ownerService = (OwnerService) getServletContext().getAttribute("ownerService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part profilePhoto = req.getPart("profilePhoto");
        OwnerDto ownerFromSession = (OwnerDto) req.getSession().getAttribute("owner");
        String photoUrl = ownerService.uploadProfilePhoto(profilePhoto, ownerFromSession.email());
        OwnerDto newOwnerDto = new OwnerDto(
                ownerFromSession.firstName(),
                ownerFromSession.lastName(),
                ownerFromSession.patronymic(),
                ownerFromSession.age(),
                ownerFromSession.email(),
                ownerFromSession.phoneNumber(),
                photoUrl
        );
        req.getSession().setAttribute("owner", newOwnerDto);
        resp.sendRedirect(getServletContext().getContextPath() + "/profile");
    }
}
