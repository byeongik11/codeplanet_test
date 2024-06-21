package com.sparta.codeplanet.product.entity;

import com.sparta.codeplanet.global.enums.Status;
import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Company extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String domain;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private User user; // 회사와 연관된 사용자

}
