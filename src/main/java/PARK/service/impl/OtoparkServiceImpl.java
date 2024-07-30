package PARK.service.impl;

import PARK.dto.OtoparkDto;
import PARK.entity.Otopark;
import PARK.entity.ParkingSpace;
import PARK.mapper.OtoparkMapper;
import PARK.repository.OtoparkRepository;
import PARK.repository.ParkingSpaceRepository;
import PARK.request.OtoparkWithAvailableSpacesRequest;
import PARK.service.OtoparkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OtoparkServiceImpl implements OtoparkService {

    @Autowired
    private OtoparkRepository otoparkRepository;

    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;

    @Autowired
    private OtoparkMapper otoparkMapper;

    /**
     * Adds a new otopark.
     */
    @Override
    public ResponseEntity<OtoparkDto> addOtopark(OtoparkDto otoparkDto) {
        Otopark otopark = otoparkMapper.toEntity(otoparkDto);
        Otopark savedOtopark = otoparkRepository.save(otopark);
        List<ParkingSpace> parkingSpaces = new ArrayList<>();
        for (int i = 0; i < savedOtopark.getCapacity(); i++) {
            ParkingSpace parkingSpace = new ParkingSpace();
            parkingSpace.setOtopark(savedOtopark);
            parkingSpace.setAvailable(true);
            parkingSpaces.add(parkingSpace);
        }
        parkingSpaceRepository.saveAll(parkingSpaces);
        return ResponseEntity.ok(otoparkMapper.toDto(savedOtopark));
    }

    /**
     * Retrieves all otoparks with available spaces.
     */
    @Override
    public ResponseEntity<List<OtoparkWithAvailableSpacesRequest>> getAllOtoparksWithAvailableSpaces() {
        List<Otopark> otoparks = otoparkRepository.findAll();
        List<OtoparkWithAvailableSpacesRequest> otoparksWithSpaces = otoparks.stream().map(otopark -> new OtoparkWithAvailableSpacesRequest(otopark.getOtoparkId(), otopark.getName(), otopark.getParkingSpaces().stream().filter(ParkingSpace::isAvailable).collect(Collectors.toList()))).collect(Collectors.toList());
        return ResponseEntity.ok(otoparksWithSpaces);
    }

    /**
     * Retrieves available spaces for all otoparks.
     */
    @Override
    public ResponseEntity<List<OtoparkWithAvailableSpacesRequest>> getAvailableSpacesForAllOtoparks() {
        List<Otopark> otoparks = otoparkRepository.findAll();
        List<OtoparkWithAvailableSpacesRequest> otoparksWithSpaces = otoparks.stream().map(otopark -> new OtoparkWithAvailableSpacesRequest(otopark.getOtoparkId(), otopark.getName(), otopark.getParkingSpaces().stream().filter(ParkingSpace::isAvailable).collect(Collectors.toList()))).collect(Collectors.toList());
        return ResponseEntity.ok(otoparksWithSpaces);
    }

    /**
     * Retrieves otoparks by owner ID.
     */
    @Override
    public ResponseEntity<List<OtoparkDto>> getOtoparksByOwner(Long ownerId) {
        List<Otopark> otoparks = otoparkRepository.findByOwner_UserId(ownerId);
        List<OtoparkDto> otoparkDtos = otoparks.stream().map(otoparkMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(otoparkDtos);
    }

    /**
     * Updates an existing otopark.
     */
    @Override
    @Transactional
    public ResponseEntity<OtoparkDto> updateOtopark(Long otoparkId, OtoparkDto otoparkDto) {
        Otopark existingOtopark = otoparkRepository.findById(otoparkId).orElseThrow(() -> new IllegalArgumentException("Otopark not found"));

        existingOtopark.setName(otoparkDto.getName());
        existingOtopark.setAddress(otoparkDto.getAddress());
        existingOtopark.setPricingSchedule(otoparkDto.getPricingSchedule());

        int currentCapacity = existingOtopark.getCapacity();
        int newCapacity = otoparkDto.getCapacity();

        if (newCapacity > currentCapacity) {
            List<ParkingSpace> newParkingSpaces = new ArrayList<>();
            for (int i = 0; i < newCapacity - currentCapacity; i++) {
                ParkingSpace parkingSpace = new ParkingSpace();
                parkingSpace.setOtopark(existingOtopark);
                parkingSpace.setAvailable(true);
                newParkingSpaces.add(parkingSpace);
            }
            parkingSpaceRepository.saveAll(newParkingSpaces);
        } else if (newCapacity < currentCapacity) {
            List<ParkingSpace> existingParkingSpaces = existingOtopark.getParkingSpaces();
            List<ParkingSpace> spacesToRemove = existingParkingSpaces.stream().filter(ParkingSpace::isAvailable).limit(currentCapacity - newCapacity).collect(Collectors.toList());

            if (spacesToRemove.size() < (currentCapacity - newCapacity)) {
                throw new IllegalStateException("Not enough available parking spaces to remove");
            }

            List<Long> idsToRemove = spacesToRemove.stream().map(ParkingSpace::getId).collect(Collectors.toList());

            parkingSpaceRepository.deleteByIdIn(idsToRemove);
            existingParkingSpaces.removeAll(spacesToRemove);
        }

        existingOtopark.setCapacity(newCapacity);

        Otopark updatedOtopark = otoparkRepository.save(existingOtopark);
        return ResponseEntity.ok(otoparkMapper.toDto(updatedOtopark));
    }
}
