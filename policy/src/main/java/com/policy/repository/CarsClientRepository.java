package com.policy.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.policy.entity.CarsBranch;
import com.policy.entity.CarsClient;
@Repository
@EnableJpaRepositories
public interface  CarsClientRepository extends JpaRepository<CarsClient,String> {


	
	
	Optional<CarsClient> findByClientInsuranceIdAndClientNum1(String clientId, String clientNum1);
}
