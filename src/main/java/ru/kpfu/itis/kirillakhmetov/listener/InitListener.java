package ru.kpfu.itis.kirillakhmetov.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.kpfu.itis.kirillakhmetov.dao.FinanceDao;
import ru.kpfu.itis.kirillakhmetov.dao.InvoiceDao;
import ru.kpfu.itis.kirillakhmetov.dao.OwnerDao;
import ru.kpfu.itis.kirillakhmetov.dao.StaffDao;
import ru.kpfu.itis.kirillakhmetov.service.FinanceService;
import ru.kpfu.itis.kirillakhmetov.service.InvoiceService;
import ru.kpfu.itis.kirillakhmetov.service.SecurityService;
import ru.kpfu.itis.kirillakhmetov.service.StaffService;

@WebListener
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        StaffDao staffDao = new StaffDao();
        OwnerDao ownerDao = new OwnerDao();
        FinanceDao financeDao = new FinanceDao();
        InvoiceDao invoiceDao = new InvoiceDao();

        StaffService staffService = new StaffService(staffDao);
        SecurityService securityService = new SecurityService(ownerDao);
        FinanceService financeService = new FinanceService(financeDao);
        InvoiceService invoiceService = new InvoiceService(invoiceDao);

        sce.getServletContext().setAttribute("staffService", staffService);
        sce.getServletContext().setAttribute("securityService", securityService);
        sce.getServletContext().setAttribute("financeService", financeService);
        sce.getServletContext().setAttribute("invoiceService", invoiceService);
    }
}
