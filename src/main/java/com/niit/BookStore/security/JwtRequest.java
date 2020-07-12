package com.niit.BookStore.security;

import java.io.Serializable;
import java.util.Objects;

public class JwtRequest implements Serializable {
    private String email;
    private String password;

    public String getEmail() {
        return Objects.isNull(email) ? email: email.toLowerCase();
    }

    public void setEmail(String email) {
        if(Objects.nonNull(email)) {
            this.email = email.toLowerCase();
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}