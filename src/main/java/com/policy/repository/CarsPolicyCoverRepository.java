package com.policy.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.policy.entity.CarsPolicy;
import com.policy.entity.CarsPolicyCover;
@Repository
@EnableJpaRepositories
public interface CarsPolicyCoverRepository  extends JpaRepository<CarsPolicyCover,String> {

	@Modifying
	@org.springframework.transaction.annotation.Transactional
	@Query(value=" delete from CARS_POLICY_COVER where POLICY_COVERS_CAR_ID  = :carID  ",nativeQuery = true)
	public void deleteCovers(@Param("carID")String carId) ;
}
