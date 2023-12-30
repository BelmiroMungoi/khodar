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
@Table(name = "tb_events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime date;
    private String start_time;
    private String end_time;
    private int eventLimit;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

    @OneToMany(mappedBy = "event")
    private List<Ticket> tickets;
}
