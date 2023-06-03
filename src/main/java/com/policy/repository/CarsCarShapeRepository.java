package com.policy.repository;

import com.policy.response.CarShapeRespomse;
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
            " AND UPPER(TRADEMARK_DESCRIPTION)=?2 " +
            " AND ROWNUM=1 ",nativeQuery = true)
    Optional<String> findShapeCode (String carMake,String tradeMarkDescriptopn);

    @Query(value = "  SELECT BRAND_CODE ,BRAND_DESCRIPTION ,TRADEMARK_CODE ,TRADEMARK_DESCRIPTION ,SHAPE_CODE ,SHAPE_DESCRIPTION ,DESCRIPTION FROM BRAND_TRADEMARK_LOV_VW WHERE SHAPE_CODE = ?1 AND ROWNUM=1 ",nativeQuery = true)
    Optional<CarShapeRespomse> findByShapeCode (String code);

}
