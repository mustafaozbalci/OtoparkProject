package PARK.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSpaceDto {
    private Long id;
    private boolean available;
    private Long otoparkId;
}
