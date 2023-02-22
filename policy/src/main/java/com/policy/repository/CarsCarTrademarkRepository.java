package com.policy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.policy.entity.CarsCarTrademark;
@Repository
@EnableJpaRepositories
public interface CarsCarTrademarkRepository extends JpaRepository<CarsCarTrademark,String> {



}
