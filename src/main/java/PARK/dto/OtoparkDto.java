package PARK.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtoparkDto {
    private Long otoparkId;
    private String name;
    private String address;
    private int capacity;
    private String pricingSchedule;
    private Long ownerId;
}
