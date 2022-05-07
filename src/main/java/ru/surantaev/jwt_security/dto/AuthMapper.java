package ru.surantaev.jwt_security.dto;

import org.springframework.stereotype.Component;
import ru.surantaev.jwt_security.entity.Role;
import ru.surantaev.jwt_security.entity.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class AuthMapper {

    public AuthResponce view(String token, String message, User user) {

        var authResponce = new AuthResponce();
        if (user != null) {
            setAuthority(authResponce,user.getRoles());
        }
        authResponce.setJwtToken(token);
        authResponce.setMessage(message);
        return authResponce;
    }

    private void setAuthority(AuthResponce authResponce, List<Role> roles) {
        Set<String> authorities = new HashSet<>();
        for(Role role : roles) {
            authorities.add(role.getName());
        }
        authResponce.setAuthorities(authorities);
    }

}
