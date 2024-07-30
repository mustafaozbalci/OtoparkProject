package PARK.mapper;

import PARK.dto.OtoparkDto;
import PARK.entity.Otopark;
import PARK.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-30T18:35:24+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Amazon.com Inc.)"
)
@Component
public class OtoparkMapperImpl implements OtoparkMapper {

    @Override
    public OtoparkDto toDto(Otopark otopark) {
        if ( otopark == null ) {
            return null;
        }

        OtoparkDto otoparkDto = new OtoparkDto();

        otoparkDto.setOwnerId( otoparkOwnerUserId( otopark ) );
        otoparkDto.setOtoparkId( otopark.getOtoparkId() );
        otoparkDto.setName( otopark.getName() );
        otoparkDto.setAddress( otopark.getAddress() );
        otoparkDto.setCapacity( otopark.getCapacity() );
        otoparkDto.setPricingSchedule( otopark.getPricingSchedule() );

        return otoparkDto;
    }

    @Override
    public Otopark toEntity(OtoparkDto otoparkDto) {
        if ( otoparkDto == null ) {
            return null;
        }

        Otopark otopark = new Otopark();

        otopark.setOwner( otoparkDtoToUser( otoparkDto ) );
        otopark.setOtoparkId( otoparkDto.getOtoparkId() );
        otopark.setName( otoparkDto.getName() );
        otopark.setAddress( otoparkDto.getAddress() );
        otopark.setCapacity( otoparkDto.getCapacity() );
        otopark.setPricingSchedule( otoparkDto.getPricingSchedule() );

        return otopark;
    }

    private Long otoparkOwnerUserId(Otopark otopark) {
        if ( otopark == null ) {
            return null;
        }
        User owner = otopark.getOwner();
        if ( owner == null ) {
            return null;
        }
        Long userId = owner.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }

    protected User otoparkDtoToUser(OtoparkDto otoparkDto) {
        if ( otoparkDto == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( otoparkDto.getOwnerId() );

        return user;
    }
}
