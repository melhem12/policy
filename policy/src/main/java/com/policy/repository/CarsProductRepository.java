package com.policy.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.policy.entity.CarsProducts;
@Repository
@EnableJpaRepositories
@Transactional
public interface CarsProductRepository extends JpaRepository<CarsProducts,String> {
	
	Optional<CarsProducts> findByProductsCodeAndProductsInsuranceId(String productsCode,String productsInsuranceId);

}
