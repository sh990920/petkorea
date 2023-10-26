package com.project.petkorea.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "Board")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncrement 를 설정
    private Long idx;

    @Column(length = 50, nullable = false)
    private String writer;

    @Column(length = 20, nullable = false)
    private String title;

    @Column(length = 255, nullable = false)
    private String content;

    @Column(length = 10, nullable = false)
    private String writeDate;
}
