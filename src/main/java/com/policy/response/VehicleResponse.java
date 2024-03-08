package com.policy.response;

import org.springframework.beans.factory.annotation.Value;

import java.math.BigInteger;

public interface VehicleResponse {

    @Value("#{target.CertificateNo}")
    String getCertificateNo ();

    @Value("#{target.CarInsuredCode}")
    String getCarInsuredCode();


    @Value("#{target.CarInsuredID}")
    String getCarInsuredID();

    @Value("#{target.CarMake}")
    String getCarMake();



    @Value("#{target.CarMakeCode}")
    String getCarMakeCode();


    @Value("#{target.CarMakeID}")
    String getCarMakeID();

    @Value("#{target.CarModelCode}")
    String getCarModelCode();


    @Value("#{target.CarModelID}")
    int getCarModelID();



    @Value("#{target.SHAPE_ID}")
    String getShapeID();



    @Value("#{target.ModelToPrint}")
    String getModelToPrint();

    @Value("#{target.CarYear}")
    String getCarYear();


    @Value("#{target.CarPlatePrefix}")
    String getCarPlatePrefix();

    @Value("#{target.CarPlateNumber}")
    String getCarPlateNumber();

    @Value("#{target.CarUsage}")
    String getCarUsage();

    @Value("#{target.CarChassis}")
    String getCarChassis();

    @Value("#{target.CarEngine}")
    String getCarEngine();


    @Value("#{target.CarStatus}")
    String getCarStatus();

    @Value("#{target.certifId}")
    BigInteger getCertifId();

    @Value("#{target.carTradeMarkDesc}")
    String getCarTradeMarkDesc();

    @Value("#{target.carBrandDesc}")
    String getCarBrandDesc();


    @Value("#{target.carValue}")
    String getCarValue();

    @Value("#{target.carBeneficiaryDesc}")
    String getCarBeneficiaryDesc();

    @Value("#{target.carDealerWorkShopName}")
    String getCarDealerWorkShopName();


    @Value("#{target.policyAgencyRepair}")
    String getPolicyAgencyRepair();

}
