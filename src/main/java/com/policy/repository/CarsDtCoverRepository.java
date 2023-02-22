package com.policy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.policy.entity.CarsDtCoverContract;
import com.policy.entity.CarsDtPolicy;

@Repository
public interface CarsDtCoverRepository  extends JpaRepository<CarsDtCoverContract,String>{
	
	

	

}
