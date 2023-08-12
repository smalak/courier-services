package com.sezer.courier.courierlog;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Integer courierId;

    private String detail;
}
