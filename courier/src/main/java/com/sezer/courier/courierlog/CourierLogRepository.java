package com.sezer.courier.courierlog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierLogRepository extends JpaRepository<CourierLog, Integer> {
}
