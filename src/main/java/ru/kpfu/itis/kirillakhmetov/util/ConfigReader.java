package ru.kpfu.itis.kirillakhmetov.util;

import ru.kpfu.itis.kirillakhmetov.exception.GetConfigInfoException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.NoSuchFileException;
import java.util.Properties;

public class ConfigReader {
    public static Properties getConfigProperty(String filename) throws GetConfigInfoException {
        Properties properties = new Properties();
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(filename)) {
            if (input == null) {
                throw new NoSuchFileException("No such file file with name " + filename);
            }
            properties.load(input);
        } catch (IOException e) {
            throw new GetConfigInfoException();
        }
        return properties;
    }
}