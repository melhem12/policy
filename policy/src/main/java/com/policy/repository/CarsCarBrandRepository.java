package com.policy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.policy.entity.CarsCarBrand;

@Repository
@EnableJpaRepositories
@Transactional
public interface CarsCarBrandRepository extends JpaRepository<CarsCarBrand,String> {

}
