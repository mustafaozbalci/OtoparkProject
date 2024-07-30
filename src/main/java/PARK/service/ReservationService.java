package PARK.service;

import PARK.dto.ReservationDto;
import PARK.request.ReservationRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReservationService {
    ResponseEntity<ReservationDto> createReservation(ReservationRequest reservationRequest);

    ResponseEntity<List<ReservationDto>> getReservationsByUserId(Long userId);

    ResponseEntity<ReservationDto> endReservation(Long reservationId);

    ResponseEntity<List<ReservationDto>> getPastReservationsByUserId(Long userId);

    ResponseEntity<List<ReservationDto>> getReservationsByOtoparkId(Long otoparkId);

    ResponseEntity<List<ReservationDto>> getReservationsByOwnerId(Long ownerId);
}
