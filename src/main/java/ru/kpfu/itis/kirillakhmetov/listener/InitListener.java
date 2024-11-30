package ru.kpfu.itis.kirillakhmetov.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.kpfu.itis.kirillakhmetov.dao.AnalyticsDao;
import ru.kpfu.itis.kirillakhmetov.dao.OwnerDao;
import ru.kpfu.itis.kirillakhmetov.dao.StaffDao;
import ru.kpfu.itis.kirillakhmetov.service.AnalyticsService;
import ru.kpfu.itis.kirillakhmetov.service.SecurityService;
import ru.kpfu.itis.kirillakhmetov.service.StaffService;

@WebListener
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        StaffDao staffDao = new StaffDao();
        AnalyticsDao analyticsDao = new AnalyticsDao();
        OwnerDao ownerDao = new OwnerDao();

        StaffService staffService = new StaffService(staffDao);
        AnalyticsService analyticsService = new AnalyticsService(analyticsDao);
        SecurityService securityService = new SecurityService(ownerDao);

        sce.getServletContext().setAttribute("staffService", staffService);
        sce.getServletContext().setAttribute("analyticsService", analyticsService);
        sce.getServletContext().setAttribute("securityService", securityService);
    }
}
