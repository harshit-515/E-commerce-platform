package com.ecommerce.util;

public class PasswordHashGenerator {

    public static void main(String[] args) {
       
        String plainPassword = "admin123";

        System.out.println("Plain password : " + plainPassword);

        String hash = PasswordUtil.hashPassword(plainPassword);

        System.out.println("Generated hash:");
        System.out.println(hash);
        System.out.println("Length: " + hash.length());
    }
}
