package com.policy.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.policy.entity.CarsBranch;
import com.policy.entity.CarsBroker;

@Repository
@EnableJpaRepositories
public interface CarsBrokerRepository extends JpaRepository<CarsBroker,String> {
	@Query(value = "select cb from CarsBroker cb  where (brokerId =?1 and brokerInsuranceId=?2)  or (brokerNum =?3 and brokerInsuranceId=?2)   ")
	Optional<CarsBroker> findByBrokerIdAndBrokerInsuranceIdrOrBrokerNum(String brokerId, String brokerInsuranceId,String brokerNum);


	Optional<CarsBroker> findByBrokerInsuranceIdOrBrokerNum(String brokerInsuranceId, String brokerCode);

	Optional<CarsBroker> findByBrokerInsuranceIdAndBrokerNum(String brokerInsuranceId, String brokerCode);

	@Query("SELECT cb FROM CarsBroker cb WHERE (cb.brokerReference = :param OR cb.brokerNum = :param) AND cb.brokerInsuranceId = :insuranceId")

	Optional<CarsBroker> findByBrokerReferenceOrBrokerNumAndInsuranceId(String param, String insuranceId);
}
