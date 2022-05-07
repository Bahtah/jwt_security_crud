package ru.surantaev.jwt_security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {

    private String email;
    private String password;
}
