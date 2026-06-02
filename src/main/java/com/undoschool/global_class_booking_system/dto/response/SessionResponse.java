package com.undoschool.global_class_booking_system.dto.response;

import lombok.Data;
import java.time.ZonedDateTime;

@Data
public class SessionResponse {
    private Long id;
    private Long offeringId;
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;
}