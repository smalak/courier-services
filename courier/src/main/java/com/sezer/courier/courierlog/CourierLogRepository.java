package com.sezer.courier.courierlog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourierLogRepository extends JpaRepository<CourierLog, Integer> {

    List<CourierLog> findAllByCourierId(Integer courierId);

}
