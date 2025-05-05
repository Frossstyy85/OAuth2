package org.example.springweb.service.oauth2;

import org.example.springweb.service.UserProvider;
import org.example.springweb.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User extends DefaultOAuth2User implements UserProvider {

    private final User user;

    @Override
    public User getUser() {
        return user;
    }

    public CustomOAuth2User(Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes, String nameAttributeKey, User user) {
        super(authorities, attributes, nameAttributeKey);
        this.user = user;
    }

}
