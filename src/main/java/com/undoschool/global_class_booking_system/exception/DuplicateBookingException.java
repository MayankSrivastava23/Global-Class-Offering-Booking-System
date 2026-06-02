package com.undoschool.global_class_booking_system.exception;

public class DuplicateBookingException extends RuntimeException {

    public DuplicateBookingException(String message) {
        super(message);
    }
}