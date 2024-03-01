package com.policy.repository;

import com.policy.entity.CarsPolicyJson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public interface CarsPolicyJsonRepository extends JpaRepository<CarsPolicyJson,String> {
    @Query(nativeQuery = true, value = " select count(*) from CARS_POLICY_JSON where POLICY_JSON_STATUS IS NULL  ")
    Long getPoliciesCount() ;


    @Query(nativeQuery = true, value = " select * from CARS_POLICY_JSON where POLICY_JSON_STATUS IS NULL AND ROWNUM = 1  ")
    Optional<CarsPolicyJson> getPolicyJson() ;
}
