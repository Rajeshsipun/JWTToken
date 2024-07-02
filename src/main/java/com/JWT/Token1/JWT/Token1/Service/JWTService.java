package com.JWT.Token1.JWT.Token1.Service;


import com.JWT.Token1.JWT.Token1.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {


    @Value("${jwt.algorithm.key}")
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private  String issuer;


    @Value("${jwt.expiryTime}")
    private int expiryTime;


    private Algorithm algorithm;

    private  final  String USER_NAME="User_name";


    @PostConstruct
    public  void PostConstruct (){
        algorithm=Algorithm.HMAC256(algorithmKey);
    }

    public  String  generateJWTToken(User user ){
        return JWT.create()
                .withClaim(USER_NAME, user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+expiryTime))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String getUsername(String token){
        DecodedJWT decodedJWT =
                JWT.require(algorithm).withIssuer(issuer).build().verify(token);
        return  decodedJWT.getClaim(USER_NAME).asString();
    }
}
