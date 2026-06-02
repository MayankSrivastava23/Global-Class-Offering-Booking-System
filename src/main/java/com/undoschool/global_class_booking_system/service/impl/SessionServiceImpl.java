package com.undoschool.global_class_booking_system.service.impl;

import com.undoschool.global_class_booking_system.dto.request.CreateBulkSessionRequest;
import com.undoschool.global_class_booking_system.dto.request.CreateSessionRequest;
import com.undoschool.global_class_booking_system.dto.response.SessionResponse;
import com.undoschool.global_class_booking_system.entity.Offering;
import com.undoschool.global_class_booking_system.entity.Session;
import com.undoschool.global_class_booking_system.exception.ResourceNotFoundException;
import com.undoschool.global_class_booking_system.mapper.SessionMapper;
import com.undoschool.global_class_booking_system.repository.OfferingRepository;
import com.undoschool.global_class_booking_system.repository.SessionRepository;
import com.undoschool.global_class_booking_system.service.SessionService;
import com.undoschool.global_class_booking_system.util.SessionValidationUtil;
import com.undoschool.global_class_booking_system.util.TimezoneUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;
    private final OfferingRepository offeringRepository;

    public SessionServiceImpl(SessionRepository sessionRepository,
                              OfferingRepository offeringRepository) {
        this.sessionRepository = sessionRepository;
        this.offeringRepository = offeringRepository;
    }

    @Override
    @Transactional
    public List<SessionResponse> createSessions(
            Long offeringId,
            CreateBulkSessionRequest request
    ) {

        Offering offering = offeringRepository.findById(offeringId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Offering not found"
                        ));

        List<Session> sessions = new ArrayList<>();

        for (CreateSessionRequest req : request.getSessions()) {
            LocalDateTime startLocal = LocalDateTime.parse(req.getStartTime());
            LocalDateTime endLocal = LocalDateTime.parse(req.getEndTime());
            Instant startUtc = TimezoneUtil.toUtc(startLocal, request.getTimezone());
            Instant endUtc = TimezoneUtil.toUtc(endLocal, request.getTimezone());
            SessionValidationUtil.validateSession(startUtc, endUtc);
            Session session = new Session();
            session.setOffering(offering);
            session.setStartTime(startUtc);
            session.setEndTime(endUtc);
            sessions.add(session);
        }
        List<Session> savedSessions = sessionRepository.saveAll(sessions);

        return savedSessions.stream()
                .map(session ->
                        SessionMapper.toResponse(
                                session,
                                request.getTimezone()
                        ))
                .toList();
    }
}