package com.sezer.courier.courierlog;

import com.sezer.courier.courier.Courier;
import com.sezer.courier.courier.CourierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class CourierLogRepositoryTest {

    @Autowired
    private CourierLogRepository underTest;

    @Autowired
    private CourierRepository underCourier;

    @BeforeEach
    public void setUp() {
        Integer courierId = 1;
        Courier c = new Courier(courierId, "sezer","malak");
        underCourier.saveAndFlush(c);
    }

    @Test
    void findAllByCourierId() {
        // given
        CourierLog courierLog = new CourierLog();
        courierLog.setCourierId(1);
        courierLog.setDetail("test");
        courierLog = underTest.save(courierLog);

        // when
        List<CourierLog> logsByCourier = underTest.findAllByCourierId(1);

        // then
        assertThat(logsByCourier.get(0).getId().equals(courierLog.getId())).isTrue();
    }
}