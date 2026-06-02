package com.undoschool.global_class_booking_system.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class TeacherOfferingResponse {
    private Long teacherId;
    private List<OfferingResponse> offerings;
}