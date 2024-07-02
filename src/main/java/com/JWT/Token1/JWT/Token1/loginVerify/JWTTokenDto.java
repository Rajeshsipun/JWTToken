package com.JWT.Token1.JWT.Token1.loginVerify;

public class JWTTokenDto {

    private  String type;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String token;
}
