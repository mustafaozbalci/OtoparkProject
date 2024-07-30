package PARK.mapper;

import PARK.dto.ParkingSpaceDto;
import PARK.entity.ParkingSpace;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ParkingSpaceMapper {

    ParkingSpaceMapper INSTANCE = Mappers.getMapper(ParkingSpaceMapper.class);

    @Mapping(target = "otoparkId", source = "otopark.otoparkId")
    ParkingSpaceDto toDto(ParkingSpace parkingSpace);

    @Mapping(target = "otopark.otoparkId", source = "otoparkId")
    ParkingSpace toEntity(ParkingSpaceDto parkingSpaceDto);
}
