CREATE INDEX idx_booking_parent
ON bookings(parent_id);

CREATE INDEX idx_booking_offering
ON bookings(offering_id);

CREATE INDEX idx_session_offering
ON sessions(offering_id);

CREATE INDEX idx_session_start_time
ON sessions(start_time);