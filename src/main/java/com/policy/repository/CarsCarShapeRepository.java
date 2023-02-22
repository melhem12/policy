package com.policy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.policy.entity.CarsCarShape;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface CarsCarShapeRepository extends JpaRepository<CarsCarShape,String> {


    @Query(value = " SELECT DISTINCT SHAPE_CODE FROM BRAND_TRADEMARK_LOV_VW WHERE UPPER(BRAND_DESCRIPTION) = ?1 " +
            " AND UPPER(TRADEMARK_DESCRIPTION)='NOT LISTED' " +
            " AND ROWNUM=1 ",nativeQuery = true)
    Optional<String> findShapeCode (String carMake);
}
