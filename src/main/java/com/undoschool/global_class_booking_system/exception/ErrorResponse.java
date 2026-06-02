package com.undoschool.global_class_booking_system.exception;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.Instant;
@Data
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private Integer status;
    private Instant timestamp;
}