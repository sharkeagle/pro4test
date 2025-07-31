package com.project4test.project4test.util;

import org.mindrot.jbcrypt.BCrypt;

public class BcryptUtil {
    public static String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean check(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}
