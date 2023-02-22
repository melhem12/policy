package com.policy.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.policy.entity.CarsBranch;

@Repository
@EnableJpaRepositories
public interface CarsBranchRepository extends JpaRepository<CarsBranch,String> {
	
	Optional<CarsBranch> findByBranchCodeAndBranchInsuranceId(String branchCode, String branchInsuranceId);
	

}
