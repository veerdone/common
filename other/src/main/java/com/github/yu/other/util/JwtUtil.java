package com.github.yu.other.util;

import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${config.jwt.key:key}")
    private String key;

    @Value("${config.jwt.expiration:60}")
    private int expiration;

    public String create() {
        return createWithConfig().sign();
    }

    public JWT createWithConfig() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, expiration);
        Date expireTime = calendar.getTime();
        return JWT.create().setKey(key.getBytes())
                .setNotBefore(date)
                .setIssuedAt(date)
                .setExpiresAt(expireTime);
    }

    public boolean verify(String token) {
        try {
            return JWT.of(token).setKey(key.getBytes()).verify();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isExpire(String token) {
        try {
            JWTValidator.of(token).validateDate();
            return false;
        } catch (ValidateException e) {
            return true;
        }
    }
}
