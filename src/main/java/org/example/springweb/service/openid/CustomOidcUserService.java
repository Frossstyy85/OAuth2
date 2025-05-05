package org.example.springweb.service.openid;

import org.example.springweb.entity.User;
import org.example.springweb.repository.UserRepository;
import org.example.springweb.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class CustomOidcUserService extends OidcUserService {


    private final UserService userService;
    private final UserRepository userRepository;

    public CustomOidcUserService(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {

        OidcUser oidcUser = super.loadUser(userRequest);


        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId = oidcUser.getSubject();
        String username = oidcUser.getGivenName();
        String avatar = oidcUser.getPicture();

        User user = userRepository.findByProviderAndProviderId(provider, providerId).orElse(
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

        return new CustomOidcUser(authorities, oidcUser.getIdToken(), user);
    }



}

