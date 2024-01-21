package com.bbm.khodar.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_communities")
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String website;
    @Column(nullable = false)
    private String password;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "community")
    private List<Event> events;
}
