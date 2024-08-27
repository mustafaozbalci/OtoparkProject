package PARK.service.impl;

import PARK.dto.ParkingSpaceDto;
import PARK.entity.ParkingSpace;
import PARK.mapper.ParkingSpaceMapper;
import PARK.repository.ParkingSpaceRepository;
import PARK.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSpaceServiceImpl implements ParkingSpaceService {

    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;

    @Autowired
    private ParkingSpaceMapper parkingSpaceMapper;

    /**
     * Retrieves parking spaces by otopark ID.
     */
    @Override
    public ResponseEntity<List<ParkingSpaceDto>> getParkingSpacesByOtoparkId(Long otoparkId) {
        List<ParkingSpace> parkingSpaces = parkingSpaceRepository.findByOtopark_OtoparkId(otoparkId);
        if (parkingSpaces.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<ParkingSpaceDto> parkingSpaceDtos = parkingSpaces.stream().map(parkingSpaceMapper::toDto).toList();
        return new ResponseEntity<>(parkingSpaceDtos, HttpStatus.OK);
    }
}
