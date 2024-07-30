package PARK.repository;

import PARK.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserUserIdAndExitTimeIsNotNullOrderByExitTimeDesc(Long userId);

    List<Reservation> findByUserUserIdAndExitTimeIsNull(Long userId);

    List<Reservation> findByOtoparkOtoparkId(Long otoparkId);

    List<Reservation> findByOtoparkOwnerUserId(Long ownerId);
}
