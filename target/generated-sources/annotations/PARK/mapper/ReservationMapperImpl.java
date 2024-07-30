package PARK.mapper;

import PARK.dto.ReservationDto;
import PARK.entity.Otopark;
import PARK.entity.ParkingSpace;
import PARK.entity.Reservation;
import PARK.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-30T18:35:24+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Amazon.com Inc.)"
)
@Component
public class ReservationMapperImpl implements ReservationMapper {

    @Override
    public ReservationDto toDto(Reservation reservation) {
        if ( reservation == null ) {
            return null;
        }

        ReservationDto reservationDto = new ReservationDto();

        reservationDto.setUserId( reservationUserUserId( reservation ) );
        reservationDto.setUsername( reservationUserUsername( reservation ) );
        reservationDto.setOtoparkId( reservationOtoparkOtoparkId( reservation ) );
        reservationDto.setOtoparkName( reservationOtoparkName( reservation ) );
        reservationDto.setParkingSpaceId( reservationParkingSpaceId( reservation ) );
        reservationDto.setReservationId( reservation.getReservationId() );
        reservationDto.setEntryTime( reservation.getEntryTime() );
        reservationDto.setExitTime( reservation.getExitTime() );
        reservationDto.setTotalFee( reservation.getTotalFee() );

        return reservationDto;
    }

    @Override
    public Reservation toEntity(ReservationDto reservationDto) {
        if ( reservationDto == null ) {
            return null;
        }

        Reservation reservation = new Reservation();

        reservation.setUser( reservationDtoToUser( reservationDto ) );
        reservation.setOtopark( reservationDtoToOtopark( reservationDto ) );
        reservation.setParkingSpace( reservationDtoToParkingSpace( reservationDto ) );
        reservation.setReservationId( reservationDto.getReservationId() );
        reservation.setEntryTime( reservationDto.getEntryTime() );
        reservation.setExitTime( reservationDto.getExitTime() );
        reservation.setTotalFee( reservationDto.getTotalFee() );

        return reservation;
    }

    private Long reservationUserUserId(Reservation reservation) {
        if ( reservation == null ) {
            return null;
        }
        User user = reservation.getUser();
        if ( user == null ) {
            return null;
        }
        Long userId = user.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }

    private String reservationUserUsername(Reservation reservation) {
        if ( reservation == null ) {
            return null;
        }
        User user = reservation.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }

    private Long reservationOtoparkOtoparkId(Reservation reservation) {
        if ( reservation == null ) {
            return null;
        }
        Otopark otopark = reservation.getOtopark();
        if ( otopark == null ) {
            return null;
        }
        Long otoparkId = otopark.getOtoparkId();
        if ( otoparkId == null ) {
            return null;
        }
        return otoparkId;
    }

    private String reservationOtoparkName(Reservation reservation) {
        if ( reservation == null ) {
            return null;
        }
        Otopark otopark = reservation.getOtopark();
        if ( otopark == null ) {
            return null;
        }
        String name = otopark.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Long reservationParkingSpaceId(Reservation reservation) {
        if ( reservation == null ) {
            return null;
        }
        ParkingSpace parkingSpace = reservation.getParkingSpace();
        if ( parkingSpace == null ) {
            return null;
        }
        Long id = parkingSpace.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected User reservationDtoToUser(ReservationDto reservationDto) {
        if ( reservationDto == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( reservationDto.getUserId() );

        return user;
    }

    protected Otopark reservationDtoToOtopark(ReservationDto reservationDto) {
        if ( reservationDto == null ) {
            return null;
        }

        Otopark otopark = new Otopark();

        otopark.setOtoparkId( reservationDto.getOtoparkId() );

        return otopark;
    }

    protected ParkingSpace reservationDtoToParkingSpace(ReservationDto reservationDto) {
        if ( reservationDto == null ) {
            return null;
        }

        ParkingSpace parkingSpace = new ParkingSpace();

        parkingSpace.setId( reservationDto.getParkingSpaceId() );

        return parkingSpace;
    }
}
