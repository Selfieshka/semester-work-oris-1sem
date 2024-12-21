package ru.kpfu.itis.kirillakhmetov.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import ru.kpfu.itis.kirillakhmetov.dto.InvoiceDto;
import ru.kpfu.itis.kirillakhmetov.service.InvoiceService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@WebServlet("/invoices")
@MultipartConfig()
public class InvoicesServlet extends HttpServlet {
    private InvoiceService invoiceService;

    @Override
    public void init() throws ServletException {
        invoiceService = (InvoiceService) getServletContext().getAttribute("invoiceService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<InvoiceDto> invoiceDtos = invoiceService.getAllInvoices((Long) req.getSession().getAttribute("id"));
        req.setAttribute("invoices", invoiceDtos);
        getServletContext().getRequestDispatcher("/WEB-INF/view/invoices.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part invoice = req.getPart("invoice");
        Map<String, String> headerNames = Map.of(
                "productName", req.getParameter("productName"),
                "unitMeasure", req.getParameter("unitMeasure"),
                "quantity", req.getParameter("quantity"),
                "costPerUnit", req.getParameter("costPerUnit")
        );

        if (invoiceService.checkExtension(invoice)) {
            invoiceService.saveInvoiceInfo(new InvoiceDto(
                    (Long) req.getSession().getAttribute("id"),
                    null,
                    req.getParameter("number"),
                    LocalDate.parse(req.getParameter("date")),
                    null, null, null), invoice, headerNames);
            resp.sendRedirect(getServletContext().getContextPath() + "/invoices");
        } else {
            throw new RuntimeException("Неподдерживаемый тип файла");
        }
    }
}
