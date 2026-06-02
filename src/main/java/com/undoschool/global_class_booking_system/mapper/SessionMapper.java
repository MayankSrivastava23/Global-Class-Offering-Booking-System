package com.undoschool.global_class_booking_system.mapper;

import com.undoschool.global_class_booking_system.dto.response.SessionResponse;
import com.undoschool.global_class_booking_system.entity.Session;
import java.time.ZoneId;

public class SessionMapper {

    private SessionMapper() {}

    public static SessionResponse toResponse(Session session, String timezone) {
        SessionResponse response = new SessionResponse();
        response.setId(session.getId());
        response.setOfferingId(session.getOffering().getId());
        response.setStartTime(session.getStartTime().atZone(ZoneId.of(timezone)));
        response.setEndTime(session.getEndTime().atZone(ZoneId.of(timezone)));
        return response;
    }
}
