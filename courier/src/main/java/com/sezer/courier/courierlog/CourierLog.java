package com.sezer.courier.courierlog;

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
public class CourierLog {

    @Id
    @SequenceGenerator(
            name = "courier_log_id_sequence",
            sequenceName = "courier_log_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "courier_log_id_sequence"
    )
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;

    private String detail;
}
