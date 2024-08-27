package PARK.controller;

import PARK.dto.ParkingSpaceDto;
import PARK.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/parking-spaces")
public class ParkingSpaceController {

    @Autowired
    private ParkingSpaceService parkingSpaceService;

    @GetMapping("/otopark/{otoparkId}")
    public ResponseEntity<List<ParkingSpaceDto>> getParkingSpacesByOtoparkId(@PathVariable Long otoparkId) {
        return parkingSpaceService.getParkingSpacesByOtoparkId(otoparkId);
    }
}
