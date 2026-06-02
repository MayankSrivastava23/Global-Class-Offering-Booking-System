package com.undoschool.global_class_booking_system.util;

import java.time.Instant;

public class BookingConflictUtil {

    public static boolean isOverlapping(
            Instant existingStart,
            Instant existingEnd,
            Instant newStart,
            Instant newEnd
    ) {

        if (existingStart == null || existingEnd == null
                || newStart == null || newEnd == null) {
            throw new IllegalArgumentException("Invalid session times");
        }

        return existingStart.isBefore(newEnd)
                && existingEnd.isAfter(newStart);
    }
}