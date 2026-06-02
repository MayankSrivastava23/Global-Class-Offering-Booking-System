package com.undoschool.global_class_booking_system.dto.request;

import lombok.Data;

@Data
public class CreateSessionRequest {
    private String startTime;
    private String endTime;
    private String timezone;
}
