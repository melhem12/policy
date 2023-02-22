package com.policy.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.policy.entity.CarsBroker;
import com.policy.entity.CarsDtCurrency;

@Repository
@EnableJpaRepositories
public interface CarsDtCurrencyRepository  extends JpaRepository<CarsDtCurrency,String>{

	Optional<CarsDtCurrency> findByInsCurrencyCodeAndInsId(String insCurrencyCode,Integer InsuranceID);
}
