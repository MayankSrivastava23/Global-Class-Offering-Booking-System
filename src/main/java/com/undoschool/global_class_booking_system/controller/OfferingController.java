package com.undoschool.global_class_booking_system.controller;

import com.undoschool.global_class_booking_system.dto.response.ApiResponse;
import com.undoschool.global_class_booking_system.dto.response.OfferingResponse;
import com.undoschool.global_class_booking_system.service.OfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/offerings")
@RequiredArgsConstructor
public class OfferingController {

    private final OfferingService offeringService;

    @GetMapping("/{id}")
    public ApiResponse<OfferingResponse> getOffering(@PathVariable Long id) {
        return new ApiResponse<>(
                true,
                "Offering fetched successfully",
                offeringService.getOfferingById(id)
        );
    }
}