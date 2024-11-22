package ru.kpfu.itis.kirillakhmetov.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.kpfu.itis.kirillakhmetov.dao.AnalyticsDao;
import ru.kpfu.itis.kirillakhmetov.dao.StaffDao;
import ru.kpfu.itis.kirillakhmetov.service.AnalyticsService;
import ru.kpfu.itis.kirillakhmetov.service.StaffService;

@WebListener
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        StaffDao staffDao = new StaffDao();
        AnalyticsDao analyticsDao = new AnalyticsDao();

        StaffService staffService = new StaffService(staffDao);
        AnalyticsService analyticsService = new AnalyticsService(analyticsDao);
        sce.getServletContext().setAttribute("staffService", staffService);
        sce.getServletContext().setAttribute("analyticsService", analyticsService);
    }
}
