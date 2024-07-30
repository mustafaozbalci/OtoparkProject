package PARK.controller;

import PARK.dto.ReservationDto;
import PARK.request.ReservationRequest;
import PARK.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationRequest reservationRequest) {
        return reservationService.createReservation(reservationRequest);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReservationDto>> getReservationsByUserId(@PathVariable Long userId) {
        return reservationService.getReservationsByUserId(userId);
    }

    @PutMapping("/end/{reservationId}")
    public ResponseEntity<ReservationDto> endReservation(@PathVariable Long reservationId) {
        return reservationService.endReservation(reservationId);
    }

    @GetMapping("/user/{userId}/past")
    public ResponseEntity<List<ReservationDto>> getPastReservationsByUserId(@PathVariable Long userId) {
        return reservationService.getPastReservationsByUserId(userId);
    }

    @GetMapping("/otopark/{otoparkId}")
    public ResponseEntity<List<ReservationDto>> getReservationsByOtoparkId(@PathVariable Long otoparkId) {
        return reservationService.getReservationsByOtoparkId(otoparkId);
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<ReservationDto>> getReservationsByOwnerId(@PathVariable Long ownerId) {
        return reservationService.getReservationsByOwnerId(ownerId);
    }
}
