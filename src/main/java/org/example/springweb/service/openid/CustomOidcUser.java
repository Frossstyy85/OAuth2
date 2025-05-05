package org.example.springweb.service.openid;

import org.example.springweb.service.UserProvider;
import org.example.springweb.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import java.util.Collection;

public class CustomOidcUser extends DefaultOidcUser implements UserProvider {


    private final User user;

    @Override
    public User getUser() {
        return user;
    }

    public CustomOidcUser(Collection<? extends GrantedAuthority> authorities, OidcIdToken idToken, User user) {
        super(authorities, idToken);
        this.user = user;
    }

}