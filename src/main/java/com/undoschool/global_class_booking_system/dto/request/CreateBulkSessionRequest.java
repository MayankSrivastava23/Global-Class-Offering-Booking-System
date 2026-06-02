package com.undoschool.global_class_booking_system.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class CreateBulkSessionRequest {
    private String timezone;
    private List<CreateSessionRequest> sessions;
}