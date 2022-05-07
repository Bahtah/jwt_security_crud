package ru.surantaev.jwt_security.entity.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.surantaev.jwt_security.enam.StudyFormat;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_base", schema = "jwt_sec", catalog = "postgres")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_fName")
    private String firstName;

    @Column(name = "student_lName")
    private String lastName;

    @Column(name = "student_email", unique = true)
    private String email;

    @Column(name = "study_format")
    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;

    //Связь с таблицой Group
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;
}
