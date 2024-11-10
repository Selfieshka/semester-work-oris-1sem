package ru.kpfu.itis.kirillakhmetov.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.kpfu.itis.kirillakhmetov.dao.StaffDao;
import ru.kpfu.itis.kirillakhmetov.service.StaffService;
import ru.kpfu.itis.kirillakhmetov.util.ConnectionProvider;

@WebListener
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionProvider connectionProvider = ConnectionProvider.getInstance();
        StaffDao staffService = new StaffDao(connectionProvider);
        sce.getServletContext().setAttribute("staffService", staffService);

        StaffService staffDao = new StaffService(staffService);
        sce.getServletContext().setAttribute("staffDao", staffDao);
    }
}
