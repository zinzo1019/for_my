package com.example.HelloSpringboot.config.login.object;

import java.time.LocalDate;

public class KakaoUserInfo implements OAuth2UserInfo {

    private String id;
    private String[] kakaoAccount;

    public KakaoUserInfo(String[] attributes, String id ) {
        this.kakaoAccount = attributes;
        this.id = id;
    }

    @Override
    public String getProviderId() {
        return id;
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getUsername() {
        return kakaoAccount[6];
    }

    @Override
    public String getNickname() {
        return kakaoAccount[6];
    }

    @Override
    public LocalDate getBirth() {
        return null;
    }

    @Override
    public String getGivenName() {
        return null;
    }

    @Override
    public String getFamilyName() {
        return null;
    }

    @Override
    public String getPicture() {
        return kakaoAccount[10];
    }
}