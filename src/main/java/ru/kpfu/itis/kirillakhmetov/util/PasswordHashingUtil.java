package ru.kpfu.itis.kirillakhmetov.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PasswordHashingUtil {
    public static String encrypt(String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }

    public static boolean compare(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
