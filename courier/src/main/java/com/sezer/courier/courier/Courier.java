package com.sezer.courier.courier;

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
public class Courier {

    @Id
    @SequenceGenerator(
            name = "courier_id_sequence",
            sequenceName = "courier_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "courier_id_sequence"
    )
    private Integer id;
    private String firstName;
    private String lastName;
}
