package com.policy.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.policy.entity.CarsDtModel;
@Repository
@EnableJpaRepositories
public interface CarsDtModelRepository extends JpaRepository<CarsDtModel,String>{

	@Query(value=" select DT_ID from CARS_DT_MODEL where INS_ID  =  ?1  AND  upper(TRIM(MODEL_NAME))  = upper(TRIM(?2)) AND INS_MAKE_CODE= upper(TRIM(?3)) ",nativeQuery = true)
	public List<String> getDataTransferByModel(String insuranceId, String modelName, String carMake) ;
}
		//INS_ID  =  ?1  AND 

