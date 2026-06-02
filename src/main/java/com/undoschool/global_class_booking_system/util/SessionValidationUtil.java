package com.undoschool.global_class_booking_system.util;

import java.time.Instant;

public class SessionValidationUtil {

    public static void validateSession(Instant start, Instant end) {

        if (start == null || end == null) {
            throw new IllegalArgumentException("Session time cannot be null");
        }

        if (!start.isBefore(end)) {
            throw new IllegalArgumentException(
                    "Session start time must be before end time"
            );
        }
    }
}