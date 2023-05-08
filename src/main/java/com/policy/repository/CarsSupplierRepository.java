package com.policy.repository;

import com.policy.entity.CarsSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface CarsSupplierRepository extends JpaRepository<CarsSupplier,String> {
}
