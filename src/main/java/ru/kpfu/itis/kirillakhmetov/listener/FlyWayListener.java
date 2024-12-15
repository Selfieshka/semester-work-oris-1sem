package ru.kpfu.itis.kirillakhmetov.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.flywaydb.core.Flyway;

@WebListener
public class FlyWayListener implements ServletContextListener {
    private static final String DB_HOST = System.getenv("DB_HOST");
    private static final String DB_PORT = System.getenv("DB_PORT");
    private static final String DB_NAME = System.getenv("DB_NAME");
    private static final String DB_USER = System.getenv("DB_USER");
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:postgresql://%s:%s/%s".formatted(DB_HOST, DB_PORT, DB_NAME),
                        DB_USER,
                        DB_PASSWORD)
                .load();
        flyway.migrate();
    }
}

