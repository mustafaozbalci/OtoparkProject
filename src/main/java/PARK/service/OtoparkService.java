package PARK.service;

import PARK.dto.OtoparkDto;
import PARK.request.OtoparkWithAvailableSpacesRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OtoparkService {
    ResponseEntity<OtoparkDto> addOtopark(OtoparkDto otoparkDto);

    ResponseEntity<List<OtoparkWithAvailableSpacesRequest>> getAllOtoparksWithAvailableSpaces();

    ResponseEntity<List<OtoparkWithAvailableSpacesRequest>> getAvailableSpacesForAllOtoparks();

    ResponseEntity<List<OtoparkDto>> getOtoparksByOwner(Long ownerId);

    ResponseEntity<OtoparkDto> updateOtopark(Long otoparkId, OtoparkDto otoparkDto);

}
