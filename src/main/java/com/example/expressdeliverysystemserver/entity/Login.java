package com.example.expressdeliverysystemserver.entity;

public class Login {
    private int code;
    private String token;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Login{" +
                "code=" + code +
                ", token='" + token + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
