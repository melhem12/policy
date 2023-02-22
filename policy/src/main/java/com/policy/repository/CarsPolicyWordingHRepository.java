package com.policy.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.policy.entity.CarsPolicyWordingH;
import com.policy.entity.CarsProducts;

@Repository
@EnableJpaRepositories
public interface CarsPolicyWordingHRepository extends JpaRepository<CarsPolicyWordingH,String> {
	
	
@Query(value=" delete from CARS_POLICY_WORDING_H where POLICY_ID  =  ?1  ",nativeQuery = true)
void deleteCarsPolicyWordingH(String policyId);

}
