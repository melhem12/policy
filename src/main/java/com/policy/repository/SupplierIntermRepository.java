package com.policy.repository;

import com.policy.LOV.SupplierIntermTypeLov;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierIntermRepository  extends JpaRepository<SupplierIntermTypeLov,String> {
}
