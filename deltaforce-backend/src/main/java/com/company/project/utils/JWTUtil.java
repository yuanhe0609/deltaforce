package com.company.project.utils;

import com.company.project.entity.Result;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {

    private static final String SECRET_KEY = "springboot.deltaforce-backend.dnjsgur";
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    public static String createToken(String username, long ttlMillis) {
        byte[] secretKeyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
        Date now = new Date();
        Date expiration = new Date(now.getTime() + ttlMillis);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(secretKeySpec,signatureAlgorithm)
                .compact();
    }

    public static Result checkToken(Map<String,String> tokenMap){
        Claims claims;
        Map<String,String> resultMap = new HashMap<>();
        try {
            claims = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8))) // 设置密钥
                    .parseClaimsJws(tokenMap.get("Access-Token")) // 解析token
                    .getBody(); // 获取负载
        } catch (ExpiredJwtException e) {
            claims = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8))) // 设置密钥
                    .parseClaimsJws(tokenMap.get("Refresh-Token")) // 解析token
                    .getBody(); // 获取负载
            Date expiration = claims.getExpiration();
            if(expiration.before(new Date())){
                return new Result().error("Refresh-Token失效");
            }
            String AccessToken = createToken(claims.getSubject().toString(),3600000L);
            resultMap.put("Access-Token",AccessToken);
            resultMap.put("Refresh-Token",tokenMap.get("Refresh-Token"));
        } catch (UnsupportedJwtException e) {
            return new Result().error("Access-Token不受支持");
        } catch (MalformedJwtException e) {
            return new Result().error("Access-Token格式错误");
        } catch (SignatureException e) {
            return new Result().error("Access-Token签名错误");
        } catch (IllegalArgumentException e) {
            return new Result().error("Access-Token为空或非法");
        } catch (Exception e) {
            return new Result().error("发生未知错误: " + e.getMessage());
        }
        String subject = claims.getSubject();
        resultMap.put("username",subject);
        return new Result().success(resultMap,"检验通过");
    }
}

