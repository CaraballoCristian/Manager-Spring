package com.musa.project.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

public class JWTUtil {

    public static String generateToken(String username){
        List<String> roles = List.of("ROLE_USER");
        return Jwts.builder()
                .subject(username)
                .claim("roles", roles)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3_600_000))
                .signWith(getSecretKey())
                .compact();
    }

    public static List<String> getRolesFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return claims.get("roles", List.class);
    }

    public static boolean validateToken(String token){
        try {
            Claims claims = getAllClaimsFromToken(token);
            return claims != null && claims.getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private static Claims getAllClaimsFromToken(String token) {

        try{
            return Jwts
                    .parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getUsernameFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return claims.getSubject();
    }

    private static SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(SecretKeyReader.getSecretKeyProperty().getBytes(StandardCharsets.UTF_8));
    }
}
