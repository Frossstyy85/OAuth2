package org.example.springweb.service.oauth2;

import org.example.springweb.entity.User;
import org.example.springweb.repository.UserRepository;
import org.example.springweb.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Map;

@Service
public class CustomOAuth2Service extends DefaultOAuth2UserService {

    private final UserService userService;
    private final UserRepository userRepository;

    public CustomOAuth2Service(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId = String.valueOf(attributes.get("id"));
        String username = String.valueOf(attributes.get("login"));
        String avatar = String.valueOf(attributes.get("avatar_url"));

        User user = userRepository.findByProviderAndProviderId(provider, providerId).orElseGet( () ->
                userService.saveUser(
                        User.builder()
                                .username(username)
                                .provider(provider)
                                .providerId(providerId)
                                .avatar(avatar)
                                .build()
                )
        );

        Collection<? extends GrantedAuthority> authorities = userService.getGrantedAuthorities(user);


        return new CustomOAuth2User(authorities, attributes, "id", user);
    }

}