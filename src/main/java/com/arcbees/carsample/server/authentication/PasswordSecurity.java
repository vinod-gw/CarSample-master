package com.arcbees.carsample.server.authentication;

public interface PasswordSecurity {
    Boolean check(String password, String hashPassword);

    String hashPassword(String password);
}
