package com.policy.repository;

import com.policy.entity.CarsDtSupplier;
import com.policy.entity.CarsSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface CarsDtSupplierRepository  extends JpaRepository<CarsDtSupplier,String> {

    Optional<CarsDtSupplier> findByInsIdAndInsSupplierCode(String insId,String insSupplierCode);
}
