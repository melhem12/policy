package com.policy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.policy.entity.CarsSubline;

@Repository
@EnableJpaRepositories
public interface CarsSublineRepository  extends JpaRepository<CarsSubline,String>  {
	
	Optional<CarsSubline> findBySublineCodeAndSublineInsuranceId(String sublineCode, String sublineInsuranceId);
	
	Optional<CarsSubline> findBySublineIdAndSublineInsuranceId(String sublineId, String sublineInsuranceId);

}
