package com.example.demo.user.token;

import io.jsonwebtoken.*;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @Auther: lichangtong
 * @Date: 2018/11/19 0019 15:59
 * @Description:
 */
public class UserToken {
    private static final String SECRET = "jwt_test";

    public static void main(String[] args) {
        String token = getJwtTokent("lichangtong");
        System.out.println(token);
        System.out.println("token length:" + token.length());
        getUserId(token);
    }
    public static SecretKey generalKey() {
        String stringKey = "abcd";
        // 本地的密码解码
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        // 根据给定的字节数组使用AES加密算法构造一个密钥
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");

        return key;
    }
    private static String getJwtTokent(String userId) {
        // expire time
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        Claims claims = Jwts.claims();
        claims.put("name", "cy");
        claims.put("userId", userId);
        claims.setAudience("cy");
        claims.setIssuer("cy");
        Calendar nowTime = Calendar.getInstance();
//        //有10天有效期
        nowTime.add(Calendar.DATE, 10);
        Date expiresDate = nowTime.getTime();
            JwtBuilder builder = Jwts.builder()
                    .setId(userId)                                  // JWT_ID
                    .setAudience("APP")                                // 接受者
                    .setClaims(claims)                                // 自定义属性
                    .setSubject("Token")                                 // 主题
                    .setIssuer("lct")                                  // 签发者
                    .setIssuedAt(new Date())                        // 签发时间
                    .setNotBefore(new Date())                       // 失效时间
                    .setExpiration(expiresDate)                      // 过期时间
                    .signWith(signatureAlgorithm, secretKey);           // 签名算法以及密匙
        String token = builder.compact();
//        Calendar nowTime = Calendar.getInstance();
//        //有10天有效期
//        nowTime.add(Calendar.DATE, 10);
//        Date expiresDate = nowTime.getTime();
//        Claims claims = Jwts.claims();
//        claims.put("name", "cy");
//        claims.put("userId", userId);
//        claims.setAudience("cy");
//        claims.setIssuer("cy");
//        String token = Jwts.builder().setClaims(claims).setExpiration(expiresDate).signWith(SignatureAlgorithm.HS512, SECRET).compact();

//        Map<String, Object> objectMap = Maps.newHashMap();
//        objectMap.put("name","cy");
//        objectMap.put("userId",userId);
//        String token = Jwts.builder().setClaims(objectMap).setExpiration(expiresDate).signWith(SignatureAlgorithm.HS512, SECRET).compact();

        return token;
    }

    private static String getUserId(String token) {
        Jws<Claims> jws = Jwts.parser().setSigningKey(generalKey()).parseClaimsJws(token);
        String signature = jws.getSignature();
        System.out.println("signature:"+signature);
        Map<String, String> header = jws.getHeader();
        Claims claims = jws.getBody();

//        for(Object key:header.keySet().toArray()){
//            System.out.println("key:"+key);
//        }

        System.out.println("header alg :"+header.get("alg"));
        System.out.println("name claims.get = " + claims.get("name"));
        System.out.println("userId claims.get( = " + claims.get("userId"));
        System.out.println(claims.getAudience());
        System.out.println(claims.getIssuer());
        return null;
    }
}
