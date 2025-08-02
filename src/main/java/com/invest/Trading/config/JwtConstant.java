package com.invest.Trading.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtConstant {

    public static final String JWT_HEADER = "Authorization";
    public static String SECRET_KEY;

    @Value("${jwt.secret.key}")
    public void setSecretKey(String secretKey) {
        SECRET_KEY = secretKey;
    }
}