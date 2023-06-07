package com.policy.repository;

import com.policy.bean.Vehicles;
import com.policy.response.VehicleResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.policy.entity.CarsPolicyCar;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface CarsPolicyCarRepository extends JpaRepository<CarsPolicyCar,String> {
    @Query(value=" SELECT DISTINCT    POLICY_CAR   \"CertificateNo\",   POLICY_CLIENT  \"CarInsuredCode\",   POLICY_CLIENT_ID  \"CarInsuredID\",     CAR_BRAND_DESC     \"CarMake\",    BRAND_ID   \"CarMakeID\",      CAR_BRAND_CODE   \"CarMakeCode\",     CAR_TRADEMARK_CODE   \"CarModelCode\" , " +

            "       TRADEMARK_ID   \"CarModelID\", POLICY_ID_INS  \"certifId\" ,  SHAPE_ID,  CAR_MODEL_TO_PRINT   \"ModelToPrint\",      CAR_YEAR  \"CarYear\",     CAR_PLATE_CHAR  \"CarPlatePrefix\",    CAR_PLATE_NUM  \"CarPlateNumber\",     CAR_USAGE  \"CarUsage\",       CAR_CHASSIS       \"CarChassis\",     CAR_ENGINE  \"CarEngine\", " +

            "         'C' \"CarStatus\"   FROM CARS_POLICY AL,CARS_POLICY_CAR VL,CARS_PRODUCTS PL, CARS_INSURANCE I WHERE AL.POLICY_ID =VL.POLICY_ID AND  PL.PRODUCTS_ID=AL.POLICY_PRODUCTS_ID    AND POLICY_INSURANCE_ID= :companyId  "+

            " AND POLICY_NUMBER = :policyNumber  \n ",nativeQuery = true)
    public List<VehicleResponse> getVehicles(@Param("companyId")int companyId  , @Param("policyNumber")String policyNumber) ;
}
