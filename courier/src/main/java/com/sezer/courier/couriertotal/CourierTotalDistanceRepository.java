package com.sezer.courier.couriertotal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourierTotalDistanceRepository extends JpaRepository<CourierTotalDistance, Integer> {

    Optional<CourierTotalDistance> findByCourierId(Integer courierId);
}
