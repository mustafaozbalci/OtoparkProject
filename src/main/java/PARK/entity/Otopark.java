package PARK.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Otopark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long otoparkId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private String pricingSchedule;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "otopark", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ParkingSpace> parkingSpaces = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Otopark otopark = (Otopark) o;

        return otoparkId != null ? otoparkId.equals(otopark.otoparkId) : otopark.otoparkId == null;
    }

    @Override
    public int hashCode() {
        return otoparkId != null ? otoparkId.hashCode() : 0;
    }
}
