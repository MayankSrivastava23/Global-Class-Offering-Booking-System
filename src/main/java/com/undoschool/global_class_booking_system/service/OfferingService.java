package com.undoschool.global_class_booking_system.service;

import com.undoschool.global_class_booking_system.dto.request.CreateOfferingRequest;
import com.undoschool.global_class_booking_system.dto.response.OfferingResponse;
import java.util.List;

public interface OfferingService {
    OfferingResponse createOffering(CreateOfferingRequest request);
    List<OfferingResponse> getOfferingsByTeacher(Long teacherId);
    OfferingResponse getOfferingById(Long id);
    List<OfferingResponse> getAllOfferings();
}