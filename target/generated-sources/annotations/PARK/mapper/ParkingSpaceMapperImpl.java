package PARK.mapper;

import PARK.dto.ParkingSpaceDto;
import PARK.entity.Otopark;
import PARK.entity.ParkingSpace;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-30T18:35:24+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Amazon.com Inc.)"
)
@Component
public class ParkingSpaceMapperImpl implements ParkingSpaceMapper {

    @Override
    public ParkingSpaceDto toDto(ParkingSpace parkingSpace) {
        if ( parkingSpace == null ) {
            return null;
        }

        ParkingSpaceDto parkingSpaceDto = new ParkingSpaceDto();

        parkingSpaceDto.setOtoparkId( parkingSpaceOtoparkOtoparkId( parkingSpace ) );
        parkingSpaceDto.setId( parkingSpace.getId() );
        parkingSpaceDto.setAvailable( parkingSpace.isAvailable() );

        return parkingSpaceDto;
    }

    @Override
    public ParkingSpace toEntity(ParkingSpaceDto parkingSpaceDto) {
        if ( parkingSpaceDto == null ) {
            return null;
        }

        ParkingSpace parkingSpace = new ParkingSpace();

        parkingSpace.setOtopark( parkingSpaceDtoToOtopark( parkingSpaceDto ) );
        parkingSpace.setId( parkingSpaceDto.getId() );
        parkingSpace.setAvailable( parkingSpaceDto.isAvailable() );

        return parkingSpace;
    }

    private Long parkingSpaceOtoparkOtoparkId(ParkingSpace parkingSpace) {
        if ( parkingSpace == null ) {
            return null;
        }
        Otopark otopark = parkingSpace.getOtopark();
        if ( otopark == null ) {
            return null;
        }
        Long otoparkId = otopark.getOtoparkId();
        if ( otoparkId == null ) {
            return null;
        }
        return otoparkId;
    }

    protected Otopark parkingSpaceDtoToOtopark(ParkingSpaceDto parkingSpaceDto) {
        if ( parkingSpaceDto == null ) {
            return null;
        }

        Otopark otopark = new Otopark();

        otopark.setOtoparkId( parkingSpaceDto.getOtoparkId() );

        return otopark;
    }
}
