package com.undoschool.global_class_booking_system.controller;

import com.undoschool.global_class_booking_system.dto.request.BookOfferingRequest;
import com.undoschool.global_class_booking_system.dto.response.ApiResponse;
import com.undoschool.global_class_booking_system.dto.response.OfferingResponse;
import com.undoschool.global_class_booking_system.entity.Booking;
import com.undoschool.global_class_booking_system.service.BookingService;
import com.undoschool.global_class_booking_system.service.OfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parents")
@RequiredArgsConstructor
public class ParentController {

    private final BookingService bookingService;
    private final OfferingService offeringService;

    @GetMapping("/offerings")
    public ApiResponse<List<OfferingResponse>> getAllOfferings() {

        return new ApiResponse<>(
                true,
                "Offerings fetched successfully",
                offeringService.getAllOfferings()
        );
    }

    @PostMapping("/bookings")
    public ApiResponse<Booking> bookOffering(
            @RequestBody BookOfferingRequest request) {

        return new ApiResponse<>(
                true,
                "Booking successful",
                bookingService.bookOffering(request)
        );
    }

    @GetMapping("/{parentId}/bookings")
    public ApiResponse<List<Booking>> getBookings(
            @PathVariable Long parentId) {

        return new ApiResponse<>(
                true,
                "Bookings fetched successfully",
                bookingService.getBookingsByParent(parentId)
        );
    }
}