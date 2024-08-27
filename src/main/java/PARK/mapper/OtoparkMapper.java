package PARK.mapper;

import PARK.dto.OtoparkDto;
import PARK.entity.Otopark;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OtoparkMapper {

    OtoparkMapper INSTANCE = Mappers.getMapper(OtoparkMapper.class);

    @Mapping(target = "ownerId", source = "owner.userId")
    OtoparkDto toDto(Otopark otopark);

    @Mapping(target = "owner.userId", source = "ownerId")
    Otopark toEntity(OtoparkDto otoparkDto);
}
