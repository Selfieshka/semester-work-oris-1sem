package ru.kpfu.itis.kirillakhmetov.util;

import ru.kpfu.itis.kirillakhmetov.exception.CreateConnectionDBException;
import ru.kpfu.itis.kirillakhmetov.exception.GetConfigInfoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionProvider {
    private static final String CONFIG_FILENAME = "db-dev.properties";

    public static Connection getConnection() throws CreateConnectionDBException {
        try {
            Properties properties = ConfigReader.getConfigProperty(CONFIG_FILENAME);
            Class.forName(properties.getProperty("db.driver"));
            return DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.user"),
                    properties.getProperty("db.password")
            );
        } catch (GetConfigInfoException | SQLException | ClassNotFoundException e) {
            throw new CreateConnectionDBException(e);
        }
    }
}
