package PARK.controller;

import PARK.dto.OtoparkDto;
import PARK.request.OtoparkWithAvailableSpacesRequest;
import PARK.service.OtoparkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/otoparks")
public class OtoparkController {

    @Autowired
    private OtoparkService otoparkService;

    @GetMapping
    public ResponseEntity<List<OtoparkWithAvailableSpacesRequest>> getAllOtoparks() {
        return otoparkService.getAllOtoparksWithAvailableSpaces();
    }

    @GetMapping("/available-spaces")
    public ResponseEntity<List<OtoparkWithAvailableSpacesRequest>> getAvailableSpacesForAllOtoparks() {
        return otoparkService.getAvailableSpacesForAllOtoparks();
    }

    @PostMapping("/add")
    public ResponseEntity<OtoparkDto> addOtopark(@RequestBody OtoparkDto otoparkDto) {
        return otoparkService.addOtopark(otoparkDto);
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<OtoparkDto>> getOtoparksByOwner(@PathVariable Long ownerId) {
        return otoparkService.getOtoparksByOwner(ownerId);
    }

    @PutMapping("/update/{otoparkId}")
    public ResponseEntity<OtoparkDto> updateOtopark(@PathVariable Long otoparkId, @RequestBody OtoparkDto otoparkDto) {
        return otoparkService.updateOtopark(otoparkId, otoparkDto);
    }
}
