package com.undoschool.global_class_booking_system.mapper;

import com.undoschool.global_class_booking_system.dto.response.BookingResponse;
import com.undoschool.global_class_booking_system.entity.Booking;

public class BookingMapper {
    public static BookingResponse toResponse(Booking booking) {
        BookingResponse response = new BookingResponse();
        response.setBookingId(booking.getId());
        response.setParentId(booking.getParent().getId());
        response.setOfferingId(booking.getOffering().getId());
        response.setOfferingName(booking.getOffering().getName());
        response.setMessage("Booking successful");
        return response;
    }
}
