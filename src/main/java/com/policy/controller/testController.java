package com.policy.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import com.policy.bean.*;
import com.policy.exceptionHandler.CustomResponseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.policy.entity.CarsBlackList;
import com.policy.entity.CarsBranch;
import com.policy.entity.CarsBroker;
import com.policy.entity.CarsClient;
import com.policy.entity.CarsCover;
import com.policy.entity.CarsDtClauseContract;
import com.policy.entity.CarsDtCurrency;
import com.policy.entity.CarsDtModel;
import com.policy.entity.CarsPolicy;
import com.policy.entity.CarsPolicyCar;
import com.policy.entity.CarsPolicyCover;
import com.policy.entity.CarsPolicyWordingD;
import com.policy.entity.CarsPolicyWordingH;
import com.policy.entity.CarsProducts;
import com.policy.entity.CarsSubline;
import com.policy.repository.DB;
import com.policy.service.TestService;
import com.policy.config.Utility;
import com.policy.config.SendingMail;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/auth/constant")
@Service
@ComponentScan("com.policy")
public class testController {

	private static Logger logger = LoggerFactory.getLogger(testController.class);
	
	
	@Autowired
	public DB db;

	
	
	@Autowired
	public TestService testService;
	public static String CREATED_BY_QUARTZ = "Test03";
	public static int i = 0;
	public static String insuranceCode = "10";

//	@PostMapping("/test")
//	public ResponseEntity<Policies> policyUpload(@Validated @RequestBody Policies policies) throws Exception {
//		try {
////			String dId = "70870162-aeb3-4d63-b28a-4f9c7749536c";
////			Optional<CarsPolicyWordingD> row = db.carsPolicyWordingDRepository.findById(dId);
////
////			if (row != null) {
////				System.out.println(row.get().getPolicyWordingDFreeText());
////			}
//
//			StringBuffer stringBufferTrademark = new StringBuffer("");
//			PolicyVehicle policyVehicle = new PolicyVehicle();
//			for (Policy policy : policies.getPolicies()) {
//				policyVehicle.setPolicy(policy);
//
////				CarsProducts products = validateCarsProduct(policy.getProductCode(), policy.getProductID(),
////					policy.getProductDescription(), insuranceCode);// ask about insurance id
//				CarsProducts product = validateCarsProduct(policy.getSubLineCode(), policy.getSublineDescription(),
//						insuranceCode);// ask about insurance id
//
//				CarsSubline carsSubline = validateCarsSubline(policy.getProductCode(), insuranceCode,
//						policy.getProductDescription(), policy.getProductID());
//
//				CarsBroker broker = validateCarsBroker(policy.getBrokerCode(), policy.getBrokerID().toString(),
//						insuranceCode, policy.getBrokerName(), policy.getBrokerPhoneNumber(),
//						policy.getIsDirectBroker(), policy.getBrokerMail(), policy.getBrokerWebOrigin(),
//						policy.getBrokerBlacklisted(), policy.getBrokerBlackReason(), policy.getInsBlackNote());
//
//				CarsBranch branch = validateCarsBranch(policy.getBranchCode(), insuranceCode,
//						policy.getBranchDescription());
//
//				CarsClient client = validateCarsClient(policy.getInsuredID().toString(), policy.getInsuredCode(),
//						insuranceCode, policy.getPrintName(), policy.getFirstInsuredName(),
//						policy.getFatherInsuredName(), policy.getLastInsuredName(), policy.getInsuredPhoneNumber(),
//						policy.getInsBlacklisted(), policy.getInsBlackReason(), policy.getInsBlackNote());
//
//				System.out.println("brokerId -------------> " + broker.getBrokerId());
//				String policyId = null;
//
////				Collection<CarsPolicy> carsPolicyToSearchList = carsPolicySearchable(policyVehicle);
////				if (carsPolicyToSearchList != null && !carsPolicyToSearchList.isEmpty()) {
////					policyId = updatePolicyCar(policyVehicle, products.getProductsId(), branch.getBranchId(),
////							client.getClientId(), broker.getBrokerId(), carsPolicyToSearchList, insuranceCode);
////				} else {
////					policyId = insertPolicyCar(policyVehicle, products.getProductsId(), branch.getBranchId(),
////							client.getClientId(), broker.getBrokerId(), insuranceCode);
////				}
//				if (policy.getVehicles() != null) {
//					// Collection<CarsPolicy> carsPolicyToSearchList =
//					// carsPolicySearchable(policyVehicle);
//					for (Vehicles vehicle : policy.getVehicles()) {
//						policyVehicle.setVehicle(vehicle);
//
//						CarsClient client2 = validateCarsClient(vehicle.getCarInsuredID().toString(),
//								vehicle.getCarInsuredCode(), insuranceCode, policy.getPrintName(),
//								vehicle.getCarinsuredfirstName(), vehicle.getCarinsuredfatherName(),
//								vehicle.getCarinsuredlastName(), policy.getInsuredPhoneNumber(),
//								policy.getInsBlacklisted(), policy.getInsBlackReason(), policy.getInsBlackNote());
//
//						CarsPolicyCar carsPolicyCarToSearch = new CarsPolicyCar();
//
//						Collection<CarsPolicy> carsPolicyToSearchList = carsPolicySearchable(policyVehicle,
//								product.getProductsId());
//
//						if (carsPolicyToSearchList != null && !carsPolicyToSearchList.isEmpty()) {
//
//							policyId = updatePolicyCar(policyVehicle, product.getProductsId(), branch.getBranchId(),
//									client2.getClientId(), broker.getBrokerId(), carsSubline.getSublineId(),
//									carsPolicyToSearchList, insuranceCode);
//
//						} else {
//
//							policyId = insertPolicyCar(policyVehicle, product.getProductsId(), branch.getBranchId(),
//									client2.getClientId(), broker.getBrokerId(), carsSubline.getSublineId(),
//									insuranceCode);
//						}
//						// carsPolicyCarToSearch.setCarObjectNumber(dataTransferPolicyLoaded.getPolicyCertificate());
//						carsPolicyCarToSearch.setPolicyId(policyId);
//
//						Collection<CarsDtModel> dataTransferModelList = getDataTransferByModel(insuranceCode,
//								vehicle.getCarModel(), vehicle.getCarMake());
//
//						String carId = null;
//
//						// carsPolicyToSearchList = carsPolicySearchable(policyVehicle);
////						if (carsPolicyToSearchList != null && !carsPolicyToSearchList.isEmpty()) {
////							policyId = updatePolicyCar(policyVehicle, products.getProductsId(), branch.getBranchId(),
////									client.getClientId(), broker.getBrokerId(), carsPolicyToSearchList, insuranceCode);
////						} else {
////							policyId = insertPolicyCar(policyVehicle, products.getProductsId(), branch.getBranchId(),
////									client.getClientId(), broker.getBrokerId(), insuranceCode);
////						}
//
//						Example<CarsPolicyCar> example = Example.of(carsPolicyCarToSearch);
//
//						Collection<CarsPolicyCar> carsPolicyCarList = db.carsPolicyCarRepository.findAll(example);
//						if (carsPolicyCarList != null && !carsPolicyCarList.isEmpty()) {
//
//							carId = updateCarsPolicyCar(vehicle, policyId, carsPolicyCarList, dataTransferModelList,
//									stringBufferTrademark);
//						} else {
//
//							carId = insertCarsPolicyCar(vehicle, policyId, dataTransferModelList,
//									stringBufferTrademark);
//						}
//
//						// carsDtPolicyService.insertCarPolicy(vehicle);
//						if (vehicle.getClauses() != null) {
//							DeletePolicyWording(policyId);
//							for (Clauses clauses : vehicle.getClauses()) {
//
//								insertCarsPolicyWordingHAndD(clauses, policyId);
//							}
//						}
//						if (vehicle.getCovers() != null) {
//							deleteCover(carId);
//							//String a = policy.getPolicyID().toString();
//							for (Covers covers : vehicle.getCovers()) {
//								String CarsCover = validateCover(covers);
//								insertCarsCover(covers, null, covers.getCoverCode().toString(), carId, CarsCover, null);
//								if (covers.getSubCovers() != null && !covers.getSubCovers().isEmpty()) {
//									for (SubCovers subCovers : covers.getSubCovers()) {
//										String CarsSubCover = validateSubCover(subCovers, covers.getCoverCode());
//										// carsDtCoverContractService.insertSubCover(subCovers, vehicle.getPolicyID());
//										insertCarsCover(null, subCovers, subCovers.getSubCoverID().toString(), carId,
//												covers.getCoverCode(), CarsSubCover);
//									}
//								}
//							}
//						}
//					}
//				}
//			}
//
////		if(policies.getPolicies().get(0).getEndorsementSubTypeDescription() == null) {
////		System.out.println("test");	
////		}
////		if(policies.getPolicies().get(0).getBrokerWebOrigin() == false) {
////			System.out.println("test1");	
////		}
//			// Authentication authentication =
//			// SecurityContextHolder.getContext().getAuthentication();
//			// if (authentication != null &&
//			// authentication.getAuthorities().stream().anyMatch(a ->
//			// a.getAuthority().equals("ROLE_USER"))) {
//			return new ResponseEntity(policies, HttpStatus.CREATED);
//
//		} catch (
//
//		Exception e) {
//			
//			e.printStackTrace();
//			StringWriter sw = new StringWriter();
//			PrintWriter pw = new PrintWriter(sw);
//			e.printStackTrace(pw);
//			String sStackTrace = sw.toString();
//			return new ResponseEntity(sStackTrace, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		// }
//		// return new ResponseEntity( HttpStatus.UNAUTHORIZED);
//
//	}
//
////	public CarsProducts validateCarsProduct(String productCode, Integer productId, String productDescription,
////			String productInsuranceId) throws Exception {
////		if (productCode == null || productId == 0 || productDescription == null) {
////			throw new Exception("Error :");
////		}
////		System.out.println("validateCarsProduct function");
////		Optional<CarsProducts> carsProducts = db.carsProductRepository
////				.findByProductsCodeAndProductsInsuranceId(productCode, productInsuranceId);
////
////		if (!carsProducts.isPresent()) {
////			CarsProducts carsProductsNew = new CarsProducts();
////			carsProductsNew.setProductsCode(productCode);
////			carsProductsNew.setProductsId(productId.toString());
////			carsProductsNew.setProductsInsuranceId(productInsuranceId);
////			carsProductsNew.setProductsDescription(productDescription);
////			carsProductsNew.setSysVersionNumber(0);
////			carsProductsNew.setSysCreatedBy(CREATED_BY_QUARTZ);
////			carsProductsNew.setSysUpdatedBy(CREATED_BY_QUARTZ);
////			carsProductsNew.setSysCreatedDate(new Timestamp(new Date().getTime()));
////			carsProductsNew.setSysUpdatedDate(new Timestamp(new Date().getTime()));
////			// carsProducts.setSysCreatedBy(new Timestamp(new Date().getTime()));
////			System.out.println("validateCarsProduct function1");
////			db.carsProductRepository.save(carsProductsNew);
////			return carsProductsNew;
////		}
////
////		if (carsProducts.isPresent()) {
////			carsProducts.get().setProductsDescription(productDescription);
////			carsProducts.get().setSysUpdatedBy(CREATED_BY_QUARTZ);
////			carsProducts.get().setSysUpdatedDate(new Timestamp(new Date().getTime()));
////			db.carsProductRepository.save(carsProducts.get());
////
////		}
////		System.out.println("validateCarsProduct function2");
////		return carsProducts.get();
////	}
//
//	public CarsProducts validateCarsProduct(String productCode, String productDescription, String productInsuranceId)
//			throws Exception {
//		if (productCode == null || productDescription == null) {
//			SendingMail sendingMail = new SendingMail();
//			try {
//				sendingMail.run("Banker Policy Upload Error ","ERROR OCCURED on upload of policyNumber");
//			} catch (Exception e) {
//				e.printStackTrace();
//			//	logger.error(e);
//			}
//			throw new Exception("Error :");
//		}
//		System.out.println("validateCarsProduct function");
//		Optional<CarsProducts> carsProducts = db.carsProductRepository
//				.findByProductsCodeAndProductsInsuranceId(productCode, productInsuranceId);
//
//		if (!carsProducts.isPresent()) {
//			CarsProducts carsProductsNew = new CarsProducts();
//			carsProductsNew.setProductsCode(productCode);
//			carsProductsNew.setProductsId(productInsuranceId + "." + productCode);
//			carsProductsNew.setProductsInsuranceId(productInsuranceId);
//			carsProductsNew.setProductsDescription(productDescription);
//			carsProductsNew.setSysVersionNumber(0);
//			carsProductsNew.setSysCreatedBy(CREATED_BY_QUARTZ);
//			carsProductsNew.setSysUpdatedBy(CREATED_BY_QUARTZ);
//			carsProductsNew.setSysCreatedDate(new Timestamp(new Date().getTime()));
//			carsProductsNew.setSysUpdatedDate(new Timestamp(new Date().getTime()));
//			// carsProducts.setSysCreatedBy(new Timestamp(new Date().getTime()));
//			System.out.println("validateCarsProduct function1");
//			db.carsProductRepository.save(carsProductsNew);
//			return carsProductsNew;
//		}
//
//		if (carsProducts.isPresent()) {
//			carsProducts.get().setProductsDescription(productDescription);
//			carsProducts.get().setSysUpdatedBy(CREATED_BY_QUARTZ);
//			carsProducts.get().setSysUpdatedDate(new Timestamp(new Date().getTime()));
//			db.carsProductRepository.save(carsProducts.get());
//
//		}
//		System.out.println("validateCarsProduct function2");
//		return carsProducts.get();
//	}
//
//	public CarsBranch validateCarsBranch(String branchCode, String branchInsuranceId, String branchDescription)
//			throws Exception {
//		if (branchCode == null || branchDescription == null) {
//			throw new Exception("Error :");
//		}
//		String branchCodeNew = branchCode;
//		branchCodeNew = branchCodeNew.replaceFirst("^0+(?=.)", "");
//		System.out.println("validateCarsBranch function");
//		Optional<CarsBranch> carsBranch = db.carsBranchRepository.findByBranchCodeAndBranchInsuranceId(branchCodeNew,
//				branchInsuranceId);
//
//		if (!carsBranch.isPresent()) {
//
//			CarsBranch carsBranchNew = new CarsBranch();
//			carsBranchNew.setBranchCode(branchCodeNew);
//			carsBranchNew.setBranchId(insuranceCode + "." + branchCodeNew);// ask jean
//			carsBranchNew.setBranchInsuranceId(branchInsuranceId);
//			carsBranchNew.setBranchDes(branchDescription);
//			carsBranchNew.setSysVersionNumber(0);
//			carsBranchNew.setSysCreatedBy(CREATED_BY_QUARTZ);
//			carsBranchNew.setSysUpdatedBy(CREATED_BY_QUARTZ);
//			carsBranchNew.setSysCreatedDate(new Timestamp(new Date().getTime()));
//			carsBranchNew.setSysUpdatedDate(new Timestamp(new Date().getTime()));
//			// carsProducts.setSysCreatedBy(new Timestamp(new Date().getTime()));
//			db.carsBranchRepository.save(carsBranchNew);
//			System.out.println("validateCarsBranch function1");
//			return carsBranchNew;
//		}
//
//		// Optional<CarsBranch> carsBranch =
//		// db.carsBranchRepository.findByBranchCode(branchCode);
//		if (carsBranch.isPresent()) {
//			carsBranch.get().setBranchDes(branchDescription);
//			carsBranch.get().setSysUpdatedBy(CREATED_BY_QUARTZ);
//			carsBranch.get().setSysUpdatedDate(new Timestamp(new Date().getTime()));
//			db.carsBranchRepository.save(carsBranch.get());
//
//		}
//		System.out.println("validateCarsBranch function2");
//		return carsBranch.get();
//	}
//
////	public CarsSubline validateCarsSubline(String sublineCode, String sublineInsuranceId, String sublineDescription)
////			throws Exception {
////		if (sublineCode == null || sublineDescription == null) {
////			throw new Exception("Error :");
////		}
////		System.out.println("validateCarsSubline function");
////		Optional<CarsSubline> carsSublineOpt = db.carsSublineRepository
////				.findBySublineCodeAndSublineInsuranceId(sublineCode, sublineInsuranceId);
////
////		if (!carsSublineOpt.isPresent()) {
////			CarsSubline carsSubline = new CarsSubline();
////			carsSubline.setSublineCode(sublineCode);
////			carsSubline.setSublineDes(sublineDescription);
////			carsSubline.setSublineInsuranceId(sublineInsuranceId+"."+sublineCode);
////			carsSubline.setSysVersionNumber(0);
////			carsSubline.setSysCreatedBy(CREATED_BY_QUARTZ);
////			carsSubline.setSysUpdatedBy(CREATED_BY_QUARTZ);
////			carsSubline.setSysCreatedDate(new Timestamp(new Date().getTime()));
////			carsSubline.setSysUpdatedDate(new Timestamp(new Date().getTime()));
////			System.out.println("validateCarsSubline function1");
////			// carsProducts.setSysCreatedBy(new Timestamp(new Date().getTime()));
////			db.carsSublineRepository.save(carsSubline);
////			return carsSubline;
////		}
////
////		Optional<CarsSubline> carsSubline = db.carsSublineRepository.findBySublineCodeAndSublineInsuranceId(sublineCode,
////				sublineInsuranceId);
////		if (carsSubline.isPresent()) {
////			carsSubline.get().setSublineDes(sublineDescription);
////			carsSubline.get().setSysUpdatedBy(CREATED_BY_QUARTZ);
////			carsSubline.get().setSysUpdatedDate(new Timestamp(new Date().getTime()));
////			db.carsSublineRepository.save(carsSubline.get());
////
////		}
////		System.out.println("validateCarsSubline function1");
////		return carsSubline.get();
////	}
//	public CarsSubline validateCarsSubline(String sublineCode, String sublineInsuranceId, String sublineDescription,
//			Integer sublineId) throws Exception {
//		if (sublineCode == null || sublineDescription == null) {
//			throw new Exception("Error :");
//		}
//		System.out.println("validateCarsSubline function");
//		Optional<CarsSubline> carsSublineOpt = db.carsSublineRepository
//				.findBySublineCodeAndSublineInsuranceId(sublineCode, sublineInsuranceId);
//
//		if (!carsSublineOpt.isPresent()) {
//			CarsSubline carsSubline = new CarsSubline();
//			carsSubline.setSublineCode(sublineCode);
//			carsSubline.setSublineDes(sublineDescription);
//			carsSubline.setSublineInsuranceId(sublineInsuranceId);
//			carsSubline.setSublineId(sublineInsuranceId + "." + sublineId);
//			carsSubline.setSysVersionNumber(0);
//			carsSubline.setSysCreatedBy(CREATED_BY_QUARTZ);
//			carsSubline.setSysUpdatedBy(CREATED_BY_QUARTZ);
//			carsSubline.setSysCreatedDate(new Timestamp(new Date().getTime()));
//			carsSubline.setSysUpdatedDate(new Timestamp(new Date().getTime()));
//			System.out.println("validateCarsSubline function1");
//			// carsProducts.setSysCreatedBy(new Timestamp(new Date().getTime()));
//			db.carsSublineRepository.save(carsSubline);
//			return carsSubline;
//		}
//
//		Optional<CarsSubline> carsSubline = db.carsSublineRepository.findBySublineCodeAndSublineInsuranceId(sublineCode,
//				sublineInsuranceId);
//		if (carsSubline.isPresent()) {
//			carsSubline.get().setSublineDes(sublineDescription);
//			carsSubline.get().setSysUpdatedBy(CREATED_BY_QUARTZ);
//			carsSubline.get().setSysUpdatedDate(new Timestamp(new Date().getTime()));
//			db.carsSublineRepository.save(carsSubline.get());
//
//		}
//		System.out.println("validateCarsSubline function1");
//		return carsSubline.get();
//	}
//
//	public CarsClient validateCarsClient(String clientId, String clientCode, String clientInsuranceId, String printName,
//			String firstInsuredName, String fatherInsuredName, String lastInsuredName, String insuredPhoneNumber,
//			boolean blackListed, String reason, String note) throws Exception {
//		if (clientId == null || clientCode == null) {
//			throw new Exception("Error :");
//		}
//		System.out.println("validateCarsClient function");
//
//		String clientCodeNew = clientCode;
//		if (clientCodeNew != null) {
//			clientCodeNew = clientCodeNew.replaceFirst("-", "");
//		}
//		Optional<CarsClient> carsClient = db.carsClientRepository
//				.findByClientInsuranceIdAndClientNum1(clientInsuranceId, Long.valueOf(clientCodeNew));
//
//		if (!carsClient.isPresent()) {
//
//			CarsClient carsClientNew = new CarsClient();
//			// carsClientNew.setClientId(UUID.randomUUID().toString());
//			carsClientNew.setClientId(clientInsuranceId + "." + Long.valueOf(clientCodeNew) + "." + "0");
//			carsClientNew.setClientNum1(Long.valueOf(clientCodeNew));
//			carsClientNew.setClientNum2(Long.valueOf(0));
//			carsClientNew.setClientInsuranceId(clientInsuranceId);
//			carsClientNew.setClientFamilyName(lastInsuredName);
//			carsClientNew.setClientFatherName(fatherInsuredName);
//			carsClientNew.setClientFirstName(firstInsuredName);
//			// insuredPhoneNumber = insuredPhoneNumber.replace("-", "");
//			carsClientNew.setClientMobilePhone(insuredPhoneNumber);
//			carsClientNew.setSysVersionNumber(0);
//			carsClientNew.setSysCreatedBy(CREATED_BY_QUARTZ);
//			carsClientNew.setSysUpdatedBy(CREATED_BY_QUARTZ);
//			carsClientNew.setSysCreatedDate(new Timestamp(new Date().getTime()));
//			carsClientNew.setSysUpdatedDate(new Timestamp(new Date().getTime()));
//			// carsClientNew.setClientP
//			db.carsClientRepository.save(carsClientNew);
//			if (blackListed == true) {
//				validateInsuranceBlackList(clientCodeNew, clientInsuranceId, firstInsuredName, fatherInsuredName,
//						lastInsuredName, reason, note);
//			}
//			System.out.println("validateCarsClient function");
//			return carsClientNew;
//		}
////		Optional<CarsClient> carsClient = db.carsClientRepository.findByClientInsuranceIdAndClientNum1(clientInsuranceId,
////				Long.valueOf(clientCode));
//		if (carsClient.isPresent()) {
//			// carsClient.get().setClientId(clientInsuranceId+"."+Long.valueOf(clientCodeNew));
//			carsClient.get().setSysUpdatedBy(CREATED_BY_QUARTZ);
//			carsClient.get().setSysUpdatedDate(new Timestamp(new Date().getTime()));
//			carsClient.get().setClientFamilyName(lastInsuredName);
//			carsClient.get().setClientFatherName(fatherInsuredName);
//			carsClient.get().setClientFirstName(firstInsuredName);
//			carsClient.get().setClientMobilePhone(insuredPhoneNumber);
//			carsClient.get().setSysUpdatedBy(CREATED_BY_QUARTZ);
//			carsClient.get().setSysUpdatedDate(new Timestamp(new Date().getTime()));
//			if (blackListed == true) {
//				validateInsuranceBlackList(clientCodeNew, clientInsuranceId, firstInsuredName, fatherInsuredName,
//						lastInsuredName, reason, note);
//			}
//			db.carsClientRepository.save(carsClient.get());
//			System.out.println("validateCarsClient function");
//		}
//		return carsClient.get();
//	}
//
//	public CarsBroker validateCarsBroker(String brokerCode, String brokerId, String brokerInsuranceId,
//			String brokerName, String brokerPhoneNumber, Boolean isDirectBroker, String brokerMail,
//			Boolean brokerWebOrigin, Boolean blackListed, String reason, String note) throws Exception {
////		if (brokerCode.isEmpty() || brokerId.isEmpty()) {
////			throw new Exception("Error :");
////		}
//		System.out.println("validateCarsBroker function");
//		Optional<CarsBroker> carsBroker = db.carsBrokerRepository.findByBrokerIdAndBrokerInsuranceId(brokerId,
//				brokerInsuranceId);
//
//		if (Utility.isEmpty(brokerName)) {
//			brokerName = brokerCode;
//		}
//		if (!carsBroker.isPresent()) {
//			CarsBroker carsBrokerNew = new CarsBroker();
//			carsBrokerNew.setBrokerId(brokerInsuranceId + "." + brokerCode);
//			carsBrokerNew.setBrokerDesc(brokerName);
//			carsBrokerNew.setBrokerNum(brokerCode);
//			carsBrokerNew.setBrokerInsuranceId(brokerInsuranceId);
//			carsBrokerNew.setBrokerTelephone(brokerPhoneNumber);
//			carsBrokerNew.setBrokerReference(brokerId);
//			carsBrokerNew.setSysVersionNumber(0);
//			carsBrokerNew.setSysCreatedBy(CREATED_BY_QUARTZ);
//			carsBrokerNew.setSysUpdatedBy(CREATED_BY_QUARTZ);
//			carsBrokerNew.setSysCreatedDate(new Timestamp(new Date().getTime()));
//			carsBrokerNew.setSysUpdatedDate(new Timestamp(new Date().getTime()));
//			db.carsBrokerRepository.save(carsBrokerNew);
//
//			if (blackListed == true) {
//				validateBrokerBlackList(brokerCode, brokerId, brokerInsuranceId, brokerName, brokerPhoneNumber, reason,
//						note);
//			}
//			System.out.println("validateCarsBroker function1");
//			return carsBrokerNew;
//		}
//
//		// Optional<CarsBroker> carsBroker = db.carsBrokerRepository.findById(brokerId);
//		if (carsBroker.isPresent()) {
//			carsBroker.get().setBrokerDesc(brokerName);
//			carsBroker.get().setBrokerTelephone(brokerPhoneNumber);
//			carsBroker.get().setSysUpdatedBy(CREATED_BY_QUARTZ);
//			carsBroker.get().setSysUpdatedDate(new Timestamp(new Date().getTime()));
//			db.carsBrokerRepository.save(carsBroker.get());
//		}
//		if (blackListed == true) {
//			validateBrokerBlackList(brokerCode, brokerId, brokerInsuranceId, brokerName, brokerPhoneNumber, reason,
//					note);
//		}
//		return carsBroker.get();
//	}
//
//	public void validateInsuranceBlackList(String insuredCode, String clientInsuranceId, String firstInsuredName,
//			String fatherInsuredName, String lastInsuredName, String reason, String note) {
//		System.out.println("validateCarsBroker function");
//		Collection<CarsBlackList> carsBlackListList = db.carsBlackListRepository
//				.findByBlInsuranceIdAndClientNum(clientInsuranceId, insuredCode);
//
//		if (carsBlackListList == null || carsBlackListList == null) {
//			CarsBlackList carsBlackList = new CarsBlackList();
//			carsBlackList.setBlId(UUID.randomUUID().toString());
//			carsBlackList.setBlInsuranceId(clientInsuranceId);
//			carsBlackList.setClientNum(insuredCode);
//			carsBlackList.setBlFirstName(firstInsuredName);
//			carsBlackList.setBlFatherName(fatherInsuredName);
//			carsBlackList.setBlFamilyName(lastInsuredName);
//			carsBlackList.setBlNote(reason + " " + note);
//			carsBlackList.setSysVersionNumber(0);
//			carsBlackList.setSysCreatedBy(CREATED_BY_QUARTZ);
//			carsBlackList.setSysUpdatedBy(CREATED_BY_QUARTZ);
//			carsBlackList.setSysCreatedDate(new Timestamp(new Date().getTime()));
//			carsBlackList.setSysUpdatedDate(new Timestamp(new Date().getTime()));
//			db.carsBlackListRepository.save(carsBlackList);
//
//		}
//
//		Optional<CarsBlackList> carsBroker = db.carsBlackListRepository.findByClientNum(insuredCode);
//		if (carsBroker.isPresent()) {
//			carsBroker.get().setBlFirstName(firstInsuredName);
//			carsBroker.get().setBlFatherName(fatherInsuredName);
//			carsBroker.get().setBlFamilyName(lastInsuredName);
//			carsBroker.get().setBlNote(reason + " " + note);
//			carsBroker.get().setSysUpdatedBy(CREATED_BY_QUARTZ);
//			carsBroker.get().setSysUpdatedDate(new Timestamp(new Date().getTime()));
//			db.carsBlackListRepository.save(carsBroker.get());
//		}
//		System.out.println("validateCarsBroker function1");
//	}
//
//	public void validateBrokerBlackList(String brokerCode, String brokerId, String brokerInsuranceId, String brokerName,
//			String brokerPhoneNumber, String reason, String note) {
//
//		Collection<CarsBlackList> carsBlackListList = db.carsBlackListRepository
//				.findByBlInsuranceIdAndClientNum(brokerInsuranceId, brokerCode);
//
//		if (carsBlackListList == null || carsBlackListList == null) {
//			CarsBlackList carsBlackList = new CarsBlackList();
//			carsBlackList.setBlId(UUID.randomUUID().toString());
//			carsBlackList.setBlInsuranceId(brokerInsuranceId);
//			carsBlackList.setBlBrokerId(brokerId);
//			carsBlackList.setBlFamilyName(brokerName);
//			carsBlackList.setBlNote(reason + " " + note);
//			carsBlackList.setSysVersionNumber(0);
//			carsBlackList.setSysCreatedBy(CREATED_BY_QUARTZ);
//			carsBlackList.setSysUpdatedBy(CREATED_BY_QUARTZ);
//			carsBlackList.setSysCreatedDate(new Timestamp(new Date().getTime()));
//			carsBlackList.setSysUpdatedDate(new Timestamp(new Date().getTime()));
//			db.carsBlackListRepository.save(carsBlackList);
//
//		}
//
//		Optional<CarsBlackList> carsBroker = db.carsBlackListRepository.findByClientNum(insuranceCode);
//		if (carsBroker.isPresent()) {
//
//			carsBroker.get().setBlFamilyName(brokerName);
//			carsBroker.get().setBlNote(reason + " " + note);
//			carsBroker.get().setSysUpdatedBy(CREATED_BY_QUARTZ);
//			carsBroker.get().setSysUpdatedDate(new Timestamp(new Date().getTime()));
//			db.carsBlackListRepository.save(carsBroker.get());
//		}
//
//	}
//
//	public String validatePremiumCurrency(String premCurrency, String premCurrencyDescription, String PremExchangeRate)
//			throws Exception {
//		if (premCurrency == null || premCurrencyDescription == null || PremExchangeRate == null) {
//			throw new Exception("Error :");
//		}
//
//		Optional<CarsDtCurrency> carsDtCurrency = db.carsDtCurrencyRepository.findByInsCurrencyCode(premCurrency);
//		if (carsDtCurrency.isPresent()) {
//			return carsDtCurrency.get().getCeCode();
//		}
//
//		return null;
//
//	}
//
//	public String validateSICurrency(String siCurrency, String siCurrencyDescription, String siExchangeRate)
//			throws Exception {
//		if (siCurrency == null || siCurrencyDescription == null || siExchangeRate == null) {
//			throw new Exception("Error :");
//		}
//
//		Optional<CarsDtCurrency> carsDtCurrency = db.carsDtCurrencyRepository.findByInsCurrencyCode(siCurrency);
//		if (carsDtCurrency.isPresent()) {
//			return carsDtCurrency.get().getCeCode();
//		}
//
//		return null;
//
//	}
//
//	public String populateTrademark(StringBuffer stringBufferTrademark, String id,
//			Collection<CarsDtModel> dataTransferModelList) {
//
//		if (dataTransferModelList != null && !dataTransferModelList.isEmpty()) {
//			String modelCode = dataTransferModelList.iterator().next().getTrademarkId();
//			if (!".".equals(modelCode) && !"".equals(modelCode) && modelCode != null) {
//				return modelCode;
//			} else {
//				stringBufferTrademark.append(id + "\n");
//				i++;
//				return "ZZ.ZZZZ";
//			}
//		} else {
//			stringBufferTrademark.append(id + "\n");
//			i++;
//			return "ZZ.ZZZZ";
//		}
//	}
//
//	public String populateBrand(StringBuffer stringBufferTrademark, String id,
//			Collection<CarsDtModel> dataTransferModelList) {
//		if (dataTransferModelList != null && !dataTransferModelList.isEmpty()) {
//
//			String trademark = dataTransferModelList.iterator().next().getTrademarkId();
//			if (trademark != null) {
//				String makeCode = trademark.substring(0, trademark.lastIndexOf("."));
//
//				if (!".".equals(makeCode) && !"".equals(makeCode)) {
//					return makeCode;
//				} else {
//					stringBufferTrademark.append(id + "\n");
//					i++;
//					return "ZZ";
//				}
//			} else {
//				stringBufferTrademark.append(id + "\n");
//				i++;
//				return "ZZ";
//			}
//
//		} else
//
//		{
//			stringBufferTrademark.append(id + "\n");
//			i++;
//			return "ZZ";
//		}
//	}
//
//	public String populateShape(StringBuffer stringBufferTrademark, String id,
//			Collection<CarsDtModel> dataTransferModelList) {
//
//		if (dataTransferModelList != null && !dataTransferModelList.isEmpty()) {
//			String value = dataTransferModelList.iterator().next().getTrademarkId();
//			if (!".".equals(value) && !"".equals(value) && value != null) {
//				return value + ".ZZZZZ";
//			} else {
//				stringBufferTrademark.append(id + "\n");
//				i++;
//				return "ZZ.ZZZZ.ZZZZZ";
//			}
//
//		} else {
//			stringBufferTrademark.append(id + "\n");
//			i++;
//			return "ZZ.ZZZZ.ZZZZZ";
//		}
//	}
//
//	public void insertFleet(Clauses clauses, Integer vehiclePolicyId) {
//
//		CarsDtClauseContract carsDtClauseContract = new CarsDtClauseContract();
//		carsDtClauseContract.setDtId(UUID.randomUUID().toString());
//		carsDtClauseContract.setInsID("10");
//		carsDtClauseContract.setOrder(clauses.getOrder().toString());
//		carsDtClauseContract.setShortDesc(clauses.getDescription());
//		carsDtClauseContract.setPolicyID(vehiclePolicyId.toString());
//		carsDtClauseContract.setCode(clauses.getCode());
//		carsDtClauseContract.setClauseText(clauses.getContent());
//		// db.carsPolicyCoverRepositoryC.save(carsDtClauseContract);
//	}
//
//	public void insertIndividual(Clauses clauses, Integer vehiclePolicyId) {
//
//		CarsDtClauseContract carsDtClauseContract = new CarsDtClauseContract();
//		carsDtClauseContract.setDtId(UUID.randomUUID().toString());
//		carsDtClauseContract.setInsID("10");
//		carsDtClauseContract.setOrder(clauses.getOrder().toString());
//		carsDtClauseContract.setShortDesc(clauses.getDescription());
//		carsDtClauseContract.setPolicyID(vehiclePolicyId.toString());
//		carsDtClauseContract.setCode(clauses.getCode());
//		carsDtClauseContract.setClauseText(clauses.getContent());
//		// carsDtClauseContract.save(carsDtClauseContract);
//	}
//
//	public String insertCarsPolicyCar(Vehicles vehicles, String policyId, Collection<CarsDtModel> dataTransferModelList,
//			StringBuffer stringBufferTrademark) {
//		CarsPolicyCar carsPolicyCar = new CarsPolicyCar();
//		carsPolicyCar.setCarId(UUID.randomUUID().toString());
////	carsPolicyCar.setCarObjectNumber(dataTransferPolicyLoaded.getPolicyCertificate());
//		carsPolicyCar.setCarYear(Integer.valueOf(vehicles.getCarYear()));
//		carsPolicyCar.setCarChassis(vehicles.getCarChassis());
//		carsPolicyCar.setCarBrandCode(null);
//		carsPolicyCar.setCarTrademarkCode(null);
//		carsPolicyCar.setCarShapeCode(null);
//		carsPolicyCar.setCarInsurance(Integer.valueOf(insuranceCode));
//		carsPolicyCar.setCarLob("MO");
//		String insValue =vehicles.getCarInsVal();
//		insValue=insValue.replaceAll("[,]", "");
//		 carsPolicyCar.setCarInsuranceValue(BigDecimal.valueOf(Double.valueOf(insValue)));
//		// carsPolicyCar.setcarI(null);
////	carsPolicyCar.setCarType(vehicles.getPolicyType());
//		// carsPolicyCar.setCarUsageCode(vehicles.getCarUsage()); hjean
//		carsPolicyCar.setCarPlate(vehicles.getCarPlatePrefix() + " " + vehicles.getCarPlateNumber());// to ask
//		carsPolicyCar.setCarColor(null);
//		carsPolicyCar.setCarDepreciation(null);
//		carsPolicyCar.setCarTpDepreciation(null);
//		carsPolicyCar.setCarBrandDesc(vehicles.getCarMake());
//		carsPolicyCar.setCarTrademarkDesc(vehicles.getCarModel());
//
//		// carsPolicyCar.setCarUsage(vehicles.getCarUsage()); jeannnnnnnnn
//		carsPolicyCar.setCarEngine(vehicles.getCarEngine());
//		// carsPolicyCar.setCarDeductible(vehicles.getDeductible());
//		carsPolicyCar.setCarTheetDeductible(null);
//		carsPolicyCar.setCarHoldupDeductible(null);
//		carsPolicyCar.setCarProductDeductible(null);
//		carsPolicyCar.setCarVip(null);
//		carsPolicyCar.setPolicyId(policyId);
//		carsPolicyCar.setPolicyDealerWorkshopName(vehicles.getDealerWorkshopName());
//		carsPolicyCar.setShapeId(
//				populateShape(stringBufferTrademark, vehicles.getPolicyID().toString(), dataTransferModelList));
//
////		carsPolicyCar.setShapeId(populateShape(stringBufferTrademark,
////				policyId + ":" + dataTransferPolicyLoaded.getPolicyCarMake(),
////				dataTransferModelList));
//
//		carsPolicyCar.setCarBeneficiary(null);
//		carsPolicyCar.setCarBeneficiaryDesc(null);
//		carsPolicyCar.setBrandId(
//				populateBrand(stringBufferTrademark, vehicles.getPolicyID().toString(), dataTransferModelList));
//		carsPolicyCar.setTrademarkId(
//				populateTrademark(stringBufferTrademark, vehicles.getPolicyID().toString(), dataTransferModelList));
//		carsPolicyCar.setSysVersionNumber(0);
//		carsPolicyCar.setSysCreatedBy(CREATED_BY_QUARTZ);
//		carsPolicyCar.setSysUpdatedBy(CREATED_BY_QUARTZ);
//		carsPolicyCar.setSysCreatedDate(new Timestamp(new Date().getTime()));
//		carsPolicyCar.setSysUpdatedDate(new Timestamp(new Date().getTime()));
//		db.carsPolicyCarRepository.save(carsPolicyCar);
//		// CarsPolicyCarFactory.getService().insert(carsPolicyCar);
//		return carsPolicyCar.getCarId();
//	}
//
//	public String updateCarsPolicyCar(Vehicles vehicles, String policyId, Collection<CarsPolicyCar> carsPolicyCarList,
//			Collection<CarsDtModel> dataTransferModelList, StringBuffer stringBufferTrademark) {
//		System.out.println("CarsPolicyCar do update for ");
//		CarsPolicyCar carsPolicyCarIterator = carsPolicyCarList.iterator().next();
//		CarsPolicyCar carsPolicyCarToLoad = new CarsPolicyCar();
//		Optional<CarsPolicyCar> carsPolicy = db.carsPolicyCarRepository.findById(carsPolicyCarIterator.getCarId());
//		System.out.println("updateCarsPolicyCar function");
//		CarsPolicyCar carsPolicyCarToSave = carsPolicy.get();
//		carsPolicyCarToSave.setCarYear(Integer.valueOf(vehicles.getCarYear()));
//		carsPolicyCarToSave.setCarChassis(vehicles.getCarChassis());
//		carsPolicyCarToSave.setCarBrandCode(null);
//		carsPolicyCarToSave.setCarTrademarkCode(null);
//		carsPolicyCarToSave.setCarShapeCode(null);
//		String insValue =vehicles.getCarInsVal();
//		insValue=insValue.replaceAll("[,]", "");
//		carsPolicyCarToSave.setCarInsuranceValue(BigDecimal.valueOf(Double.valueOf(insValue)));
////	carsPolicyCar.setCarType(vehicles.getPolicyType());
//		// carsPolicyCarToSave.setCarUsageCode(vehicles.getCarUsage()); check with jean
//		// this field
//		carsPolicyCarToSave.setCarPlate(vehicles.getCarPlatePrefix() + " " + vehicles.getCarPlateNumber());// to as
//
//		carsPolicyCarToSave.setCarColor(null);
//		carsPolicyCarToSave.setCarDepreciation(null);
//		carsPolicyCarToSave.setCarTpDepreciation(null);
//		carsPolicyCarToSave.setCarBrandDesc(vehicles.getCarMake());
//		carsPolicyCarToSave.setCarTrademarkDesc(vehicles.getCarModel());
//		// carsPolicyCarToSave.setCarUsage(vehicles.getCarUsage()); jeannnnnnnnn
//		carsPolicyCarToSave.setCarEngine(vehicles.getCarEngine());
//		// carsPolicyCar.setCarDeductible(vehicles.getDeductible());
//		carsPolicyCarToSave.setCarTheetDeductible(null);
//
//		carsPolicyCarToSave.setCarHoldupDeductible(null);
//		carsPolicyCarToSave.setCarProductDeductible(null);
//		carsPolicyCarToSave.setCarVip(null);
//		carsPolicyCarToSave.setPolicyId(policyId);
//		System.out.println("updateCarsPolicyCar function");
//		carsPolicyCarToSave.setShapeId(
//				populateShape(stringBufferTrademark, vehicles.getPolicyID().toString(), dataTransferModelList));
//		carsPolicyCarToSave.setCarBeneficiary(null);
//		carsPolicyCarToSave.setCarBeneficiaryDesc(null);
//		carsPolicyCarToSave.setBrandId(
//				populateBrand(stringBufferTrademark, vehicles.getPolicyID().toString(), dataTransferModelList));
//		carsPolicyCarToSave.setTrademarkId(
//				populateTrademark(stringBufferTrademark, vehicles.getPolicyID().toString(), dataTransferModelList));
//		carsPolicyCarToSave.setSysVersionNumber(0);
//		carsPolicyCarToSave.setSysUpdatedBy(CREATED_BY_QUARTZ);
//		carsPolicyCarToSave.setSysUpdatedDate(new Timestamp(new Date().getTime()));
//		db.carsPolicyCarRepository.save(carsPolicyCarToSave);
//		System.out.print("updateCarPolicyCar");
//		return carsPolicyCarToSave.getCarId();
//	}
//
//	public String insertPolicyCar(PolicyVehicle policyVehicle, String productId, String branchId, String clientId,
//			String brokerId, String subLine, String insuranceId) {
//		CarsPolicy carsPolicy = new CarsPolicy();
//		carsPolicy.setPolicyId(UUID.randomUUID().toString());
//		// if (vehicle.getPolicyBranch() != null) {
//		// carsPolicy.setPolicyBranch(dataTransferPolicyLoaded.getPolicyBranch());
//		// }
//		carsPolicy.setPolicyNumberDisplay(policyVehicle.getPolicy().getPolicyNo());
//		carsPolicy.setPolicyLob("MO");
//		String policyNumber = policyVehicle.getPolicy().getPolicyNo();
////		Optional<CarsProducts> carsProducts = db.carsProductRepository.findById(productId);
////		String[] arrOfStr = policyNumber.split("-", 2);
////		policyNumber = arrOfStr[1].replaceAll("[^0-9]", "");
//		
//		//Optional<CarsProducts> carsProducts = db.carsProductRepository.findById(productId);
//		String[] arrOfStr = policyNumber.split("-", 4);
//		policyNumber = arrOfStr[2]+"-"+arrOfStr[3];
//		// carsPolicy.setPolicyNumber(policyVehicle.getPolicy().getPolicyNo());
//		//if (carsProducts.isPresent()) {
//		//	policyNumber = carsProducts.get().getProductsReference() + policyNumber;
//			policyNumber = policyNumber;
//		//}
//		carsPolicy.setPolicyNumber(policyNumber);
//		carsPolicy.setPolicyProduct(policyVehicle.getPolicy().getSubLineCode());// ask jean size should be 4
//		String insuredCode = policyVehicle.getPolicy().getInsuredCode();
//		if (insuredCode != null) {
//			insuredCode = insuredCode.replace("-", "");
//		}
//		carsPolicy.setPolicyClient(Long.valueOf(insuredCode));
//		// carsPolicy.setPre
//		carsPolicy.setPolicyBrokerNum(policyVehicle.getPolicy().getBrokerCode());
//		try {
//			carsPolicy.setPolicyCurrency(validateSICurrency(policyVehicle.getPolicy().getsICurrency(),
//					policyVehicle.getPolicy().getsICurrencyDescription(),
//					policyVehicle.getPolicy().getsIExchangeRate().toString()));
//			carsPolicy.setPolicySumInsCurrencyRate(policyVehicle.getPolicy().getsIExchangeRate());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		carsPolicy.setPolicyAction(policyVehicle.getVehicle().getCarStatus());
//		carsPolicy.setPolicyPremiumCurrency(policyVehicle.getPolicy().getPremCurrency());
//		carsPolicy.setPolicyPremiumCurrencyRate(policyVehicle.getPolicy().getPremExchangeRate());
//		carsPolicy.setPolicyBlacklistSetBy(policyVehicle.getPolicy().getSetBy());
//		carsPolicy.setPolicyBlacklistSetOn(policyVehicle.getPolicy().getSetOn());
//		carsPolicy.setPolicyModifiedUser(policyVehicle.getPolicy().getModifiedByUser());
//		carsPolicy.setPolicyIssuingUser(policyVehicle.getPolicy().getIssuingUser());
//		carsPolicy.setPolicyClientId(clientId);
//		if (policyVehicle.getPolicy().getIsWebPolicy() == true) {
//			carsPolicy.setPolicyWeb("Y");
//		} else {
//			carsPolicy.setPolicyWeb("N");
//		}
//		if (policyVehicle.getPolicy().getIsDirectBroker() == true) {
//			carsPolicy.setPolicyIsDirectBroker("Y");
//		} else {
//			carsPolicy.setPolicyIsDirectBroker("N");
//		}
//		if (policyVehicle.getPolicy().getBrokerWebOrigin() == true) {
//			carsPolicy.setPolicyBrokerWebOrigin("Y");
//		} else {
//			carsPolicy.setPolicyBrokerWebOrigin("N");
//		}
//
//		if (policyVehicle.getVehicle().getCertificateNo() != null) {
//			carsPolicy.setPolicyCar(Integer.valueOf(policyVehicle.getVehicle().getCertificateNo()));
//		} else {
//			carsPolicy.setPolicyCar(0);
//		}
//		if (policyVehicle.getPolicy().getEndorsementNo() != null) {
//			carsPolicy.setPolicyAmendment(Integer.valueOf(policyVehicle.getPolicy().getEndorsementNo()));
//		} else {
//			carsPolicy.setPolicyAmendment(0);
//		}
//
//		carsPolicy.setPolicyPrintName(convertText(policyVehicle.getPolicy().getPrintName()));
//		carsPolicy.setPolicyBlacklistSetBy(policyVehicle.getPolicy().getBrokerBlackSetBy());
//		carsPolicy.setPolicyBlacklistSetOn(policyVehicle.getPolicy().getBrokerBlackSetOn());
//		carsPolicy.setPolicyInsuranceBlacklistSetBy(policyVehicle.getPolicy().getInsBlackSetBy());
//		carsPolicy.setPolicyInsuranceBlacklistSetOn(policyVehicle.getPolicy().getInsBlackSetOn());
//		// if ("10".equals(insuranceId)) {
//
////		String actionTypeOriginale = DataTransferHeaderFileFactory.getService().getHeaderStateOriginal(
////				dataTransferPolicyLoaded.getPolicyProduct(), dataTransferPolicyLoaded.getPolicyNumber(),
////				dataTransferPolicyLoaded.getPolicyAmend(), insuranceId);
////
////		String actionTypeDecoded = Utility.getPropStringValues("decode." + actionTypeOriginale);
////		carsPolicy.setPolicyAction(actionTypeDecoded);
//
//		if (policyVehicle.getPolicy().getEndorsementNo() == null) {
//			carsPolicy.setPolicyAmendment(0);
//		} else {
//			carsPolicy.setPolicyAmendment(Integer.valueOf(policyVehicle.getPolicy().getEndorsementNo()));
//		}
//		// carsPolicy.setPolicyAmendment(0);
//
//		StringBuilder policyClauses = new StringBuilder();
//		if (policyVehicle.getPolicy().getReason() != null) {
//			policyClauses.append(policyVehicle.getPolicy().getReason() + " ");
//		}
//		if (policyVehicle.getPolicy().getNote() != null) {
//			policyClauses.append(policyVehicle.getPolicy().getNote() + " ");
//		}
////		if (policyVehicle.getPolicy().getMopNote() != null) {
////			policyClauses.append(policyVehicle.getPolicy().getMopNote());
////		}
//		if (!policyVehicle.getPolicy().getMopNote().isEmpty()) {
//			carsPolicy.setPolicyNumberSpecialNotes(policyVehicle.getPolicy().getMopNote());
//		}
////		carsPolicy.setPolicyClauses(policyVehicle.getPolicy().getReason() + " " + policyVehicle.getPolicy().getNote()
////				+ " " + policyVehicle.getPolicy().getMopNote());
//		carsPolicy.setPolicyClauses(policyClauses.toString());
//		carsPolicy.setPolicyBroker(policyVehicle.getPolicy().getBrokerCode());
//		carsPolicy.setPolicyBrokerId((policyVehicle.getPolicy().getBrokerID().toString()));
//		if (policyVehicle.getVehicle().getPolicyID().signum() == 0) {
//			carsPolicy.setPolicyIdIns(policyVehicle.getPolicy().getPolicyID().toString());// ask jean aya policyId
//		} else {
//			carsPolicy.setPolicyIdIns(policyVehicle.getVehicle().getPolicyID().toString());
//		}
//		carsPolicy.setPolicySublineId(subLine);
//		if(policyVehicle.getPolicy().getEndAtNoon()==true) {
//			carsPolicy.setPolicyEndAtNoon("Y");
//		}else {
//			carsPolicy.setPolicyEndAtNoon("N");
//		}
//		// carsPolicy.setPolicyPlan(dataTransferPolicyLoaded.getPolicyPlan());
//		// carsPolicy.setPolicyEndors1(dataTransferPolicyLoaded.getPolicyEndorsement1());
//		// carsPolicy.setPolicyEndors2(dataTransferPolicyLoaded.getPolicyEndorsement2());
//		// carsPolicy.setPolicyAgencyRepair(dataTransferPolicyLoaded.getPolicyAgencyRepair());//
//		// dealer repair Y N check
//		// with moahamd
//		// carsPolicy.setPolicyCarReplacement(dataTransferPolicyLoaded.getPolicyCarReplacement());
//		
//		carsPolicy.setPolicyProductsId(productId);// same as policyId sent in json
//		carsPolicy.setPolicyClientId(clientId);
//		// carsPolicy.setPolicyTowFlag(dataTransferPolicyLoaded.getPolicyTowingCondition());
//		// carsPolicy.setPolicyTowFlag(dataTransferPolicyLoaded.getPolicyTowFlag());
//		// carsPolicy.setPolicyReplacementFlag(dataTransferPolicyLoaded.getPolicyReplacementFlag());
//		// if (dataTransferPolicyLoaded.getPolicyDeductibleAmount() != null) {
//		// carsPolicy.setPolicyDeductibleAmt(Integer.valueOf(dataTransferPolicyLoaded.getPolicyDeductibleAmount()));
//		// }
//		// carsPolicy.setPolicyDeductible(dataTransferPolicyLoaded.getPolicyDeductible());
//		carsPolicy.setPolicyBranchId(branchId);
//		carsPolicy.setPolicyBrokerId(brokerId);
//		carsPolicy.setPolicyInsuranceId(insuranceCode);
//
//		carsPolicy.setPolicyBlacklistSetBy(policyVehicle.getPolicy().getBrokerBlackSetBy());
//		carsPolicy.setPolicyBlacklistSetOn(policyVehicle.getPolicy().getBrokerBlackSetOn());
//		carsPolicy.setPolicyInsuranceBlacklistSetBy(policyVehicle.getPolicy().getInsBlackSetBy());
//		carsPolicy.setPolicyInsuranceBlacklistSetOn(policyVehicle.getPolicy().getInsBlackSetOn());
//		// carsPolicy.setPolicyIssueDate(policyVehicle.getPolicy().getPolicyIssueDate());//
//		// ask mohamad
//		// carsPolicy.setPolicyEffectiveDate(dataTransferPolicyLoaded.getPolicyEffectiveDate());
//		// and end at noon
//		// carsPolicy.setPolicyExpiryDate(dataTransferPolicyLoaded.getPolicyExpiryDate());
//		try {
//			Date IssueDate = new SimpleDateFormat("dd-MM-yyyy").parse(policyVehicle.getPolicy().getDateIssue());
//			carsPolicy.setPolicyIssueDate(new Timestamp(IssueDate.getTime()));// ask mohamad
//
//			Date effectiveDate = new SimpleDateFormat("dd-MM-yyyy")
//					.parse(policyVehicle.getVehicle().getDateEffective());
//			Date expiryDate = new SimpleDateFormat("dd-MM-yyyy").parse(policyVehicle.getVehicle().getDateExpiry());
//
//			if (policyVehicle.getPolicy().getEndAtNoon() == true) {
//				effectiveDate.setHours(12);
//				expiryDate.setHours(11);
//				expiryDate.setMinutes(59);
//				carsPolicy.setPolicyEffectiveDate(new Timestamp(effectiveDate.getTime()));
//				carsPolicy.setPolicyExpiryDate(new Timestamp(expiryDate.getTime()));
//			} else {
//				carsPolicy.setPolicyEffectiveDate(new Timestamp(effectiveDate.getTime()));
//				carsPolicy.setPolicyExpiryDate(new Timestamp(expiryDate.getTime()));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		;
//
//		StringBuilder policyRemark = new StringBuilder();
//		if (policyVehicle.getPolicy().getReason() != null) {
//			policyRemark.append(policyVehicle.getPolicy().getReason() + " ");
//		}
//		if (policyVehicle.getPolicy().getNote() != null) {
//			policyRemark.append(policyVehicle.getPolicy().getNote() + " ");
//		}
//		carsPolicy.setPolicyRemarks(policyRemark.toString());
//		carsPolicy.setSysVersionNumber(0);
//		carsPolicy.setSysCreatedBy(CREATED_BY_QUARTZ);
//		carsPolicy.setSysUpdatedBy(CREATED_BY_QUARTZ);
//		carsPolicy.setSysCreatedDate(new Timestamp(new Date().getTime()));
//		carsPolicy.setSysUpdatedDate(new Timestamp(new Date().getTime()));
//		db.carsPolicyRepository.save(carsPolicy);
//		System.out.print("insertCarPolicyCar");
//		return carsPolicy.getPolicyId();
//	}
//
//	public String updatePolicyCar(PolicyVehicle policyVehicle, String productId, String branchId, String clientId,
//			String brokerId, String subLine, Collection<CarsPolicy> carsPolicyToSearchList, String insuranceId) {
//		CarsPolicy carsPolicyLoad = carsPolicyToSearchList.iterator().next();
//		System.out.println("found for : " + carsPolicyLoad.getPolicyId());
//		CarsPolicy carsPolicyToLoad = new CarsPolicy();
//		carsPolicyToLoad.setPolicyId(carsPolicyLoad.getPolicyId());
//		Optional<CarsPolicy> carsPolicy = db.carsPolicyRepository.findById(carsPolicyLoad.getPolicyId());
//		if (carsPolicy.isPresent()) {
//			System.out.println("present");
//		}
//		CarsPolicy carsPolicyToSave = carsPolicy.get();
//		carsPolicyToSave.setPolicyProduct(policyVehicle.getPolicy().getProductDescription());
//		String insuredCode = policyVehicle.getPolicy().getInsuredCode();
//		if (insuredCode != null) {
//			insuredCode = insuredCode.replace("-", "");
//		}
//		carsPolicyToSave.setPolicyNumberDisplay(policyVehicle.getPolicy().getPolicyNo());
//		carsPolicyToSave.setPolicyNumberSpecialNotes(policyVehicle.getPolicy().getMopNote());
//		carsPolicyToSave.setPolicyClient(Long.valueOf(insuredCode));
//		carsPolicyToSave.setPolicyBrokerNum(policyVehicle.getPolicy().getBrokerCode());
//		carsPolicyToSave.setPolicyProduct(policyVehicle.getPolicy().getSubLineCode());
//		try {
//			carsPolicyToSave.setPolicyCurrency(validateSICurrency(policyVehicle.getPolicy().getsICurrency(),
//					policyVehicle.getPolicy().getsICurrencyDescription(),
//					policyVehicle.getPolicy().getsIExchangeRate().toString()));
//			System.out.println("block1");
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
////		String actionTypeOriginale = DataTransferHeaderFileFactory.getService().getHeaderStateOriginal(
////				dataTransferPolicyLoaded.getPolicyProduct(), dataTransferPolicyLoaded.getPolicyNumber(),
////				dataTransferPolicyLoaded.getPolicyAmend(), insuranceId);
////
////		String actionTypeDecoded = Utility.getPropStringValues("decode." + actionTypeOriginale);
//		// carsPolicyToSave.setPolicyAction(actionTypeDecoded);
////		if ("C".equals(actionTypeOriginale) || "S".equals(actionTypeOriginale)) {
////			if (carsPolicyToSave.getPolicyEffectiveDate() == carsPolicyLoad.getPolicyEffectiveDate()) {
////				carsPolicyToSave.setPolicyAmendment(999);
////				carsPolicyToSave.setPolicyId(UUID.randomUUID().toString());
////			} else {
////				if (dataTransferPolicyLoaded.getPolicyAmend() == null) {
////					carsPolicyToSave.setPolicyAmendment(0);
////				} else {
////					carsPolicyToSave.setPolicyAmendment(dataTransferPolicyLoaded.getPolicyAmend());
////				}
////
////			}
////		} else {
////			if (dataTransferPolicyLoaded.getPolicyAmend() == null) {
////				carsPolicyToSave.setPolicyAmendment(0);
////			} else {
////				carsPolicyToSave.setPolicyAmendment(dataTransferPolicyLoaded.getPolicyAmend());
////			}
////
////		}
//		if (policyVehicle.getVehicle().getCertificateNo() != null) {
//			carsPolicyToSave.setPolicyCar(Integer.valueOf(policyVehicle.getVehicle().getCertificateNo()));
//		} else {
//			carsPolicyToSave.setPolicyCar(0);
//		}
//		if (policyVehicle.getPolicy().getEndorsementNo() != null) {
//			carsPolicyToSave.setPolicyAmendment(Integer.valueOf(policyVehicle.getPolicy().getEndorsementNo()));
//		} else {
//			carsPolicyToSave.setPolicyAmendment(0);
//		}
//
//		StringBuilder policyClauses = new StringBuilder();
//		if (policyVehicle.getPolicy().getReason() != null) {
//			policyClauses.append(policyVehicle.getPolicy().getReason() + " ");
//		}
//		if (policyVehicle.getPolicy().getNote() != null) {
//			policyClauses.append(policyVehicle.getPolicy().getNote() + " ");
//		}
////		if (policyVehicle.getPolicy().getMopNote() != null) {
////			policyClauses.append(policyVehicle.getPolicy().getMopNote());
////		}
////			carsPolicy.setPolicyClauses(policyVehicle.getPolicy().getReason() + " " + policyVehicle.getPolicy().getNote()
////					+ " " + policyVehicle.getPolicy().getMopNote());
//		carsPolicyToSave.setPolicyClauses(policyClauses.toString());
//		carsPolicyToSave.setPolicyBroker(policyVehicle.getPolicy().getBrokerCode());
//		carsPolicyToSave.setPolicyBrokerId((policyVehicle.getPolicy().getBrokerID().toString()));
//
//		if (policyVehicle.getVehicle().getPolicyID().signum() == 0) {
//			carsPolicyToSave.setPolicyIdIns(policyVehicle.getPolicy().getPolicyID().toString());// ask jean aya policyId
//		} else {
//			carsPolicyToSave.setPolicyIdIns(policyVehicle.getVehicle().getPolicyID().toString());
//		}
//
//		if (policyVehicle.getPolicy().getIsWebPolicy() == true) {
//			carsPolicyToSave.setPolicyWeb("Y");
//		} else {
//			carsPolicyToSave.setPolicyWeb("N");
//		}
//		if (policyVehicle.getPolicy().getIsDirectBroker() == true) {
//			carsPolicyToSave.setPolicyIsDirectBroker("Y");
//		} else {
//			carsPolicyToSave.setPolicyIsDirectBroker("N");
//		}
//		if (policyVehicle.getPolicy().getBrokerWebOrigin() == true) {
//			carsPolicyToSave.setPolicyBrokerWebOrigin("Y");
//		} else {
//			carsPolicyToSave.setPolicyBrokerWebOrigin("N");
//		}
//		carsPolicyToSave.setPolicyProduct(productId);
//		carsPolicyToSave.setPolicyAction(policyVehicle.getVehicle().getCarStatus());
//		carsPolicyToSave.setPolicyPrintName(convertText(policyVehicle.getPolicy().getPrintName()));
//		carsPolicyToSave.setPolicyBlacklistSetBy(policyVehicle.getPolicy().getBrokerBlackSetBy());
//		carsPolicyToSave.setPolicyBlacklistSetOn(policyVehicle.getPolicy().getBrokerBlackSetOn());
//		carsPolicyToSave.setPolicyInsuranceBlacklistSetBy(policyVehicle.getPolicy().getInsBlackSetBy());
//		carsPolicyToSave.setPolicyInsuranceBlacklistSetOn(policyVehicle.getPolicy().getInsBlackSetOn());
//		System.out.println("block2");
//	//	carsPolicyToSave.setPolicySubline(policyVehicle.getPolicy().getSubLineCode());
//		carsPolicyToSave.setPolicySublineId(subLine);
//		if(policyVehicle.getPolicy().getEndAtNoon()==true) {
//			carsPolicyToSave.setPolicyEndAtNoon("Y");
//		}else {
//			carsPolicyToSave.setPolicyEndAtNoon("N");
//		}
//		// carsPolicy.setPolicyPlan(dataTransferPolicyLoaded.getPolicyPlan());
//		// carsPolicy.setPolicyEndors1(dataTransferPolicyLoaded.getPolicyEndorsement1());
//		// carsPolicy.setPolicyEndors2(dataTransferPolicyLoaded.getPolicyEndorsement2());
//		if (policyVehicle.getVehicle().getDealerRepair() == true) {
//			carsPolicyToSave.setPolicyAgencyRepair("Y");// dealer repair Y N
//		} else {
//			carsPolicyToSave.setPolicyAgencyRepair("N");
//		} // check
//			// with moahamd
//			// carsPolicy.setPolicyCarReplacement(dataTransferPolicyLoaded.getPolicyCarReplacement());
//		
//		
//		carsPolicyToSave.setPolicyProductsId(productId);// same as policyId sent in json ask jean
//		carsPolicyToSave.setPolicyClientId(clientId);
//		carsPolicyToSave.setPolicyBranchId(branchId);
//		carsPolicyToSave.setPolicyBrokerId(brokerId);
//		carsPolicyToSave.setPolicyInsuranceId(insuranceCode);
//		System.out.println("block3");
//		try {
//			Date IssueDate = new SimpleDateFormat("dd-MM-yyyy").parse(policyVehicle.getPolicy().getDateIssue());
//			carsPolicyToSave.setPolicyIssueDate(new Timestamp(IssueDate.getTime()));// ask mohamad
//
//			Date effectiveDate = new SimpleDateFormat("dd-MM-yyyy")
//					.parse(policyVehicle.getVehicle().getDateEffective());
//			Date expiryDate = new SimpleDateFormat("dd-MM-yyyy").parse(policyVehicle.getVehicle().getDateExpiry());
//
//			if (policyVehicle.getPolicy().getEndAtNoon() == true) {
//				effectiveDate.setHours(12);
//				expiryDate.setHours(11);
//				expiryDate.setMinutes(59);
//				carsPolicyToSave.setPolicyEffectiveDate(new Timestamp(effectiveDate.getTime()));
//				carsPolicyToSave.setPolicyExpiryDate(new Timestamp(expiryDate.getTime()));
//			} else {
//				carsPolicyToSave.setPolicyEffectiveDate(new Timestamp(effectiveDate.getTime()));
//				carsPolicyToSave.setPolicyExpiryDate(new Timestamp(expiryDate.getTime()));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		;
//		System.out.println("block4");
//		StringBuilder policyRemark = new StringBuilder();
//		if (policyVehicle.getPolicy().getReason() != null) {
//			policyRemark.append(policyVehicle.getPolicy().getReason() + " ");
//		}
//		if (policyVehicle.getPolicy().getNote() != null) {
//			policyRemark.append(policyVehicle.getPolicy().getNote() + " ");
//		}
//		carsPolicyToSave.setPolicyRemarks(policyRemark.toString());
//		carsPolicyToSave.setSysVersionNumber(0);
//		carsPolicyToSave.setSysUpdatedBy(CREATED_BY_QUARTZ);
//
//		carsPolicyToSave.setSysUpdatedDate(new Timestamp(new Date().getTime()));
//		// carsPolicyToSave.setPolicyTowFlag(dataTransferPolicyLoaded.getPolicyTowingCondition());
//
//		db.carsPolicyRepository.save(carsPolicyToSave);
//
////		if (carsPolicyToSave.getPolicyCar() != null && carsPolicyToSave.getPolicyCar() == 999) {
////			db.carsPolicyRepository.save(carsPolicyToSave);
////		} else {
////			db.carsPolicyRepository.save(carsPolicyToSave);
////		}
//		System.out.println("found for1 : " + carsPolicyLoad.getPolicyId());
//		return carsPolicyToSave.getPolicyId();
//	}
//
//	public Collection<CarsPolicy> carsPolicySearchable(PolicyVehicle policyVehicle, String productId) {
//		CarsPolicy carsPolicyToSearch = new CarsPolicy();
//		// carsPolicyToSearch.setPolicyBranch(policyVehicle.getPolicy().getBranchCode());
//		// carsPolicyToSearch.setPolicyLob("MO");
//		// String policyNumber = policyVehicle.getPolicy().getPolicyNo();
//		// policyNumber = policyNumber.replaceAll("[^0-9]", "");
//		// carsPolicy.setPolicyNumber(policyVehicle.getPolicy().getPolicyNo());
//		// carsPolicyToSearch.setPolicyNumber(policyNumber);
//		// carsPolicyToSearch.setPolicyCar(0);
//		// carsPolicyToSearch.setPolicyAmendment(dataTransferPolicyLoaded.getPolicyAmend());
//
////		String branchCodeNew = policyVehicle.getPolicy().getBranchCode();
////		branchCodeNew = branchCodeNew.replaceFirst("^0+(?=.)", "");
////		carsPolicyToSearch.setPolicyBranchId(insuranceCode + "." + branchCodeNew);
////		String policyNumber = policyVehicle.getPolicy().getPolicyNo();
////		Optional<CarsProducts> carsProducts = db.carsProductRepository.findById(productId);
////		String[] arrOfStr = policyNumber.split("-", 2);
////		policyNumber = arrOfStr[1].replaceAll("[^0-9]", "");
//		
//		//Optional<CarsProducts> carsProducts = db.carsProductRepository.findById(productId);
////		String[] arrOfStr = policyNumber.split("-", 4);
////		policyNumber = arrOfStr[2]+"-"+arrOfStr[3];
//		// carsPolicy.setPolicyNumber(policyVehicle.getPolicy().getPolicyNo());
//		//if (carsProducts.isPresent()) {
//		//	policyNumber = carsProducts.get().getProductsReference() + policyNumber;
//			
//		//}
//		carsPolicyToSearch.setPolicyIdIns(policyVehicle.getPolicy().getPolicyID().toString());
////		if (policyVehicle.getVehicle().getCertificateNo() != null) {
////			carsPolicyToSearch.setPolicyCar(Integer.valueOf(policyVehicle.getVehicle().getCertificateNo()));
////		} else {
////			carsPolicyToSearch.setPolicyCar(0);
////		}
////		if (policyVehicle.getPolicy().getEndorsementNo() != null) {
////			carsPolicyToSearch.setPolicyAmendment(Integer.valueOf(policyVehicle.getPolicy().getEndorsementNo()));
////		} else {
////			carsPolicyToSearch.setPolicyAmendment(0);
////		}
////		carsPolicyToSearch.setPolicyInsuranceId(insuranceCode);
//		Example<CarsPolicy> example = Example.of(carsPolicyToSearch);
//
//		Collection<CarsPolicy> carsPolicyToSearchList = db.carsPolicyRepository.findAll(example);
//		if (carsPolicyToSearchList != null && !carsPolicyToSearchList.isEmpty()) {
//
//		}
//		return carsPolicyToSearchList;
//	}
//
//	public Collection<CarsDtModel> getDataTransferByModel(String insuranceId, String modelName, String carMake) {
//		Collection<CarsDtModel> lista = new ArrayList<CarsDtModel>();
//
//		Collection<String> modelList = db.carsDtModelRepository.getDataTransferByModel(insuranceId, modelName, carMake);
//
//		if (modelList == null || modelList == null) {
//			insertDTModel(insuranceId, modelName);
//		}
//
//		if (modelList != null && modelList != null) {
//			Iterator<String> iterator = modelList.iterator();
//			while (iterator.hasNext()) {
//				String idss = iterator.next();
//				Optional<CarsDtModel> qmpQuestLoaded = db.carsDtModelRepository.findById(idss);
//				if (qmpQuestLoaded.isPresent()) {
//					lista.add(qmpQuestLoaded.get());
//				}
//			}
//		}
//		System.out.println("model");
//		return lista;
//	}
//
//	public void insertDTModel(String insuranceId, String modelName) {
//		CarsDtModel carsDtModel = new CarsDtModel();
//		carsDtModel.setId(UUID.randomUUID().toString());
//		carsDtModel.setInsuranceId(insuranceId);
//		carsDtModel.setModelName(modelName);
//		carsDtModel.setSysCreatedBy("Test03");
//		carsDtModel.setSysUpdatedBy("Test03");
//		carsDtModel.setSysCreatedDate(new Timestamp(new Date().getTime()));
//		carsDtModel.setSysUpdatedDate(new Timestamp(new Date().getTime()));
//		carsDtModel.setSysVersionNumber(0);
//		db.carsDtModelRepository.save(carsDtModel);
//
//	}
//
//	public void insertCarsPolicyCover(Covers cover, String carId) {
//
//		int coversOrder = 0;
//		CarsPolicyCover carsPolicyCoverToInsert = new CarsPolicyCover();
//		carsPolicyCoverToInsert.setPolicyCoversId(UUID.randomUUID().toString());
//		carsPolicyCoverToInsert.setPolicyCoversCover(cover.getCoverCode());
//		carsPolicyCoverToInsert.setPolicyCoversOrder(coversOrder);
//		if (cover.getSumInsured() > 0) {
//			carsPolicyCoverToInsert.setPolicyCoversSumInsured((long) cover.getSumInsured());
//		}
//		carsPolicyCoverToInsert.setPolicyCoversDeductible((double) cover.getDeductibleFlatAmount());
//		carsPolicyCoverToInsert.setPolicyCoversDeductible100((double) cover.getDeductiblePercentage());
////				carsPolicyCoverToInsert
////						.setPolicyCoversPercentage(cover.getDeductibleType());
//		carsPolicyCoverToInsert.setPolicyCoversCarId(carId);
//		carsPolicyCoverToInsert.setPolicyCoversCoverId(cover.getCoverID().toString());
//		carsPolicyCoverToInsert.setPolicyCoversPolcoverId(cover.getPolicyCoverID().toString());
//		carsPolicyCoverToInsert.setSysVersionNumber(0);
//		carsPolicyCoverToInsert.setSysCreatedBy(CREATED_BY_QUARTZ);
//		carsPolicyCoverToInsert.setSysUpdatedBy(CREATED_BY_QUARTZ);
//		carsPolicyCoverToInsert.setSysCreatedDate(new Timestamp(new Date().getTime()));
//		carsPolicyCoverToInsert.setSysUpdatedDate(new Timestamp(new Date().getTime()));
//
//		db.carsPolicyCoverRepository.save(carsPolicyCoverToInsert);
//	}
//
//	public void deleteCover(String carId) {
//
//		db.carsPolicyCoverRepository.deleteCovers(carId);
//	}
//
//	public void insertCarsPolicyWordingHAndD(Clauses clause, String policyId) {
//
//		CarsPolicyWordingH carsPolicyWordingHToInsert = new CarsPolicyWordingH();
//		carsPolicyWordingHToInsert.setPolicyWordingHId(UUID.randomUUID().toString());
//		carsPolicyWordingHToInsert.setPolicyId(policyId);
//		// carsPolicyWordingHToInsert.setWordingId(coversOrder);
//		carsPolicyWordingHToInsert.setSysVersionNumber(0);
//		carsPolicyWordingHToInsert.setSysCreatedBy(CREATED_BY_QUARTZ);
//		carsPolicyWordingHToInsert.setSysUpdatedBy(CREATED_BY_QUARTZ);
//		carsPolicyWordingHToInsert.setSysCreatedDate(new Timestamp(new Date().getTime()));
//		carsPolicyWordingHToInsert.setSysUpdatedDate(new Timestamp(new Date().getTime()));
//		db.carsPolicyWordingHRepository.save(carsPolicyWordingHToInsert);
//
//		CarsPolicyWordingD carsPolicyWordingDToInsert = new CarsPolicyWordingD();
//		carsPolicyWordingDToInsert.setPolicyWordingDId(UUID.randomUUID().toString());
//		carsPolicyWordingDToInsert.setPolicyWordingHId(carsPolicyWordingHToInsert.getPolicyWordingHId());
//		carsPolicyWordingDToInsert.setPolicyWordingDFreeText(convertText(clause.getContent()));
//		carsPolicyWordingDToInsert.setPolicyWordingDHtml(clause.getContent());
//		carsPolicyWordingDToInsert.setPolicyWordingDLine(Integer.valueOf(clause.getOrder()));
//		// carsPolicyWordingDToInsert.setPolicyWordingDCode(dataTransferClauseContractLoaded.getCode());
//		// carsPolicyWordingDToInsert
//		// .setPolicyWordingDLine(String.valueOf(dataTransferClauseContractLoaded.getSequence()));
//
//		carsPolicyWordingDToInsert.setSysVersionNumber(0);
//		carsPolicyWordingDToInsert.setSysCreatedBy(CREATED_BY_QUARTZ);
//		carsPolicyWordingDToInsert.setSysUpdatedBy(CREATED_BY_QUARTZ);
//		carsPolicyWordingDToInsert.setSysCreatedDate(new Timestamp(new Date().getTime()));
//		carsPolicyWordingDToInsert.setSysUpdatedDate(new Timestamp(new Date().getTime()));
//		db.carsPolicyWordingDRepository.save(carsPolicyWordingDToInsert);
//	}
//
//	void DeletePolicyWording(String policyId) {
//		CarsPolicyWordingH carsPolicyWordingH = new CarsPolicyWordingH();
//		carsPolicyWordingH.setPolicyId(policyId);
//		Example<CarsPolicyWordingH> example = Example.of(carsPolicyWordingH);
//
//		Collection<CarsPolicyWordingH> carsPolicyWordingHList = db.carsPolicyWordingHRepository.findAll(example);
//
//		System.out.println(" carsPolicyWordingHList for policyId :" + policyId);
//
//		Iterator<CarsPolicyWordingH> carsPolicyWordingHIterator = carsPolicyWordingHList.iterator();
//		while (carsPolicyWordingHIterator.hasNext()) {
//			CarsPolicyWordingH carsPolicyWordingHToDelete = carsPolicyWordingHIterator.next();
//
//			CarsPolicyWordingD carsPolicyWordingDToSearch = new CarsPolicyWordingD();
//			carsPolicyWordingDToSearch.setPolicyWordingHId(carsPolicyWordingHToDelete.getPolicyWordingHId());
//
//			// Collection<CarsPolicyWordingD> carsPolicyWordingDList =
//			// CarsPolicyWordingDFactory.getService()
//			// .searchWithCriteria(carsPolicyWordingDToSearch,
//			// carsPolicyWordingDToSearch,
//			// Utility.getCriteriaMapCarsPolicyWordingD());
//
//			Collection<CarsPolicyWordingD> carsPolicyWordingDList = db.carsPolicyWordingDRepository
//					.findByPolicyWordingHId(carsPolicyWordingHToDelete.getPolicyWordingHId());
//			if (carsPolicyWordingDList != null && carsPolicyWordingDList != null) {
//				Iterator<CarsPolicyWordingD> carsPolicyWordingDIterator = carsPolicyWordingDList.iterator();
//				while (carsPolicyWordingDIterator.hasNext()) {
//					CarsPolicyWordingD carsPolicyWordingDToDelete = carsPolicyWordingDIterator.next();
//					db.carsPolicyWordingDRepository.delete(carsPolicyWordingDToDelete);
//				}
//			}
//			db.carsPolicyWordingHRepository.delete(carsPolicyWordingHToDelete);
//
//		}
//	}
//
//	String validateCover(Covers cover) throws Exception {
//		if (cover.getCoverCode() == null || cover.getCoverDesc() == null || cover.getCoverID().toString() == null) {
//			throw new Exception("Error :");
//		}
////		Optional<CarsCover> carsCoverOpt = db.carsCoverRepository
////				.findByCoverCodeAndCoverInsuranceId(insuranceCode + cover.getCoverCode(), insuranceCode);//byCoverId
//		Optional<CarsCover> carsCoverOpt = db.carsCoverRepository
//				.findByCoverCodeAndCoverInsuranceId(insuranceCode + cover.getCoverID(), insuranceCode);
//
//		if (!carsCoverOpt.isPresent()) {
//			CarsCover carsCover = new CarsCover();
//			carsCover.setCoverCode(cover.getCoverCode());// jean
//			// carsCover.setCoverCode("test");
//			carsCover.setCoverDescription(cover.getCoverDesc());
//			// carsCover.setCoverID(cover.getCoverID().toString());
//			carsCover.setCoverID(insuranceCode + "." + cover.getCoverID().toString());
//			carsCover.setCoverReference(cover.getCoverID().toString());
//			// carsCover.setCoverInsuranceId(insuranceCode);
//			carsCover.setCoverInsurance(Integer.valueOf(insuranceCode));
//			// carsCover.setCoverInsurance(coverInsurance);
//			// carsCover.setCoverInsurance();
//			// carsCover.setCoverType(coverType);
//			// carsCover.setCoverState(coverState);
//			carsCover.setSysVersionNumber(0);
//			carsCover.setSysCreatedBy(CREATED_BY_QUARTZ);
//			carsCover.setSysUpdatedBy(CREATED_BY_QUARTZ);
//			carsCover.setSysCreatedDate(new Timestamp(new Date().getTime()));
//			carsCover.setSysUpdatedDate(new Timestamp(new Date().getTime()));
//			// carsProducts.setSysCreatedBy(new Timestamp(new Date().getTime()));
//			db.carsCoverRepository.save(carsCover);
//			return carsCover.getCoverID();
//		}
//
//		if (carsCoverOpt.isPresent()) {
//			carsCoverOpt.get().setCoverDescription(cover.getCoverDesc());
//			carsCoverOpt.get().setSysUpdatedBy(CREATED_BY_QUARTZ);
//			carsCoverOpt.get().setSysUpdatedDate(new Timestamp(new Date().getTime()));
//			db.carsCoverRepository.save(carsCoverOpt.get());
//
//		}
//		return carsCoverOpt.get().getCoverID();
//
//	}
//
//	String validateSubCover(SubCovers subCovers, String Cover) throws Exception {
//		if (subCovers.getSubCoverCode() == null || subCovers.getSubCoverDesc() == null
//				|| subCovers.getSubCoverID().toString() == null) {
//			throw new Exception("Error :");
//		}
////		Optional<CarsCover> carsCoverOpt = db.carsCoverRepository
////		.findByCoverCodeAndCoverInsuranceId(insuranceCode + cover.getCoverCode(), insuranceCode);//byCoverId
//		Optional<CarsCover> carsCoverOpt = db.carsCoverRepository
//				.findByCoverCodeAndCoverInsuranceId(insuranceCode + subCovers.getSubCoverID(), insuranceCode);
//		if (!carsCoverOpt.isPresent()) {
//			CarsCover carsCover = new CarsCover();
//			carsCover.setCoverCode(Cover + "." + subCovers.getSubCoverCode());// jean
//			// carsCover.setCoverCode("test");
//			carsCover.setCoverInsurance(Integer.valueOf(insuranceCode));
//			carsCover.setCoverDescription(subCovers.getSubCoverDesc());
//			// carsCover.setCoverID(cover.getCoverID().toString());
//			carsCover.setCoverID(insuranceCode + "." + subCovers.getSubCoverID().toString());
//			carsCover.setCoverReference(subCovers.getSubCoverID().toString());
//			// carsCover.setCoverInsuranceId(insuranceCode);
//			// carsCover.setCoverInsurance(coverInsurance);
//			// carsCover.setCoverInsurance();
//			// carsCover.setCoverType(coverType);
//			// carsCover.setCoverState(coverState);
//			carsCover.setSysVersionNumber(0);
//			carsCover.setSysCreatedBy(CREATED_BY_QUARTZ);
//			carsCover.setSysUpdatedBy(CREATED_BY_QUARTZ);
//			carsCover.setSysCreatedDate(new Timestamp(new Date().getTime()));
//			carsCover.setSysUpdatedDate(new Timestamp(new Date().getTime()));
//			// carsProducts.setSysCreatedBy(new Timestamp(new Date().getTime()));
//			db.carsCoverRepository.save(carsCover);
//			return carsCover.getCoverID();
//		}
//
//		if (carsCoverOpt.isPresent()) {
//			carsCoverOpt.get().setCoverDescription(subCovers.getSubCoverDesc());
//			carsCoverOpt.get().setSysUpdatedBy(CREATED_BY_QUARTZ);
//			carsCoverOpt.get().setSysUpdatedDate(new Timestamp(new Date().getTime()));
//			db.carsCoverRepository.save(carsCoverOpt.get());
//
//		}
//		return carsCoverOpt.get().getCoverID();
//
//	}
//
//	public void insertCarsCover(Covers cover, SubCovers subCover, String carCoverCode, String carId, String CoverCode,
//			String subCoverCode) {
//		if (cover != null) {
//			CarsPolicyCover carsPolicyCoverToInsert = new CarsPolicyCover();
//			carsPolicyCoverToInsert.setPolicyCoversId(UUID.randomUUID().toString());
//			// carsPolicyCoverToInsert.setPolicyCoversCover(CoverCode + ".");
//			carsPolicyCoverToInsert.setPolicyCoversCover(carCoverCode);
//			carsPolicyCoverToInsert.setPolicyCoversCoverId(CoverCode);
//
//			if (cover.getSumInsured() > 0) {
//				carsPolicyCoverToInsert.setPolicyCoversSumInsured((long) cover.getSumInsured());
//			}
//
//			carsPolicyCoverToInsert.setPolicyCoversDeductible((double) cover.getDeductibleFlatAmount());
//			carsPolicyCoverToInsert.setPolicyCoversDeductible100((double) cover.getDeductiblePercentage());
//			// carsPolicyCoverToInsert.setPolicyCoversPercentage(Float.toString(cover.getDeductiblePercentage()));
//			carsPolicyCoverToInsert.setPolicyCoversPercentage(cover.getDeductibleType());
//			carsPolicyCoverToInsert.setPolicyCoversCarId(carId);
//
//			// carsPolicyCoverToInsert.setPolicyCoversCoverId(insuranceCode+"."+cover.getCoverCode());
//			// carsPolicyCoverToInsert.setPolicyCoversCoverId(carCoverId);jean
//			// batchCover = dataTransferCoverContractLoaded.getScriptName();
//			carsPolicyCoverToInsert.setSysVersionNumber(0);
//			carsPolicyCoverToInsert.setSysCreatedBy(CREATED_BY_QUARTZ);
//			carsPolicyCoverToInsert.setSysUpdatedBy(CREATED_BY_QUARTZ);
//			carsPolicyCoverToInsert.setSysCreatedDate(new Timestamp(new Date().getTime()));
//			carsPolicyCoverToInsert.setSysUpdatedDate(new Timestamp(new Date().getTime()));
//			db.carsPolicyCoverRepository.save(carsPolicyCoverToInsert);
//		}
//		if (subCover != null) {
//			CarsPolicyCover carsPolicySubCoverToInsert = new CarsPolicyCover();
//			carsPolicySubCoverToInsert.setPolicyCoversId(UUID.randomUUID().toString());
//			carsPolicySubCoverToInsert.setPolicyCoversCover(CoverCode + "." + subCover.getSubCoverCode());
//			carsPolicySubCoverToInsert.setPolicyCoversCoverId(subCoverCode);
//			if (subCover.getSubCoverSumInsured() > 0) {
//				carsPolicySubCoverToInsert.setPolicyCoversSumInsured((long) subCover.getSubCoverSumInsured());
//			}
//			carsPolicySubCoverToInsert.setPolicyCoversDeductible((double) subCover.getSubCoverDeductibleFlatAmount());
//			carsPolicySubCoverToInsert
//					.setPolicyCoversDeductible100((double) subCover.getSubCoverDeductiblePercentage());
//			// carsPolicySubCoverToInsert.setPolicyCoversPercentage(Float.toString(subCover.getSubCoverDeductiblePercentage()));
//
//			carsPolicySubCoverToInsert.setPolicyCoversPercentage(subCover.getSubCoverDeductibleType());
//			carsPolicySubCoverToInsert.setPolicyCoversCarId(carId);
//
//			// carsPolicyCoverToInsert.setPolicyCoversCoverId(carCoverId);jean
//			// batchCover = dataTransferCoverContractLoaded.getScriptName();
//			carsPolicySubCoverToInsert.setSysVersionNumber(0);
//			carsPolicySubCoverToInsert.setSysCreatedBy(CREATED_BY_QUARTZ);
//			carsPolicySubCoverToInsert.setSysUpdatedBy(CREATED_BY_QUARTZ);
//			carsPolicySubCoverToInsert.setSysCreatedDate(new Timestamp(new Date().getTime()));
//			carsPolicySubCoverToInsert.setSysUpdatedDate(new Timestamp(new Date().getTime()));
//			db.carsPolicyCoverRepository.save(carsPolicySubCoverToInsert);
//		}
//	}
//
//	public String convertText(String text) {
//
//		Document jsoupDoc = Jsoup.parse(text);
//		Document.OutputSettings outputSettings = new Document.OutputSettings();
//		outputSettings.prettyPrint(false);
//		jsoupDoc.outputSettings(outputSettings);
//		jsoupDoc.select("br").before("\\n");
//		jsoupDoc.select("p").before("\\n");
//		// jsoupDoc.select("br").before("br");
//		// jsoupDoc.select("p").append("br");
//		String str = jsoupDoc.html().replaceAll("\\\\n", "\n");
//
//		String strWithNewLines = Jsoup.clean(str, "", Whitelist.none(), outputSettings);
//		strWithNewLines = strWithNewLines.replaceAll("\\\\r", "");
//		// strWithNewLines = strWithNewLines.replaceAll("\n", "<br>");
//		return strWithNewLines;
//
//	}
//



	@PostMapping("/uploadPolicy")
	public ResponseEntity<Policies> policyUpload1(@Validated @RequestBody Policies policies) throws Exception {
		try {
			logger.info("Inside Validation");
			return testService.policyUpload(policies);

		} catch (

		Exception e) {
			e.printStackTrace();
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String sStackTrace = sw.toString();
			logger.error("failed",sStackTrace);
			return new ResponseEntity(sStackTrace, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/deletePolicy")
	public ResponseEntity<String> deletePolicy( @RequestBody PolicyToDelete policyToDelete)  throws CustomResponseException {



			logger.info("Inside Validation");


			return  testService.deletePolicyFunction(policyToDelete.getInsuranceId(),policyToDelete.getPolicyId(),policyToDelete.getBranchId()
					,policyToDelete.getPolicyNumber(),policyToDelete.getVehicleId(),policyToDelete.getAmendment(),policyToDelete.getCertificate());


	}


	@PutMapping("/blackWhiteListing")
	public ResponseEntity<String> blackWhiteListing( @RequestBody PolicyListhing policyListhing) throws Exception {


		try {
			logger.info("Inside Validation");
			return  testService.blackWhiteListing(policyListhing);

		} catch (

				Exception e) {
			e.printStackTrace();
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String sStackTrace = sw.toString();
			logger.error("failed",sStackTrace);
			return new ResponseEntity(sStackTrace, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}




}
