package ru.kpfu.itis.kirillakhmetov.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.kirillakhmetov.service.InvoiceService;

import java.io.IOException;

@WebServlet("/invoices/*")
public class DeleteInvoiceServlet extends HttpServlet {
    private InvoiceService invoiceService;

    @Override
    public void init() throws ServletException {
        invoiceService = (InvoiceService) getServletContext().getAttribute("invoiceService");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        long invoiceId;
        try {
            invoiceId = Long.parseLong(pathParts[1]);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid invoice ID.");
            return;
        }
        invoiceService.deleteInvoiceById(invoiceId);
    }
}
