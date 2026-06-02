package com.undoschool.global_class_booking_system.dto.request;

import lombok.Data;

@Data
public class CreateTeacherRequest {
    private String name;
    private String timezone;
}
