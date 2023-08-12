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
    private Integer id;
    private String firstName;
    private String lastName;

    public Courier(Integer courierId){
        this.id = courierId;
    }

}
