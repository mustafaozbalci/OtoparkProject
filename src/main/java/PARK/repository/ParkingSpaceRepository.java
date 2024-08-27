package PARK.repository;

import PARK.entity.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Long> {
    List<ParkingSpace> findByOtopark_OtoparkIdAndAvailableTrue(Long otoparkId);

    List<ParkingSpace> findByOtopark_OtoparkId(Long otoparkId);

    void deleteByIdIn(List<Long> ids);
}
