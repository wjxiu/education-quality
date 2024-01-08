package com.github.wjxiu.utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 * @author xiu
 * @create 2024-01-07 23:04
 */
public class PasswordUtil {

    // 生成加密后的密码
    public static String hashPassword(String plainPassword) {
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        return hashedPassword;
    }

    // 验证密码
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    public static void main(String[] args) {
        // 示例：注册时加密密码
        String plainPassword = "user123";
        String hashedPassword = hashPassword(plainPassword);
        System.out.println("Plain Password: " + plainPassword);
        System.out.println("Hashed Password: " + hashedPassword);

        // 示例：登录时验证密码
        String userInputPassword = "user123";
        if (verifyPassword(userInputPassword, hashedPassword)) {
            System.out.println("Password is correct. Login successful.");
        } else {
            System.out.println("Password is incorrect. Login failed.");
        }
    }
}
