package cn.pompip.myblog.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class TokenUtil {

    public static final String tokenSecret = "hello_world";


    public static String getToken(String name ) {
        return JWT.create().withIssuer(name)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60))
                .sign(Algorithm.HMAC256(tokenSecret));
    }

    public static String refreshToken(String token){
        DecodedJWT decodedJWT = JWT.decode(token);
        String username = decodedJWT.getIssuer();
        String newToken =getToken(username);
        return newToken;
    }
}
