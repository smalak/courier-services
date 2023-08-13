package com.sezer.courier.couriertotal;

import com.sezer.courier.courier.Courier;
import com.sezer.courier.courier.CourierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.ZonedDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class CourierTotalDistanceRepositoryTest {

    @Autowired
    private CourierTotalDistanceRepository underTest;

    @Autowired
    private CourierRepository underCourier;

    @BeforeEach
    public void setUp() {
        Integer courierId = 1;
        Courier c = new Courier(courierId, "sezer","malak");
        underCourier.saveAndFlush(c);
    }
    @Test
    void itShouldFindTotalDistanceByCourier() {
        // given
        CourierTotalDistance courierTotalDistance = new CourierTotalDistance();
        courierTotalDistance.setCourier(new Courier(1));
        courierTotalDistance.setTime(ZonedDateTime.now());
        courierTotalDistance.setDistance(0d);
        underTest.save(courierTotalDistance);

        // when
        Optional<CourierTotalDistance> totalDistance = underTest.findByCourierId(1);

        // then
        assertThat(totalDistance.get().equals(courierTotalDistance)).isTrue();

    }
}