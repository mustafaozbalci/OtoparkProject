package PARK.service;

import PARK.dto.ParkingSpaceDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ParkingSpaceService {
    ResponseEntity<List<ParkingSpaceDto>> getParkingSpacesByOtoparkId(Long otoparkId);
}
