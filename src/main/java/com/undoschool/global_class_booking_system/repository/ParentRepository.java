package com.undoschool.global_class_booking_system.repository;

import com.undoschool.global_class_booking_system.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
}
