package com.policy.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.policy.entity.CarsPolicyWordingD;
@Repository
@EnableJpaRepositories
public interface CarsPolicyWordingDRepository extends JpaRepository<CarsPolicyWordingD,String> {

	
	@Query(value=" DELETE FROM CARS_POLICY_WORDING_D WHERE POLICY_ID  =  ?1  ",nativeQuery = true)
	void deleteCarsPolicyWordingD(String policyId);
	
	Collection<CarsPolicyWordingD>findByPolicyWordingHId(String HId);
}
