package PARK.repository;

import PARK.entity.Otopark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OtoparkRepository extends JpaRepository<Otopark, Long> {
    Otopark findByOtoparkId(Long otoparkId);

    List<Otopark> findByOwner_UserId(Long ownerId);
}
