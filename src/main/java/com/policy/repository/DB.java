package com.policy.repository;

import com.policy.entity.CarsDtParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("barFormatter")
public class DB {

	@Autowired
	public CarsBranchRepository carsBranchRepository ;
	
	@Autowired
	public CarsBrokerRepository carsBrokerRepository ;
	
	@Autowired
	public CarsCarBrandRepository carsCarBrandRepository ;

	@Autowired
	public CarsCarShapeRepository carsCarShapeRepository ;
	
	@Autowired
	public CarsCarTrademarkRepository carsCarTrademarkRepository ;
	
	@Autowired
	public CarsClientRepository carsClientRepository ;
	
	@Autowired
	public CarsPolicyCarRepository carsPolicyCarRepository ;
	
	@Autowired
	public CarsPolicyRepository carsPolicyRepository ;
	
	@Autowired
	public CarsPolicyWordingHRepository carsPolicyWordingHRepository ;
	
	@Autowired
	public CarsPolicyWordingDRepository carsPolicyWordingDRepository ;
	
	@Autowired
	public CarsProductRepository carsProductRepository ;

	@Autowired
	public CarsSublineRepository carsSublineRepository ;
	
	@Autowired
	public CarsBlackListRepository carsBlackListRepository ;
	
	@Autowired
	public CarsDtCurrencyRepository carsDtCurrencyRepository ;
	
	@Autowired
	public CarsDtModelRepository carsDtModelRepository ;
	
	@Autowired
	public CarsPolicyCoverRepository carsPolicyCoverRepository ;
	
	@Autowired
	public CarsCoverRepository carsCoverRepository ;
	
	@Autowired
	public CarsErrorlogRepository carsErrorlogRepository ;
	
	@Autowired
	public CarsDtPolicyTransferLogRepository carsDtPolicyTransferLogRepository ;

	@Autowired
	public CompanyRepository companyRepository ;

	@Autowired
	public  CarsInsuranceRepository carsInsuranceRepository;

	@Autowired
	public  CarsSupplierRepository carsSupplierRepository;


	@Autowired
	public  CarsDtSupplierRepository carsDtSupplierRepository;


	@Autowired
	public CarsDtParamsRepository carsDtParamsRepository;


	@Autowired
	public SupplierIntermRepository supplierIntermRepository;

	@Autowired
	public  AddressViewRepository addressViewRepository;
}
