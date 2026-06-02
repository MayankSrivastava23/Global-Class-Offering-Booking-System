package com.undoschool.global_class_booking_system.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimezoneUtil {

    public static Instant toUtc(LocalDateTime localDateTime, String timezone) {

        if (localDateTime == null || timezone == null) {
            throw new IllegalArgumentException("Invalid datetime or timezone");
        }

        return localDateTime
                .atZone(ZoneId.of(timezone))
                .toInstant();
    }

    public static ZonedDateTime fromUtc(Instant instant, String timezone) {

        if (instant == null || timezone == null) {
            throw new IllegalArgumentException("Invalid instant or timezone");
        }

        return instant.atZone(ZoneId.of(timezone));
    }
}
