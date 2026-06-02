package com.undoschool.global_class_booking_system.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class OfferingResponse {
    private Long id;
    private String name;
    private Long courseId;
    private String courseName;
    private Long teacherId;
    private String teacherName;
    private List<SessionResponse> sessions;
}