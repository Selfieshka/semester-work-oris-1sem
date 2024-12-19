package ru.kpfu.itis.kirillakhmetov.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.kirillakhmetov.exception.CreateConnectionDBException;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConnectionProvider {
    private static final String DB_DRIVER = System.getenv("DB_DRIVER");
    private static final String DB_HOST = System.getenv("DB_HOST");
    private static final String DB_PORT = System.getenv("DB_PORT");
    private static final String DB_NAME = System.getenv("DB_NAME");
    private static final String DB_USER = System.getenv("DB_USER");
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");
    private static final int DEFAULT_POOL_SIZE = 20;
    private static BlockingQueue<Connection> pool;

    static {
        try {
            createConnectionPool();
        } catch (CreateConnectionDBException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createConnectionPool() throws CreateConnectionDBException {
        pool = new ArrayBlockingQueue<>(DEFAULT_POOL_SIZE);
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            Connection connection = createConnection();
            var proxyConnection = (Connection) Proxy.newProxyInstance(
                    ConnectionProvider.class.getClassLoader(),
                    new Class[]{Connection.class},
                    ((proxy, method, args) ->
                            method.getName().equals("close") ?
                                    pool.add((Connection) proxy) :
                                    method.invoke(connection, args))
            );
            pool.add(proxyConnection);
        }
    }

    private static Connection createConnection() throws CreateConnectionDBException {
        try {
            Class.forName(DB_DRIVER);
            return DriverManager.getConnection(
                    "jdbc:postgresql://%s:%s/%s".formatted(DB_HOST, DB_PORT, DB_NAME),
                    DB_USER,
                    DB_PASSWORD
            );
        } catch (SQLException | ClassNotFoundException e) {
            throw new CreateConnectionDBException(e);
        }
    }
}
