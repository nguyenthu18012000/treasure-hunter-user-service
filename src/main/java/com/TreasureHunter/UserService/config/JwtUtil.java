package com.TreasureHunter.UserService.config;

import com.TreasureHunter.UserService.exception.CommonException;
import com.TreasureHunter.UserService.pojo.entity.postgres.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
@RequiredArgsConstructor
@Data
@Slf4j
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Duration expiration;

    public String generateLoginJwtToken(UserEntity userLogin) {
        try {
            return Jwts.builder()
                    .subject(String.valueOf(userLogin.getId()))
                    .claim("username", userLogin.getUsername())
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(Date.from(
                            LocalDateTime.now()
                                    .plusDays(expiration.toDays())
                                    .atZone(ZoneId.systemDefault())
                                    .toInstant()
                    ))
                    .signWith(getSecretKey())
                    .compact();

        } catch (Exception ex) {
            log.error("generateLoginJwtToken: {}", ex.getMessage());
            throw new CommonException("500", "Can't generate token");
        }
    }

    public UserEntity validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parseClaimsJws(token);
            Claims body = claims.getBody();
            String userId = body.getSubject();
            String username = body.get("username").toString();

            UserEntity userLogin = new UserEntity();
            userLogin.setId(Long.valueOf(userId));
            userLogin.setUsername(username);
            log.info("validateToken: userLogin - {}", userLogin);
            return userLogin;
        } catch (Exception ex) {
            log.error("validateToken: error - {}", ex.getMessage());
            return null;
        }
    }

    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}
