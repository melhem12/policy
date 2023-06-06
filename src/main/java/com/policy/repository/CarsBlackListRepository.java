package com.policy.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.policy.entity.CarsBlackList;
@Repository
@EnableJpaRepositories

public interface CarsBlackListRepository extends JpaRepository<CarsBlackList,String> {



	List<CarsBlackList> findByBlInsuranceIdAndClientNum(String blInsuranceId, String clientNum);
	
	Optional<CarsBlackList> findByClientNum(String clientNum);


	Optional<CarsBlackList> findTopByClientNumAndBlInsuranceIdOrderBySysUpdatedDateDesc(String clientNum,String insId);

    List<CarsBlackList> findByBlBrokerId(String s);

	Optional<CarsBlackList> findTopByBlBrokerIdOrderBySysUpdatedDateDesc(String s);

	@Query(" select   b from CarsBlackList b join fetch  b.blSupplier  s   where s.supplierId = ?1  and  b.blInsuranceId =?2 order by b.sysUpdatedDate  desc   ")
	List<CarsBlackList> findByBlSupplierIdAndBlInsuranceId(String profileCode, String insuranceId);

	Optional<CarsBlackList> findTopByBlFirstNameAndBlFatherNameAndBlFamilyNameOrderBySysUpdatedDateDesc(String toUpperCase, String toUpperCase1, String toUpperCase2);
}
