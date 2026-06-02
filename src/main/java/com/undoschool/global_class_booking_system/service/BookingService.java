package com.undoschool.global_class_booking_system.service;


import com.undoschool.global_class_booking_system.dto.request.BookOfferingRequest;
import com.undoschool.global_class_booking_system.entity.Booking;
import java.util.List;

public interface BookingService {
    Booking bookOffering(BookOfferingRequest request);
    List<Booking> getBookingsByParent(Long parentId);
}