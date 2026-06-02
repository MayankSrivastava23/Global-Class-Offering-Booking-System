package com.undoschool.global_class_booking_system.controller;

import com.undoschool.global_class_booking_system.dto.request.CreateBulkSessionRequest;
import com.undoschool.global_class_booking_system.dto.request.CreateCourseRequest;
import com.undoschool.global_class_booking_system.dto.request.CreateOfferingRequest;
import com.undoschool.global_class_booking_system.dto.response.ApiResponse;
import com.undoschool.global_class_booking_system.dto.response.OfferingResponse;
import com.undoschool.global_class_booking_system.dto.response.SessionResponse;
import com.undoschool.global_class_booking_system.entity.Course;
import com.undoschool.global_class_booking_system.repository.CourseRepository;
import com.undoschool.global_class_booking_system.service.OfferingService;
import com.undoschool.global_class_booking_system.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final OfferingService offeringService;
    private final SessionService sessionService;
    private final CourseRepository courseRepository;

    @PostMapping("/courses")
    public ApiResponse<Course> createCourse(@RequestBody CreateCourseRequest request) {
        Course course = new Course();
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        return new ApiResponse<>(
                true,
                "Course created successfully",
                courseRepository.save(course)
        );
    }

    @PostMapping("/offerings")
    public ApiResponse<String> createOffering(
            @RequestBody CreateOfferingRequest request) {
        offeringService.createOffering(request);
        return new ApiResponse<>(
                true,
                "Offering created successfully",
                "SUCCESS"
        );
    }

    @GetMapping("/{teacherId}/offerings")
    public ApiResponse<List<OfferingResponse>> getTeacherOfferings(
            @PathVariable Long teacherId) {
        return new ApiResponse<>(
                true,
                "Teacher offerings fetched",
                offeringService.getOfferingsByTeacher(teacherId)
        );
    }

    @PostMapping("/offerings/{offeringId}/sessions")
    public ApiResponse<List<SessionResponse>> addSessions(
            @PathVariable Long offeringId,
            @RequestBody CreateBulkSessionRequest request) {
        return new ApiResponse<>(
                true,
                "Sessions added successfully",
                sessionService.createSessions(offeringId, request)
        );
    }
}