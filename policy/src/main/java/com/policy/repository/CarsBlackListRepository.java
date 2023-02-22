package com.policy.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.policy.entity.CarsBlackList;
@Repository
@EnableJpaRepositories

public interface CarsBlackListRepository extends JpaRepository<CarsBlackList,String> {


	Collection<CarsBlackList> findByBlInsuranceIdAndClientNum(String blInsuranceId, String clientNum);
	
	Optional<CarsBlackList> findByClientNum(String clientNum);

}
