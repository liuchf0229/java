package com.example.usermanager.util;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;

/**
 * ClassName: JWTUtils
 * Package: com.example.usermanager.util
 *
 * @Author: QINKUI
 * @createTime: 2024年03月14日 14:51:00
 */
public class JWTUtils {
    private static long time=30*60*1000; //过期时间，单位是毫秒：30分钟
    private static String secret="#$%1321FSF";//密钥

    /**
     *  生成token令牌(JWT字符串)
     *  @param userId  用户编号
     * @param userName  用户名称
     * @return token令牌
     */
    public static String getToken(Integer userId,String userName){
        JwtBuilder builder = Jwts.builder();//Jwt的构建对象
        //头部
        String token= builder.setHeaderParam("typ","JWT").setHeaderParam("alg","HS256")
                //载荷
                .claim("username",userName).claim("role","admin").claim("userid",userId)
                .setSubject("admin-test").setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                //签名
                .signWith(SignatureAlgorithm.HS256, secret).compact();
        return token;
    }



    /**
     *  验证token令牌(是否过期，是否篡改等)
     * @param token  token令牌(JWT字符串)
     * @return Claims
     */
    public static Claims parseToken(String token){
        JwtParser parser = Jwts.parser();
        Jws<Claims> claimsJws = parser.setSigningKey(secret).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return claims;
    }
}
