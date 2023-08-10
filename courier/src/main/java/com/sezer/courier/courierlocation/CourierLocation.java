package com.sezer.courier.courierlocation;

import com.sezer.courier.courier.Courier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CourierLocation {

    @Id
    @SequenceGenerator(
            name = "courier_location_id_sequence",
            sequenceName = "courier_location_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "courier_location_id_sequence"
    )
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;

    private Point location;
    private ZonedDateTime time;
}
