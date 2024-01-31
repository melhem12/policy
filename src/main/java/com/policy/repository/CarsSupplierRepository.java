package com.policy.repository;

import com.policy.entity.CarsSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface CarsSupplierRepository extends JpaRepository<CarsSupplier,String> {
    @Query("SELECT MAX(s.supplierNum) FROM CarsSupplier s WHERE s.supplierInterm = ?1")
    Long findLatestSupplierNumberByInterm(String intermCode);




    @Query(value = "select * from CARS_SUPPLIER s inner join CARS_DT_SUPPLIER d on s.SUPPLIER_ID = d.CE_CODE where d.INS_ID = ?1 and d.INS_SUPPLIER_CODE = ?2", nativeQuery = true)
    Optional<CarsSupplier> findSupplierByInsCodeAndInsSupplierCode(String insId, String insSupplierCode);

}
