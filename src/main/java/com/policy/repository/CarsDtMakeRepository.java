package com.policy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.policy.entity.CarsDtCurrency;
import com.policy.entity.CarsDtMake;
@Repository
@EnableJpaRepositories
public interface CarsDtMakeRepository  extends JpaRepository<CarsDtMake,String>{

}
