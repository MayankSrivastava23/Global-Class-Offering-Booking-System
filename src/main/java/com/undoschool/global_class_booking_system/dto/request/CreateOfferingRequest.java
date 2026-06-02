package com.undoschool.global_class_booking_system.dto.request;

import lombok.Data;

@Data
public class CreateOfferingRequest {
    private Long courseId;
    private Long teacherId;
    private String name;
}