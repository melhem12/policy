package com.policy.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.policy.entity.CarsBranch;
import com.policy.entity.CarsClient;
@Repository
@EnableJpaRepositories
public interface  CarsClientRepository extends JpaRepository<CarsClient,String> {



	@Query(value = "select cc from  CarsClient cc where cc.clientInsuranceId =?1 and cc.clientNum1= ?2 ")
	Optional<CarsClient> findByClientInsuranceIdAndClientNum1(String clientId, String clientNum1);

	@Query("FROM CarsClient WHERE (clientReference = :param OR clientNum1 = :param) AND clientInsuranceId = :insuranceId")
	Optional<CarsClient> findByClientReferenceOrClientNum1AndInsuranceId( String insuranceId,String param);
}
