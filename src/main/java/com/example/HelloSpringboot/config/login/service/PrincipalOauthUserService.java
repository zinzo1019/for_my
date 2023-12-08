package com.example.HelloSpringboot.config.login.service;

import com.example.HelloSpringboot.entity.User;
import com.example.HelloSpringboot.config.login.object.PrincipalDetails;
import com.example.HelloSpringboot.config.login.object.*;
import com.example.HelloSpringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;

@Service
public class PrincipalOauthUserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * userRequest 데이터에 대한 후처리되는 함수
     * 함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2UserInfo oAuth2UserInfo = getAuth2UserInfo(userRequest, oAuth2User);
        User user = userRepository.findByEmail(oAuth2UserInfo.getEmail()); // db에서 user 데이터 찾기

        /** 회원가입 */
        if (user == null) {
            String email = oAuth2UserInfo.getEmail();
            if (email == null) { // email에 null 값이 들어온다면
                email = "user_" + new Random().nextInt(10000); // 임시 email 생성
            }
            user = User.builder()
                    .email(email)
                    .role("ROLE_USER")
                    .username(oAuth2UserInfo.getUsername())
                    .lastName(oAuth2UserInfo.getFamilyName())
                    .firstName(oAuth2UserInfo.getGivenName())
                    .nickname(oAuth2UserInfo.getNickname())
                    .picture(oAuth2UserInfo.getPicture())
                    .birth(oAuth2UserInfo.getBirth())
                    .provider(oAuth2UserInfo.getProvider())
                    .provideId(oAuth2UserInfo.getProviderId())
                    .build();

            userRepository.save(user);
        }

        return new PrincipalDetails(user, oAuth2User.getAttributes()); // user 데이터 반환
    }

    /** userRequest -> OAuth2UserInfo 타입 변환 */
    private static OAuth2UserInfo getAuth2UserInfo(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {

        if (userRequest.getClientRegistration().getRegistrationId().equals("google")) { // 구글 로그인
            return new GoogleUserInfo(oAuth2User.getAttributes());

        } else if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")) { //  페이스북 로그인
            return new FacebookUserInfo(oAuth2User.getAttributes());

        } else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")) { // 네이버 로그인
            return new NaverUserInfo((Map) oAuth2User.getAttributes().get("response"));

        } else if (userRequest.getClientRegistration().getRegistrationId().equals("kakao")) { // 카카오 로그인
            String[] profiles = String.valueOf(oAuth2User.getAttributes().get("kakao_account")).split("=|,");
            return new KakaoUserInfo(profiles, String.valueOf(oAuth2User.getAttributes().get("id")));

        } else {
            System.out.println("지원하지 않은 로그인 서비스 입니다.");
            return null;
        }
    }
}