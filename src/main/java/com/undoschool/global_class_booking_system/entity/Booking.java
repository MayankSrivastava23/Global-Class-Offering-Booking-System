package com.undoschool.global_class_booking_system.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(
        name = "bookings",
        uniqueConstraints = {@UniqueConstraint(name = "uk_parent_offering", columnNames = {"parent_id", "offering_id"})
        }
)
@Data
@EqualsAndHashCode(callSuper = true, of = "id")
@ToString(exclude = {"parent", "offering"})
public class Booking extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", nullable = false)
    private Parent parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offering_id", nullable = false)
    private Offering offering;
}
