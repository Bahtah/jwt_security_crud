package ru.surantaev.jwt_security.entity.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teacher_base", schema = "jwt_sec", catalog = "postgres")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "teacher_fName")
    private String firstName;

    @Column(name = "teacher_lName")
    private String lastName;

    @Column(name = "teacher_email", unique = true)
    private String email;

    //Связь с таблицой Course
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

}
