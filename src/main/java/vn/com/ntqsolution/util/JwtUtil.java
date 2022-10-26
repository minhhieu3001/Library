package vn.com.ntqsolution.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import vn.com.ntqsolution.response.JwtResponse;

import java.util.Date;
import java.util.Map;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

public class JwtUtil {
    private static String secret = "minhhieu301";
    private static SignatureAlgorithm signatureAlgorithm = HS256;
    
    public static Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public static Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    public static Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }


    public static String generateToken(Map<String, Object> claims) {
        claims.put("email", claims.get("email"));
        claims.put("role", claims.get("role"));
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(signatureAlgorithm, secret).compact();
    }

    public static JwtResponse parseToken(String token) {
        try {
            JwtResponse jwtResponse = new JwtResponse();
            Claims claims = getClaimsFromToken(token);
            String email = (String) claims.get("email");
            String role = (String) claims.get("role");

            jwtResponse.setEmail(email);
            jwtResponse.setRole(role);

            return jwtResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}