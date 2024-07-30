package PARK.request;

import PARK.entity.ParkingSpace;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtoparkWithAvailableSpacesRequest {
    private Long otoparkId;
    private String name;
    private List<ParkingSpace> availableSpaces;
}
