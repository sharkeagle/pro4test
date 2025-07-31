package com.project4test.project4test.util;

import org.mindrot.jbcrypt.BCrypt;

public class BcryptUtil {
    /**
     * 加密
     * @return 加密后的字符串
     */
    public static String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * 校验
     * @return true 校验通过 false 校验不通过
     */
    public static boolean check(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}
