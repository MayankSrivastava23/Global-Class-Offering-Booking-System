package com.undoschool.global_class_booking_system.repository;

import com.undoschool.global_class_booking_system.entity.Booking;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByParentId(Long parentId);
    List<Booking> findByOfferingId(Long offeringId);
    Optional<Booking> findByParentIdAndOfferingId(Long parentId, Long offeringId);
    boolean existsByParentIdAndOfferingId(Long parentId, Long offeringId);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT b FROM Booking b WHERE b.parent.id = :parentId")
    List<Booking> lockBookingsByParentId(@Param("parentId") Long parentId);
}