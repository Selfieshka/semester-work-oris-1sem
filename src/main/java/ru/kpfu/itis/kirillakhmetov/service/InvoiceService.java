package ru.kpfu.itis.kirillakhmetov.service;

import jakarta.servlet.http.Part;
import org.apache.poi.xssf.usermodel.XSSFWorkbookType;
import ru.kpfu.itis.kirillakhmetov.dao.InvoiceDao;
import ru.kpfu.itis.kirillakhmetov.dto.InvoiceDto;
import ru.kpfu.itis.kirillakhmetov.dto.ProductDto;
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

    public void saveInvoiceInfo(InvoiceDto invoiceDto, Part invoice, Map<String, String> headerNames) {
        List<ProductDto> products = analyze(invoice, headerNames);
        invoiceDao.saveInvoiceWithProducts(invoiceDto, products);
    }

    private List<ProductDto> analyze(Part invoice, Map<String, String> headerNames) {
        try (InputStream invoiceInputStream = invoice.getInputStream()) {
            return ExcelReader.readAllProductsByColumns(invoiceInputStream, headerNames);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkExtension(Part part) {
        String fileName = part.getSubmittedFileName();
        String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1).toUpperCase();
        return fileExtension.equals(XSSFWorkbookType.XLSX.name());
    }
}
