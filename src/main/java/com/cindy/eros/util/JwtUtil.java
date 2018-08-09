package com.cindy.eros.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.sql.SQLOutput;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: eros
 * @description: Json Web Token鉴权工具
 * @author: cindy
 * @create: 2018-07-31 10:58
 **/

public class JwtUtil {

    private static final String JWT_KEY = "erosFunky";

    /**创建JWT*/
    public String createJwt(Integer userId) throws IllegalArgumentException, UnsupportedEncodingException {
        Algorithm al = Algorithm.HMAC256(JWT_KEY);
        return JWT.create()
                .withClaim("userid", userId)
                .withExpiresAt(new Date(System.currentTimeMillis()+360000))
                .sign(al);

    }

    /**验证jwt*/
    public Map verifyJwt(String token){
        Map map = new HashMap();
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_KEY);

            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String,Claim> claimMap = jwt.getClaims();
            Claim id = claimMap.get("userid");
            System.out.println(id.asInt());
            return claimMap;

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return map;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return map;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            System.out.println("校验失败");
            return map;
        }
    }

}
