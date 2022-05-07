package ru.surantaev.jwt_security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.surantaev.jwt_security.dto.UserRequest;
import ru.surantaev.jwt_security.dto.UserResponce;
import ru.surantaev.jwt_security.entity.User;
import ru.surantaev.jwt_security.repo.UserRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException("User with email not found"));
    }

    public UserResponce create(UserRequest userRequest) {
        User user = mapToEntity(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setActive(true);
        userRepository.save(user);
        return mapToResponce(user);
    }

    public User mapToEntity(UserRequest userRequest) {
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setCreated(LocalDateTime.now());
        return user;
    }

    public UserResponce mapToResponce(User user) {
        return UserResponce.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .isActive(user.isActive())
                .created(user.getCreated())
                .build();
    }
}
