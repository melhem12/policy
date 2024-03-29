package com.policy.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.policy.entity.CarsCover;

@Repository
@EnableJpaRepositories
public interface CarsCoverRepository extends JpaRepository<CarsCover,String>{

	Optional<CarsCover> findByCoverCodeAndCoverInsuranceId(String coverCode,String coverInsuranceId);
	
	Optional<CarsCover> findByCoverIDAndCoverInsurance(String coverId,Integer coverInsurance);
	Optional<CarsCover> findByCoverCodeAndCoverInsurance(String coverCode,Integer coverInsuranceId);

}
