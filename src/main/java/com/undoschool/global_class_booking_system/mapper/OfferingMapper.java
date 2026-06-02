package com.undoschool.global_class_booking_system.mapper;

import com.undoschool.global_class_booking_system.dto.response.OfferingResponse;
import com.undoschool.global_class_booking_system.dto.response.SessionResponse;
import com.undoschool.global_class_booking_system.entity.Offering;
import com.undoschool.global_class_booking_system.entity.Session;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

public class OfferingMapper {
    public static OfferingResponse toResponse(Offering offering, String viewerTimezone) {
        if (offering == null) {
            return null;
        }

        OfferingResponse response = new OfferingResponse();
        response.setId(offering.getId());
        response.setName(offering.getName());

        if (offering.getCourse() != null) {
            response.setCourseId(offering.getCourse().getId());
            response.setCourseName(offering.getCourse().getTitle());
        }

        if (offering.getTeacher() != null) {
            response.setTeacherId(offering.getTeacher().getId());
            response.setTeacherName(offering.getTeacher().getName());
        }

        if (offering.getSessions() != null && !offering.getSessions().isEmpty()) {
            List<SessionResponse> sessionResponses = offering.getSessions()
                    .stream()
                    .map(session -> toSessionResponse(session, viewerTimezone))
                    .collect(Collectors.toList());
            response.setSessions(sessionResponses);
        }
        return response;
    }

    private static SessionResponse toSessionResponse(
            Session session,
            String viewerTimezone
    ) {
        SessionResponse res = new SessionResponse();
        res.setId(session.getId());
        res.setOfferingId(session.getOffering().getId());
        res.setStartTime(session.getStartTime().atZone(ZoneId.of(viewerTimezone)));
        res.setEndTime(session.getEndTime().atZone(ZoneId.of(viewerTimezone)));
        return res;
    }
}