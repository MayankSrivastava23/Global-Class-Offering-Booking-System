package com.undoschool.global_class_booking_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "courses")
@Data
@EqualsAndHashCode(callSuper = true, of = "id")
@ToString(exclude = "offerings")
public class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private List<Offering> offerings;
}
