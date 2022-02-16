package com.UVCLabs.uvcportalbackend.api.core.security;

import java.util.concurrent.TimeUnit;

public class SecurityConstants {
    public static final String SECRET = "TesteUVCLABS";
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
    static final String SIGN_UP_URL = "";
    static final long EXPIRATION_TIME =  86400000L; //1 dia

/*    public static void main(String[] args) {
        System.out.println(TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
    }*/
}
