package com.undoschool.global_class_booking_system.service;

import com.undoschool.global_class_booking_system.dto.request.CreateBulkSessionRequest;
import com.undoschool.global_class_booking_system.dto.response.SessionResponse;
import java.util.List;

public interface SessionService {
    List<SessionResponse> createSessions(Long offeringId, CreateBulkSessionRequest request);
}
