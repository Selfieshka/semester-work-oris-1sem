package ru.kpfu.itis.kirillakhmetov.service;

import jakarta.servlet.http.Part;
import org.apache.poi.xssf.usermodel.XSSFWorkbookType;
import ru.kpfu.itis.kirillakhmetov.dao.InvoiceDao;
import ru.kpfu.itis.kirillakhmetov.entity.Product;
import ru.kpfu.itis.kirillakhmetov.util.ExcelReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class InvoiceService {
    private final InvoiceDao invoiceDao;

    public InvoiceService(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }


    public boolean checkExtension(Part part) {
        String fileName = part.getSubmittedFileName();
        String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1).toUpperCase();
        return fileExtension.equals(XSSFWorkbookType.XLSX.name());
    }

    public void analyze(Part invoice, Map<String, String> headerNames) {
        try (InputStream invoiceInputStream = invoice.getInputStream()) {
            List<Product> products = ExcelReader.readAllProductsByColumns(invoiceInputStream, headerNames);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
