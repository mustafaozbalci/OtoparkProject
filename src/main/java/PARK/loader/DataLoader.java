package PARK.loader;

import PARK.entity.Otopark;
import PARK.entity.ParkingSpace;
import PARK.entity.User;
import PARK.repository.OtoparkRepository;
import PARK.repository.ParkingSpaceRepository;
import PARK.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private OtoparkRepository otoparkRepository;

    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if (otoparkRepository.count() == 0) {
            // Kullanıcıları oluştur ve kaydet
            User owner1 = new User(null, "owner1", "password", "owner1@example.com", "Owner", "One", "111AAA111", true);
            User owner2 = new User(null, "owner2", "password", "owner2@example.com", "Owner", "Two", "222BBB222", true);
            User owner3 = new User(null, "owner3", "password", "owner3@example.com", "Owner", "Three", "333CCC333", true);

            owner1 = userRepository.save(owner1);
            owner2 = userRepository.save(owner2);
            owner3 = userRepository.save(owner3);

            // Otoparkları oluştur ve sahiplerini belirle
            Otopark otopark1 = new Otopark(null, "Test Otopark", "123 Test Street", 10, "Hourly", owner1, new ArrayList<>());
            Otopark otopark2 = new Otopark(null, "Example Otopark", "456 Example Avenue", 10, "Daily", owner2, new ArrayList<>());
            Otopark otopark3 = new Otopark(null, "Sample Otopark", "789 Sample Road", 10, "Monthly", owner3, new ArrayList<>());

            otopark1 = otoparkRepository.save(otopark1);
            otopark2 = otoparkRepository.save(otopark2);
            otopark3 = otoparkRepository.save(otopark3);

            createParkingSpacesForOtopark(otopark1, 10);
            createParkingSpacesForOtopark(otopark2, 10);
            createParkingSpacesForOtopark(otopark3, 10);
        }
    }

    private void createParkingSpacesForOtopark(Otopark otopark, int count) {
        List<ParkingSpace> parkingSpaces = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            ParkingSpace parkingSpace = new ParkingSpace();
            parkingSpace.setAvailable(true);
            parkingSpace.setOtopark(otopark);
            parkingSpaces.add(parkingSpace);
        }
        parkingSpaceRepository.saveAll(parkingSpaces);
        otopark.setParkingSpaces(parkingSpaces);
        otoparkRepository.save(otopark);
    }
}
