package PARK.service.impl;

import PARK.dto.ReservationDto;
import PARK.entity.Otopark;
import PARK.entity.ParkingSpace;
import PARK.entity.Reservation;
import PARK.entity.User;
import PARK.mapper.ReservationMapper;
import PARK.repository.OtoparkRepository;
import PARK.repository.ParkingSpaceRepository;
import PARK.repository.ReservationRepository;
import PARK.repository.UserRepository;
import PARK.request.ReservationRequest;
import PARK.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;

    @Autowired
    private OtoparkRepository otoparkRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationMapper reservationMapper;

    /**
     * Creates a new reservation.
     */
    @Override
    public ResponseEntity<ReservationDto> createReservation(ReservationRequest reservationRequest) {
        if (reservationRequest.getOtoparkId() == null || reservationRequest.getUserId() == null || reservationRequest.getParkingSpaceId() == null || reservationRequest.getEntryTime() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Otopark otopark = otoparkRepository.findById(reservationRequest.getOtoparkId()).orElseThrow(() -> new RuntimeException("Otopark bulunamadı"));
        User user = userRepository.findById(reservationRequest.getUserId()).orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
        ParkingSpace parkingSpace = parkingSpaceRepository.findById(reservationRequest.getParkingSpaceId()).orElseThrow(() -> new RuntimeException("Park alanı bulunamadı"));

        if (!parkingSpace.isAvailable()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        Reservation reservation = new Reservation();
        reservation.setOtopark(otopark);
        reservation.setUser(user);
        reservation.setParkingSpace(parkingSpace);
        reservation.setEntryTime(reservationRequest.getEntryTime());

        // Update parking space availability
        parkingSpace.setAvailable(false);
        parkingSpaceRepository.save(parkingSpace);

        // Save reservation
        Reservation savedReservation = reservationRepository.save(reservation);
        return ResponseEntity.ok(reservationMapper.toDto(savedReservation));
    }

    /**
     * Retrieves ongoing reservations by user ID.
     */
    @Override
    public ResponseEntity<List<ReservationDto>> getReservationsByUserId(Long userId) {
        List<Reservation> reservations = reservationRepository.findByUserUserIdAndExitTimeIsNull(userId);
        List<ReservationDto> reservationDtos = reservations.stream().map(reservationMapper::toDto).toList();
        return ResponseEntity.ok(reservationDtos);
    }

    /**
     * Ends a reservation and calculates the total fee.
     */
    @Override
    public ResponseEntity<ReservationDto> endReservation(Long reservationId) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(reservationId);
        if (reservationOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Reservation reservation = reservationOpt.get();
        reservation.setExitTime(LocalDateTime.now());
        reservation.getParkingSpace().setAvailable(true);

        Duration duration = Duration.between(reservation.getEntryTime(), reservation.getExitTime());
        long minutes = duration.toMinutes();
        double totalFee = calculateTotalFee(reservation.getOtopark().getPricingSchedule(), minutes);

        reservation.setTotalFee(totalFee);

        Reservation updatedReservation = reservationRepository.save(reservation);
        return ResponseEntity.ok(reservationMapper.toDto(updatedReservation));
    }

    private double calculateTotalFee(String pricingSchedule, long minutes) {
        switch (pricingSchedule) {
            case "Flat Rate":
                return 20.0; // Flat fee
            case "Hourly Rate":
                return (minutes / 60.0) * 5.0; // Hourly fee: $5 per hour
            case "Peak Time Rate":
                // Peak time rate: $10 from 07:00-19:00, $5 otherwise
                LocalDateTime now = LocalDateTime.now();
                int hour = now.getHour();
                if (hour >= 7 && hour < 19) {
                    return (minutes / 60.0) * 10.0;
                } else {
                    return (minutes / 60.0) * 5.0;
                }
            default:
                return 0.0; // Default to 0 for invalid pricing schedule
        }
    }

    /**
     * Retrieves past reservations by user ID.
     */
    @Override
    public ResponseEntity<List<ReservationDto>> getPastReservationsByUserId(Long userId) {
        List<Reservation> reservations = reservationRepository.findByUserUserIdAndExitTimeIsNotNullOrderByExitTimeDesc(userId);
        List<ReservationDto> reservationDtos = reservations.stream().map(reservationMapper::toDto).toList();
        return ResponseEntity.ok(reservationDtos);
    }

    /**
     * Retrieves reservations by otopark ID.
     */
    @Override
    public ResponseEntity<List<ReservationDto>> getReservationsByOtoparkId(Long otoparkId) {
        List<Reservation> reservations = reservationRepository.findByOtoparkOtoparkId(otoparkId);
        List<ReservationDto> reservationDtos = reservations.stream().map(reservationMapper::toDto).toList();
        return ResponseEntity.ok(reservationDtos);
    }

    /**
     * Retrieves reservations by owner ID.
     */
    @Override
    public ResponseEntity<List<ReservationDto>> getReservationsByOwnerId(Long ownerId) {
        List<Reservation> reservations = reservationRepository.findByOtoparkOwnerUserId(ownerId);
        return ResponseEntity.ok(reservations.stream().map(reservationMapper::toDto).toList());
    }
}
