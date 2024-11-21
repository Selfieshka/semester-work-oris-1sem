package ru.kpfu.itis.kirillakhmetov.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.kpfu.itis.kirillakhmetov.service.AnalyticsService;
import ru.kpfu.itis.kirillakhmetov.service.StaffService;

@WebListener
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        StaffService staffService = new StaffService();
        AnalyticsService analyticsService = new AnalyticsService();
        sce.getServletContext().setAttribute("staffService", staffService);
        sce.getServletContext().setAttribute("analyticsService", analyticsService);
    }
}
