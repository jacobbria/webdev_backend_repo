package com.franklin.techblog.security;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordVerifier {
    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}


