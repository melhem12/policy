package com.policy.repository;

import com.policy.entity.CarsInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface CarsInsuranceRepository  extends JpaRepository<CarsInsurance,String> {


        }
