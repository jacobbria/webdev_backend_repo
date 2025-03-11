package com.franklin.techblog.security;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;


@Component
public class PasswordVerifier {
    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}


