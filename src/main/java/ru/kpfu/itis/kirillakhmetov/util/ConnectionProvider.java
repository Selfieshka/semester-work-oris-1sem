package ru.kpfu.itis.kirillakhmetov.util;

import ru.kpfu.itis.kirillakhmetov.exception.CreateConnectionDBException;
import ru.kpfu.itis.kirillakhmetov.exception.GetConfigInfoException;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionProvider {
    private static ConnectionProvider instance;
    private Connection connection;
    private static final String CONFIG_FILENAME = "config.properties";

    private ConnectionProvider() {
    }

    public static ConnectionProvider getInstance() {
        if (instance == null) {
            instance = new ConnectionProvider();
        }
        return instance;
    }

    public Connection getConnection() throws CreateConnectionDBException {
        if (connection == null) {
            try {
                Properties properties = ConfigReader.getInstance().getConfigProperty(CONFIG_FILENAME);
                Class.forName(properties.getProperty("db.driver")).getDeclaredConstructor().newInstance();
                connection = DriverManager.getConnection(
                        properties.getProperty("db.url"),
                        properties.getProperty("db.user"),
                        properties.getProperty("db.password")
                );
            } catch (GetConfigInfoException | SQLException | IllegalAccessException | NoSuchMethodException |
                     InstantiationException |
                     InvocationTargetException | ClassNotFoundException e) {
                throw new CreateConnectionDBException(e);
            }
        }
        return connection;
    }
}