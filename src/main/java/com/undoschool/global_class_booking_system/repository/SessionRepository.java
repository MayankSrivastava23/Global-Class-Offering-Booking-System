package com.undoschool.global_class_booking_system.repository;

import com.undoschool.global_class_booking_system.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.Instant;
import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByOfferingId(Long offeringId);
    List<Session> findByStartTimeBetween(Instant start, Instant end);
}
