package ru.surantaev.jwt_security.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserResponce {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isActive;
    private LocalDateTime created;
}
