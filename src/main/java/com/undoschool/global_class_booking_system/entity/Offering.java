package com.undoschool.global_class_booking_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "offerings")
@Data
@EqualsAndHashCode(callSuper = true, of = "id")
@ToString(exclude = {"teacher", "course", "sessions", "bookings"})
public class Offering extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @JsonIgnore
    @OneToMany(mappedBy = "offering", cascade = CascadeType.ALL)
    private List<Session> sessions;

    @JsonIgnore
    @OneToMany(mappedBy = "offering")
    private List<Booking> bookings;
}