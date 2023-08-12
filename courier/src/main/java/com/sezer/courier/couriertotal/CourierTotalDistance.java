package com.sezer.courier.couriertotal;

import com.sezer.courier.courier.Courier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CourierTotalDistance {

    @Id
    @SequenceGenerator(
            name = "courier_distance_id_sequence",
            sequenceName = "courier_distance_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "courier_distance_id_sequence"
    )
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;

    private Double distance;

    private ZonedDateTime time;

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }
}
