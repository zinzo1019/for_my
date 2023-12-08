package com.example.HelloSpringboot.config.login.object;

import java.time.LocalDate;
import java.util.Map;

public class GoogleUserInfo implements OAuth2UserInfo {
    private Map<String, Object> attributes;

    public GoogleUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return String.valueOf(attributes.get("sub"));
    }

    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getEmail() {
        return String.valueOf(attributes.get("email"));
    }

    @Override
    public String getNickname() {
        return String.valueOf(attributes.get("nickname"));
    }

    @Override
    public LocalDate getBirth() {
        return null;
    }

    @Override
    public String getUsername() {
        return String.valueOf(attributes.get("name"));
    }

    @Override
    public String getGivenName() {
        return String.valueOf(attributes.get("given_name"));
    }

    @Override
    public String getFamilyName() {
        return String.valueOf(attributes.get("family_name"));
    }

    @Override
    public String getPicture() {
        return String.valueOf(attributes.get("picture"));
    }
}