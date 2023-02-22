package com.policy.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.policy.entity.CarsBranch;
import com.policy.entity.CarsBroker;

@Repository
@EnableJpaRepositories
public interface CarsBrokerRepository extends JpaRepository<CarsBroker,String> {
	
	Optional<CarsBroker> findByBrokerIdAndBrokerInsuranceId(String brokerId, String brokerInsuranceId);

}
