package ru.kpfu.itis.kirillakhmetov.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.kpfu.itis.kirillakhmetov.dao.*;
import ru.kpfu.itis.kirillakhmetov.service.*;

@WebListener
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        StaffDao staffDao = new StaffDao();
        OwnerDao ownerDao = new OwnerDao();
        FinanceDao financeDao = new FinanceDao();
        InvoiceDao invoiceDao = new InvoiceDao();
        BankAccountDao bankAccountDao = new BankAccountDao();

        StaffService staffService = new StaffService(staffDao);
        SecurityService securityService = new SecurityService(ownerDao);
        FinanceService financeService = new FinanceService(financeDao);
        InvoiceService invoiceService = new InvoiceService(invoiceDao);
        OwnerService ownerService = new OwnerService(ownerDao);
        BankAccountService bankAccountService = new BankAccountService(bankAccountDao);

        sce.getServletContext().setAttribute("staffService", staffService);
        sce.getServletContext().setAttribute("securityService", securityService);
        sce.getServletContext().setAttribute("financeService", financeService);
        sce.getServletContext().setAttribute("invoiceService", invoiceService);
        sce.getServletContext().setAttribute("ownerService", ownerService);
        sce.getServletContext().setAttribute("bankAccountService", bankAccountService);
    }
}
