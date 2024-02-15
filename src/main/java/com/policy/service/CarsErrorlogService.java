package com.policy.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import com.policy.entity.CarsInsurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.policy.entity.CarsErrorlog;
import com.policy.repository.DB;
@Service
public class CarsErrorlogService{

	public static String CREATED_BY_QUARTZ = "Transfer";
	public static int i = 0;
	public static String insuranceCode = "11";
	String companyName ="";
	@Autowired
	public DB db;	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void insertError(String errorMsg,String insuranceId,String tableName,String code) {
		Optional<CarsInsurance> carsInsurance = db.carsInsuranceRepository.findById(insuranceCode);
		carsInsurance.ifPresent(value -> companyName = value.getInsuranceDesc());
		CarsErrorlog log = new CarsErrorlog();
		
		log.setErrorlogId(UUID.randomUUID().toString());
		log.setErrorlogProgName(companyName+"PolicyValidator");
		log.setErrorlogErrorMessage(errorMsg);
		log.setErrorlogErrorCode(code);
		log.setErrorlogInsuranceId(insuranceId);
		log.setErrorlogTableName(tableName);
		log.setSysVersionNumber(0);
		log.setSysCreatedBy(CREATED_BY_QUARTZ);
		log.setSysUpdatedBy(CREATED_BY_QUARTZ);
		log.setSysCreatedDate(new Timestamp(new Date().getTime()));
		log.setSysUpdatedDate(new Timestamp(new Date().getTime()));
		db.carsErrorlogRepository.save(log);
	}

}
