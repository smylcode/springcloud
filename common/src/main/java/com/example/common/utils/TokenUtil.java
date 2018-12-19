package com.example.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

/**
 * 该类是基于JWTS 认证协议的公共封装类，用于生成指定有效期的token，并对客户端传上来的token进行校验合法性。
 * @author gouchao
 * @since 2018.12.3 13:43
 */
@Log4j2
@Getter
@Setter
public class TokenUtil {
    private static RSAPrivateKey priKey;
    private static RSAPublicKey pubKey;

    private static class SingletonHolder {
        private static final TokenUtil INSTANCE = new TokenUtil();
    }

    public synchronized static TokenUtil getInstance(String modulus, String privateExponent, String publicExponent) {
        if (priKey == null && pubKey == null) {
            priKey = RSAUtil.getPrivateKey(modulus, privateExponent);
            pubKey = RSAUtil.getPublicKey(modulus, publicExponent);
        }
        return SingletonHolder.INSTANCE;
    }

    public synchronized static void reload(String modulus, String privateExponent, String publicExponent) {
        priKey = RSAUtil.getPrivateKey(modulus, privateExponent);
        pubKey = RSAUtil.getPublicKey(modulus, publicExponent);
    }

    /**
     * 获取实例
     * @return
     */
    public synchronized static TokenUtil getInstance() {
        if (priKey == null && pubKey == null) {
            priKey = RSAUtil.getPrivateKey(RSAUtil.modulus, RSAUtil.private_exponent);
            pubKey = RSAUtil.getPublicKey(RSAUtil.modulus, RSAUtil.public_exponent);
        }
        return SingletonHolder.INSTANCE;
    }

    /**
     * 获取Token
     *
     * @param uid 用户ID
     * @param exp 失效时间，单位秒
     * @return
     */
    public  String getToken(String uid, int exp) {
        long endTime = System.currentTimeMillis() +  exp;
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("author","06care.com");
//        map.put(Claims.SUBJECT,uid);
//        map.put(Claims.EXPIRATION,endTime);
//        return Jwts.builder().setClaims(map).signWith(SignatureAlgorithm.RS512, priKey).compact();
        return Jwts.builder().setSubject(uid).setExpiration(new Date(endTime)).signWith(SignatureAlgorithm.RS512, priKey).compact();
    }

    public static void main(String[] args) {

        try {
            String esp = TokenUtil.getInstance().getToken("0010001201100018",0);
            System.out.println(esp);
            System.out.println(TokenUtil.getInstance().checkToken(esp));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Token
     *
     * @param uid 用户ID
     * @return
     */
    public String getToken(String uid) {
        long endTime = System.currentTimeMillis() + 1000 * 60 * 1440;
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("author","06care.com");
//        map.put(Claims.SUBJECT,uid);
//        map.put(Claims.EXPIRATION,endTime);
        return Jwts.builder().setSubject(uid).setExpiration(new Date(endTime)).signWith(SignatureAlgorithm.RS512, priKey).compact();
//        return Jwts.builder().setClaims(map)
//                .signWith(SignatureAlgorithm.RS512, priKey).compact();
    }

    /**
     * 检查Token是否合法
     *
     * @param token
     * @return JWTResult
     */
    public ReturnModel checkToken(String token) {
        try {
            Jws<Claims> rtn =  Jwts.parser().setSigningKey(pubKey).parseClaimsJws(token);
            Claims claims = rtn.getBody();
            Header header  = rtn.getHeader();
            String sub = claims.get(Claims.SUBJECT, String.class); //用户ID
            //Date exp = claims.get(Claims.EXPIRATION, Date.class);
            return ReturnModel.success("合法请求", sub);
        } catch (ExpiredJwtException e) {
            log.error(e.getMessage());
            // 在解析JWT字符串时，如果‘过期时间字段’已经早于当前时间，将会抛出ExpiredJwtException异常，说明本次请求已经失效
            return ReturnModel.fail(-2, "登录信息已失效，请重新登录！");
        } catch (Exception e) {
            log.error(e);
            System.out.println(e.getStackTrace());
            return ReturnModel.fail(-3, "非法请求");
        }
    }
}
