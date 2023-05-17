package com.policy.repository;

import com.policy.entity.CarsDtModel;
import com.policy.entity.CarsDtParams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface CarsDtParamsRepository extends JpaRepository<CarsDtParams,String> {

    Optional<CarsDtParams> findByParamsIdAndInsuranceId(String profileType, String insuranceId);
}
