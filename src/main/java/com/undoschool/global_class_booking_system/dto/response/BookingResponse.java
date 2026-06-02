package com.undoschool.global_class_booking_system.dto.response;

import lombok.Data;

@Data
public class BookingResponse {
    private Long bookingId;
    private Long parentId;
    private Long offeringId;
    private String offeringName;
    private String message;
}