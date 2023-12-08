package com.example.HelloSpringboot.config.login.object;

import java.time.LocalDate;

public interface OAuth2UserInfo {
    String getProviderId();
    String getProvider();
    String getEmail();
    String getUsername();
    String getGivenName();
    String getFamilyName();
    String getPicture();
    String getNickname();
    LocalDate getBirth();
}