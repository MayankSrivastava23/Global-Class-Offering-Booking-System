package com.undoschool.global_class_booking_system.service.impl;

import com.undoschool.global_class_booking_system.dto.request.BookOfferingRequest;
import com.undoschool.global_class_booking_system.entity.Booking;
import com.undoschool.global_class_booking_system.entity.Offering;
import com.undoschool.global_class_booking_system.entity.Parent;
import com.undoschool.global_class_booking_system.entity.Session;
import com.undoschool.global_class_booking_system.exception.BookingConflictException;
import com.undoschool.global_class_booking_system.exception.ResourceNotFoundException;
import com.undoschool.global_class_booking_system.repository.BookingRepository;
import com.undoschool.global_class_booking_system.repository.OfferingRepository;
import com.undoschool.global_class_booking_system.repository.ParentRepository;
import com.undoschool.global_class_booking_system.repository.SessionRepository;
import com.undoschool.global_class_booking_system.service.BookingService;
import com.undoschool.global_class_booking_system.util.BookingConflictUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final ParentRepository parentRepository;
    private final OfferingRepository offeringRepository;
    private final SessionRepository sessionRepository;

    public BookingServiceImpl(BookingRepository bookingRepository,
                              ParentRepository parentRepository,
                              OfferingRepository offeringRepository,
                              SessionRepository sessionRepository) {
        this.bookingRepository = bookingRepository;
        this.parentRepository = parentRepository;
        this.offeringRepository = offeringRepository;
        this.sessionRepository = sessionRepository;
    }

    @Transactional
    @Override
    public Booking bookOffering(BookOfferingRequest request) {
        bookingRepository.lockBookingsByParentId(
                request.getParentId()
        );
        Parent parent = parentRepository.findById(
                request.getParentId()
        ).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Parent not found"
                ));
        Offering newOffering = offeringRepository.findById(
                request.getOfferingId()
        ).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Offering not found"
                ));
        if (bookingRepository.existsByParentIdAndOfferingId(
                parent.getId(),
                newOffering.getId())) {
            throw new BookingConflictException(
                    "Parent has already booked this offering"
            );
        }
        List<Session> newSessions =
                sessionRepository.findByOfferingId(
                        newOffering.getId()
                );
        List<Booking> existingBookings =
                bookingRepository.findByParentId(
                        parent.getId()
                );

        for (Booking booking : existingBookings) {
            List<Session> existingSessions =
                    sessionRepository.findByOfferingId(
                            booking.getOffering().getId()
                    );

            for (Session existingSession : existingSessions) {

                for (Session newSession : newSessions) {

                    if (BookingConflictUtil.isOverlapping(
                            existingSession.getStartTime(),
                            existingSession.getEndTime(),
                            newSession.getStartTime(),
                            newSession.getEndTime())) {

                        throw new BookingConflictException(
                                "Booking conflict detected with existing sessions"
                        );
                    }
                }
            }
        }

        Booking booking = new Booking();
        booking.setParent(parent);
        booking.setOffering(newOffering);
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByParent(Long parentId) {
        return bookingRepository.findByParentId(parentId);
    }
}
