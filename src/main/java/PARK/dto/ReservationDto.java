package PARK.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {
    private Long reservationId;
    private Long userId;
    private String username;
    private Long otoparkId;
    private String otoparkName;
    private Long parkingSpaceId;
    private String parkingSpaceName;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private Double totalFee;
}
