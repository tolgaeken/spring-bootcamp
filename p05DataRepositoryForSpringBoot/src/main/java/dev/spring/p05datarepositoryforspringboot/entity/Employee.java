package dev.spring.p05datarepositoryforspringboot.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@EqualsAndHashCode
//@ToString
//@Value
@Data // --> @RequiredArgsConstructor, @Getter, @Setter, @EqualsAndHashCode , @ToString
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "fullname")
    private String fullName;
    private int age;
    private double salary;

}
