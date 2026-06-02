package com.undoschool.global_class_booking_system.service.impl;

import com.undoschool.global_class_booking_system.dto.request.CreateOfferingRequest;
import com.undoschool.global_class_booking_system.dto.response.OfferingResponse;
import com.undoschool.global_class_booking_system.entity.Course;
import com.undoschool.global_class_booking_system.entity.Offering;
import com.undoschool.global_class_booking_system.entity.Teacher;
import com.undoschool.global_class_booking_system.exception.ResourceNotFoundException;
import com.undoschool.global_class_booking_system.mapper.OfferingMapper;
import com.undoschool.global_class_booking_system.repository.CourseRepository;
import com.undoschool.global_class_booking_system.repository.OfferingRepository;
import com.undoschool.global_class_booking_system.repository.TeacherRepository;
import com.undoschool.global_class_booking_system.service.OfferingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferingServiceImpl implements OfferingService {

    private final OfferingRepository offeringRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    @Override
    @Transactional
    public OfferingResponse createOffering(CreateOfferingRequest request) {
        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Teacher not found"));
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found"));
        Offering offering = new Offering();
        offering.setName(request.getName());
        offering.setTeacher(teacher);
        offering.setCourse(course);
        Offering savedOffering = offeringRepository.save(offering);
        return OfferingMapper.toResponse(
                savedOffering,
                teacher.getTimezone()
        );
    }

    @Override
    public List<OfferingResponse> getOfferingsByTeacher(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Teacher not found"));
        return offeringRepository.findByTeacherId(teacherId)
                .stream()
                .map(offering ->
                        OfferingMapper.toResponse(
                                offering,
                                teacher.getTimezone()
                        ))
                .toList();
    }

    @Override
    public List<OfferingResponse> getAllOfferings() {
        return offeringRepository.findAll()
                .stream()
                .map(offering ->
                        OfferingMapper.toResponse(
                                offering,
                                offering.getTeacher().getTimezone()
                        ))
                .toList();
    }

    @Override
    public OfferingResponse getOfferingById(Long id) {
        Offering offering = offeringRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Offering not found"));
        return OfferingMapper.toResponse(
                offering,
                offering.getTeacher().getTimezone()
        );
    }
}