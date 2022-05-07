package ru.surantaev.jwt_security.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles", schema = "jwt_sec", catalog = "postgres")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_generator")
    @SequenceGenerator(name = "role_generator", sequenceName = "role_sec", allocationSize = 1, schema = "jwt_sec", catalog = "postgres")
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
