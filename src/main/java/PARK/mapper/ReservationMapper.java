package PARK.mapper;

import PARK.dto.ReservationDto;
import PARK.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    @Mapping(target = "userId", source = "user.userId")
    @Mapping(target = "username", source = "user.username") // Kullanıcı adı
    @Mapping(target = "otoparkId", source = "otopark.otoparkId")
    @Mapping(target = "otoparkName", source = "otopark.name") // Otopark adı
    @Mapping(target = "parkingSpaceId", source = "parkingSpace.id")
    ReservationDto toDto(Reservation reservation);

    @Mapping(target = "user.userId", source = "userId")
    @Mapping(target = "otopark.otoparkId", source = "otoparkId")
    @Mapping(target = "parkingSpace.id", source = "parkingSpaceId")
    Reservation toEntity(ReservationDto reservationDto);
}
