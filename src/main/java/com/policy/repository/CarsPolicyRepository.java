package com.policy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import com.policy.entity.CarsPolicy;
import java.util.Collection;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface CarsPolicyRepository  extends JpaRepository<CarsPolicy,String> {



    Collection<CarsPolicy> findByPolicyInsuranceIdAndPolicyIdIns(String insuranceId,String policyIdIns);

    Optional<CarsPolicy> findByPolicyCertIfID(String certifId);

    List<CarsPolicy> findByPolicyClientId(String clientId);


    @Query(value = " SELECT CONFIG_VALUE FROM CORE_CONFIGURATION WHERE ID = ?1 ", nativeQuery = true)
    String findConfigByKey(String key);
}
