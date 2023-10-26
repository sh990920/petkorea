package com.project.petkorea.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "Member")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncrement 를 설정
    private Long idx;

    @Column(length = 50, unique = true, nullable = false)
    private String id;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private String rollName;

}
