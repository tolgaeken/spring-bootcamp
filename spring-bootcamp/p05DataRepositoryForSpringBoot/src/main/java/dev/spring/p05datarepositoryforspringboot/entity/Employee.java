package dev.spring.p05datarepositoryforspringboot.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@EqualsAndHashCode
//@ToString

//@Value
@Data // --> RequiredArgsConstructor, Getter, Setter, EqualsAndHashCode, ToString
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

//https://www.mockaroo.com/
