package com.undoschool.global_class_booking_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "teachers")
@Data
@EqualsAndHashCode(callSuper = true, of = "id")
@ToString(exclude = "offerings")
public class Teacher extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String timezone;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private List<Offering> offerings;
}