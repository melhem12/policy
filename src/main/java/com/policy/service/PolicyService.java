package com.policy.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.policy.bean.*;
import com.policy.config.SendingMail;
import com.policy.config.SendingMail2;
import com.policy.config.Utility;
import com.policy.entity.*;
import com.policy.repository.CarsPolicyJsonRepository;
import com.policy.repository.DB;
import com.policy.response.CarShapeRespomse;
import com.policy.response.VehicleResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class PolicyService {
//    @PersistenceContext
//    private EntityManager entityManager;

    Logger logger = LoggerFactory.getLogger(PolicyService.class);

    @Autowired
    public DB db;
    @Autowired
    public CarsErrorlogService carsErrorlogService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    public CarsDtPolicyTransferLogService carsDtPolicyTransferLogService;
    @Autowired
    CarsPolicyJsonRepository carsPolicyJsonRepository;
    public static String CREATED_BY_QUARTZ = "Transfer";
    public static int i = 0;
    public static String insuranceCode = "11";
    String policyNo = null;
    String policyId = null;
    String policyIdFromJson = null;
    String brokerId = null;
    String ClientId = null;
    String productId = null;
    String SublineId = null;
    String policyRootId = null;
    String companyName;
    int counter = 0;


    private SimpleJdbcCall simpleJdbcCall;


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ResponseEntity<String> deletePolicyFunction(String insuranceId, String policyId, String branchId, String policyNumber, String vehicleId, String amendment, String certificate) {

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("FC_POLICY_DELETE")
                .declareParameters(new SqlParameter("u_id", Types.BIGINT));

        String out = simpleJdbcCall.executeFunction(String.class,

                new MapSqlParameterSource("I_COMPANY", insuranceId)
                        .addValue("I_POLICY_ID", policyId)
                        .addValue("I_BRANCH", branchId)
                        .addValue("I_POLICY_NUMBER", policyNumber)
                        .addValue("I_CAR", vehicleId)
                        .addValue("I_AMENDMENT", amendment)
                        .addValue("I_CERTIFICATE", certificate));
        if (out.equals("POLICY NOT FOUND") || out.equals("Deletion is not allowed.Claim already exist")) {
            return new ResponseEntity(out, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(out, HttpStatus.OK);

        }


    }


//    public CompletableFuture<ResponseEntity<Policies>> policyUpload(Policies policies) {


    //	@javax.transaction.Transactional(propagation = Propagation.REQUIRED)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Policies> policyUpload(Policies policies) throws Exception {


        Optional<CarsInsurance> carsInsurance = db.carsInsuranceRepository.findById(insuranceCode);
        carsInsurance.ifPresent(value -> companyName = value.getInsuranceDesc());


        String value = db.carsPolicyRepository.findConfigByKey(insuranceCode + ".send.JSON.policy");
        if (value != null) {
            if (value.equals("true")) {

                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                String json = ow.writeValueAsString(policies);
                String body = json;
                SendingMail sendingMail = new SendingMail();
                sendingMail.run(companyName + " Policy Upload policy " + policies.getPolicies().get(0).getPolicyNo(), body);
            }
        }


        try {
//			String dId = "70870162-aeb3-4d63-b28a-4f9c7749536c";
//			Optional<CarsPolicyWordingD> row = db.carsPolicyWordingDRepository.findById(dId);
//
//			if (row != null) {
//				System.out.println(row.get().getPolicyWordingDFreeText());
//			}

            StringBuffer stringBufferTrademark = new StringBuffer("");
            PolicyVehicle policyVehicle = new PolicyVehicle();
            for (Policy policy : policies.getPolicies()) {
                if (policy.getEndorsementTypeCode() != null) {

                    if (policy.getEndorsementTypeCode().equals("C")) {
                        String branchCode = "";

                        if (!Utility.isEmpty(policy.getBranchCode())) {
                            branchCode = policy.getBranchCode();
                            branchCode = branchCode.replaceFirst("^0+(?=.)", "");

                        }
                        Date policyExpDate = db.carsPolicyRepository.policyExpDate(policy.getPolicyNo().toString(), branchCode, insuranceCode);

                        if (policyExpDate != null) {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                            String formattedDate = sdf.format(policyExpDate);
                            System.out.println("new Formated expiration date for cancelation " + formattedDate);
                            policy.setPolDateExpiry(formattedDate);
                            if (policy.getVehicles().size() > 0) {
                                policy.getVehicles().forEach(vehicles -> {
                                    vehicles.setDateExpiry(formattedDate);
                                });
                            }
                        }

                    }


                    if (policy.getEndorsementTypeCode().equals("C") && policy.getVehicles().size() == 0) {
                        logger.info("cacncelation policy " + policyNo);
                        Date policyExpDate = db.carsPolicyRepository.policyExpDate(policy.getPolicyNo().toString(), policy.getBranchCode(), insuranceCode);
//                        if(policyExpDate!=null) {
//                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//                            String formattedDate = sdf.format(policyExpDate);
//                            System.out.println("new Formated expiration date for cancelation "+formattedDate);
//                            policy.setPolDateExpiry(formattedDate);
//                        }
                        System.out.println("my policy No  " + policy.getPolicyNo());
                        String branchCodeNew = policy.getBranchCode();

                        branchCodeNew = branchCodeNew.replaceFirst("^0+(?=.)", "");


                        List<VehicleResponse> savedVehicles = db.carsPolicyCarRepository.getVehicles(Integer.parseInt(insuranceCode), policy.getPolicyNo(), branchCodeNew);
                        List<Vehicles> newVehicles = new ArrayList<>();
                        int certId = 0;

                        if (savedVehicles.size() > 0) {
                            System.out.println("size of v is not 0");
                            savedVehicles.forEach(savedVehicle -> {
                                Vehicles vehicles = new Vehicles();
                                vehicles.setPolicyID(policy.getPolicyID());
                                vehicles.setCertificateBlacklisted(false);
                                vehicles.setCarChassis(savedVehicle.getCarChassis());

                                vehicles.setDealerWorkshopName(savedVehicle.getCarDealerWorkShopName());
                                if (Utility.isEmpty(savedVehicle.getPolicyAgencyRepair())) {
                                    vehicles.setDealerRepair(null);

                                } else {
                                    if (savedVehicle.getPolicyAgencyRepair().equals("Y")) {
                                        vehicles.setDealerRepair(true);
                                    }
                                    if (savedVehicle.getPolicyAgencyRepair().equals("N")) {
                                        vehicles.setDealerRepair(false);
                                    }
                                }
                                vehicles.setCarBeneficiary(savedVehicle.getCarBeneficiaryDesc());
                                vehicles.setCarInsVal(savedVehicle.getCarValue());



                                vehicles.setCarEngine(savedVehicle.getCarEngine());
                                vehicles.setCarStatus(savedVehicle.getCarStatus());
                                if (savedVehicle.getCertifId() != null) {
                                    vehicles.setCertifID(savedVehicle.getCertifId().negate());

                                }
//                            System.out.println("cars Status is C");
                                vehicles.setCertificateNo(savedVehicle.getCertificateNo());
                                if (policy.getPolDateEffective() != null) {
                                    System.out.println("PolDateEffective() " + policy.getPolDateEffective());
                                    vehicles.setDateEffective(policy.getPolDateEffective());

                                }
                                if (policy.getPolDateExpiry() != null) {
                                    vehicles.setDateExpiry(policy.getPolDateExpiry());

                                }
                                vehicles.setCarModel(savedVehicle.getCarTradeMarkDesc());

                                db.carsClientRepository.findById(savedVehicle.getCarInsuredID()).ifPresent(
                                        carsClient -> {
                                            vehicles.setCarInsuredCode(carsClient.getClientNum1());
                                            vehicles.setCarinsuredfirstName(carsClient.getClientFirstName());
                                            vehicles.setCarinsuredfatherName(carsClient.getClientFatherName());
                                            vehicles.setCarinsuredlastName(carsClient.getClientFamilyName());
                                            vehicles.setCarInsuredID(Integer.parseInt(carsClient.getClientReference()));
                                        }
                                );


                                vehicles.setCarMake(savedVehicle.getCarBrandDesc());
//                                vehicles.setCarMakeCode(savedVehicle.getCarMakeCode());
//                                vehicles.setCarMakeID(savedVehicle.getCarMakeID());
                                //  vehicles.setCarModelID(savedVehicle.getCarModelID());
//                                vehicles.setCarModelCode(savedVehicle.getCarModelCode());
                                vehicles.setModelToPrint(savedVehicle.getModelToPrint());
                                vehicles.setCarYear(savedVehicle.getCarYear());
                                vehicles.setCarPlateNumber(savedVehicle.getCarPlateNumber());
                                if (savedVehicle.getCarPlatePrefix() == null) {
                                    vehicles.setCarPlatePrefix("");

                                } else {
                                    vehicles.setCarPlatePrefix(savedVehicle.getCarPlatePrefix());

                                }
                                vehicles.setCarUsage(savedVehicle.getCarUsage());
                                newVehicles.add(vehicles);
                            });
                        }
                        System.out.println("set succcess");


                        policy.setVehicles(newVehicles);


                    }
                }

                ValidatePolicy(policy);
                policyVehicle.setPolicy(policy);
                if (policy.getPolicyID() != null) {
                    policyIdFromJson = policy.getPolicyID().toString();
                } // this is policyId from json
                policyNo = ValidatePolicyNo(policy.getPolicyNo());


//				CarsProducts products = validateCarsProduct(policy.getProductCode(), policy.getProductID(),
//					policy.getProductDescription(), insuranceCode);// ask about insurance id
                CarsProducts product = validateCarsProduct(policy.getSubLineCode(), policy.getSublineDescription(),
                        insuranceCode, policyNo);// ask about insurance id

                CarsSubline carsSubline = validateCarsSubline(policy.getProductCode(), insuranceCode,
                        policy.getProductDescription(), policy.getProductID(), policyNo);

//				CarsBroker broker = validateCarsBroker(policy.getBrokerCode(), policy.getBrokerID().toString(),
//						insuranceCode, policy.getBrokerName(), policy.getBrokerPhoneNumber(),
//						policy.getIsDirectBroker(), policy.getBrokerMail(), policy.getBrokerWebOrigin(),
//						policy.getBrokerBlacklisted(), policy.getBrokerBlackReason(), policy.getInsBlackNote());


                CarsBroker broker = validateCarsBroker(policy.getBrokerCode(), policy.getBrokerID().toString(),
                        insuranceCode, policy.getBrokerName(), policy.getBrokerPhoneNumber(),
                        policy.getIsDirectBroker(), policy.getBrokerMail(), policy.getBrokerWebOrigin(),
                        policy.getBrokerBlacklisted(), policy.getBrokerBlackReason(), policy.getBrokerBlackNote(), policy.getBrokerBlackSetOn(), policy.getBrokerBlackSetBy());


                CarsBranch branch = validateCarsBranch(policy.getBranchCode(), insuranceCode,
                        policy.getBranchDescription(), policyNo);

                CarsClient client = validateCarsClient(policy.getInsuredID(), policy.getInsuredCode(), insuranceCode,
                        policy.getPrintName(), policy.getFirstInsuredName(), policy.getFatherInsuredName(),
                        policy.getLastInsuredName(), policy.getInsuredPhoneNumber(), policy.getInsBlacklisted(),
                        policy.getInsBlackReason(), policy.getInsBlackNote(), policy.getPolicyNo(), null, policy.getInsBlackSetOn(), policy.getInsBlackSetBy());


//				Collection<CarsPolicy> carsPolicyToSearchList = carsPolicySearchable(policyVehicle);
//				if (carsPolicyToSearchList != null && !carsPolicyToSearchList.isEmpty()) {
//					policyId = updatePolicyCar(policyVehicle, products.getProductsId(), branch.getBranchId(),
//							client.getClientId(), broker.getBrokerId(), carsPolicyToSearchList, insuranceCode);
//				} else {
//					policyId = insertPolicyCar(policyVehicle, products.getProductsId(), branch.getBranchId(),
//							client.getClientId(), broker.getBrokerId(), insuranceCode);
//				}
                if (policy.getVehicles() != null) {


                    // Collection<CarsPolicy> carsPolicyToSearchList =
                    // carsPolicySearchable(policyVehicle);
                    for (Vehicles vehicle : policy.getVehicles()) {
                        if (vehicle.getCertifID() != null) {
                            if (!vehicle.getCertifID().equals(BigInteger.valueOf(0))) {
                                vehicle.setPolicyID(vehicle.getCertifID());

                            }

                        }


                        policyVehicle.setVehicle(vehicle);
//						CarsClient client2 = validateCarsClient(vehicle.getCarInsuredID(), vehicle.getCarInsuredCode(),
//								insuranceCode, policy.getPrintName(), vehicle.getCarinsuredfirstName(),
//								vehicle.getCarinsuredfatherName(), vehicle.getCarinsuredlastName(),
//								vehicle.getCarInsuredPhoneNumber(), policy.getInsBlacklisted(),
//								policy.getInsBlackReason(), policy.getInsBlackNote(), policy.getPolicyNo(),
//								vehicle.getCertificateNo(),vehicle.getCertifID().toString(),policy.getInsBlackSetOn(),policy.getInsBlackSetBy());


                        CarsClient client2 = validateCarsClient(vehicle.getCarInsuredID(), vehicle.getCarInsuredCode(),
                                insuranceCode, policy.getPrintName(), vehicle.getCarinsuredfirstName(),
                                vehicle.getCarinsuredfatherName(), vehicle.getCarinsuredlastName(),
                                vehicle.getCarInsuredPhoneNumber(), vehicle.getCertificateBlacklisted(),
                                vehicle.getCertificateReason(), vehicle.getCertificateNote(), policy.getPolicyNo(),
                                vehicle.getCertificateNo(), vehicle.getCertificateSetOn(), vehicle.getCertificateSetBy());


                        CarsPolicyCar carsPolicyCarToSearch = new CarsPolicyCar();
                        if (product != null) {
                            productId = product.getProductsId();
                        }

                        Collection<CarsPolicy> carsPolicyToSearchList = carsPolicySearchable(policyVehicle, productId);
                        Collection<CarsPolicy> carsPolicyToSearchList2 = carsPolicySearchable2(policyVehicle, productId);


                        if (broker != null) {
                            brokerId = broker.getBrokerId();
                        }
                        if (client2 != null) {
                            ClientId = client2.getClientId();
                        }

                        if (carsSubline != null) {

                            SublineId = carsSubline.getSublineId();
                        }

                        if (carsPolicyToSearchList != null && !carsPolicyToSearchList.isEmpty()) {
                            String businessType = null;
                            if (policy.getBusinessType() != null) {
                                businessType = policy.getBusinessType();
                            }

                            policyId = updatePolicyCar(policyVehicle, productId, branch.getBranchId(), ClientId,
                                    brokerId, SublineId, carsPolicyToSearchList, insuranceCode, policy.getSubLineCode(),
                                    policy.getBlacklisted(), policy.getEndorsementTypeDescription(), policy.getEndorsementTypeCode(),
                                    policy.getEndorsementSubTypeDescription(), policy.getEndorsementSubTypeCode(), policy.getPolicyRootID().toString(),
                                    policy.getPolicyID().toString(), vehicle.getCertifID() != null ? vehicle.getCertifID().toString() : "", policy.getInsuredID().toString(),
                                    (policy.getFirstInsuredName() != null ? policy.getFirstInsuredName() : "") + " " + (policy.getFatherInsuredName() == null ? "" : policy.getFatherInsuredName()) + " " + (policy.getLastInsuredName() == null ? "" : policy.getLastInsuredName()), policy.getInsuredCode(), policy.getInsuredPhoneNumber(), businessType);

                        } else {
                            if (carsPolicyToSearchList2.size() > 0) {
                                String businessType = null;
                                if (policy.getBusinessType() != null) {
                                    businessType = policy.getBusinessType();
                                }

                                policyId = updatePolicyCar(policyVehicle, productId, branch.getBranchId(), ClientId,
                                        brokerId, SublineId, carsPolicyToSearchList2, insuranceCode,
                                        policy.getSubLineCode(), policy.getBlacklisted(), policy.getEndorsementTypeDescription(),
                                        policy.getEndorsementTypeCode(), policy.getEndorsementSubTypeDescription(), policy.getEndorsementSubTypeCode(),
                                        policy.getPolicyRootID().toString(), policy.getPolicyID().toString(),
                                        vehicle.getCertifID() != null ? vehicle.getCertifID().toString() : "", policy.getInsuredID().toString(),
                                        (policy.getFirstInsuredName() != null ? policy.getFirstInsuredName() : "") + " " + (policy.getFatherInsuredName() == null ? "" : policy.getFatherInsuredName()) + " " + (policy.getLastInsuredName() == null ? "" : policy.getLastInsuredName()), policy.getInsuredCode(), policy.getInsuredPhoneNumber(), businessType);
                            } else {
                                String businessType = null;
                                if (policy.getBusinessType() != null) {
                                    businessType = policy.getBusinessType();
                                }
                                policyId = insertPolicyCar(policyVehicle, productId, branch.getBranchId(), ClientId,
                                        broker.getBrokerId(), SublineId, insuranceCode,
                                        policy.getSysID().toString(), policy.getSubLineCode(),
                                        policy.getBlacklisted(), policy.getEndorsementTypeDescription(),
                                        policy.getEndorsementTypeCode(), policy.getEndorsementSubTypeDescription(),
                                        policy.getEndorsementSubTypeCode(), policy.getPolicyRootID().toString(),
                                        policy.getPolicyID().toString(), vehicle.getCertifID() != null ? vehicle.getCertifID().toString() : "", policy.getInsuredID().toString(),
                                        (policy.getFirstInsuredName() != null ? policy.getFirstInsuredName() : "") + " " + (policy.getFatherInsuredName() == null ? "" : policy.getFatherInsuredName()) + " " + (policy.getLastInsuredName() == null ? "" : policy.getLastInsuredName()), policy.getInsuredCode(), policy.getInsuredPhoneNumber(), businessType);

                            }
                        }


                        // carsPolicyCarToSearch.setCarObjectNumber(dataTransferPolicyLoaded.getPolicyCertificate());
                        carsPolicyCarToSearch.setPolicyId(policyId);

                        Collection<CarsDtModel> dataTransferModelList = getDataTransferByModel(insuranceCode,
                                vehicle.getCarModel(), vehicle.getCarMake());

                        String carId = null;

                        // carsPolicyToSearchList = carsPolicySearchable(policyVehicle);
//						if (carsPolicyToSearchList != null && !carsPolicyToSearchList.isEmpty()) {
//							policyId = updatePolicyCar(policyVehicle, products.getProductsId(), branch.getBranchId(),
//									client.getClientId(), broker.getBrokerId(), carsPolicyToSearchList, insuranceCode);
//						} else {
//							policyId = insertPolicyCar(policyVehicle, products.getProductsId(), branch.getBranchId(),
//									client.getClientId(), broker.getBrokerId(), insuranceCode);
//						}
                        System.out.println("plate test search " + carsPolicyCarToSearch.getCarPlate());
                        Example<CarsPolicyCar> example = Example.of(carsPolicyCarToSearch);

                        Collection<CarsPolicyCar> carsPolicyCarList = db.carsPolicyCarRepository.findAll(example);
                        System.out.println(" cert size" + carsPolicyCarList.size());

                        if (carsPolicyCarList != null && !carsPolicyCarList.isEmpty()) {
                            System.out.println("test cert update" + vehicle.getCertificateNo());

                            carId = updateCarsPolicyCar(vehicle, policyId, carsPolicyCarList, dataTransferModelList,
                                    stringBufferTrademark, policyNo);

                        } else {
                            System.out.println("test cert insert" + vehicle.getCertificateNo());

                            carId = insertCarsPolicyCar(vehicle, policyId, dataTransferModelList, stringBufferTrademark,
                                    policyNo);
                        }

                        // carsDtPolicyService.insertCarPolicy(vehicle);
                        if (vehicle.getClauses() != null) {
                            DeletePolicyWording(policyId);
                            for (Clauses clauses : vehicle.getClauses()) {


                                insertCarsPolicyWordingHAndD(clauses, policyId);
                            }
                        }
                        if (vehicle.getCovers() != null) {
                            deleteCover(carId);
                            // String a = policy.getPolicyID().toString()+"asdasdsd";
                            for (Covers covers : vehicle.getCovers()) {
                                String CarsCover = validateCover(covers, policyNo, vehicle.getCertificateNo());


                                insertCarsCover(covers, null, covers.getCoverCode().toString(), carId, CarsCover, null, covers.getCoverOrder());
                                if (covers.getSubCovers() != null && !covers.getSubCovers().isEmpty()) {
                                    for (SubCovers subCovers : covers.getSubCovers()) {
                                        String CarsSubCover = validateSubCover(subCovers, covers.getCoverCode(),
                                                policyNo, vehicle.getCertificateNo(), covers.getCoverID().toString());
                                        // carsDtCoverContractService.insertSubCover(subCovers, vehicle.getPolicyID());
                                        insertCarsCover(null, subCovers, subCovers.getSubCoverID().toString(), carId,
                                                covers.getCoverCode(), CarsSubCover, covers.getCoverOrder());
                                    }
                                }
                            }
                        }
                    }


                }
            }

            carsDtPolicyTransferLogService.insertTransferLog("Successful", insuranceCode, null, policyIdFromJson, policyNo);
//            entityManager.flush();
//            entityManager.clear();

            return new ResponseEntity(policies, HttpStatus.CREATED);

        } catch (

                Exception e) {
            counter++;
            if (counter == 1) {
                policyUpload(policies);
            }

            logger.error(policyNo + " " + e.getMessage());
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String sStackTrace = sw.toString();
            logger.error(sStackTrace);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//            SendingMail sendingMail = new SendingMail();
//            sendingMail.run(companyName + " Policy Upload policy " + policies.getPolicies().get(0).getPolicyNo(), body);//				SendingMail sendingMail = new SendingMail();
            try {
                SendingMail sendingMail = new SendingMail();

                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                String json = ow.writeValueAsString(policies);
                sendingMail.run(companyName + " Upload Error on policy Number " + policyNo, e.getMessage() + "\n " + json);
            } catch (Exception ee) {
                ee.printStackTrace();
                logger.error(ee.toString());
            }
            // TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error(e.toString());
            carsDtPolicyTransferLogService.insertTransferLog("Unsuccessful", insuranceCode, null, policyIdFromJson, policyNo);
            return new ResponseEntity(sStackTrace, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //	public CarsProducts validateCarsProduct(String productCode, Integer productId, String productDescription,
//			String productInsuranceId) throws Exception {
//		if (productCode == null || productId == 0 || productDescription == null) {
//			throw new Exception("Error :");
//		}
//		System.out.println("validateCarsProduct function");
//		Optional<CarsProducts> carsProducts = db.carsProductRepository
//				.findByProductsCodeAndProductsInsuranceId(productCode, productInsuranceId);
//
//		if (!carsProducts.isPresent()) {
//			CarsProducts carsProductsNew = new CarsProducts();
//			carsProductsNew.setProductsCode(productCode);
//			carsProductsNew.setProductsId(productId.toString());
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
    public CarsProducts validateCarsProduct(String productCode, String productDescription, String productInsuranceId,
                                            String policyNo) throws Exception {
        SendingMail sendingMail = new SendingMail();
//		if (productCode == null) {
//			StringBuffer message = new StringBuffer();
//			SendingMail sendingMail = new SendingMail();
//		if (Utility.isEmpty(productCode)) {
//			saveMessage(policyNo, "Subline Code", "Missing Field", "CARS_SUBLINE", productInsuranceId, null);
//		}
//		if (Utility.isEmpty(productDescription)) {
//			saveMessage(policyNo, "Subline Description", "Missing Field", "CARS_SUBLINE", productInsuranceId, null);
//		}

        if (Utility.isEmpty(productCode)) {
            return null;
        }
        Optional<CarsProducts> carsProducts = db.carsProductRepository
                .findByProductsCodeAndProductsInsuranceId(productCode, productInsuranceId);

        if (!carsProducts.isPresent()) {
            CarsProducts carsProductsNew = new CarsProducts();
            carsProductsNew.setProductsCode(productCode);
            carsProductsNew.setProductsId(productInsuranceId + "." + productCode);
            carsProductsNew.setProductsInsuranceId(productInsuranceId);
            carsProductsNew.setProductsDescription(productDescription);
            carsProductsNew.setSysVersionNumber(0);
            carsProductsNew.setSysCreatedBy(CREATED_BY_QUARTZ);
            carsProductsNew.setSysUpdatedBy(CREATED_BY_QUARTZ);
            carsProductsNew.setSysCreatedDate(new Timestamp(new Date().getTime()));
            carsProductsNew.setSysUpdatedDate(new Timestamp(new Date().getTime()));
            // carsProducts.setSysCreatedBy(new Timestamp(new Date().getTime()));
            db.carsProductRepository.save(carsProductsNew);
            saveMessage(policyNo, "Product " + productCode + " " + productDescription, "Added Field", "CARS_PRODUCTS",
                    productInsuranceId, null);

            return carsProductsNew;
        } else {
            carsProducts.get().setProductsCode(productCode);
            carsProducts.get().setProductsDescription(productDescription);
            carsProducts.get().setSysUpdatedBy(CREATED_BY_QUARTZ);
            carsProducts.get().setSysUpdatedDate(new Timestamp(new Date().getTime()));
            db.carsProductRepository.save(carsProducts.get());
            return carsProducts.get();
        }

    }

    public CarsBranch validateCarsBranch(String branchCode, String branchInsuranceId, String branchDescription,
                                         String policyNo) throws Exception {
        SendingMail sendingMail = new SendingMail();
        if (Utility.isEmpty(branchCode)) {

            // saveMessage(policyNo, "Branch Code", "Blocking Field", "CARS_BRANCH",
            // branchInsuranceId, null);
            throw new Exception("Error:");
        }
        if (Utility.isEmpty(branchDescription)) {
            // saveMessage(policyNo, "Branch Description", "Blocking Field", "CARS_BRANCH",
            // branchInsuranceId, null);
            throw new Exception("Error:");
        }
        String branchCodeNew = branchCode;
        branchCodeNew = branchCodeNew.replaceFirst("^0+(?=.)", "");

        Optional<CarsBranch> carsBranch = db.carsBranchRepository.findByBranchCodeAndBranchInsuranceId(branchCodeNew,
                branchInsuranceId);

        if (!carsBranch.isPresent()) {

            CarsBranch carsBranchNew = new CarsBranch();
            carsBranchNew.setBranchCode(branchCodeNew);
            carsBranchNew.setBranchId(insuranceCode + "." + branchCodeNew);// ask jean
            carsBranchNew.setBranchInsuranceId(branchInsuranceId);
            carsBranchNew.setBranchDes(branchDescription);
            carsBranchNew.setSysVersionNumber(0);
            carsBranchNew.setSysCreatedBy(CREATED_BY_QUARTZ);
            carsBranchNew.setSysUpdatedBy(CREATED_BY_QUARTZ);
            carsBranchNew.setSysCreatedDate(new Timestamp(new Date().getTime()));
            carsBranchNew.setSysUpdatedDate(new Timestamp(new Date().getTime()));
            // carsProducts.setSysCreatedBy(new Timestamp(new Date().getTime()));
            db.carsBranchRepository.save(carsBranchNew);

            saveMessage(policyNo, "Branch " + branchCode + " " + branchDescription, "Adding Field", "CARS_BRANCH",
                    branchInsuranceId, null);

            return carsBranchNew;
        }

        // Optional<CarsBranch> carsBranch =
        // db.carsBranchRepository.findByBranchCode(branchCode);
        if (carsBranch.isPresent()) {
            carsBranch.get().setBranchDes(branchDescription);
            carsBranch.get().setSysUpdatedBy(CREATED_BY_QUARTZ);
            carsBranch.get().setSysUpdatedDate(new Timestamp(new Date().getTime()));
            db.carsBranchRepository.save(carsBranch.get());

        }

        return carsBranch.get();
    }

    //	public CarsSubline validateCarsSubline(String sublineCode, String sublineInsuranceId, String sublineDescription)
//			throws Exception {
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
//			carsSubline.setSublineInsuranceId(sublineInsuranceId+"."+sublineCode);
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
    public CarsSubline validateCarsSubline(String sublineCode, String sublineInsuranceId, String sublineDescription,
                                           Integer sublineId, String policyNo) throws Exception {

        SendingMail sendingMail = new SendingMail();

        if (Utility.isEmpty(sublineCode)) {
            // saveMessage(policyNo, "Product Code", "Missing Field", "CARS_PRODUCTS",
            // sublineInsuranceId, null);

            return null;
        }
        if (sublineId == 0) {
            // saveMessage(policyNo, "ProductID", "Missing Field", "CARS_PRODUCTS",
            // sublineInsuranceId, null);
            return null;
        }

        if (Utility.isEmpty(sublineDescription)) {
            // saveMessage(policyNo, "Product Description", "Missing Field",
            // "CARS_PRODUCTS", sublineInsuranceId, null);
        }


        Optional<CarsSubline> carsSublineOpt = db.carsSublineRepository
                .findBySublineIdAndSublineInsuranceId(insuranceCode + "." + sublineId, sublineInsuranceId);

        if (!carsSublineOpt.isPresent()) {
            CarsSubline carsSubline = new CarsSubline();
            carsSubline.setSublineCode(sublineCode);
            carsSubline.setSublineDes(sublineDescription);
            carsSubline.setSublineInsuranceId(sublineInsuranceId);
            carsSubline.setSublineId(sublineInsuranceId + "." + sublineId);
            carsSubline.setSysVersionNumber(0);
            carsSubline.setSysCreatedBy(CREATED_BY_QUARTZ);
            carsSubline.setSysUpdatedBy(CREATED_BY_QUARTZ);
            carsSubline.setSysCreatedDate(new Timestamp(new Date().getTime()));
            carsSubline.setSysUpdatedDate(new Timestamp(new Date().getTime()));

            // carsProducts.setSysCreatedBy(new Timestamp(new Date().getTime()));
            db.carsSublineRepository.save(carsSubline);

            saveMessage(policyNo, sublineCode + " " + sublineDescription, "Added Field", "CARS_SUBLINE",
                    sublineInsuranceId, null);

            return carsSubline;
        }

//		Optional<CarsSubline> carsSubline = db.carsSublineRepository.findBySublineCodeAndSublineInsuranceId(sublineCode,
//				sublineInsuranceId);
        if (carsSublineOpt.isPresent()) {
            carsSublineOpt.get().setSublineDes(sublineDescription);
            carsSublineOpt.get().setSublineCode(sublineCode);
            carsSublineOpt.get().setSysUpdatedBy(CREATED_BY_QUARTZ);
            carsSublineOpt.get().setSysUpdatedDate(new Timestamp(new Date().getTime()));
            db.carsSublineRepository.save(carsSublineOpt.get());

        }

        return carsSublineOpt.get();
    }

    public CarsClient validateCarsClient(Integer clientId, String clientCode, String clientInsuranceId,
                                         String printName, String firstInsuredName, String fatherInsuredName, String lastInsuredName,
                                         String insuredPhoneNumber, boolean blackListed, String reason, String note, String policyNo,
                                         String certificateNo, String insBlackSetOn,
                                         String insBlackSetBy) throws Exception {


//		Optional<CarsPolicy> carsPolicy = db.carsPolicyRepository.findByPolicyCertIfID(certifId);
//	if(	carsPolicy.isPresent()){
//		carsPolicy.get().setPolicyHolderPhone(insuredPhoneNumber);
//		carsPolicy.get().setPolicyHolderId(clientId.toString());
//		carsPolicy.get().setPolicyHolderCode(clientCode);
//		carsPolicy.get().setPolicyHolderName(firstInsuredName+" "+fatherInsuredName+ ""+ lastInsuredName);
//		db.carsPolicyRepository.save(carsPolicy.get());
//	}

//		if (clientId == 0 || clientId==null) {
//throw new Exception ("Error :");
//
//		}
        if (Utility.isEmpty(clientCode)) {
            // saveMessage(policyNo, "Client Code", "Missing Field", "CARS_CLIENT",
            // clientInsuranceId, certificateNo);
            return null;
        }


//		String clientCodeNew = clientCode;
//		if (clientCodeNew != null) {
//		clientCodeNew = clientCodeNew.replaceFirst("-", "");
//		}
//		Optional<CarsClient> carsClient = db.carsClientRepository
//				.findByClientInsuranceIdAndClientNum1(clientInsuranceId, clientCodeNew);
        System.out.println("clientInsuranceId" + clientInsuranceId);
        System.out.println("clientCode" + clientCode);
        try {
            Optional<CarsClient> carsClient = db.carsClientRepository
                    .findByClientInsuranceIdAndClientNum1(clientInsuranceId, clientCode);


            if (!carsClient.isPresent()) {


                CarsClient carsClientNew = new CarsClient();
                // carsClientNew.setClientId(UUID.randomUUID().toString());
//			carsClientNew.setClientId(clientInsuranceId + "." + Long.valueOf(clientCodeNew) + "." + "0");
//			carsClientNew.setClientNum1(clientCodeNew);
                String ClientCodeInt = clientCode.replaceAll("-", "");

//                String cId = clientInsuranceId + "." + Integer.valueOf(ClientCodeInt) + "." + "0";
//                List<CarsPolicy> carsPolicyList = db.carsPolicyRepository.findByPolicyClientId(cId);
//                Set<String > businessPhoneNumbers = new HashSet<>();
//                StringBuffer stringBuffer=new StringBuffer();
//                if (carsPolicyList.size() > 0) {
////                    Date date = new Date();
////                    Timestamp ts = new Timestamp(date.getTime());
//                    carsPolicyList.forEach(carsPolicy -> {
////                        if (carsPolicy.getPolicyExpiryDate().before(ts)) {
//                            Optional<CarsClient> clientOptional = db.carsClientRepository.findById(carsPolicy.getPolicyClientId());
//                            if (clientOptional.isPresent()) {
//                                if (clientOptional.get().getClientMobilePhone() != null &&!clientOptional.get().getClientMobilePhone().isEmpty()) {
//                                    if(insuredPhoneNumber!=null  && !insuredPhoneNumber.isEmpty() ){
//
//                                        if(clientOptional.get().getClientMobilePhone().equals(insuredPhoneNumber)||clientOptional.get().getClientBusinessPhone().contains(insuredPhoneNumber)){
//                                        businessPhoneNumbers.add(clientOptional.get().getClientMobilePhone());
//
//
//                                    }
//
//
//                                }
//                                }
//
//
//                            }
//                            //carsClientNew.setClientBusinessPhone(carsPolicy.getph);
////                        }
//                    });
//
//                    if(businessPhoneNumbers.size()>0){
//                        businessPhoneNumbers.forEach(businessPhoneNumber->{
//                            stringBuffer.append(businessPhoneNumber);
//                            stringBuffer.append(" ");
//                        });
//                        carsClientNew.setClientBusinessPhone(stringBuffer.toString());
//
//                    }
//
//                }else{
                if (!Utility.isEmpty(insuredPhoneNumber)) {
                    carsClientNew.setClientMobilePhone(insuredPhoneNumber);

                }


                //     }


//                } else {
//                    carsClientNew.setClientBusinessPhone(null);
//                    carsClientNew.setClientMobilePhone(insuredPhoneNumber);
//                }

                carsClientNew.setClientReference(String.valueOf(clientId));

                carsClientNew.setClientId(clientInsuranceId + "." + ClientCodeInt + "." + "0");
                carsClientNew.setClientNum1(clientCode);

                carsClientNew.setClientNum2(Long.valueOf(0));
                carsClientNew.setClientInsuranceId(clientInsuranceId);
                carsClientNew.setClientFamilyName(lastInsuredName);
                carsClientNew.setClientFatherName(fatherInsuredName);
                carsClientNew.setClientFirstName(firstInsuredName);


                if (fatherInsuredName == null) {
                    carsClientNew.setClientFatherName("");

                }
                if (lastInsuredName == null) {
                    carsClientNew.setClientFamilyName("");
                }
                if (firstInsuredName == null) {
                    carsClientNew.setClientFirstName("");
                }

                if (Utility.isEmpty(firstInsuredName) && Utility.isEmpty(fatherInsuredName) && Utility.isEmpty(lastInsuredName)) {
                    carsClientNew.setClientFamilyName(printName);
                }

                // insuredPhoneNumber = insuredPhoneNumber.replace("-", "");
                carsClientNew.setClientMobilePhone(insuredPhoneNumber);
                carsClientNew.setSysVersionNumber(0);
                carsClientNew.setSysCreatedBy(CREATED_BY_QUARTZ);
                carsClientNew.setSysUpdatedBy(CREATED_BY_QUARTZ);
                carsClientNew.setSysCreatedDate(new Timestamp(new Date().getTime()));
                carsClientNew.setSysUpdatedDate(new Timestamp(new Date().getTime()));
                // carsClientNew.setClientP
                db.carsClientRepository.save(carsClientNew);
                logger.info("cars client of policy No " + policyNo + " inserted client id " + carsClientNew.getClientId());
                validateInsuranceBlackList(clientCode, clientInsuranceId, firstInsuredName, fatherInsuredName,
                        lastInsuredName, reason, note, insBlackSetOn, insBlackSetBy, blackListed);

                return carsClientNew;
            }
//		Optional<CarsClient> carsClient = db.carsClientRepository.findByClientInsuranceIdAndClientNum1(clientInsuranceId,
//				Long.valueOf(clientCode));
            if (carsClient.isPresent()) {


                // carsClient.get().setClientId(clientInsuranceId+"."+Long.valueOf(clientCodeNew));


                //           carsClient.get().setClientInsuranceId(clientInsuranceId);

//                List<CarsPolicy> carsPolicyList = db.carsPolicyRepository.findByPolicyClientId(carsClient.get().getClientId());
//                if (carsPolicyList.size() > 0) {
                System.out.println("we have an existing policy on update this");
                //   Date date = new Date();
//                    Timestamp ts = new Timestamp(date.getTime());
//                    carsPolicyList.forEach(carsPolicy -> {
                //   if (carsPolicy.getPolicyExpiryDate().before(ts)) {
//                        Optional<CarsClient> clientOptional = db.carsClientRepository.findById(carsPolicy.getPolicyClientId());
//                        if (clientOptional.isPresent()) {
                if (!Utility.isEmpty(insuredPhoneNumber)) {

                    if (Utility.isEmpty(carsClient.get().getClientBusinessPhone()) && Utility.isEmpty(carsClient.get().getClientMobilePhone())) {
                        carsClient.get().setClientMobilePhone(insuredPhoneNumber);

                    } else {

//                                    if(!carsClient.get().getClientBusinessPhone().isEmpty()||carsClient.get().getClientBusinessPhone()!=null){}
                        if (!insuredPhoneNumber.equals(carsClient.get().getClientMobilePhone())) {

                            if (!Utility.isEmpty(carsClient.get().getClientBusinessPhone())) {

                                if (!carsClient.get().getClientBusinessPhone().contains(insuredPhoneNumber)) {

                                    if (!Utility.isEmpty(carsClient.get().getClientMobilePhone())) {
                                        carsClient.get().setClientBusinessPhone(carsClient.get().getClientMobilePhone() + " " + carsClient.get().getClientBusinessPhone());

                                    }

                                    carsClient.get().setClientMobilePhone(insuredPhoneNumber);
                                }


                            } else {
                                if (!Utility.isEmpty(carsClient.get().getClientMobilePhone())) {
                                    carsClient.get().setClientBusinessPhone(carsClient.get().getClientMobilePhone());

                                }

                                carsClient.get().setClientMobilePhone(insuredPhoneNumber);
                            }


                        }


                    }


                }

                //carsClientNew.setClientBusinessPhone(carsPolicy.getph);
                //     }
//                    });
//                }
//                else {
//                    carsClient.get().setClientBusinessPhone(null);
//                    carsClient.get().setClientMobilePhone(insuredPhoneNumber);
//                }


                carsClient.get().setSysUpdatedBy(CREATED_BY_QUARTZ);
                carsClient.get().setSysUpdatedDate(new Timestamp(new Date().getTime()));
                carsClient.get().setClientFamilyName(lastInsuredName);
                carsClient.get().setClientFatherName(fatherInsuredName);
                carsClient.get().setClientFirstName(firstInsuredName);
                if (fatherInsuredName == null) {
                    carsClient.get().setClientFatherName("");

                }
                if (lastInsuredName == null) {
                    carsClient.get().setClientFamilyName("");
                }
                if (firstInsuredName == null) {
                    carsClient.get().setClientFirstName("");
                }

                if (Utility.isEmpty(firstInsuredName) && Utility.isEmpty(fatherInsuredName) && Utility.isEmpty(lastInsuredName)) {
                    carsClient.get().setClientFamilyName(printName);
                }


                carsClient.get().setClientReference(String.valueOf(clientId));
//carsClient.get().setClientNum1();


                //    carsClient.get().setClientMobilePhone(insuredPhoneNumber);
                carsClient.get().setSysUpdatedBy(CREATED_BY_QUARTZ);
                carsClient.get().setSysUpdatedDate(new Timestamp(new Date().getTime()));

                validateInsuranceBlackList(clientCode, clientInsuranceId, firstInsuredName, fatherInsuredName,
                        lastInsuredName, reason, note, insBlackSetOn, insBlackSetBy, blackListed);

                db.carsClientRepository.save(carsClient.get());
                logger.info("cars client of policy No " + policyNo + " updated client id " + carsClient.get().getClientId());
                return carsClient.get();
            }
        }
//todo
        catch (Exception e) {
            System.out.println("exep....." + e.toString());
        }


        return null;

    }

    public CarsBroker validateCarsBroker(String brokerCode, String brokerId, String brokerInsuranceId,
                                         String brokerName, String brokerPhoneNumber, Boolean isDirectBroker, String brokerMail,
                                         Boolean brokerWebOrigin, Boolean blackListed, String reason, String note, String setOn, String setBy) throws Exception {

        if (brokerId == null || brokerId.equals("0") || brokerId.equals("null")) {
            saveMessage(policyNo, "Broker Id", "Blocking Field",
                    "CARS_DT_CURRENCY", insuranceCode, null);
            throw new Exception("error :");

        }
        if (Utility.isEmpty(brokerCode) || Utility.isEmpty(brokerId)) {


            return null;
        }


        Optional<CarsBroker> carsBroker2 = db.carsBrokerRepository.findByBrokerInsuranceIdAndBrokerNum(
                brokerInsuranceId, brokerCode);
        //  Optional<CarsBroker> carsBroker = db.carsBrokerRepository.findById(brokerInsuranceId + "." + brokerCode);

        if (Utility.isEmpty(brokerName)) {
            brokerName = brokerCode;
        }


        if (!carsBroker2.isPresent()) {
            CarsBroker carsBrokerNew = new CarsBroker();
            carsBrokerNew.setBrokerId(brokerInsuranceId + "." + brokerCode);
            carsBrokerNew.setBrokerDesc(brokerName);
            carsBrokerNew.setBrokerNum(brokerCode);
            carsBrokerNew.setBrokerInsuranceId(brokerInsuranceId);
            carsBrokerNew.setBrokerTelephone(brokerPhoneNumber);
            carsBrokerNew.setBrokerReference(brokerId);
            carsBrokerNew.setBrokerEmail(brokerMail);
            carsBrokerNew.setSysVersionNumber(0);
            carsBrokerNew.setSysCreatedBy(CREATED_BY_QUARTZ);
            carsBrokerNew.setSysUpdatedBy(CREATED_BY_QUARTZ);
            carsBrokerNew.setSysCreatedDate(new Timestamp(new Date().getTime()));
            carsBrokerNew.setSysUpdatedDate(new Timestamp(new Date().getTime()));

            db.carsBrokerRepository.save(carsBrokerNew);


            validateBrokerBlackList(brokerCode, brokerId, brokerInsuranceId, brokerName, brokerPhoneNumber, reason,
                    note, setOn, setBy, blackListed);


            return carsBrokerNew;
        }

        // Optional<CarsBroker> carsBroker = db.carsBrokerRepository.findById(brokerId);
        if (carsBroker2.isPresent()) {
            carsBroker2.get().setBrokerDesc(brokerName);
            carsBroker2.get().setBrokerTelephone(brokerPhoneNumber);
            carsBroker2.get().setSysUpdatedBy(CREATED_BY_QUARTZ);
            carsBroker2.get().setBrokerReference(brokerId);
            carsBroker2.get().setBrokerEmail(brokerMail);
            carsBroker2.get().setSysUpdatedDate(new Timestamp(new Date().getTime()));
            db.carsBrokerRepository.save(carsBroker2.get());
        }

        validateBrokerBlackList(brokerCode, brokerId, brokerInsuranceId, brokerName, brokerPhoneNumber, reason,
                note, setOn, setBy, blackListed);

        return carsBroker2.get();
    }

    public void validateInsuranceBlackList(String insuredCode, String clientInsuranceId, String firstInsuredName,
                                           String fatherInsuredName, String lastInsuredName, String reason, String note, String insBlackSetOn, String insBlackSetBy, boolean blackListed) {

        Optional<CarsBlackList> carsBlackListOptional = db.carsBlackListRepository
                .findTopByClientNumAndBlInsuranceIdOrderBySysUpdatedDateDesc(insuredCode, clientInsuranceId);

        if (!carsBlackListOptional.isPresent()) {
            if (blackListed) {
                CarsBlackList carsBlackList = new CarsBlackList();
                carsBlackList.setBlId(UUID.randomUUID().toString());
                carsBlackList.setBlInsuranceId(clientInsuranceId);
                carsBlackList.setClientNum(insuredCode);
                carsBlackList.setBlFirstName(firstInsuredName);
                carsBlackList.setBlFatherName(fatherInsuredName);
                carsBlackList.setBlFamilyName(lastInsuredName);
                carsBlackList.setBlCar("INS");
                if (fatherInsuredName == null) {
                    carsBlackList.setBlFatherName("");

                }
                if (lastInsuredName == null) {
                    carsBlackList.setBlFamilyName("");
                }


                carsBlackList.setBlNote(note);
                carsBlackList.setBlReason(reason);
                carsBlackList.setBlStatus("IN");

                carsBlackList.setSysVersionNumber(0);
                carsBlackList.setSysCreatedBy(CREATED_BY_QUARTZ);
                carsBlackList.setSysUpdatedBy(CREATED_BY_QUARTZ);
                carsBlackList.setSysCreatedDate(new Timestamp(new Date().getTime()));
                carsBlackList.setSysUpdatedDate(new Timestamp(new Date().getTime()));


                if (!Utility.isEmpty(insBlackSetOn)) {
                    try {
                        Date setOnn = new SimpleDateFormat("dd-MM-yyyy").parse(insBlackSetOn);
                        carsBlackList.setBlDate(new Timestamp(setOnn.getTime()));

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                carsBlackList.setBlSetBy(insBlackSetBy);
                System.out.println("client balckList saved");

                db.carsBlackListRepository.save(carsBlackList);
                logger.info(" InsuranceBlackList of  policy No " + policyNo, " added  blid  " + carsBlackList.getBlId());

            }
        }

        if (carsBlackListOptional.isPresent()) {

            if ((blackListed && carsBlackListOptional.get().getBlStatus().equals("IN")) || (!blackListed && carsBlackListOptional.get().getBlStatus().equals("OU"))) {

                carsBlackListOptional.get().setBlFirstName(firstInsuredName);
                carsBlackListOptional.get().setBlFatherName(fatherInsuredName);
                carsBlackListOptional.get().setBlFamilyName(lastInsuredName);
                carsBlackListOptional.get().setBlCar("INS");

                if (fatherInsuredName == null) {
                    carsBlackListOptional.get().setBlFatherName("");

                }
                if (lastInsuredName == null) {
                    carsBlackListOptional.get().setBlFamilyName("");
                }
                if (blackListed) {
                    carsBlackListOptional.get().setBlStatus("IN");

                } else {
                    carsBlackListOptional.get().setBlStatus("OU");

                }


                if (!Utility.isEmpty(insBlackSetOn)) {
                    try {
                        Date setOnn = new SimpleDateFormat("dd-MM-yyyy").parse(insBlackSetOn);
                        carsBlackListOptional.get().setBlDate(new Timestamp(setOnn.getTime()));

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                carsBlackListOptional.get().setBlSetBy(insBlackSetBy);


                carsBlackListOptional.get().setBlNote(note);
                carsBlackListOptional.get().setBlReason(reason);
                carsBlackListOptional.get().setSysUpdatedBy(CREATED_BY_QUARTZ);
                carsBlackListOptional.get().setSysUpdatedDate(new Timestamp(new Date().getTime()));
                System.out.println("client balckList updated");

                db.carsBlackListRepository.save(carsBlackListOptional.get());
            } else {


                CarsBlackList carsBlackList = new CarsBlackList();


                if (blackListed) {
                    carsBlackList.setBlStatus("IN");

                } else {
                    carsBlackList.setBlStatus("OU");

                }

                carsBlackList.setBlId(UUID.randomUUID().toString());
                carsBlackList.setBlInsuranceId(carsBlackListOptional.get().getBlInsuranceId());
                carsBlackList.setClientNum(carsBlackListOptional.get().getClientNum());
                carsBlackList.setBlFirstName(firstInsuredName);
                carsBlackList.setBlFatherName(fatherInsuredName);
                carsBlackList.setBlFamilyName(lastInsuredName);
                carsBlackList.setBlCar("INS");
                if (fatherInsuredName == null) {
                    carsBlackList.setBlFatherName("");

                }
                if (lastInsuredName == null) {
                    carsBlackList.setBlFamilyName("");
                }


                carsBlackList.setBlNote(note);
                carsBlackList.setBlReason(reason);

                carsBlackList.setSysVersionNumber(0);
                carsBlackList.setSysCreatedBy(CREATED_BY_QUARTZ);
                carsBlackList.setSysUpdatedBy(CREATED_BY_QUARTZ);
                carsBlackList.setSysCreatedDate(new Timestamp(new Date().getTime()));
                carsBlackList.setSysUpdatedDate(new Timestamp(new Date().getTime()));


                if (!Utility.isEmpty(insBlackSetOn)) {
                    try {
                        Date setOnn = new SimpleDateFormat("dd-MM-yyyy").parse(insBlackSetOn);
                        carsBlackList.setBlDate(new Timestamp(setOnn.getTime()));

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                carsBlackList.setBlSetBy(insBlackSetBy);
                System.out.println("client balckList saved");

                db.carsBlackListRepository.save(carsBlackList);
                logger.info(" InsuranceBlackList of  policy No " + policyNo, " updated  blid   " + carsBlackList.getBlId());


            }

        }

    }


    public void validateBrokerBlackList(String brokerCode, String brokerId, String brokerInsuranceId, String brokerName,
                                        String brokerPhoneNumber, String reason, String note, String setOn, String setBy, boolean blackListed) {

//        List<CarsBlackList> carsBlackListList = db.carsBlackListRepository
//                .findByBlInsuranceIdAndClientNum(brokerInsuranceId, brokerCode);
        System.out.println("brokerCode is  " + brokerCode);
        Optional<CarsBlackList> carsBroker = db.carsBlackListRepository.findTopByBlBrokerIdOrderBySysUpdatedDateDesc(brokerInsuranceId + "." + brokerCode);
        if (!carsBroker.isPresent()) {
            if (blackListed) {
                System.out.println("new broker black list");
                CarsBlackList carsBlackList = new CarsBlackList();
                carsBlackList.setBlId(UUID.randomUUID().toString());
                carsBlackList.setBlInsuranceId(brokerInsuranceId);
                carsBlackList.setBlBrokerId(brokerInsuranceId + "." + brokerCode);
                carsBlackList.setBlFamilyName(brokerName);
                carsBlackList.setBlStatus("IN");
                carsBlackList.setBlReason(reason);
                if (!Utility.isEmpty(setOn)) {
                    try {
                        Date setOnn = new SimpleDateFormat("dd-MM-yyyy").parse(setOn);
                        carsBlackList.setBlDate(new Timestamp(setOnn.getTime()));

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                carsBlackList.setBlNote(note);

                carsBlackList.setBlSetBy(setBy);
                carsBlackList.setSysVersionNumber(0);
                carsBlackList.setSysCreatedBy(CREATED_BY_QUARTZ);
                carsBlackList.setSysUpdatedBy(CREATED_BY_QUARTZ);
                carsBlackList.setSysCreatedDate(new Timestamp(new Date().getTime()));
                carsBlackList.setSysUpdatedDate(new Timestamp(new Date().getTime()));
                db.carsBlackListRepository.save(carsBlackList);
            }
        }

        //Optional<CarsBlackList> carsBroker = db.carsBlackListRepository.findByClientNum(insuranceCode);
        if (carsBroker.isPresent()) {
            if ((blackListed && carsBroker.get().getBlStatus().equals("IN")) || (!blackListed && carsBroker.get().getBlStatus().equals("OU"))) {

                carsBroker.get().setBlFamilyName(brokerName);
                carsBroker.get().setBlReason(reason);
                if (!Utility.isEmpty(setOn)) {
                    try {
                        Date setOnn = new SimpleDateFormat("dd-MM-yyyy").parse(setOn);
                        carsBroker.get().setBlDate(new Timestamp(setOnn.getTime()));

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                carsBroker.get().setBlNote(note);

                carsBroker.get().setBlSetBy(setBy);

                carsBroker.get().setSysUpdatedBy(CREATED_BY_QUARTZ);
                carsBroker.get().setSysUpdatedDate(new Timestamp(new Date().getTime()));
                db.carsBlackListRepository.save(carsBroker.get());


            } else {


                CarsBlackList carsBlackList = new CarsBlackList();
                carsBlackList.setBlId(UUID.randomUUID().toString());
                carsBlackList.setBlInsuranceId(carsBroker.get().getBlInsuranceId());
                carsBlackList.setBlBrokerId(carsBroker.get().getBlBrokerId());
                carsBlackList.setBlFamilyName(brokerName);
                if (blackListed) {
                    carsBlackList.setBlStatus("IN");

                } else {
                    carsBlackList.setBlStatus("OU");

                }
                carsBlackList.setBlReason(reason);
                if (!Utility.isEmpty(setOn)) {
                    try {
                        Date setOnn = new SimpleDateFormat("dd-MM-yyyy").parse(setOn);
                        carsBlackList.setBlDate(new Timestamp(setOnn.getTime()));


                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                carsBlackList.setBlNote(note);

                carsBlackList.setBlSetBy(setBy);
                carsBlackList.setSysVersionNumber(0);
                carsBlackList.setSysCreatedBy(CREATED_BY_QUARTZ);
                carsBlackList.setSysUpdatedBy(CREATED_BY_QUARTZ);
                carsBlackList.setSysCreatedDate(new Timestamp(new Date().getTime()));
                carsBlackList.setSysUpdatedDate(new Timestamp(new Date().getTime()));
                db.carsBlackListRepository.save(carsBlackList);

            }


        }

    }

    public String validatePremiumCurrency(String premCurrency, String premCurrencyDescription, double PremExchangeRate,
                                          String policyNo) throws Exception {

        if (Utility.isEmpty(premCurrency)) {
            // saveMessage(policyNo, "Premium Currency", "Blocking Field",
            // "CARS_DT_CURRENCY", insuranceCode, null);
            throw new Exception("Error :");
        }
        if (Utility.isEmpty(premCurrencyDescription)) {
            // saveMessage(policyNo, "Premium Currency Description", "Blocking Field",
            // "CARS_DT_CURRENCY", insuranceCode,
            // null);
            throw new Exception("Error :");
        }
        if (PremExchangeRate == 0) {
            // saveMessage(policyNo, "Premium Exchange Rate", "Blocking Field",
            // "CARS_DT_CURRENCY", insuranceCode, null);
            throw new Exception("Error :");
        }

        Optional<CarsDtCurrency> carsDtCurrency = db.carsDtCurrencyRepository
                .findByInsCurrencyCodeAndInsId(premCurrency, Integer.valueOf(insuranceCode));
        if (carsDtCurrency.isPresent()) {
            return carsDtCurrency.get().getCeCode();
        }
        saveMessage(policyNo, "Si Currency conversion from " + premCurrency + " to ce code", "Blocking Field",
                "CARS_DT_CURRENCY", insuranceCode, null);
        throw new Exception("Error :");

    }

    public String validateSICurrency(String siCurrency, String siCurrencyDescription, double siExchangeRate,
                                     String policyNo) throws Exception {

        if (Utility.isEmpty(siCurrency)) {
            // saveMessage(policyNo, "si Currency", "Blocking Field", "CARS_DT_CURRENCY",
            // insuranceCode, null);
            throw new Exception("Error :");
        }
        if (Utility.isEmpty(siCurrencyDescription)) {
            // saveMessage(policyNo, "si Currency Description", "Blocking Field",
            // "CARS_DT_CURRENCY", insuranceCode, null);
            throw new Exception("Error :");
        }
        if (siExchangeRate == 0.0) {
            // saveMessage(policyNo, "si Exchange Rate", "Blocking Field",
            // "CARS_DT_CURRENCY", insuranceCode, null);
            throw new Exception("Error :");
        }

        Optional<CarsDtCurrency> carsDtCurrency = db.carsDtCurrencyRepository.findByInsCurrencyCodeAndInsId(siCurrency,
                Integer.valueOf(insuranceCode));
        if (carsDtCurrency.isPresent()) {
            return carsDtCurrency.get().getCeCode();
        }
        saveMessage(policyNo, "Si Currency conversion from " + siCurrency + " to ce code", "Blocking Field",
                "CARS_DT_CURRENCY", insuranceCode, null);
        throw new Exception("Error :");

    }

    public String populateTrademark(StringBuffer stringBufferTrademark, String id,
                                    Collection<CarsDtModel> dataTransferModelList) {

        if (dataTransferModelList != null && !dataTransferModelList.isEmpty()) {
            String modelCode = dataTransferModelList.iterator().next().getTrademarkId();
            if (!".".equals(modelCode) && !"".equals(modelCode) && modelCode != null) {
                return modelCode;
            } else {
                stringBufferTrademark.append(id + "\n");
                i++;
                return "ZZ.ZZZZ";
            }
        } else {
            stringBufferTrademark.append(id + "\n");
            i++;
            return "ZZ.ZZZZ";
        }
    }

    public String populateBrand(StringBuffer stringBufferTrademark, String id,
                                Collection<CarsDtModel> dataTransferModelList) {
        if (dataTransferModelList != null && !dataTransferModelList.isEmpty()) {

            String trademark = dataTransferModelList.iterator().next().getTrademarkId();
            if (trademark != null) {
                String makeCode = trademark.substring(0, trademark.lastIndexOf("."));

                if (!".".equals(makeCode) && !"".equals(makeCode)) {
                    return makeCode;
                } else {
                    stringBufferTrademark.append(id + "\n");
                    i++;
                    return "ZZ";
                }
            } else {
                stringBufferTrademark.append(id + "\n");
                i++;
                return "ZZ";
            }

        } else {
            stringBufferTrademark.append(id + "\n");
            i++;
            return "ZZ";
        }
    }

    public String populateShape(StringBuffer stringBufferTrademark, String id,
                                Collection<CarsDtModel> dataTransferModelList) {
        //System.out.println( "populate my shape"+dataTransferModelList.iterator().next().getTrademarkId());
        if (dataTransferModelList != null && !dataTransferModelList.isEmpty()) {
            String value = dataTransferModelList.iterator().next().getTrademarkId();
            if (!".".equals(value) && !"".equals(value) && value != null) {
                return value + ".ZZZZZ";
            } else {
                stringBufferTrademark.append(id + "\n");
                i++;
                return "ZZ.ZZZZ.ZZZZZ";
            }

        } else {
            stringBufferTrademark.append(id + "\n");
            i++;
            return "ZZ.ZZZZ.ZZZZZ";
        }
    }

    public String insertCarsPolicyCar(Vehicles vehicles, String policyId, Collection<CarsDtModel> dataTransferModelList,
                                      StringBuffer stringBufferTrademark, String policyNo) {
        CarsPolicyCar carsPolicyCar = new CarsPolicyCar();
        carsPolicyCar.setCarId(UUID.randomUUID().toString());


        if (Utility.isEmpty(vehicles.getCertificateNo()) && vehicles.getCertificateNo() != null) {
            carsPolicyCar.setCarObjectNumber(Integer.valueOf(vehicles.getCertificateNo()));

        } else {
            carsPolicyCar.setCarObjectNumber(0);

        }


        if (!Utility.isEmpty(vehicles.getCarYear())) {
            carsPolicyCar.setCarYear(Integer.valueOf(vehicles.getCarYear()));
        }
        // else {
//			saveMessage(policyNo, "Car Year", "Missing Field", "CARS_POLICY_CAR", insuranceCode,
//					vehicles.getCertificateNo());
//		}
        if (!Utility.isEmpty(vehicles.getCarChassis())) {
            carsPolicyCar.setCarChassis(vehicles.getCarChassis());
        }


//		else {
//			saveMessage(policyNo, " Car Chassis", "Missing Field", "CARS_POLICY_CAR", insuranceCode, null);
//		}
        carsPolicyCar.setCarModelToPrint(vehicles.getModelToPrint());
//		if(vehicles.getBlacklisted()){
        if (vehicles.getCertificateBlacklisted()) {
            carsPolicyCar.setCarBlackListing("Y");


        } else {
            carsPolicyCar.setCarBlackListing("N");

        }


        carsPolicyCar.setCarBrandCode(null);
        carsPolicyCar.setCarTrademarkCode(null);
        carsPolicyCar.setCarShapeCode(null);
        carsPolicyCar.setCarInsurance(Integer.valueOf(insuranceCode));
        carsPolicyCar.setCarLob("MO");
        String insValue = vehicles.getCarInsVal();
        if (!Utility.isEmpty(insValue)) {
            insValue = insValue.replaceAll("[,]", "");
            carsPolicyCar.setCarInsuranceValue(BigDecimal.valueOf(Double.valueOf(insValue)));
        }
        // carsPolicyCar.setcarI(null);
//	carsPolicyCar.setCarType(vehicles.getPolicyType());
        // carsPolicyCar.setCarUsageCode(vehicles.getCarUsage()); hjean
        carsPolicyCar.setCarPlate(vehicles.getCarPlatePrefix() + " " + vehicles.getCarPlateNumber());// to ask
        carsPolicyCar.setCarColor(null);
        carsPolicyCar.setCarDepreciation(null);
        carsPolicyCar.setCarTpDepreciation(null);
        carsPolicyCar.setCarBrandDesc(vehicles.getCarMake());
        carsPolicyCar.setCarTrademarkDesc(vehicles.getCarModel());
        carsPolicyCar.setCarUsage(vehicles.getCarUsage());
        // carsPolicyCar.setCarUsage(vehicles.getCarUsage()); jeannnnnnnnn
        carsPolicyCar.setCarEngine(vehicles.getCarEngine());
        // carsPolicyCar.setCarDeductible(vehicles.getDeductible());
        carsPolicyCar.setCarTheetDeductible(null);
        carsPolicyCar.setCarHoldupDeductible(null);
        carsPolicyCar.setCarProductDeductible(null);
        carsPolicyCar.setCarVip(null);
        carsPolicyCar.setPolicyId(policyId);
        carsPolicyCar.setPolicyDealerWorkshopName(vehicles.getDealerWorkshopName());
        carsPolicyCar.setShapeId(
                populateShape(stringBufferTrademark, vehicles.getPolicyID().toString(), dataTransferModelList));

//		carsPolicyCar.setShapeId(populateShape(stringBufferTrademark,
//				policyId + ":" + dataTransferPolicyLoaded.getPolicyCarMake(),
//				dataTransferModelList));

        carsPolicyCar.setCarBeneficiary(null);
        carsPolicyCar.setCarBeneficiaryDesc(vehicles.getCarBeneficiary());
        carsPolicyCar.setBrandId(
                populateBrand(stringBufferTrademark, vehicles.getPolicyID().toString(), dataTransferModelList));
        carsPolicyCar.setTrademarkId(
                populateTrademark(stringBufferTrademark, vehicles.getPolicyID().toString(), dataTransferModelList));
        carsPolicyCar.setSysVersionNumber(0);
        carsPolicyCar.setSysCreatedBy(CREATED_BY_QUARTZ);
        carsPolicyCar.setSysUpdatedBy(CREATED_BY_QUARTZ);
        carsPolicyCar.setSysCreatedDate(new Timestamp(new Date().getTime()));
        carsPolicyCar.setSysUpdatedDate(new Timestamp(new Date().getTime()));
        carsPolicyCar.setCarBeneficiaryDesc(vehicles.getCarBeneficiary());

        //	if(vehicles.getBlacklisted()){
        if (vehicles.getCertificateBlacklisted()) {
//todo check
//
//			validateCertificateBlackList(vehicles.getCarInsuredCode(),vehicles.getCarInsuredID().toString(),vehicles.getCarinsuredfirstName(),
//					vehicles.getCarinsuredfatherName(),vehicles.getCarinsuredlastName(),vehicles.getCertificateReason(),vehicles.getCertificateNote(),vehicles.getCertificateSetOn().toString(),vehicles.getCertificateSetBy().toString());
            carsPolicyCar.setCarCertifdBlackListed("Y");


        } else {
            carsPolicyCar.setCarCertifdBlackListed("N");

        }
//		carsPolicyCar.setCarCertifdBlackListedSetOn(vehicles.getSetOn());
//		carsPolicyCar.setCarCertifdBlackListedSetBy(vehicles.getSetBy());
//		carsPolicyCar.setCarCertifdBlackListedReason(vehicles.getReason());
//		carsPolicyCar.setCarCertifdBlackListedNote(vehicles.getNote());
        carsPolicyCar.setCarCertifdBlackListedSetOn(vehicles.getCertificateSetOn());
        carsPolicyCar.setCarCertifdBlackListedSetBy(vehicles.getCertificateSetBy());
        carsPolicyCar.setCarCertifdBlackListedReason(vehicles.getCertificateReason());
        carsPolicyCar.setCarCertifdBlackListedNote(vehicles.getCertificateNote());
        db.carsPolicyCarRepository.save(carsPolicyCar);
        logger.info("insert cars policy car  of " + policyNo + "and carId " + carsPolicyCar.getCarId());
        // CarsPolicyCarFactory.getService().insert(carsPolicyCar);
        return carsPolicyCar.getCarId();
    }

    public String updateCarsPolicyCar(Vehicles vehicles, String policyId, Collection<CarsPolicyCar> carsPolicyCarList,
                                      Collection<CarsDtModel> dataTransferModelList, StringBuffer stringBufferTrademark, String policyNo) {

        CarsPolicyCar carsPolicyCarIterator = carsPolicyCarList.iterator().next();
        // CarsPolicyCar carsPolicyCarToLoad = new CarsPolicyCar();
        Optional<CarsPolicyCar> carsPolicy = db.carsPolicyCarRepository.findById(carsPolicyCarIterator.getCarId());


        CarsPolicyCar carsPolicyCarToSave = carsPolicy.get();

        if (Utility.isEmpty(vehicles.getCertificateNo()) && vehicles.getCertificateNo() != null) {
            carsPolicyCarToSave.setCarObjectNumber(Integer.valueOf(vehicles.getCertificateNo()));

        } else {
            carsPolicyCarToSave.setCarObjectNumber(0);

        }


        //	if(vehicles.getBlacklisted()){
        if (vehicles.getCertificateBlacklisted()) {
            carsPolicyCarToSave.setCarBlackListing("Y");


        } else {
            carsPolicyCarToSave.setCarBlackListing("N");

        }


        if (!Utility.isEmpty(vehicles.getCarYear())) {
            carsPolicyCarToSave.setCarYear(Integer.valueOf(vehicles.getCarYear()));
        }


        //	if(vehicles.getBlacklisted()){
        if (vehicles.getCertificateBlacklisted()) {
            carsPolicyCarToSave.setCarCertifdBlackListed("Y");


        } else {
            carsPolicyCarToSave.setCarCertifdBlackListed("N");

        }
//		carsPolicyCarToSave.setCarCertifdBlackListedSetOn(vehicles.getSetOn());
//		carsPolicyCarToSave.setCarCertifdBlackListedSetBy(vehicles.getSetBy());
//		carsPolicyCarToSave.setCarCertifdBlackListedReason(vehicles.getReason());
//		carsPolicyCarToSave.setCarCertifdBlackListedNote(vehicles.getNote());
//
        carsPolicyCarToSave.setCarCertifdBlackListedSetOn(vehicles.getCertificateSetOn());
        carsPolicyCarToSave.setCarCertifdBlackListedSetBy(vehicles.getCertificateSetBy());
        carsPolicyCarToSave.setCarCertifdBlackListedReason(vehicles.getCertificateReason());
        carsPolicyCarToSave.setCarCertifdBlackListedNote(vehicles.getCertificateNote());


//		else {
//			saveMessage(policyNo, "Car Year", "Missing Field", "CARS_POLICY_CAR", insuranceCode,
//					vehicles.getCertificateNo());
//		}
        if (!Utility.isEmpty(vehicles.getCarChassis())) {
            carsPolicyCarToSave.setCarChassis(vehicles.getCarChassis());
        }
//		else {
//			saveMessage(policyNo, " Car Chassis", "Missing Field", "CARS_POLICY_CAR", insuranceCode, null);
//		}
        carsPolicyCarToSave.setCarBrandCode(null);
        carsPolicyCarToSave.setCarTrademarkCode(null);
        carsPolicyCarToSave.setCarShapeCode(null);
        String insValue = vehicles.getCarInsVal();
        if (!Utility.isEmpty(insValue)) {
            insValue = insValue.replaceAll("[,]", "");
            carsPolicyCarToSave.setCarInsuranceValue(BigDecimal.valueOf(Double.valueOf(insValue)));
        }
//	carsPolicyCar.setCarType(vehicles.getPolicyType());
        // carsPolicyCarToSave.setCarUsageCode(vehicles.getCarUsage()); check with jean
        // this field


        if (vehicles.getCarPlateNumber() != null && vehicles.getCarPlatePrefix() != null) {
            carsPolicyCarToSave.setCarPlate(vehicles.getCarPlatePrefix() + " " + vehicles.getCarPlateNumber());

        }
        if (vehicles.getCarPlateNumber() == null && vehicles.getCarPlatePrefix() != null) {
            carsPolicyCarToSave.setCarPlate(vehicles.getCarPlatePrefix());

        }
        if (vehicles.getCarPlatePrefix() == null && vehicles.getCarPlateNumber() != null) {
            carsPolicyCarToSave.setCarPlate(vehicles.getCarPlateNumber());

        }
        if (vehicles.getCarPlatePrefix() == null && vehicles.getCarPlateNumber() == null) {
            carsPolicyCarToSave.setCarPlate("");

        }


        // to as
        carsPolicyCarToSave.setCarUsage(vehicles.getCarUsage());
        carsPolicyCarToSave.setCarColor(null);
        carsPolicyCarToSave.setCarDepreciation(null);
        carsPolicyCarToSave.setCarTpDepreciation(null);
        carsPolicyCarToSave.setCarBrandDesc(vehicles.getCarMake());
        carsPolicyCarToSave.setCarTrademarkDesc(vehicles.getCarModel());
        // carsPolicyCarToSave.setCarUsage(vehicles.getCarUsage()); jeannnnnnnnn
        carsPolicyCarToSave.setCarEngine(vehicles.getCarEngine());
        carsPolicyCarToSave.setPolicyDealerWorkshopName(vehicles.getDealerWorkshopName());
        carsPolicyCarToSave.setCarModelToPrint(vehicles.getModelToPrint());
        // carsPolicyCar.setCarDeductible(vehicles.getDeductible());
        carsPolicyCarToSave.setCarTheetDeductible(null);

        carsPolicyCarToSave.setCarHoldupDeductible(null);
        carsPolicyCarToSave.setCarProductDeductible(null);
        carsPolicyCarToSave.setCarVip(null);
        carsPolicyCarToSave.setPolicyId(policyId);
        carsPolicyCarToSave.setCarCertIfID(vehicles.getCertifID() != null ? vehicles.getCertifID().toString() : "");

        carsPolicyCarToSave.setShapeId(
                populateShape(stringBufferTrademark, vehicles.getPolicyID().toString(), dataTransferModelList));
        carsPolicyCarToSave.setCarBeneficiary(null);
        carsPolicyCarToSave.setCarBeneficiaryDesc(vehicles.getCarBeneficiary());
        carsPolicyCarToSave.setBrandId(
                populateBrand(stringBufferTrademark, vehicles.getPolicyID().toString(), dataTransferModelList));
        carsPolicyCarToSave.setTrademarkId(
                populateTrademark(stringBufferTrademark, vehicles.getPolicyID().toString(), dataTransferModelList));
        carsPolicyCarToSave.setSysVersionNumber(0);
        carsPolicyCarToSave.setSysUpdatedBy(CREATED_BY_QUARTZ);
        carsPolicyCarToSave.setSysUpdatedDate(new Timestamp(new Date().getTime()));
        db.carsPolicyCarRepository.save(carsPolicyCarToSave);
        logger.info("update cars policy car  of " + policyNo + "and carId " + carsPolicyCarToSave.getCarId());

        return carsPolicyCarToSave.getCarId();
    }

    public String insertPolicyCar(PolicyVehicle policyVehicle, String productId, String branchId, String clientId,
                                  String brokerId, String subLine, String insuranceId, String sysId, String subLineCode, boolean blackListed, String endorsementTypeDes, String endorsementTypeCode, String endorsementSubTypeDes, String endorsementSubTypeCode, String policyRootId, String policyId, String certifID, String insuredID, String insuredFirstName, String insuredCode1, String insuredPhoneNumber, String businessType) throws Exception {

        CarsPolicy carsPolicy = new CarsPolicy();
        carsPolicy.setPolicyId(UUID.randomUUID().toString());
        carsPolicy.setPolicyRootId(policyRootId);
        carsPolicy.setPolicyFleetId(policyId);
        carsPolicy.setPolicyCertIfID(certifID);

        carsPolicy.setPolicyHolderPhone(insuredPhoneNumber);
        carsPolicy.setPolicyHolderCode(insuredCode1);

        Optional<CarsClient> carsClient = db.carsClientRepository
                .findByClientInsuranceIdAndClientNum1(insuranceCode, insuredCode1);
        if (carsClient.isPresent()) {
            carsPolicy.setPolicyHolderId(carsClient.get().getClientId());

        }


        carsPolicy.setPolicyHolderName(insuredFirstName);

        if (blackListed) {
            carsPolicy.setPolicyBlackListed("Y");
        } else {
            carsPolicy.setPolicyBlackListed("N");
        }
        // if (vehicle.getPolicyBranch() != null) {
        // carsPolicy.setPolicyBranch(dataTransferPolicyLoaded.getPolicyBranch());
        // }
        carsPolicy.setPolicyNumberDisplay(policyVehicle.getPolicy().getPolicyNo());
        String branchCodeNew = policyVehicle.getPolicy().getBranchCode();
        branchCodeNew = branchCodeNew.replaceFirst("^0+(?=.)", "");
        carsPolicy.setPolicyBranch(branchCodeNew);
        carsPolicy.setPolicyLob("MO");
        carsPolicy.setPolicyBatchNum(sysId);
        String policyNumber = policyVehicle.getPolicy().getPolicyNo();
//		Optional<CarsProducts> carsProducts = db.carsProductRepository.findById(productId);
//		String[] arrOfStr = policyNumber.split("-", 2);
//		policyNumber = arrOfStr[1].replaceAll("[^0-9]", "");

        // Optional<CarsProducts> carsProducts =
        // db.carsProductRepository.findById(productId);

        // String[] arrOfStr = policyNumber.split("-", 4);
        // policyNumber = arrOfStr[2] + "-" + arrOfStr[3];

        // carsPolicy.setPolicyNumber(policyVehicle.getPolicy().getPolicyNo());
        // if (carsProducts.isPresent()) {
        // policyNumber = carsProducts.get().getProductsReference() + policyNumber;
        // }
        // carsPolicy.setPolicyNumber(policyNumber);
        carsPolicy.setPolicyNumber(policyVehicle.getPolicy().getPolicyNo());
        carsPolicy.setPolicyProduct(subLineCode);// ask jean size should be 4
        String insuredCode = policyVehicle.getVehicle().getCarInsuredCode();
        if (!Utility.isEmpty(insuredCode)) {
//            insuredCode = insuredCode.replace("-", "");
            carsPolicy.setPolicyClient(insuredCode);
        }

        // carsPolicy.setPre

        String curr = validateSICurrency(policyVehicle.getPolicy().getsICurrency(),
                policyVehicle.getPolicy().getsICurrencyDescription(),
                policyVehicle.getPolicy().getsIExchangeRate().doubleValue(), policyVehicle.getPolicy().getPolicyNo());
        if (Utility.isEmpty(curr)) {
            throw new Exception("error");
        }
        carsPolicy.setPolicyCurrency(curr);
        carsPolicy.setPolicySumInsCurrencyRate(policyVehicle.getPolicy().getsIExchangeRate());


        if (policyVehicle.getVehicle() != null) {
            if (policyVehicle.getVehicle().getCovers() != null) {
                policyVehicle.getVehicle().getCovers().forEach(covers -> {
                    if (!Utility.isEmpty(covers.getTpaCoverTypeCode())) {

                        if (covers.getTpaCoverTypeCode().equals("6")) {
                            carsPolicy.setPolicyTowFlag("Y");
                        }
                    }
                });
            }
        }


        if (!Utility.isEmpty(endorsementTypeCode)) {
            String endorsementTypeCodeeDecoded = Utility
                    .getPropStringValuesWithNotFound("decode." + endorsementTypeCode);
            if (endorsementTypeCodeeDecoded != null) {
                System.out.println("endorsementTypeCodeeDecoded " + endorsementTypeCodeeDecoded);
                carsPolicy.setPolicyAction(endorsementTypeCodeeDecoded);
            } else {


                System.out.println("endorsementTypeCodeeDecoded not found ");
//                carsPolicy.setPolicyAction("M");

                if (!Utility.isEmpty(policyVehicle.getVehicle().getCarStatus())) {
                    String actionTypeDecoded = Utility
                            .getPropStringValues("decode." + policyVehicle.getVehicle().getCarStatus());
                    carsPolicy.setPolicyAction(actionTypeDecoded);
                } else {
                    saveMessage(policyVehicle.getPolicy().getPolicyNo(), " Business Type", "Missing Field", "CARS_POLICY",
                            insuranceCode, null);
                }


            }


        } else {
            if (!Utility.isEmpty(businessType)) {


                String businessTypeDecoded = Utility
                        .getPropStringValuesWithNotFound("decode." + businessType);
                if (businessTypeDecoded != null) {
                    System.out.println("businessTypeDecoded " + businessTypeDecoded);
                    carsPolicy.setPolicyAction(businessTypeDecoded);
                } else {
                    System.out.println("businessTypeDecoded not found ");
                    carsPolicy.setPolicyAction("M");
                }


            } else {


                if (!Utility.isEmpty(policyVehicle.getVehicle().getCarStatus())) {
                    String actionTypeDecoded = Utility
                            .getPropStringValues("decode." + policyVehicle.getVehicle().getCarStatus());
                    carsPolicy.setPolicyAction(actionTypeDecoded);
                } else {
                    saveMessage(policyVehicle.getPolicy().getPolicyNo(), " Business Type", "Missing Field", "CARS_POLICY",
                            insuranceCode, null);
                }

            }

        }


//        if (!Utility.isEmpty(policyVehicle.getVehicle().getCarStatus())) {
//            String actionTypeDecoded = Utility
//                    .getPropStringValues("decode." + policyVehicle.getVehicle().getCarStatus());
//            System.out.println("actionTypeDecoded "+actionTypeDecoded);
//            carsPolicy.setPolicyAction(actionTypeDecoded);
//
//        } else {
//            saveMessage(policyVehicle.getPolicy().getPolicyNo(), " Car Status", "Missing Field", "CARS_POLICY",
//                    insuranceCode, policyVehicle.getVehicle().getCertificateNo());
//        }
//


        curr = validatePremiumCurrency(policyVehicle.getPolicy().getPremCurrency(),
                policyVehicle.getPolicy().getPremCurrencyDescription(),
                policyVehicle.getPolicy().getPremExchangeRate().doubleValue(), policyVehicle.getPolicy().getPolicyNo());
        if (Utility.isEmpty(curr)) {
            throw new Exception("error");
        }
        carsPolicy.setPolicyPremiumCurrency(curr);
        carsPolicy.setPolicyPremiumCurrencyRate(policyVehicle.getPolicy().getPremExchangeRate());
        carsPolicy.setPolicyBlacklistSetBy(policyVehicle.getPolicy().getSetBy());
        carsPolicy.setPolicyBlacklistSetOn(policyVehicle.getPolicy().getSetOn());
        carsPolicy.setPolicyBlackListedNote(policyVehicle.getPolicy().getNote());
        carsPolicy.setPolicyBlackListedReason(policyVehicle.getPolicy().getReason());

        carsPolicy.setPolicyModifiedUser(policyVehicle.getPolicy().getModifiedByUser());
        carsPolicy.setPolicyIssuingUser(policyVehicle.getPolicy().getIssuingUser());
        carsPolicy.setPolicyClientId(clientId);
        if (policyVehicle.getPolicy().getIsWebPolicy() == true) {
            carsPolicy.setPolicyWeb("Y");
        } else {
            carsPolicy.setPolicyWeb("N");
        }
        if (policyVehicle.getPolicy().getIsDirectBroker() == true) {
            carsPolicy.setPolicyIsDirectBroker("Y");
        } else {
            carsPolicy.setPolicyIsDirectBroker("N");
        }
        if (policyVehicle.getPolicy().getBrokerWebOrigin() == true) {
            carsPolicy.setPolicyBrokerWebOrigin("Y");
        } else {
            carsPolicy.setPolicyBrokerWebOrigin("N");
        }

        if (!Utility.isEmpty(policyVehicle.getVehicle().getCertificateNo())) {
            carsPolicy.setPolicyCar(Integer.valueOf(policyVehicle.getVehicle().getCertificateNo()));
        } else {
            carsPolicy.setPolicyCar(1);
        }
        if (!Utility.isEmpty(policyVehicle.getPolicy().getEndorsementNo())) {
            if (policyVehicle.getPolicy().getEndorsementNo().equals("0")) {
                carsPolicy.setPolicyAmendment(-1);

            } else {
                carsPolicy.setPolicyAmendment(Integer.valueOf(policyVehicle.getPolicy().getEndorsementNo()));

            }

        } else {
            carsPolicy.setPolicyAmendment(0);
        }

        carsPolicy.setPolicyEndorsementTypeDesc(endorsementTypeDes);
        carsPolicy.setPolicyEndorsementTypeCode(endorsementTypeCode);
        carsPolicy.setPolicyEndorsSubTypeCode(endorsementSubTypeCode);
        carsPolicy.setPolicyEndorsSubTypeDesc(endorsementSubTypeDes);

        carsPolicy.setPolicyPrintName(convertText(policyVehicle.getPolicy().getPrintName()));
        carsPolicy.setPolicyBlacklistSetBy(policyVehicle.getPolicy().getSetBy());
        carsPolicy.setPolicyBlacklistSetOn(policyVehicle.getPolicy().getSetOn());
        carsPolicy.setPolicyBlackListedNote(policyVehicle.getPolicy().getNote());
        carsPolicy.setPolicyBlackListedReason(policyVehicle.getPolicy().getReason());

        carsPolicy.setPolicyInsuranceBlacklistSetBy(policyVehicle.getPolicy().getInsBlackSetBy());
        carsPolicy.setPolicyInsuranceBlacklistSetOn(policyVehicle.getPolicy().getInsBlackSetOn());

        carsPolicy.setPolicyBrokerBlacklistSetBy(policyVehicle.getPolicy().getInsBlackSetBy());
        carsPolicy.setPolicyBrokerBlacklistSetOn(policyVehicle.getPolicy().getInsBlackSetOn());
        if (policyVehicle.getPolicy().getInsBlacklisted()) {
            carsPolicy.setPolicyInsuredBlackList("Y");

        } else {
            carsPolicy.setPolicyInsuredBlackList("N");

        }
        // if ("10".equals(insuranceId)) {

//		String actionTypeOriginale = DataTransferHeaderFileFactory.getService().getHeaderStateOriginal(
//				dataTransferPolicyLoaded.getPolicyProduct(), dataTransferPolicyLoaded.getPolicyNumber(),
//				dataTransferPolicyLoaded.getPolicyAmend(), insuranceId);
//
//		String actionTypeDecoded = Utility.getPropStringValues("decode." + actionTypeOriginale);
//		carsPolicy.setPolicyAction(actionTypeDecoded);

        if (Utility.isEmpty(policyVehicle.getPolicy().getEndorsementNo())) {

            carsPolicy.setPolicyAmendment(0);
        } else {
            if (policyVehicle.getPolicy().getEndorsementNo().equals("0")) {
                carsPolicy.setPolicyAmendment(-1);

            } else {
                carsPolicy.setPolicyAmendment(Integer.valueOf(policyVehicle.getPolicy().getEndorsementNo()));

            }
        }
        // carsPolicy.setPolicyAmendment(0);

        StringBuilder policyClauses = new StringBuilder();
        if (!Utility.isEmpty(policyVehicle.getPolicy().getReason())) {
            policyClauses.append(policyVehicle.getPolicy().getReason() + " ");
        }
        if (!Utility.isEmpty(policyVehicle.getPolicy().getNote())) {
            policyClauses.append(policyVehicle.getPolicy().getNote() + " ");
        }
//		if (policyVehicle.getPolicy().getMopNote() != null) {
//			policyClauses.append(policyVehicle.getPolicy().getMopNote());
//		}
        if (!Utility.isEmpty(policyVehicle.getPolicy().getMopNote())) {
            carsPolicy.setPolicyNumberSpecialNotes(policyVehicle.getPolicy().getMopNote());
        }
//		carsPolicy.setPolicyClauses(policyVehicle.getPolicy().getReason() + " " + policyVehicle.getPolicy().getNote()
//				+ " " + policyVehicle.getPolicy().getMopNote());
        //carsPolicy.setPolicyClauses(policyClauses.toString());
        if (!Utility.isEmpty(brokerId)) {
            carsPolicy.setPolicyBrokerId((policyVehicle.getPolicy().getBrokerID().toString()));
        }
        carsPolicy.setPolicyBroker(policyVehicle.getPolicy().getBrokerCode());

        carsPolicy.setPolicyBrokerNum(policyVehicle.getPolicy().getBrokerCode());
        if (policyVehicle.getVehicle().getPolicyID().signum() == 0) {
            carsPolicy.setPolicyIdIns(policyVehicle.getPolicy().getPolicyID().toString());// ask jean aya policyId
        } else {
            carsPolicy.setPolicyIdIns(policyVehicle.getVehicle().getPolicyID().toString());
        }


        carsPolicy.setPolicySublineId(subLine);


        if (policyVehicle.getPolicy().getEndAtNoon() == true) {
            carsPolicy.setPolicyEndAtNoon("Y");
        } else {
            carsPolicy.setPolicyEndAtNoon("N");
        }


        if (policyVehicle.getVehicle().getDealerRepair() == null) {
            carsPolicy.setPolicyAgencyRepair("");// dealer repair Y N

        } else {
            if (policyVehicle.getVehicle().getDealerRepair()) {
                carsPolicy.setPolicyAgencyRepair("Y");// dealer repair Y N
            } else {
                carsPolicy.setPolicyAgencyRepair("N");
            }

        }

        // carsPolicy.setPolicyPlan(dataTransferPolicyLoaded.getPolicyPlan());
        // carsPolicy.setPolicyEndors1(dataTransferPolicyLoaded.getPolicyEndorsement1());
        // carsPolicy.setPolicyEndors2(dataTransferPolicyLoaded.getPolicyEndorsement2());
        // carsPolicy.setPolicyAgencyRepair(dataTransferPolicyLoaded.getPolicyAgencyRepair());//
        // dealer repair Y N check
        // with moahamd
        // carsPolicy.setPolicyCarReplacement(dataTransferPolicyLoaded.getPolicyCarReplacement());

        carsPolicy.setPolicyProductsId(productId);// same as policyId sent in json
        carsPolicy.setPolicyClientId(clientId);
        // carsPolicy.setPolicyTowFlag(dataTransferPolicyLoaded.getPolicyTowingCondition());
        // carsPolicy.setPolicyTowFlag(dataTransferPolicyLoaded.getPolicyTowFlag());
        // carsPolicy.setPolicyReplacementFlag(dataTransferPolicyLoaded.getPolicyReplacementFlag());
        // if (dataTransferPolicyLoaded.getPolicyDeductibleAmount() != null) {
        // carsPolicy.setPolicyDeductibleAmt(Integer.valueOf(dataTransferPolicyLoaded.getPolicyDeductibleAmount()));
        // }
        // carsPolicy.setPolicyDeductible(dataTransferPolicyLoaded.getPolicyDeductible());
        carsPolicy.setPolicyBranchId(branchId);
        carsPolicy.setPolicyBrokerId(brokerId);
        carsPolicy.setPolicyInsuranceId(insuranceCode);

        carsPolicy.setPolicyBlacklistSetBy(policyVehicle.getPolicy().getSetBy());
        carsPolicy.setPolicyBlacklistSetOn(policyVehicle.getPolicy().getSetOn());
        carsPolicy.setPolicyBlackListedNote(policyVehicle.getPolicy().getNote());
        carsPolicy.setPolicyBlackListedReason(policyVehicle.getPolicy().getReason());

        carsPolicy.setPolicyInsuranceBlacklistSetBy(policyVehicle.getPolicy().getInsBlackSetBy());
        carsPolicy.setPolicyInsuranceBlacklistSetOn(policyVehicle.getPolicy().getInsBlackSetOn());
        carsPolicy.setPolicyBrokerBlacklistSetBy(policyVehicle.getPolicy().getInsBlackSetBy());
        carsPolicy.setPolicyBrokerBlacklistSetOn(policyVehicle.getPolicy().getInsBlackSetOn());
        if (policyVehicle.getPolicy().getInsBlacklisted()) {
            carsPolicy.setPolicyInsuredBlackList("Y");

        } else {
            carsPolicy.setPolicyInsuredBlackList("N");

        }
        // carsPolicy.setPolicyIssueDate(policyVehicle.getPolicy().getPolicyIssueDate());//
        // ask mohamad
        // carsPolicy.setPolicyEffectiveDate(dataTransferPolicyLoaded.getPolicyEffectiveDate());
        // and end at noon
        // carsPolicy.setPolicyExpiryDate(dataTransferPolicyLoaded.getPolicyExpiryDate());
        Date effectiveDate = null;
        Date expiryDate = null;
        try {

            if (!Utility.isEmpty(policyVehicle.getPolicy().getDateIssue())) {
                Date IssueDate = new SimpleDateFormat("dd-MM-yyyy").parse(policyVehicle.getPolicy().getDateIssue());
                carsPolicy.setPolicyIssueDate(new Timestamp(IssueDate.getTime()));// ask mohamad
            } else {
                //saveMessage(policyVehicle.getPolicy().getPolicyNo(), "Issue Date", "Blocking Field", "CARS_POLICY",insuranceId, null);
                //throw new Exception("error");
            }
            if (!Utility.isEmpty(policyVehicle.getVehicle().getDateEffective())) {
                effectiveDate = new SimpleDateFormat("dd-MM-yyyy").parse(policyVehicle.getVehicle().getDateEffective());
            } else {
//				saveMessage(policyVehicle.getPolicy().getPolicyNo(), "EffectiveDate Date", "Missing Field",
//						"CARS_POLICY", insuranceId, policyVehicle.getVehicle().getCertificateNo());
//						throw new Exception("error");


            }
            if (!Utility.isEmpty(policyVehicle.getVehicle().getDateExpiry())) {
                expiryDate = new SimpleDateFormat("dd-MM-yyyy").parse(policyVehicle.getVehicle().getDateExpiry());

            } else {
//				saveMessage(policyVehicle.getPolicy().getPolicyNo(), "ExpiryDate Date", "Missing Field", "CARS_POLICY",
//						insuranceId, policyVehicle.getVehicle().getCertificateNo());
                //	throw new Exception("error");

            }

            if (policyVehicle.getPolicy().getEndAtNoon() == true
                    && (!Utility.isEmpty(policyVehicle.getVehicle().getDateEffective())
                    || !Utility.isEmpty(policyVehicle.getVehicle().getDateExpiry()))) {

                Calendar now = Calendar.getInstance();
                now.setTime(effectiveDate);
                // now.set(effectiveDate.getYear(), effectiveDate.getMonth(),
                // effectiveDate.getDay());
                now.set(Calendar.HOUR, 12);
                now.set(Calendar.MINUTE, 0);
                now.set(Calendar.SECOND, 0);
                // SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                // effectiveDate=sdf.parse(now.getTime().toString());
                effectiveDate = now.getTime();

                expiryDate.setHours(11);
                expiryDate.setMinutes(59);
                carsPolicy.setPolicyEffectiveDate(new Timestamp(effectiveDate.getTime()));
                carsPolicy.setPolicyExpiryDate(new Timestamp(expiryDate.getTime()));
            } else {
                Calendar now = Calendar.getInstance();
                now.setTime(effectiveDate);
                // now.set(effectiveDate.getYear(), effectiveDate.getMonth(),
                // effectiveDate.getDay());
                now.set(Calendar.HOUR, 0);
                now.set(Calendar.MINUTE, 0);
                now.set(Calendar.SECOND, 0);
                // SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                // effectiveDate=sdf.parse(now.getTime().toString());
                effectiveDate = now.getTime();

                expiryDate.setHours(23);
                expiryDate.setMinutes(59);
                carsPolicy.setPolicyEffectiveDate(new Timestamp(effectiveDate.getTime()));
                carsPolicy.setPolicyExpiryDate(new Timestamp(expiryDate.getTime()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ;

        StringBuilder policyRemark = new StringBuilder();
        if (!Utility.isEmpty(policyVehicle.getPolicy().getReason())) {
            policyRemark.append(policyVehicle.getPolicy().getReason() + " ");
        }
        if (Utility.isEmpty(policyVehicle.getPolicy().getNote())) {
            policyRemark.append(policyVehicle.getPolicy().getNote() + " ");
        }
        carsPolicy.setPolicyRemarks("");
        carsPolicy.setSysVersionNumber(0);
        carsPolicy.setSysCreatedBy(CREATED_BY_QUARTZ);
        carsPolicy.setSysUpdatedBy(CREATED_BY_QUARTZ);
        carsPolicy.setSysCreatedDate(new Timestamp(new Date().getTime()));
        carsPolicy.setSysUpdatedDate(new Timestamp(new Date().getTime()));
        db.carsPolicyRepository.save(carsPolicy);
        System.out.println("insertCarPolicyCar");
        logger.info("insert  policy car  of " + policyNo + " and policy Id  " + carsPolicy.getPolicyId());
        return carsPolicy.getPolicyId();
    }

    public String updatePolicyCar(PolicyVehicle policyVehicle, String productId, String branchId, String clientId,
                                  String brokerId, String subLine, Collection<CarsPolicy> carsPolicyToSearchList, String insuranceId, String subLineCode, boolean blackListed, String endorsementTypeDes, String endorsementTypeCode, String endorsementSubTypeDes, String endorsementSubTypeCode, String policyRootId, String policyId, String certifID, String insuredID, String insuredFirstName, String insuredCode1, String insuredPhoneNumber, String businessType)
            throws Exception {
        CarsPolicy carsPolicyLoad = carsPolicyToSearchList.iterator().next();

        CarsPolicy carsPolicyToLoad = new CarsPolicy();


        carsPolicyToLoad.setPolicyId(carsPolicyLoad.getPolicyId());
        Optional<CarsPolicy> carsPolicy = db.carsPolicyRepository.findById(carsPolicyLoad.getPolicyId());
        CarsPolicy carsPolicyToSave = carsPolicy.get();

        String branchCodeNew = policyVehicle.getPolicy().getBranchCode();
        if (!Utility.isEmpty(branchCodeNew)) {
            branchCodeNew = branchCodeNew.replaceFirst("^0+(?=.)", "");
            carsPolicyToSave.setPolicyBranch(branchCodeNew);
        }
        carsPolicyToSave.setPolicyNumberDisplay(policyVehicle.getPolicy().getPolicyNo());
        carsPolicyToSave.setPolicyNumberSpecialNotes(policyVehicle.getPolicy().getMopNote());
        String insuredCode = policyVehicle.getVehicle().getCarInsuredCode();
        if (!Utility.isEmpty(insuredCode)) {
//            insuredCode = insuredCode.replace("-", "");
            carsPolicyToSave.setPolicyClient(insuredCode);
        }
        carsPolicyToSave.setPolicyBrokerNum(policyVehicle.getPolicy().getBrokerCode());

        String curr = validateSICurrency(policyVehicle.getPolicy().getsICurrency(),
                policyVehicle.getPolicy().getsICurrencyDescription(),
                policyVehicle.getPolicy().getsIExchangeRate().doubleValue(), policyVehicle.getPolicy().getPolicyNo());
        if (Utility.isEmpty(curr)) {
            throw new Exception("Error:");
        }
        carsPolicyToSave.setPolicyCurrency(curr);


        if (blackListed) {
            carsPolicyToSave.setPolicyBlackListed("Y");
        } else {
            carsPolicyToSave.setPolicyBlackListed("N");
        }


        carsPolicyToSave.setPolicyTowFlag(policyVehicle.getVehicle().getCarTowAllowed());

        carsPolicyToSave.setPolicySumInsCurrencyRate(policyVehicle.getPolicy().getsIExchangeRate());
        curr = validatePremiumCurrency(policyVehicle.getPolicy().getPremCurrency(),
                policyVehicle.getPolicy().getPremCurrencyDescription(),
                policyVehicle.getPolicy().getPremExchangeRate().doubleValue(), policyVehicle.getPolicy().getPolicyNo());
        if (Utility.isEmpty(curr)) {
            throw new Exception("error");
        }
        carsPolicyToSave.setPolicyPremiumCurrency(curr);
        carsPolicyToSave.setPolicyPremiumCurrencyRate(policyVehicle.getPolicy().getPremExchangeRate());
        carsPolicyToSave.setPolicyEndorsementTypeDesc(endorsementTypeDes);
        System.out.println("update endorsementTypeCode " + endorsementTypeCode);
        carsPolicyToSave.setPolicyEndorsementTypeCode(endorsementTypeCode);
        carsPolicyToSave.setPolicyEndorsSubTypeCode(endorsementSubTypeCode);
        carsPolicyToSave.setPolicyBlacklistSetBy(policyVehicle.getPolicy().getSetBy());
        carsPolicyToSave.setPolicyBlacklistSetOn(policyVehicle.getPolicy().getSetOn());
        carsPolicyToSave.setPolicyBlackListedNote(policyVehicle.getPolicy().getNote());
        carsPolicyToSave.setPolicyBlackListedReason(policyVehicle.getPolicy().getReason());

        if (policyVehicle.getVehicle() != null) {
            if (policyVehicle.getVehicle().getCovers() != null) {
                policyVehicle.getVehicle().getCovers().forEach(covers -> {
                    if (!Utility.isEmpty(covers.getTpaCoverTypeCode())) {
                        if (covers.getTpaCoverTypeCode().equals("6")) {
                            carsPolicyToSave.setPolicyTowFlag("Y");
                        }
                    }
                });
            }
        }


        carsPolicyToSave.setPolicyEndorsSubTypeDesc(endorsementSubTypeDes);
        carsPolicyToSave.setPolicyRootId(policyRootId);
        carsPolicyToSave.setPolicyFleetId(policyId);
        carsPolicyToSave.setPolicyCertIfID(certifID);
        carsPolicyToSave.setPolicyHolderName(insuredFirstName);
        carsPolicyToSave.setPolicyHolderCode(insuredCode1);
//        carsPolicyToSave.setPolicyHolderId(clientId);

        Optional<CarsClient> carsClient = db.carsClientRepository
                .findByClientInsuranceIdAndClientNum1(insuranceCode, insuredCode1);
        if (carsClient.isPresent()) {
            carsPolicyToSave.setPolicyHolderId(carsClient.get().getClientId());

        }


        carsPolicyToSave.setPolicyHolderPhone(insuredPhoneNumber);
//		String actionTypeOriginale = DataTransferHeaderFileFactory.getService().getHeaderStateOriginal(
//				dataTransferPolicyLoaded.getPolicyProduct(), dataTransferPolicyLoaded.getPolicyNumber(),
//				dataTransferPolicyLoaded.getPolicyAmend(), insuranceId);
//
//		String actionTypeDecoded = Utility.getPropStringValues("decode." + actionTypeOriginale);
        // carsPolicyToSave.setPolicyAction(actionTypeDecoded);
//		if ("C".equals(actionTypeOriginale) || "S".equals(actionTypeOriginale)) {
//			if (carsPolicyToSave.getPolicyEffectiveDate() == carsPolicyLoad.getPolicyEffectiveDate()) {
//				carsPolicyToSave.setPolicyAmendment(999);
//				carsPolicyToSave.setPolicyId(UUID.randomUUID().toString());
//			} else {
//				if (dataTransferPolicyLoaded.getPolicyAmend() == null) {
//					carsPolicyToSave.setPolicyAmendment(0);
//				} else {
//					carsPolicyToSave.setPolicyAmendment(dataTransferPolicyLoaded.getPolicyAmend());
//				}
//
//			}
//		} else {
//			if (dataTransferPolicyLoaded.getPolicyAmend() == null) {
//				carsPolicyToSave.setPolicyAmendment(0);
//			} else {
//				carsPolicyToSave.setPolicyAmendment(dataTransferPolicyLoaded.getPolicyAmend());
//			}
//
//		}
        if (!Utility.isEmpty(policyVehicle.getVehicle().getCertificateNo())) {
            carsPolicyToSave.setPolicyCar(Integer.valueOf(policyVehicle.getVehicle().getCertificateNo()));
        } else {
            carsPolicyToSave.setPolicyCar(1);
        }
        if (!Utility.isEmpty(policyVehicle.getPolicy().getEndorsementNo())) {
            if (policyVehicle.getPolicy().getEndorsementNo().equals("0")) {
                carsPolicyToSave.setPolicyAmendment(-1);

            } else {
                carsPolicyToSave.setPolicyAmendment(Integer.valueOf(policyVehicle.getPolicy().getEndorsementNo()));

            }
        } else {
            carsPolicyToSave.setPolicyAmendment(0);
        }


        StringBuilder policyClauses = new StringBuilder();
        if (!Utility.isEmpty(policyVehicle.getPolicy().getReason())) {
            policyClauses.append(policyVehicle.getPolicy().getReason() + " ");
        }
        if (!Utility.isEmpty(policyVehicle.getPolicy().getNote())) {
            policyClauses.append(policyVehicle.getPolicy().getNote() + " ");
        }
//		if (policyVehicle.getPolicy().getMopNote() != null) {
//			policyClauses.append(policyVehicle.getPolicy().getMopNote());
//		}
//			carsPolicy.setPolicyClauses(policyVehicle.getPolicy().getReason() + " " + policyVehicle.getPolicy().getNote()
//					+ " " + policyVehicle.getPolicy().getMopNote());
        //carsPolicyToSave.setPolicyClauses(policyClauses.toString());
        carsPolicyToSave.setPolicyBroker(policyVehicle.getPolicy().getBrokerCode());


        carsPolicyToSave.setPolicyBrokerId((policyVehicle.getPolicy().getBrokerID().toString()));

        if (policyVehicle.getVehicle().getPolicyID().signum() == 0) {
            carsPolicyToSave.setPolicyIdIns(policyVehicle.getPolicy().getPolicyID().toString());// ask jean aya policyId
        } else {
            carsPolicyToSave.setPolicyIdIns(policyVehicle.getVehicle().getPolicyID().toString());
        }

        if (policyVehicle.getPolicy().getIsWebPolicy() == true) {
            carsPolicyToSave.setPolicyWeb("Y");
        } else {
            carsPolicyToSave.setPolicyWeb("N");
        }
        if (policyVehicle.getPolicy().getIsDirectBroker() == true) {
            carsPolicyToSave.setPolicyIsDirectBroker("Y");
        } else {
            carsPolicyToSave.setPolicyIsDirectBroker("N");
        }
        if (policyVehicle.getPolicy().getBrokerWebOrigin() == true) {
            carsPolicyToSave.setPolicyBrokerWebOrigin("Y");
        } else {
            carsPolicyToSave.setPolicyBrokerWebOrigin("N");
        }
        carsPolicyToSave.setPolicyProduct(subLineCode);

//        if (!Utility.isEmpty(policyVehicle.getVehicle().getCarStatus())) {
//            String actionTypeDecoded = Utility
//                    .getPropStringValues("decode." + policyVehicle.getVehicle().getCarStatus());
//            carsPolicyToSave.setPolicyAction(actionTypeDecoded);
//        }


        if (!Utility.isEmpty(endorsementTypeCode)) {
            String endorsementTypeCodeeDecoded = Utility
                    .getPropStringValuesWithNotFound("decode." + endorsementTypeCode);
            if (endorsementTypeCodeeDecoded != null) {
                System.out.println("endorsementTypeCodeeDecoded " + endorsementTypeCodeeDecoded);
                carsPolicyToSave.setPolicyAction(endorsementTypeCodeeDecoded);
            } else {
                System.out.println("endorsementTypeCodeeDecoded not found ");
                carsPolicyToSave.setPolicyAction("M");


                if (!Utility.isEmpty(policyVehicle.getVehicle().getCarStatus())) {
                    String actionTypeDecoded = Utility
                            .getPropStringValues("decode." + policyVehicle.getVehicle().getCarStatus());
                    carsPolicyToSave.setPolicyAction(actionTypeDecoded);
                } else {
                    saveMessage(policyVehicle.getPolicy().getPolicyNo(), " Business Type", "Missing Field", "CARS_POLICY",
                            insuranceCode, null);
                }

            }


        } else {
            if (!Utility.isEmpty(businessType)) {


                String businessTypeDecoded = Utility
                        .getPropStringValuesWithNotFound("decode." + businessType);
                if (businessTypeDecoded != null) {
                    System.out.println("businessTypeDecoded " + businessTypeDecoded);
                    carsPolicyToSave.setPolicyAction(businessTypeDecoded);
                } else {
                    System.out.println("businessTypeDecoded not found ");
                    carsPolicyToSave.setPolicyAction("M");
                }


            } else {
                if (!Utility.isEmpty(policyVehicle.getVehicle().getCarStatus())) {
                    String actionTypeDecoded = Utility
                            .getPropStringValues("decode." + policyVehicle.getVehicle().getCarStatus());
                    carsPolicyToSave.setPolicyAction(actionTypeDecoded);
                } else {
                    saveMessage(policyVehicle.getPolicy().getPolicyNo(), " Business Type", "Missing Field", "CARS_POLICY",
                            insuranceCode, null);
                }


            }

        }


//		else {
//			saveMessage(policyVehicle.getPolicy().getPolicyNo(), " Car Status", "Missing Field", "CARS_POLICY",
//					insuranceCode, policyVehicle.getVehicle().getCertificateNo());
//		}
        carsPolicyToSave.setPolicyPrintName(convertText(policyVehicle.getPolicy().getPrintName()));
        carsPolicyToSave.setPolicyBlacklistSetBy(policyVehicle.getPolicy().getSetBy());
        carsPolicyToSave.setPolicyBlacklistSetOn(policyVehicle.getPolicy().getSetOn());
        carsPolicyToSave.setPolicyBlackListedNote(policyVehicle.getPolicy().getNote());
        carsPolicyToSave.setPolicyBlackListedReason(policyVehicle.getPolicy().getReason());
        carsPolicyToSave.setPolicyInsuranceBlacklistSetBy(policyVehicle.getPolicy().getInsBlackSetBy());
        carsPolicyToSave.setPolicyInsuranceBlacklistSetOn(policyVehicle.getPolicy().getInsBlackSetOn());
        carsPolicyToSave.setPolicyBrokerBlacklistSetBy(policyVehicle.getPolicy().getInsBlackSetBy());
        carsPolicyToSave.setPolicyBrokerBlacklistSetOn(policyVehicle.getPolicy().getInsBlackSetOn());

        if (policyVehicle.getPolicy().getInsBlacklisted()) {
            carsPolicyToSave.setPolicyInsuredBlackList("Y");

        } else {
            carsPolicyToSave.setPolicyInsuredBlackList("N");

        }

        // carsPolicyToSave.setPolicySubline(policyVehicle.getPolicy().getSubLineCode());
        carsPolicyToSave.setPolicySublineId(subLine);
        if (policyVehicle.getPolicy().getEndAtNoon() == true) {
            carsPolicyToSave.setPolicyEndAtNoon("Y");
        } else {
            carsPolicyToSave.setPolicyEndAtNoon("N");
        }
        // carsPolicy.setPolicyPlan(dataTransferPolicyLoaded.getPolicyPlan());
        // carsPolicy.setPolicyEndors1(dataTransferPolicyLoaded.getPolicyEndorsement1());
        // carsPolicy.setPolicyEndors2(dataTransferPolicyLoaded.getPolicyEndorsement2());
        if (policyVehicle.getVehicle().getDealerRepair() == null) {
            carsPolicyToSave.setPolicyAgencyRepair("");// dealer repair Y N

        } else {
            if (policyVehicle.getVehicle().getDealerRepair()) {
                carsPolicyToSave.setPolicyAgencyRepair("Y");// dealer repair Y N
            } else {
                carsPolicyToSave.setPolicyAgencyRepair("N");
            }
        }


        // check
        // with moahamd
        // carsPolicy.setPolicyCarReplacement(dataTransferPolicyLoaded.getPolicyCarReplacement());

        carsPolicyToSave.setPolicyProductsId(productId);// same as policyId sent in json ask jean
        carsPolicyToSave.setPolicyClientId(clientId);

        carsPolicyToSave.setPolicyBranchId(branchId);
        carsPolicyToSave.setPolicyBrokerId(brokerId);
        carsPolicyToSave.setPolicyInsuranceId(insuranceCode);
        Date effectiveDate = null;
        Date expiryDate = null;
        try {
            if (!Utility.isEmpty(policyVehicle.getPolicy().getDateIssue())) {
                Date IssueDate = new SimpleDateFormat("dd-MM-yyyy").parse(policyVehicle.getPolicy().getDateIssue());
                carsPolicyToSave.setPolicyIssueDate(new Timestamp(IssueDate.getTime()));// ask mohamad
            }
//			else {
//				saveMessage(policyVehicle.getPolicy().getPolicyNo(), "Issue Date", "Missing Field", "CARS_POLICY",
//						insuranceId, null);
//			}
            if (!Utility.isEmpty(policyVehicle.getVehicle().getDateEffective())) {
                effectiveDate = new SimpleDateFormat("dd-MM-yyyy").parse(policyVehicle.getVehicle().getDateEffective());
            }
//			else {
//				saveMessage(policyVehicle.getPolicy().getPolicyNo(), "EffectiveDate Date", "Missing Field",
//						"CARS_POLICY", insuranceId, null);
//			}
            if (!Utility.isEmpty(policyVehicle.getVehicle().getDateExpiry())) {
                expiryDate = new SimpleDateFormat("dd-MM-yyyy").parse(policyVehicle.getVehicle().getDateExpiry());
            }
//			else {
//				saveMessage(policyVehicle.getPolicy().getPolicyNo(), "ExpiryDate Date", "Missing Field", "CARS_POLICY",
//						insuranceId, null);
//			}

            if (policyVehicle.getPolicy().getEndAtNoon() == true
                    && (!Utility.isEmpty(policyVehicle.getVehicle().getDateEffective())
                    || !Utility.isEmpty(policyVehicle.getVehicle().getDateExpiry()))) {

                Calendar now = Calendar.getInstance();
                now.setTime(effectiveDate);
                // now.set(effectiveDate.getYear(), effectiveDate.getMonth(),
                // effectiveDate.getDay());
                now.set(Calendar.HOUR, 12);
                now.set(Calendar.MINUTE, 0);
                now.set(Calendar.SECOND, 0);
                // SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                // effectiveDate=sdf.parse(now.getTime().toString());
                effectiveDate = now.getTime();

                expiryDate.setHours(11);
                expiryDate.setMinutes(59);
                carsPolicyToSave.setPolicyEffectiveDate(new Timestamp(effectiveDate.getTime()));
                carsPolicyToSave.setPolicyExpiryDate(new Timestamp(expiryDate.getTime()));
            } else {


                Calendar now = Calendar.getInstance();
                now.setTime(effectiveDate);
                // now.set(effectiveDate.getYear(), effectiveDate.getMonth(),
                // effectiveDate.getDay());
                now.set(Calendar.HOUR, 0);
                now.set(Calendar.MINUTE, 0);
                now.set(Calendar.SECOND, 0);
                // SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                // effectiveDate=sdf.parse(now.getTime().toString());
                effectiveDate = now.getTime();

                expiryDate.setHours(23);
                expiryDate.setMinutes(59);
                carsPolicyToSave.setPolicyEffectiveDate(new Timestamp(effectiveDate.getTime()));
                carsPolicyToSave.setPolicyExpiryDate(new Timestamp(expiryDate.getTime()));


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ;
        StringBuilder policyRemark = new StringBuilder();
        if (!Utility.isEmpty(policyVehicle.getPolicy().getReason())) {
            policyRemark.append(policyVehicle.getPolicy().getReason() + " ");
        }
        if (!Utility.isEmpty(policyVehicle.getPolicy().getNote())) {
            policyRemark.append(policyVehicle.getPolicy().getNote() + " ");
        }
        carsPolicyToSave.setPolicyRemarks("");
        carsPolicyToSave.setSysVersionNumber(0);
        carsPolicyToSave.setSysUpdatedBy(CREATED_BY_QUARTZ);

        carsPolicyToSave.setSysUpdatedDate(new Timestamp(new Date().getTime()));
        // carsPolicyToSave.setPolicyTowFlag(dataTransferPolicyLoaded.getPolicyTowingCondition());

        db.carsPolicyRepository.save(carsPolicyToSave);

//		if (carsPolicyToSave.getPolicyCar() != null && carsPolicyToSave.getPolicyCar() == 999) {
//			db.carsPolicyRepository.save(carsPolicyToSave);
//		} else {
//			db.carsPolicyRepository.save(carsPolicyToSave);
//		}
        logger.info("update  policy car  of " + policyNo + " and policy Id  " + carsPolicyToSave.getPolicyId());

        return carsPolicyToSave.getPolicyId();
    }

    public Collection<CarsPolicy> carsPolicySearchable(PolicyVehicle policyVehicle, String productId) {
        CarsPolicy carsPolicyToSearch = new CarsPolicy();
        // carsPolicyToSearch.setPolicyBranch(policyVehicle.getPolicy().getBranchCode());
        // carsPolicyToSearch.setPolicyLob("MO");
        // String policyNumber = policyVehicle.getPolicy().getPolicyNo();
        // policyNumber = policyNumber.replaceAll("[^0-9]", "");
        // carsPolicy.setPolicyNumber(policyVehicle.getPolicy().getPolicyNo());
        // carsPolicyToSearch.setPolicyNumber(policyNumber);
        // carsPolicyToSearch.setPolicyCar(0);
        // carsPolicyToSearch.setPolicyAmendment(dataTransferPolicyLoaded.getPolicyAmend());

        String branchCodeNew = policyVehicle.getPolicy().getBranchCode();
        branchCodeNew = branchCodeNew.replaceFirst("^0+(?=.)", "");
        carsPolicyToSearch.setPolicyBranchId(insuranceCode + "." + branchCodeNew);
        String policyNumber = policyVehicle.getPolicy().getPolicyNo();
//		Optional<CarsProducts> carsProducts = db.carsProductRepository.findById(productId);
//		String[] arrOfStr = policyNumber.split("-", 2);
//		policyNumber = arrOfStr[1].replaceAll("[^0-9]", "");

        // Optional<CarsProducts> carsProducts =
        // db.carsProductRepository.findById(productId);

        // String[] arrOfStr = policyNumber.split("-", 4);
        // policyNumber = arrOfStr[2] + "-" + arrOfStr[3];

        // carsPolicy.setPolicyNumber(policyVehicle.getPolicy().getPolicyNo());
        // if (carsProducts.isPresent()) {
        // policyNumber = carsProducts.get().getProductsReference() + policyNumber;

        // }
        carsPolicyToSearch.setPolicyNumber(policyNumber);

        if (!Utility.isEmpty(policyVehicle.getVehicle().getCertificateNo())) {
            carsPolicyToSearch.setPolicyCar(Integer.valueOf(policyVehicle.getVehicle().getCertificateNo()));
        } else {
            carsPolicyToSearch.setPolicyCar(1);
        }
        if (!Utility.isEmpty(policyVehicle.getPolicy().getEndorsementNo())) {

            if (policyVehicle.getPolicy().getEndorsementNo().equals("0")) {
                carsPolicyToSearch.setPolicyAmendment(-1);

            } else {
                carsPolicyToSearch.setPolicyAmendment(Integer.valueOf(policyVehicle.getPolicy().getEndorsementNo()));

            }

        } else {
            carsPolicyToSearch.setPolicyAmendment(0);
        }
        carsPolicyToSearch.setPolicyInsuranceId(insuranceCode);
        Example<CarsPolicy> example = Example.of(carsPolicyToSearch);

        Collection<CarsPolicy> carsPolicyToSearchList = db.carsPolicyRepository.findAll(example);
//		if (carsPolicyToSearchList != null && !carsPolicyToSearchList.isEmpty()) {
//
//		}
        return carsPolicyToSearchList;
    }


    public Collection<CarsPolicy> carsPolicySearchable2(PolicyVehicle policyVehicle, String productId) {

        Collection<CarsPolicy> cl = db.carsPolicyRepository.findByPolicyInsuranceIdAndPolicyIdIns(insuranceCode, String.valueOf(policyVehicle.getPolicy().getPolicyID()));
        System.out.println("size of cars " + cl.size());

        return cl;
    }


    public Collection<CarsDtModel> getDataTransferByModel(String insuranceId, String modelName, String carMake) {
        Collection<CarsDtModel> lista = new ArrayList<CarsDtModel>();
        System.out.println(insuranceId);
        System.out.println(modelName);
        System.out.println(carMake);


        List<String> modelList = db.carsDtModelRepository.getDataTransferByModel(insuranceId, modelName, carMake);

        if (modelList.size() == 0) {
            Optional<String> shapeId = db.carsCarShapeRepository.findShapeCode(carMake, modelName);
            System.out.println(shapeId);
            System.out.println("oooooooooooooooooooooooooooooooooooooooo");
            if (shapeId.isPresent()) {
                System.out.println("SHAPE IS PRESENT");
                Optional<CarShapeRespomse> carShapeRespomse = db.carsCarShapeRepository.findByShapeCode(shapeId.get());
                CarsDtModel carsDtModel = new CarsDtModel();
                carsDtModel.setTrademarkId(carShapeRespomse.get().getTrademarkCode());
                carsDtModel.setBrandId(carShapeRespomse.get().getBrandCode());
                carsDtModel.setInsuranceId(insuranceId);

                lista.add(carsDtModel);
                System.out.println("added tolista");


            } else {
                Optional<String> shapeId2 = db.carsCarShapeRepository.findShapeCode(carMake, "NOT LISTED");
                if (shapeId2.isPresent()) {
                    System.out.println("SHAPE IS PRESENT but model not present ");
                    System.out.println("shape Id " + shapeId2.get());
                    Optional<CarShapeRespomse> carShapeRespomse = db.carsCarShapeRepository.findByShapeCode(shapeId2.get());
                    CarsDtModel carsDtModel = new CarsDtModel();
                    carsDtModel.setTrademarkId(carShapeRespomse.get().getTrademarkCode());
                    carsDtModel.setBrandId(carShapeRespomse.get().getBrandCode());
                    carsDtModel.setInsuranceId(insuranceId);
                    lista.add(carsDtModel);
                    System.out.println("added to lista");
                    insertDTModel(insuranceId, modelName, carMake);


                }


                //  insertDTModel(insuranceId, modelName,carMake);

            }
        }


        if (modelList.size() > 0) {


            String idss = modelList.get(0);

            Optional<CarsDtModel> qmpQuestLoaded = db.carsDtModelRepository.findById(idss);
            if (qmpQuestLoaded.isPresent()) {
                if (qmpQuestLoaded.get().getTrademarkId() != null) {
                    System.out.println("modelisted finded and trademark not null" + qmpQuestLoaded.get().getTrademarkId());
                    lista.add(qmpQuestLoaded.get());
                } else {
                    System.out.println("modelisted finded and trademark null" + qmpQuestLoaded.get().getTrademarkId());


                    Optional<String> shapeId = db.carsCarShapeRepository.findShapeCode(carMake, modelName);
                    System.out.println(shapeId);
                    System.out.println("oooooooooooooooooooooooooooooooooooooooo");
                    if (shapeId.isPresent()) {
                        System.out.println("SHAPE IS PRESENT but trademark null");
                        Optional<CarShapeRespomse> carShapeRespomse = db.carsCarShapeRepository.findByShapeCode(shapeId.get());
                        CarsDtModel carsDtModel = new CarsDtModel();
                        carsDtModel.setTrademarkId(carShapeRespomse.get().getTrademarkCode());
                        carsDtModel.setBrandId(carShapeRespomse.get().getBrandCode());
                        carsDtModel.setInsuranceId(insuranceId);

                        lista.add(carsDtModel);
                        System.out.println("added tolista");


                    } else {
                        Optional<String> shapeId2 = db.carsCarShapeRepository.findShapeCode(carMake, "NOT LISTED");
                        if (shapeId2.isPresent()) {
                            System.out.println("SHAPE IS PRESENT but model not present but trademark null ");
                            System.out.println("shape Id " + shapeId2.get());
                            Optional<CarShapeRespomse> carShapeRespomse = db.carsCarShapeRepository.findByShapeCode(shapeId2.get());
                            CarsDtModel carsDtModel = new CarsDtModel();
                            carsDtModel.setTrademarkId(carShapeRespomse.get().getTrademarkCode());
                            carsDtModel.setBrandId(carShapeRespomse.get().getBrandCode());
                            carsDtModel.setInsuranceId(insuranceId);
                            lista.add(carsDtModel);
                            System.out.println("added to lista but trademark null");


                        }


                        //  insertDTModel(insuranceId, modelName,carMake);

                    }


                }
            }
        }


        return lista;
    }

    public CarsDtModel insertDTModel(String insuranceId, String modelName, String makeCode) {
        CarsDtModel carsDtModel = new CarsDtModel();
        carsDtModel.setId(UUID.randomUUID().toString());
        carsDtModel.setInsuranceId(insuranceId);
        carsDtModel.setModelName(modelName);
        carsDtModel.setSysCreatedBy("transfer");
        carsDtModel.setSysUpdatedBy("transfer");
        carsDtModel.setInsuranceMakeCode(makeCode);
        carsDtModel.setSysCreatedDate(new Timestamp(new Date().getTime()));
        carsDtModel.setSysUpdatedDate(new Timestamp(new Date().getTime()));
        carsDtModel.setSysVersionNumber(0);
        db.carsDtModelRepository.save(carsDtModel);
        return carsDtModel;

    }

    public void insertCarsPolicyCover(Covers cover, String carId) {

        //	int coversOrder = 0;
        CarsPolicyCover carsPolicyCoverToInsert = new CarsPolicyCover();
        carsPolicyCoverToInsert.setPolicyCoversId(UUID.randomUUID().toString());
        carsPolicyCoverToInsert.setPolicyCoversCover(cover.getCoverCode());
        //	carsPolicyCoverToInsert.setPolicyCoversOrder(coversOrder);

        carsPolicyCoverToInsert.setPolicyCoversSumInsured((long) cover.getSumInsured());

        carsPolicyCoverToInsert.setPolicyCoversDeductible((double) cover.getDeductibleFlatAmount());
        carsPolicyCoverToInsert.setPolicyCoversDeductible100((double) cover.getDeductiblePercentage());
//				carsPolicyCoverToInsert
//						.setPolicyCoversPercentage(cover.getDeductibleType());
        carsPolicyCoverToInsert.setPolicyCoversCarId(carId);
        carsPolicyCoverToInsert.setPolicyCoversCoverId(cover.getCoverID().toString());
        carsPolicyCoverToInsert.setPolicyCoversPolcoverId(cover.getPolicyCoverID().toString());
        carsPolicyCoverToInsert.setSysVersionNumber(0);
        carsPolicyCoverToInsert.setSysCreatedBy(CREATED_BY_QUARTZ);
        carsPolicyCoverToInsert.setSysUpdatedBy(CREATED_BY_QUARTZ);
        carsPolicyCoverToInsert.setSysCreatedDate(new Timestamp(new Date().getTime()));
        carsPolicyCoverToInsert.setSysUpdatedDate(new Timestamp(new Date().getTime()));

        db.carsPolicyCoverRepository.save(carsPolicyCoverToInsert);
    }

    public void deleteCover(String carId) {

        db.carsPolicyCoverRepository.deleteCovers(carId);
    }

    public void insertCarsPolicyWordingHAndD(Clauses clause, String policyId) {

        CarsPolicyWordingH carsPolicyWordingHToInsert = new CarsPolicyWordingH();
        carsPolicyWordingHToInsert.setPolicyWordingHId(UUID.randomUUID().toString());
        carsPolicyWordingHToInsert.setPolicyId(policyId);
        // carsPolicyWordingHToInsert.setWordingId(coversOrder);
        carsPolicyWordingHToInsert.setSysVersionNumber(0);
        carsPolicyWordingHToInsert.setSysCreatedBy(CREATED_BY_QUARTZ);
        carsPolicyWordingHToInsert.setSysUpdatedBy(CREATED_BY_QUARTZ);
        carsPolicyWordingHToInsert.setSysCreatedDate(new Timestamp(new Date().getTime()));
        carsPolicyWordingHToInsert.setSysUpdatedDate(new Timestamp(new Date().getTime()));
        logger.info("policy wording h inserted  of " + policyNo);
        db.carsPolicyWordingHRepository.save(carsPolicyWordingHToInsert);


        if (!Utility.isEmpty(clause.getContent())) {


            if (clause.getContent().length() < 3500) {
                CarsPolicyWordingD carsPolicyWordingDToInsert = new CarsPolicyWordingD();
                carsPolicyWordingDToInsert.setPolicyWordingDId(UUID.randomUUID().toString());
                carsPolicyWordingDToInsert.setPolicyWordingHId(carsPolicyWordingHToInsert.getPolicyWordingHId());
                if (!Utility.isEmpty(clause.getContent())) {
                    carsPolicyWordingDToInsert.setPolicyWordingDFreeText(convertText(clause.getContent()));
                    carsPolicyWordingDToInsert.setPolicyWordingDHtml(clause.getContent());
                    // carsPolicyWordingDToInsert.setPolicyWordingDLong(clause.getContent());
                }
                carsPolicyWordingDToInsert.setPolicyWordingDLine(Integer.valueOf(clause.getOrder()));
                // carsPolicyWordingDToInsert.setPolicyWordingDCode(dataTransferClauseContractLoaded.getCode());
                // carsPolicyWordingDToInsert.setPolicyWordingDLine(String.valueOf(dataTransferClauseContractLoaded.getSequence()));

                carsPolicyWordingDToInsert.setSysVersionNumber(0);
                carsPolicyWordingDToInsert.setSysCreatedBy(CREATED_BY_QUARTZ);
                carsPolicyWordingDToInsert.setSysUpdatedBy(CREATED_BY_QUARTZ);
                carsPolicyWordingDToInsert.setSysCreatedDate(new Timestamp(new Date().getTime()));
                carsPolicyWordingDToInsert.setSysUpdatedDate(new Timestamp(new Date().getTime()));
//                carsPolicyWordingHToInsert.setPolicyId(policyId);
                logger.info("policy wording d inserted  of " + policyNo);

                db.carsPolicyWordingDRepository.save(carsPolicyWordingDToInsert);
            } else {


                List<String> strings = new ArrayList<String>();
                int index = 0;
                while (index < clause.getContent().length()) {
                    CarsPolicyWordingD carsPolicyWordingDToInsert = new CarsPolicyWordingD();
                    carsPolicyWordingDToInsert.setPolicyWordingDId(UUID.randomUUID().toString());
                    carsPolicyWordingDToInsert.setPolicyWordingHId(carsPolicyWordingHToInsert.getPolicyWordingHId());
                    carsPolicyWordingDToInsert.setPolicyWordingDFreeText(convertText(clause.getContent().substring(index, Math.min(index + 3501, clause.getContent().length()))));
                    carsPolicyWordingDToInsert.setPolicyWordingDHtml(clause.getContent().substring(index, Math.min(index + 3501, clause.getContent().length())));
                    //carsPolicyWordingDToInsert.setPolicyWordingDLong(clause.getContent());
                    carsPolicyWordingDToInsert.setPolicyWordingDLine(Integer.valueOf(clause.getOrder()));
                    carsPolicyWordingDToInsert.setSysVersionNumber(0);
                    carsPolicyWordingDToInsert.setSysCreatedBy(CREATED_BY_QUARTZ);
                    carsPolicyWordingHToInsert.setPolicyId(policyId);
                    carsPolicyWordingDToInsert.setSysUpdatedBy(CREATED_BY_QUARTZ);
                    carsPolicyWordingDToInsert.setSysCreatedDate(new Timestamp(new Date().getTime()));
                    carsPolicyWordingDToInsert.setSysUpdatedDate(new Timestamp(new Date().getTime()));
                    db.carsPolicyWordingDRepository.save(carsPolicyWordingDToInsert);
                    logger.info("policy wording d inserted  rec 2 of " + policyNo);
                    //    strings.add(clause.getContent().substring(index, Math.min(index + 3900,clause.getContent().length())));
                    index += 3501;
                }


            }
        }

    }

    void DeletePolicyWording(String policyId) {
        CarsPolicyWordingH carsPolicyWordingH = new CarsPolicyWordingH();
        carsPolicyWordingH.setPolicyId(policyId);
        Example<CarsPolicyWordingH> example = Example.of(carsPolicyWordingH);

        Collection<CarsPolicyWordingH> carsPolicyWordingHList = db.carsPolicyWordingHRepository.findAll(example);

        Iterator<CarsPolicyWordingH> carsPolicyWordingHIterator = carsPolicyWordingHList.iterator();
        while (carsPolicyWordingHIterator.hasNext()) {
            CarsPolicyWordingH carsPolicyWordingHToDelete = carsPolicyWordingHIterator.next();

            CarsPolicyWordingD carsPolicyWordingDToSearch = new CarsPolicyWordingD();
            carsPolicyWordingDToSearch.setPolicyWordingHId(carsPolicyWordingHToDelete.getPolicyWordingHId());

            // Collection<CarsPolicyWordingD> carsPolicyWordingDList =
            // CarsPolicyWordingDFactory.getService()
            // .searchWithCriteria(carsPolicyWordingDToSearch,
            // carsPolicyWordingDToSearch,
            // Utility.getCriteriaMapCarsPolicyWordingD());

            Collection<CarsPolicyWordingD> carsPolicyWordingDList = db.carsPolicyWordingDRepository
                    .findByPolicyWordingHId(carsPolicyWordingHToDelete.getPolicyWordingHId());

            if (carsPolicyWordingDList != null) {
                Iterator<CarsPolicyWordingD> carsPolicyWordingDIterator = carsPolicyWordingDList.iterator();
                while (carsPolicyWordingDIterator.hasNext()) {
                    CarsPolicyWordingD carsPolicyWordingDToDelete = carsPolicyWordingDIterator.next();
                    db.carsPolicyWordingDRepository.delete(carsPolicyWordingDToDelete);
                }
            }
            db.carsPolicyWordingHRepository.delete(carsPolicyWordingHToDelete);

        }
    }

    String validateCover(Covers cover, String policyNo, String certificateNo) throws Exception {
        String desc = null;

        if (Utility.isEmpty(cover.getCoverCode())) {
            // saveMessage(policyNo, "Cover Code", "Blocking Field", "CARS_COVER",
            // insuranceCode, certificateNo);
            throw new Exception("Error :");
        }
        if (cover.getCoverID() == 0) {
            // saveMessage(policyNo, "Cover Id", "Blocking Field", "CARS_COVER",
            // insuranceCode, certificateNo);
            throw new Exception("Error :");
        }
//		Optional<CarsCover> carsCoverOpt = db.carsCoverRepository
//				.findByCoverCodeAndCoverInsuranceId(insuranceCode + cover.getCoverCode(), insuranceCode);//byCoverId
        Optional<CarsCover> carsCoverOpt = db.carsCoverRepository.findByCoverCodeAndCoverInsurance(
                cover.getCoverCode(), Integer.valueOf(insuranceCode));

        if (!carsCoverOpt.isPresent()) {
            CarsCover carsCover = new CarsCover();
            carsCover.setCoverCode(cover.getCoverCode());// jean
            // carsCover.setCoverCode("test");
            if (!Utility.isEmpty(cover.getCoverDesc())) {
                desc = cover.getCoverDesc().replace("•", "*");
                desc = desc.replace("?", "*");
            }
            carsCover.setCoverDescription(desc);
            // carsCover.setCoverID(cover.getCoverID().toString());
            carsCover.setCoverID((insuranceCode + "." + cover.getCoverCode()).trim());
            carsCover.setCoverReference(cover.getCoverID().toString());
            // carsCover.setCoverInsuranceId(insuranceCode);
            carsCover.setCoverInsurance(Integer.valueOf(insuranceCode));
            carsCover.setCoverInsuranceId(insuranceCode);


            // carsCover.setCoverInsurance(coverInsurance);
            // carsCover.setCoverInsurance();


            if (!Utility.isEmpty(cover.getTpaCoverTypeCode())) {
                carsCover.setCoverType(cover.getTpaCoverTypeCode());
            }


//			else {
//				saveMessage(policyNo, "Tpa Cover Type Code", "Missing Field", "CARS_COVER", insuranceCode,
//						certificateNo);
//			}
            //carsCover.setCoverType(cover.getTpaCoverTypeCode());
            // carsCover.setCoverState(coverState);
            carsCover.setSysVersionNumber(0);
            carsCover.setSysCreatedBy(CREATED_BY_QUARTZ);
            carsCover.setSysUpdatedBy(CREATED_BY_QUARTZ);
            carsCover.setSysCreatedDate(new Timestamp(new Date().getTime()));
            carsCover.setSysUpdatedDate(new Timestamp(new Date().getTime()));
            // carsProducts.setSysCreatedBy(new Timestamp(new Date().getTime()));
            db.carsCoverRepository.save(carsCover);
            saveMessage(policyNo, "Cover:" + cover.getCoverCode() + " " + cover.getCoverDesc(), "Added Field",
                    "CARS_COVER", insuranceCode, certificateNo);

            return carsCover.getCoverID();
        }

        if (carsCoverOpt.isPresent()) {
            desc = cover.getCoverDesc();
            if (!Utility.isEmpty(cover.getCoverDesc())) {
                desc = cover.getCoverDesc().replace("•", "*");
                desc = desc.replace("?", "*");
            }

            String validatePolicyCoverType = db.carsPolicyRepository.findConfigByKey(insuranceCode + ".validatePolicyCoverType");
            if (validatePolicyCoverType.equals("false")) {
                if (Utility.isEmpty(carsCoverOpt.get().getCoverType())) {
                    carsCoverOpt.get().setCoverType(cover.getTpaCoverTypeCode());
                }

            } else {

                if (!Utility.isEmpty(cover.getTpaCoverTypeCode())) {
                    carsCoverOpt.get().setCoverType(cover.getTpaCoverTypeCode());
                }
            }


            carsCoverOpt.get().setCoverDescription(desc);
            carsCoverOpt.get().setSysUpdatedBy(CREATED_BY_QUARTZ);
            carsCoverOpt.get().setSysUpdatedDate(new Timestamp(new Date().getTime()));
            db.carsCoverRepository.save(carsCoverOpt.get());

        }
        return carsCoverOpt.get().getCoverID();

    }

    String validateSubCover(SubCovers subCovers, String cover, String policyNo, String certificateNo, String coverId) throws Exception {

        if (Utility.isEmpty(subCovers.getSubCoverCode())) {
            // saveMessage(policyNo, "Cover Code", "Blocking Field", "CARS_COVER",
            // insuranceCode, certificateNo);
            throw new Exception("Error :");
        }


        if (subCovers.getTpaSubCoverTypeID() == null) {
            saveMessage(policyNo, "Tpa SubCover Type ID", "Blocking Field", "CARS_COVER",
                    insuranceCode, certificateNo);
            throw new Exception("Error :");
        }
        if (subCovers.getSubCoverID() == 0) {
            // saveMessage(policyNo, "Cover Id", "Blocking Field", "CARS_COVER",
            // insuranceCode, certificateNo);
            throw new Exception("Error :");
        }
//		Optional<CarsCover> carsCoverOpt = db.carsCoverRepository
//		.findByCoverCodeAndCoverInsuranceId(insuranceCode + cover.getCoverCode(), insuranceCode);//byCoverId
        Optional<CarsCover> carsCoverOpt = db.carsCoverRepository.findByCoverCodeAndCoverInsurance(
                cover + "." + subCovers.getSubCoverCode(), Integer.valueOf(insuranceCode));


        if (!carsCoverOpt.isPresent()) {
            CarsCover carsCover = new CarsCover();
            carsCover.setCoverCode(cover + "." + subCovers.getSubCoverCode());// jean
            // carsCover.setCoverCode("test");
            carsCover.setCoverInsurance(Integer.valueOf(insuranceCode));
            carsCover.setCoverInsuranceId(insuranceCode);

            carsCover.setCoverDescription(subCovers.getSubCoverDesc());
            // carsCover.setCoverID(cover.getCoverID().toString());

            carsCover.setCoverID((insuranceCode + "." + cover.trim() + "." + subCovers.getSubCoverCode().toString()).trim());
            carsCover.setCoverReference(subCovers.getSubCoverID().toString());
            // carsCover.setCoverInsuranceId(insuranceCode);
            // carsCover.setCoverInsurance(coverInsurance);
            // carsCover.setCoverInsurance();
            if (!Utility.isEmpty(subCovers.getTpaSubCoverTypeCode())) {
                carsCover.setCoverType(subCovers.getTpaSubCoverTypeCode());
            }


//			else {
//				saveMessage(policyNo, "Tpa SubCover Type Code", "Missing Field", "CARS_COVER", insuranceCode,
//						certificateNo);
//			}

            // carsCover.setCoverState(coverState);
            carsCover.setSysVersionNumber(0);
            carsCover.setSysCreatedBy(CREATED_BY_QUARTZ);
            carsCover.setSysUpdatedBy(CREATED_BY_QUARTZ);
            carsCover.setSysCreatedDate(new Timestamp(new Date().getTime()));
            carsCover.setSysUpdatedDate(new Timestamp(new Date().getTime()));
            // carsProducts.setSysCreatedBy(new Timestamp(new Date().getTime()));
            logger.info(" carsCover s of policy No " + policyNo + " inserted     Cover  id " + carsCover.getCoverID());

            db.carsCoverRepository.save(carsCover);
//todo
            saveMessage(policyNo, "SubCover :" + subCovers.getSubCoverCode() + " " + subCovers.getSubCoverDesc(),
                    "Added Field", "CARS_COVER", insuranceCode, certificateNo);

            return carsCover.getCoverID();
        }

        if (carsCoverOpt.isPresent()) {
            carsCoverOpt.get().setCoverDescription(subCovers.getSubCoverDesc());


//            if (!Utility.isEmpty(subCovers.getTpaSubCoverTypeCode())) {
//                carsCoverOpt.get().setCoverType(subCovers.getTpaSubCoverTypeCode());
//            }
            String validatePolicyCoverType = db.carsPolicyRepository.findConfigByKey(insuranceCode + ".validatePolicyCoverType");
            if (validatePolicyCoverType.equals("false")) {
                if (Utility.isEmpty(carsCoverOpt.get().getCoverType())) {
                    carsCoverOpt.get().setCoverType(subCovers.getTpaSubCoverTypeCode());
                }

            } else {

                if (!Utility.isEmpty(subCovers.getTpaSubCoverTypeCode())) {
                    carsCoverOpt.get().setCoverType(subCovers.getTpaSubCoverTypeCode());
                }
            }


            carsCoverOpt.get().setSysUpdatedBy(CREATED_BY_QUARTZ);
            carsCoverOpt.get().setSysUpdatedDate(new Timestamp(new Date().getTime()));


            db.carsCoverRepository.save(carsCoverOpt.get());
            logger.info(" carsCover  of policy No " + policyNo + " inserted  sub   Cover  id " + carsCoverOpt.get().getCoverID());


        }
        return carsCoverOpt.get().getCoverID();

    }

    public void insertCarsCover(Covers cover, SubCovers subCover, String carCoverCode, String carId, String coverCode,
                                String subCoverCode, Long coverOrder) {
        SendingMail sendingMail = new SendingMail();

        if (cover != null) {
            int coversExist = db.carsPolicyCoverRepository.findIfCoversExist(coverCode, carId);
            if (coversExist > 0) {
                saveMessage(policyNo, "Cover:" + cover.getCoverCode() + " " + cover.getCoverDesc(), "Duplicate Cover",
                        "CARS_COVER", insuranceCode, null);


                try {
                    sendingMail.run(companyName + " duplicate Covers in  " + policyNo,
                            "Cover:" + cover.getCoverCode() + " " + cover.getCoverDesc() + " has been duplicated \n \n This Email Is Informative");

                } catch (Exception e) {
                    e.printStackTrace();

                }


            }
            {
                CarsPolicyCover carsPolicyCoverToInsert = new CarsPolicyCover();
                carsPolicyCoverToInsert.setPolicyCoversId(UUID.randomUUID().toString());
                // carsPolicyCoverToInsert.setPolicyCoversCover(CoverCode + ".");
                carsPolicyCoverToInsert.setPolicyCoversCover(carCoverCode);
                carsPolicyCoverToInsert.setPolicyCoversCoverId(coverCode);

                carsPolicyCoverToInsert.setPolicyCoversSumInsured(cover.getSumInsured());

                carsPolicyCoverToInsert.setPolicyCoversOrder(((double) cover.getCoverOrder()));


                carsPolicyCoverToInsert.setPolicyCoversDeductible((double) cover.getDeductibleFlatAmount());
                carsPolicyCoverToInsert.setPolicyCoversLimitClaim(cover.getCoverLimitClaim());
                System.out.println("cover limit" + cover.getCoverLimitClaim());
                carsPolicyCoverToInsert.setPolicyCoversCountLimit(cover.getCoverCountLimit());
                carsPolicyCoverToInsert.setPolicyCoversDeductible100((double) cover.getDeductiblePercentage());
                // carsPolicyCoverToInsert.setPolicyCoversPercentage(Float.toString(cover.getDeductiblePercentage()));
                carsPolicyCoverToInsert.setPolicyCoversPercentage(cover.getDeductibleType());
                carsPolicyCoverToInsert.setPolicyCoversCarId(carId);
                // carsPolicyCoverToInsert.setPolicyCoversCoverId(insuranceCode+"."+cover.getCoverCode());
                // carsPolicyCoverToInsert.setPolicyCoversCoverId(carCoverId);jean
                // batchCover = dataTransferCoverContractLoaded.getScriptName();
                carsPolicyCoverToInsert.setSysVersionNumber(0);
                carsPolicyCoverToInsert.setSysCreatedBy(CREATED_BY_QUARTZ);
                carsPolicyCoverToInsert.setSysUpdatedBy(CREATED_BY_QUARTZ);
                carsPolicyCoverToInsert.setSysCreatedDate(new Timestamp(new Date().getTime()));
                carsPolicyCoverToInsert.setSysUpdatedDate(new Timestamp(new Date().getTime()));


                db.carsPolicyCoverRepository.save(carsPolicyCoverToInsert);
                logger.info(" carsPolicyCover of policy No " + policyNo + " inserted  policy Cover  id " + carsPolicyCoverToInsert.getPolicyCoversId());
            }
        }
        if (subCover != null) {
            Integer subCoverExist = db.carsPolicyCoverRepository.findIfCoversExist(subCoverCode, carId);
            if (subCoverExist > 0) {


                try {
                    sendingMail.run(companyName + " duplicate SubCovers in  " + policyNo,
                            " SubCover :" + subCoverCode + " in Cover : " + coverCode + " has been duplicated \n \n This Email Is Informative");

                } catch (Exception e) {
                    e.printStackTrace();

                }
            } else {


                CarsPolicyCover carsPolicySubCoverToInsert = new CarsPolicyCover();
                carsPolicySubCoverToInsert.setPolicyCoversId(UUID.randomUUID().toString());
                carsPolicySubCoverToInsert.setPolicyCoversOrder(Double.valueOf(coverOrder + "." + subCover.getSubCoverOrder()));

                carsPolicySubCoverToInsert.setPolicyCoversCover(coverCode + "." + subCover.getSubCoverCode());
                carsPolicySubCoverToInsert.setPolicyCoversCoverId(subCoverCode);

                carsPolicySubCoverToInsert.setPolicyCoversSumInsured(subCover.getSubCoverSumInsured());

                carsPolicySubCoverToInsert.setPolicyCoversDeductible((double) subCover.getSubCoverDeductibleFlatAmount());
                carsPolicySubCoverToInsert
                        .setPolicyCoversDeductible100((double) subCover.getSubCoverDeductiblePercentage());
                // carsPolicySubCoverToInsert.setPolicyCoversPercentage(Float.toString(subCover.getSubCoverDeductiblePercentage()));


                carsPolicySubCoverToInsert.setPolicyCoversLimitClaim(subCover.getSubCoverLimitClaim());


                carsPolicySubCoverToInsert.setPolicyCoversCountLimit(subCover.getSubCoverCountLimit());
                carsPolicySubCoverToInsert.setPolicyCoversPercentage(subCover.getSubCoverDeductibleType());
                carsPolicySubCoverToInsert.setPolicyCoversCarId(carId);

                // carsPolicyCoverToInsert.setPolicyCoversCoverId(carCoverId);jean
                // batchCover = dataTransferCoverContractLoaded.getScriptName();
                carsPolicySubCoverToInsert.setSysVersionNumber(0);
                carsPolicySubCoverToInsert.setSysCreatedBy(CREATED_BY_QUARTZ);
                carsPolicySubCoverToInsert.setSysUpdatedBy(CREATED_BY_QUARTZ);
                carsPolicySubCoverToInsert.setSysCreatedDate(new Timestamp(new Date().getTime()));
                carsPolicySubCoverToInsert.setSysUpdatedDate(new Timestamp(new Date().getTime()));
                db.carsPolicyCoverRepository.save(carsPolicySubCoverToInsert);
                logger.info(" carsPolicy sub Cover of policy No " + policyNo + " inserted  policy sub  Cover  id " + carsPolicySubCoverToInsert.getPolicyCoversId());

            }
        }
    }

    public String ValidatePolicyNo(String policyNo) throws Exception {
        if (Utility.isEmpty(policyNo)) {

            // saveMessage(policyNo, "Policy Number", "Blocking Field", "CARS_POLICY",
            // insuranceCode, null);
            throw new Exception("Error :");
        }
        return policyNo;
    }

    public String convertText(String text) {

        Document jsoupDoc = Jsoup.parse(text);
        Document.OutputSettings outputSettings = new Document.OutputSettings();
        outputSettings.prettyPrint(false);
        jsoupDoc.outputSettings(outputSettings);
        jsoupDoc.select("br").before("\\n");

        jsoupDoc.select("*").forEach(e -> {
            if (e.ownText().equalsIgnoreCase("&amp;nbsp;") || e.ownText().equalsIgnoreCase("&nbsp;")) {
                e.text(" ");
            }
        });


        jsoupDoc.select("p").before("\\n");
//		jsoupDoc.select("\'").before("\'");
//		jsoupDoc.select("\"").before("\"");


        // jsoupDoc.select("br").before("br");
        // jsoupDoc.select("p").append("br");
        String str = jsoupDoc.html().replaceAll("\\\\n", "\n");
        str = str.replaceAll("&nbsp;", " ");
        str = str.replaceAll("&amp;", "&");

        String strWithNewLines = Jsoup.clean(str, "", Whitelist.none(), outputSettings);
        strWithNewLines = strWithNewLines.replaceAll("\\\\r", "");
        // strWithNewLines = strWithNewLines.replaceAll("\n", "<br>");
        System.out.println(strWithNewLines);
        return strWithNewLines;

    }

    // TODO: ??/??/???? name of company in email
    public void saveMessage(String policyNo, String field, String messagePurpose, String TableName, String insuranceId,
                            String CertificateNumber) {
        String certificate = "";

        if (CertificateNumber != null) {
            certificate = "Vehicle Certificate Number " + CertificateNumber + ",";
        }
        if (messagePurpose.equals("Missing Field")) {
            SendingMail sendingMail = new SendingMail();
            try {
                sendingMail.run(companyName + " Policy Upload policy " + policyNo,
                        certificate + field + " is missing \n \nThis Email Is Informative");
                carsErrorlogService.insertError(
                        companyName + " Policy Upload policy " + policyNo + ": " + certificate + field + " is missing",
                        insuranceId, TableName, "Informative Missing");
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        if (messagePurpose.equals("Added Field")) {
            SendingMail sendingMail = new SendingMail();
            try {
                sendingMail.run(companyName + " Policy Upload policy " + policyNo,
                        certificate + field + " has been added \n \n This Email Is Informative");
                carsErrorlogService.insertError(
                        companyName + " Policy Upload policy " + policyNo + ": " + certificate + field + " has been added",
                        insuranceId, TableName, "Informative Addition");
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        if (messagePurpose.equals("Blocking Field")) {
            SendingMail sendingMail = new SendingMail();
            try {
                sendingMail.run(companyName + " Policy Upload policy " + policyNo,
                        field + " is missing \n \n This Email Is Blocking");
                carsErrorlogService.insertError(
                        companyName + " Policy Upload policy " + policyNo + ": " + field + " is missing", insuranceId,
                        TableName, "Blocking");
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

    public void ValidatePolicy(Policy policy) throws Exception {

        int blocking = 0;
        String Identifier = null;

        if (!Utility.isEmpty(policy.getPolicyNo())) {
            Identifier = policy.getPolicyNo();
        } else if (policy.getPolicyID() != null) {
            Identifier = policy.getPolicyID().toString();
        } else if (policy.getPolicyRootID() != null) {
            Identifier = policy.getPolicyRootID().toString();
        }


        StringBuffer error = new StringBuffer();
        StringBuffer errorDeferred = new StringBuffer();
        System.out.printf("llllllllllllllllllllllllllllllll");
        //	System.out.printf(policy.getSysID().toString());
        if (policy.getSysID() == null) {
            blocking = 1;
            error.append(" Sys Id   is missing *This message is Blocking* \n \n");

            carsErrorlogService.insertError(companyName + " Policy Upload policy " + Identifier + "Sys Id is missing",
                    insuranceCode, "CARS_POLICY", "Blocking");
        }


        if (Utility.isEmpty(policy.getPolicyNo())) {
            blocking = 1;

            error.append(" Policy Number is missing *This message is Blocking* \n \n");

            carsErrorlogService.insertError(companyName + " Policy Upload policy " + Identifier + "Policy Number is missing",
                    insuranceCode, "CARS_POLICY", "Blocking");
            // saveMessage(policyNo, "Policy Number", "Blocking Field", "CARS_POLICY",
            // insuranceCode, null);
        } else {
            policyNo = policy.getPolicyNo();
        }

        if (policy.getPolicyID() == null || policy.getPolicyID() == BigInteger.valueOf(0)) {
            blocking = 1;
            error.append(" Policy Id is missing *This message is Blocking* \n \n");

            carsErrorlogService.insertError(companyName + " Policy Upload policy " + Identifier + "Policy Id is missing",
                    insuranceCode, "CARS_POLICY", "Blocking");

        } else {
            policyId = policy.getPolicyID().toString();
        }
        if (policy.getPolicyRootID() == null || policy.getPolicyRootID() == 0) {
            blocking = 1;
            error.append(" Policy RootId is missing *This message is Blocking* \n \n");

            carsErrorlogService.insertError(companyName + " Policy Upload policy " + Identifier + "Policy RootID is missing",
                    insuranceCode, "CARS_POLICY", "Blocking");

        } else {
            policyRootId = policy.getPolicyRootID().toString();
        }

        if (Utility.isEmpty(policy.getSubLineCode())) {
            error.append(" SubLine Code is missing *This message is Informative* \n \n ");
            errorDeferred.append(" SubLine Code is missing *This message is Informative* \n \n ");

            carsErrorlogService.insertError(companyName + " Policy Upload policy " + Identifier + "SubLine Code is missing",
                    insuranceCode, "CARS_SUBLINE", "Informative Missing");

            // saveMessage(policyNo, "Subline Code", "Missing Field", "CARS_SUBLINE",
            // productInsuranceId, null);
        }
        if (Utility.isEmpty(policy.getSublineDescription())) {
            error.append(" Subline Description is missing *This message is Informative* \n \n ");
            carsErrorlogService.insertError(
                    companyName + " Policy Upload policy " + Identifier + "Subline Description is missing", insuranceCode,
                    "CARS_SUBLINE", "Informative Missing");
            // saveMessage(policyNo, "Subline Description", "Missing Field", "CARS_SUBLINE",
            // productInsuranceId, null);
        }

        if (Utility.isEmpty(policy.getProductCode())) {
            // saveMessage(policyNo, "Product Code", "Missing Field", "CARS_PRODUCTS",
            // sublineInsuranceId, null);
            carsErrorlogService.insertError(companyName + " Policy Upload policy " + Identifier + "Product Code is missing",
                    insuranceCode, "CARS_SUBLINE", "Informative Missing");

            error.append(" Product Code is missing *This message is Informative*  \n \n");
        }
        if (policy.getProductID() == 0 || policy.getProductID() == null) {
            //blocking=1;
            error.append(" Product ID is missing *This message is Informative*  \n \n");
            errorDeferred.append(" Product ID is missing *This message is Informative*  \n \n");
            carsErrorlogService.insertError(companyName + " Policy Upload policy " + Identifier + "ProductID is missing",
                    insuranceCode, "CARS_PRODUCTS", "Informative Missing");
            // saveMessage(policyNo, "ProductID", "Missing Field", "CARS_PRODUCTS",
            // sublineInsuranceId, null);
            // return null;
        }

        if (Utility.isEmpty(policy.getProductDescription())) {
            error.append(" Product Description is missing *This message is Informative* \n \n ");

            carsErrorlogService.insertError(
                    companyName + " Policy Upload policy " + Identifier + "Product Description is missing", insuranceCode,
                    "CARS_PRODUCTS", "Informative Missing");
            // saveMessage(policyNo, "Product Description", "Missing Field",
            // "CARS_PRODUCTS", sublineInsuranceId, null);
        }
        if (Utility.isEmpty(policy.getBranchCode())) {
            error.append(" Branch Code is missing *This message is Blocking* \n \n ");
            carsErrorlogService.insertError(companyName + " Policy Upload policy " + Identifier + "Branch Code is missing",
                    insuranceCode, "CARS_BRANCH", "Blocking");
//			saveMessage(policyNo, "Branch Code", "Blocking Field", "CARS_BRANCH", branchInsuranceId, null);
            blocking = 1;
        }
        if (Utility.isEmpty(policy.getBranchDescription())) {
            error.append(" Branch Description is missing *This message is Blocking* \n \n");
            carsErrorlogService.insertError(
                    companyName + " Policy Upload policy " + Identifier + "Branch Description is missing", insuranceCode,
                    "CARS_BRANCH", "Blocking");
            blocking = 1;
        }
        if (policy.getInsuredID() == 0 || policy.getInsuredID() == null) {
            error.append(" Insured ID is missing *This message is Informative* \n \n ");

            carsErrorlogService.insertError(companyName + " Policy Upload policy " + Identifier + "Insured ID is missing",
                    insuranceCode, "CARS_CLIENT", "Informative Missing");
            // saveMessage(policyNo, "Insured ID", "Missing Field", "CARS_CLIENT",
            // clientInsuranceId, certificateNo);
            // return null;
        }
        if (Utility.isEmpty(policy.getInsuredCode())) {
            error.append(" Insured Code is missing *This message is Informative* \n \n");

            carsErrorlogService.insertError(companyName + " Policy Upload policy " + Identifier + "Insured Code is missing",
                    insuranceCode, "CARS_CLIENT", "Informative Missing");
            // saveMessage(policyNo, "Insured Code", "Missing Field", "CARS_CLIENT",
            // clientInsuranceId, certificateNo);
            // return null;
        }
        if (Utility.isEmpty(policy.getDateIssue())) {
            blocking = 1;
            error.append(" Issue Date is missing *This message is blocking* \n \n");
            carsErrorlogService.insertError(companyName + " Policy Upload policy " + Identifier + "Issue Date is missing",
                    insuranceCode, "CARS_POLICY", "Blocking");
            // saveMessage(policyVehicle.getPolicy().getPolicyNo(), "Issue Date", "Missing
            // Field", "CARS_POLICY",
            // insuranceId, null);
        }
        if (Utility.isEmpty(policy.getPremCurrency())) {
            blocking = 1;
            error.append(" Premium Currency is missing *This message is Blocking* \n \n");
            carsErrorlogService.insertError(
                    companyName + " Policy Upload policy " + Identifier + "Premium Currency is missing", insuranceCode,
                    "CARS_DT_CURRENCY", "Blocking");
            // saveMessage(policyNo, "Premium Currency", "Blocking Field",
            // "CARS_DT_CURRENCY", insuranceCode, null);
        }
        if (Utility.isEmpty(policy.getPremCurrencyDescription())) {
            blocking = 1;
            error.append(" Premium Currency Description is missing *This message is Blocking* \n \n");
            carsErrorlogService.insertError(
                    companyName + " Policy Upload policy " + Identifier + "Premium Currency Description is missing",
                    insuranceCode, "CARS_DT_CURRENCY", "Blocking");
            // saveMessage(policyNo, "Premium Currency Description", "Blocking Field",
            // "CARS_DT_CURRENCY", insuranceCode,
            // null);
        }
        if (policy.getPremExchangeRate().doubleValue() == 0.0) {
            blocking = 1;
            error.append(" Premium Currency Exchange Rate is missing *This message is Blocking* \n \n");
            carsErrorlogService.insertError(
                    companyName + " Policy Upload policy " + Identifier + "Premium Exchange Rate is missing", insuranceCode,
                    "CARS_DT_CURRENCY", "Blocking");
            // saveMessage(policyNo, "Premium Exchange Rate", "Blocking Field",
            // "CARS_DT_CURRENCY", insuranceCode, null);
        }

        if (Utility.isEmpty(policy.getsICurrency())) {
            blocking = 1;
            error.append(" SI Currency is missing *This message is Blocking* \n \n");
            carsErrorlogService.insertError(
                    companyName + " Policy Upload policy " + Identifier + "SI Currency Rate is missing", insuranceCode,
                    "CARS_DT_CURRENCY", "Blocking");
            // saveMessage(policyNo, "si Currency", "Blocking Field", "CARS_DT_CURRENCY",
            // insuranceCode, null);
            // throw new Exception("Error :");
        }
        if (Utility.isEmpty(policy.getsICurrencyDescription())) {
            blocking = 1;
            error.append(" SI Currency Description is missing *This message is Blocking* \n \n");
            carsErrorlogService.insertError(
                    companyName + " Policy Upload policy " + Identifier + "SI Currency Description is missing", insuranceCode,
                    "CARS_DT_CURRENCY", "Blocking");
            // saveMessage(policyNo, "si Currency Description", "Blocking Field",
            // "CARS_DT_CURRENCY", insuranceCode, null);
        }
        if (policy.getsIExchangeRate().doubleValue() == 0.0) {
            blocking = 1;
            error.append(" SI Exchange Rate is missing *This message is Blocking* \n \n");
            carsErrorlogService.insertError(
                    companyName + " Policy Upload policy " + Identifier + "SI Exchange Rate is missing", insuranceCode,
                    "CARS_DT_CURRENCY", "Blocking");
        }

        if (policy.getVehicles() != null) {
            // Collection<CarsPolicy> carsPolicyToSearchList =
            // carsPolicySearchable(policyVehicle);
            String certificateNo = null;
            for (Vehicles vehicle : policy.getVehicles()) {
                if (Utility.isEmpty(vehicle.getCertificateNo())) {
                    certificateNo = "0000";
                } else {
                    certificateNo = vehicle.getCertificateNo();
                }
                if (Utility.isEmpty(vehicle.getDateEffective())) {
                    blocking = 1;
                    error.append(" Effective Date in Vehicle Certificate Number " + certificateNo + " is missing "
                            + "   *This message is blocking* \n \n");
                    carsErrorlogService.insertError(
                            companyName + " Policy Upload policy " + Identifier
                                    + " Effective Date in Vehicle Certificate Number " + certificateNo + " is missing ",
                            insuranceCode, "CARS_POLICY", "Blocking");
//					saveMessage(vehicle.getPolicyNo(), "EffectiveDate Date", "Missing Field",
//							"CARS_POLICY", insuranceId, policyVehicle.getVehicle().getCertificateNo());
                }
                if (Utility.isEmpty(vehicle.getDateExpiry())) {
                    blocking = 1;
                    error.append(" Expiry Date in Vehicle Certificate Number " + certificateNo + " is missing "
                            + " is missing *This message is blocking* \n \n");
                    carsErrorlogService.insertError(
                            companyName + " Policy Upload policy " + Identifier + " Expiry Date in Vehicle Certificate Number "
                                    + certificateNo + " is missing ",
                            insuranceCode, "CARS_POLICY", "Blocking");
                    // saveMessage(policyVehicle.getPolicy().getPolicyNo(), "ExpiryDate Date",
                    // "Missing Field",
                    // "CARS_POLICY", insuranceId, policyVehicle.getVehicle().getCertificateNo());
                }

                if (vehicle.getCarInsuredID() == 0 || vehicle.getCarInsuredID() == null) {
                    blocking = 1;
                    error.append(" Insured Id in Vehicle Certificate Number " + certificateNo + " is missing "
                            + " is missing *This message is blocking * \n \n");
                    carsErrorlogService.insertError(
                            companyName + " Policy Upload policy " + Identifier + "Insured Id in Vehicle Certificate Number "
                                    + certificateNo + " is missing ",
                            insuranceCode, "CARS_CLIENT", "Blocking");
                    // saveMessage(policyNo, "Client ID", "Missing Field", "CARS_CLIENT",
                    // clientInsuranceId, certificateNo);
                    // return null;
                }
                if (Utility.isEmpty(vehicle.getCarInsuredCode())) {
                    error.append(" Insured Code in Vehicle Certificate Number " + certificateNo + " is missing "
                            + " *This message is Informative* \n \n");
                    carsErrorlogService.insertError(
                            companyName + " Policy Upload policy " + Identifier + "Insured Id in Vehicle Certificate Number "
                                    + certificateNo + " is missing ",
                            insuranceCode, "CARS_CLIENT", "Informative Missing");
                    // saveMessage(policyNo, "Client Code", "Missing Field", "CARS_CLIENT",
                    // clientInsuranceId, certificateNo);
                    // return null;
                }


                if (Utility.isEmpty(vehicle.getCarYear())) {
                    error.append(" Car Year in Vehicle Certificate Number " + certificateNo + " is missing "
                            + " *This message is Informative* \n \n");
                    carsErrorlogService.insertError(
                            companyName + " Policy Upload policy " + Identifier + " Car Year in Vehicle Certificate Number "
                                    + certificateNo + " is missing ",
                            insuranceCode, "CARS_POLICY_CAR", "Informative Missing");
                    // saveMessage(policyNo, "Car Year", "Missing Field", "CARS_POLICY_CAR",
                    // insuranceCode,
                    // vehicles.getCertificateNo());
                }


//todo


                if (vehicle.getClauses() != null) {
                    DeletePolicyWording(policyId);
                    for (Clauses clauses : vehicle.getClauses()) {


                        if (Utility.isEmpty(clauses.getContent())) {
                            String value = db.carsPolicyRepository.findConfigByKey(insuranceCode + ".emailValidateClauses");

                            if (value != null && value.equals("true")) {
                                error.append(" content in clauses  with  clauses  Id  " + clauses.getClauseID() + " certificate Number " + certificateNo + " and policy Id " + policyId + " is missing "
                                        + " *This message is Informative* \n \n");
                                carsErrorlogService.insertError(
                                        companyName + " Policy Upload policy " + Identifier + " content   in clauses  Certificate Number "
                                                + certificateNo + "and clause Id " + clauses.getClauseID() + " is missing ",
                                        insuranceCode, "CARS_POLICY_CAR", "Informative Missing");
                            }
                            // saveMessage(policyNo, "Car Year", "Missing Field", "CARS_POLICY_CAR",
                            // insuranceCode,
                            // vehicles.getCertificateNo());
                        }


                    }
                }


                if (Utility.isEmpty(vehicle.getCarChassis())) {
                    error.append(" Car Chassis in Vehicle Certificate Number " + certificateNo + " is missing "
                            + " *This message is Informative* \n \n");
                    carsErrorlogService.insertError(
                            companyName + " Policy Upload policy " + Identifier + " Car Chassis in Vehicle Certificate Number "
                                    + certificateNo + " is missing ",
                            insuranceCode, "CARS_POLICY_CAR", "Informative Missing");
                    // saveMessage(policyNo, " Car Chassis", "Missing Field", "CARS_POLICY_CAR",
                    // insuranceCode, null);
                }
                boolean succChar = false;
                if (vehicle.getCarStatus().equals("M")) {
                    succChar = true;
                }
                if (vehicle.getCarStatus().equals("A")) {
                    succChar = true;
                }
                if (vehicle.getCarStatus().equals("D")) {
                    succChar = true;
                }
                if (vehicle.getCarStatus().equals("C")) {
                    succChar = true;
                }

                if (Utility.isEmpty(vehicle.getCarStatus()) || succChar == false) {
                    error.append(" Car Status in Vehicle Certificate Number " + certificateNo + " is missing or false"
                            + " *This message is Blocking* \n \n");
                    carsErrorlogService.insertError(
                            companyName + " Policy Upload policy " + Identifier + " Car Status in Vehicle Certificate Number "
                                    + certificateNo + " is missing ",
                            insuranceCode, "CARS_POLICY", "Blocking");
                    blocking = 1;
                    // saveMessage(policyVehicle.getPolicy().getPolicyNo(), " Car Status", "Missing
                    // Field", "CARS_POLICY",
                    // insuranceCode, policyVehicle.getVehicle().getCertificateNo());
                }
                if (vehicle.getCovers() != null) {
                    for (Covers covers : vehicle.getCovers()) {

                        if (Utility.isEmpty(covers.getCoverCode())) {
                            blocking = 1;
                            error.append(" Cover Code in Vehicle Certificate Number " + certificateNo + " is missing "
                                    + " *This message is Blocking* \n \n");
                            carsErrorlogService.insertError(companyName + " Policy Upload policy " + Identifier
                                            + " Cover Code in Vehicle Certificate Number " + certificateNo + " is missing ",
                                    insuranceCode, "CARS_COVER", "Blocking");
                            // saveMessage(policyNo, "Cover Code", "Blocking Field", "CARS_COVER",
                            // insuranceCode, certificateNo);
                            // throw new Exception("Error :");
                        }
                        if (covers.getCoverID() == 0) {
                            blocking = 1;
                            error.append(" Cover ID in Vehicle Certificate Number " + certificateNo +

                                    " , cover code " + covers.getCoverCode() +
                                    " and cover name " + covers.getCoverDesc() +
                                    " is missing "
                                    + " *This message is Blocking* \n \n");
                            carsErrorlogService.insertError(companyName + " Policy Upload policy " + Identifier
                                            + " Cover ID in Vehicle Certificate Number " + certificateNo + " is missing ",
                                    insuranceCode, "CARS_COVER", "Blocking");
                            // saveMessage(policyNo, "Cover Id", "Blocking Field", "CARS_COVER",
                            // insuranceCode, certificateNo);
                            // throw new Exception("Error :");
                        }


                        if (Utility.isEmpty(covers.getTpaCoverTypeCode())) {
                            //todo  today
                            String validatePolicyCoverType = db.carsPolicyRepository.findConfigByKey(insuranceCode + ".validatePolicyCoverType");
                            if (validatePolicyCoverType != null && validatePolicyCoverType.equals("false")) {
                                Optional<CarsCover> carsCoverOptional = db.carsCoverRepository.findByCoverCodeAndCoverInsurance(covers.getCoverCode(), Integer.parseInt(insuranceCode));
                                if (carsCoverOptional.isPresent()) {
                                    if (Utility.isEmpty(carsCoverOptional.get().getCoverType())) {
                                        error.append(" Tpa Cover Type Code in Vehicle Certificate Number " + certificateNo
                                                + " , cover code " + covers.getCoverCode() +
                                                " and cover name " + covers.getCoverDesc() +

                                                " is missing " + " *This message is Informative* \n \n");


                                        carsErrorlogService.insertError(companyName + " Policy Upload policy " + Identifier
                                                + " Tpa Cover Type Code in Vehicle Certificate Number " + certificateNo
                                                + " is missing ", insuranceCode, "CARS_COVER", "Informative Missing");
                                    }

                                } else {
                                    error.append(" Tpa Cover Type Code in Vehicle Certificate Number " + certificateNo
                                            + " , cover code " + covers.getCoverCode() +
                                            " and cover name " + covers.getCoverDesc() +

                                            " is missing " + " *This message is Informative* \n \n");


                                    carsErrorlogService.insertError(companyName + " Policy Upload policy " + Identifier
                                            + " Tpa Cover Type Code in Vehicle Certificate Number " + certificateNo
                                            + " is missing ", insuranceCode, "CARS_COVER", "Informative Missing");
                                }

                            } else {
                                error.append(" Tpa Cover Type Code in Vehicle Certificate Number " + certificateNo
                                        + " , cover code " + covers.getCoverCode() +
                                        " and cover name " + covers.getCoverDesc() +

                                        " is missing " + " *This message is Informative* \n \n");


                                carsErrorlogService.insertError(companyName + " Policy Upload policy " + Identifier
                                        + " Tpa Cover Type Code in Vehicle Certificate Number " + certificateNo
                                        + " is missing ", insuranceCode, "CARS_COVER", "Informative Missing");

                            }


                            // saveMessage(policyNo, "Tpa Cover Type Code", "Missing Field", "CARS_COVER",
                            // insuranceCode,
                            // certificateNo);
                        }


                        if (covers.getTpaCoverTypeId() == null) {
                            blocking = 1;
                            error.append(" Tpa Cover Type id in Vehicle Certificate Number " + certificateNo
                                    + " , cover code " + covers.getCoverCode() +
                                    " and cover name " + covers.getCoverDesc() +

                                    " is missing " + " *This message is blocking* \n \n");
                            carsErrorlogService.insertError(companyName + " Policy Upload policy " + Identifier
                                    + " Tpa Cover Type Code in Vehicle Certificate Number " + certificateNo
                                    + " is missing ", insuranceCode, "CARS_COVER", "Blocking");
                            // saveMessage(policyNo, "Tpa Cover Type Code", "Missing Field", "CARS_COVER",
                            // insuranceCode,
                            // certificateNo);
                        }


                        if (covers.getSubCovers() != null && !covers.getSubCovers().isEmpty()) {
                            for (SubCovers subCovers : covers.getSubCovers()) {

                                if (Utility.isEmpty(subCovers.getSubCoverCode())) {
                                    blocking = 1;
                                    error.append(" SubCover Code in Vehicle Certificate Number " + certificateNo
                                            + " , cover code " + covers.getCoverCode() +
                                            " and cover name " + covers.getCoverDesc() +

                                            " is missing " + " *This message is Blocking* \n \n");
                                    carsErrorlogService.insertError(companyName + " Policy Upload policy " + Identifier
                                            + " SubCover Code in Vehicle Certificate Number " + certificateNo
                                            + " is missing ", insuranceCode, "CARS_COVER", "Blocking");
                                    // saveMessage(policyNo, "Cover Code", "Blocking Field", "CARS_COVER",
                                    // insuranceCode, certificateNo);
                                    // throw new Exception("Error :");
                                }
                                if (subCovers.getSubCoverID() == 0) {
                                    blocking = 1;
                                    error.append(" SubCover ID in Vehicle Certificate Number " + certificateNo
                                            + " , cover code " + covers.getCoverCode()
                                            +
                                            " and cover name " + covers.getCoverDesc() +
                                            " is missing " + " *This message is Blocking* \n \n");
                                    carsErrorlogService.insertError(companyName + " Policy Upload policy " + Identifier
                                            + " SubCover ID in Vehicle Certificate Number " + certificateNo
                                            + " is missing ", insuranceCode, "CARS_COVER", "Blocking");
                                    // saveMessage(policyNo, "Cover Id", "Blocking Field", "CARS_COVER",
                                    // insuranceCode, certificateNo);
                                    // throw new Exception("Error :");
                                }


                                if (Utility.isEmpty(subCovers.getTpaSubCoverTypeCode())) {


                                    String validatePolicyCoverType = db.carsPolicyRepository.findConfigByKey(insuranceCode + ".validatePolicyCoverType");

                                    if (validatePolicyCoverType != null && validatePolicyCoverType.equals("false")) {
                                        Optional<CarsCover> carsCoverOptional = db.carsCoverRepository.findByCoverCodeAndCoverInsurance(covers.getCoverCode() + "." + subCovers.getSubCoverCode(), Integer.parseInt(insuranceCode));
                                        if (carsCoverOptional.isPresent()) {
                                            if (Utility.isEmpty(carsCoverOptional.get().getCoverType())) {
                                                error.append(" Tpa SubCover Type Code in Vehicle Certificate Number "
                                                        + certificateNo +
                                                        " , cover code " + covers.getCoverCode() +
                                                        " and cover name " + covers.getCoverDesc() +

                                                        " is missing " + " *This message is Informative* \n \n");
                                                carsErrorlogService.insertError(
                                                        companyName + " Policy Upload policy " + Identifier
                                                                + " Tpa SubCover Type Code in Vehicle Certificate Number "
                                                                + certificateNo + " is missing ",
                                                        insuranceCode, "CARS_COVER", "Informative Missing");
                                            }

                                        } else {
                                            error.append(" Tpa SubCover Type Code in Vehicle Certificate Number "
                                                    + certificateNo +
                                                    " , cover code " + covers.getCoverCode() +
                                                    " and cover name " + covers.getCoverDesc() +

                                                    " is missing " + " *This message is Informative* \n \n");
                                            carsErrorlogService.insertError(
                                                    companyName + " Policy Upload policy " + Identifier
                                                            + " Tpa SubCover Type Code in Vehicle Certificate Number "
                                                            + certificateNo + " is missing ",
                                                    insuranceCode, "CARS_COVER", "Informative Missing");
                                        }

                                    } else {
                                        error.append(" Tpa SubCover Type Code in Vehicle Certificate Number "
                                                + certificateNo +
                                                " , cover code " + covers.getCoverCode() +
                                                " and cover name " + covers.getCoverDesc() +

                                                " is missing " + " *This message is Informative* \n \n");
                                        carsErrorlogService.insertError(
                                                companyName + " Policy Upload policy " + Identifier
                                                        + " Tpa SubCover Type Code in Vehicle Certificate Number "
                                                        + certificateNo + " is missing ",
                                                insuranceCode, "CARS_COVER", "Informative Missing");

                                    }


                                    // saveMessage(policyNo, "Tpa Cover Type Code", "Missing Field", "CARS_COVER",
                                    // insuranceCode,
                                    // certificateNo);


                                }


                            }
                        }

                    }
                }

            }
        }
        if (!Utility.isEmpty(error.toString())) {

            SendingMail sendingMail = new SendingMail();
            try {
                sendingMail.run(companyName + " Policy Upload policy " + Identifier, error.toString());

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        if (!Utility.isEmpty(errorDeferred.toString())) {

            SendingMail2 sendingMail = new SendingMail2();
            try {
                sendingMail.run(companyName + " Policy Upload policy " + Identifier, errorDeferred.toString());

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        if (blocking == 1) {

            throw new Exception("Error:");
        }

    }


    public ResponseEntity<String> blackWhiteListing(PolicyListhing policyListhing) {

        String response = "";
        Optional<CarsDtParams> carsDtParamsOptional = db.carsDtParamsRepository.findByParamsIdAndInsuranceId(policyListhing.getProfileTypeId(), policyListhing.getInsuranceId());
        carsDtParamsOptional.ifPresent(carsDtParams -> policyListhing.setProfileTypeId(carsDtParams.getParamsCeCode()));


        ResponseEntity res = new ResponseEntity(response, HttpStatus.OK);
        if (policyListhing.getProfileTypeId().equals("BROKER")) {
            Optional<CarsBroker> brk = db.carsBrokerRepository.findByBrokerReferenceOrBrokerNumAndInsuranceId(
                    policyListhing.getInsuranceId(), policyListhing.getProfileId());
            if (brk.isPresent()) {


                Optional<CarsBlackList> carsBrokerblackList = db.carsBlackListRepository.findTopByBlBrokerIdOrderBySysUpdatedDateDesc(brk.get().getBrokerId());


                if (carsBrokerblackList.isPresent()) {

                    if ((policyListhing.getBlacklisted() && carsBrokerblackList.get().getBlStatus().equals("IN")) || (!policyListhing.getBlacklisted() && carsBrokerblackList.get().getBlStatus().equals("OU"))) {

                        carsBrokerblackList.get().setBlSetBy(policyListhing.getSetBy());
                        if (policyListhing.getBlacklisted()) {
                            carsBrokerblackList.get().setBlStatus("IN");
                        }

                        if (!policyListhing.getBlacklisted()) {
                            carsBrokerblackList.get().setBlStatus("OU");
                        }

                        carsBrokerblackList.get().setBlReason(policyListhing.getReason());
                        carsBrokerblackList.get().setBlNote(policyListhing.getNote());
                        if (!Utility.isEmpty(policyListhing.getSetOn())) {
                            try {

                                Date setOnn = new SimpleDateFormat("dd-MM-yyyy").parse(policyListhing.getSetOn());
                                carsBrokerblackList.get().setBlDate(new Timestamp(setOnn.getTime()));

                            } catch (ParseException e) {
                                e.printStackTrace();
                                response = "error in date format dd-MM-yyyy ";

                                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
                            }
                        }

                        carsBrokerblackList.get().setSysUpdatedBy(CREATED_BY_QUARTZ);
                        carsBrokerblackList.get().setSysUpdatedDate(new Timestamp(new Date().getTime()));
                        response = "Broker Black Listing Status Updated";
                        res = new ResponseEntity(response, HttpStatus.OK);
                    } else {


                        CarsBlackList carsBlackList = new CarsBlackList();
                        carsBlackList.setBlId(UUID.randomUUID().toString());
                        carsBlackList.setBlInsuranceId(carsBrokerblackList.get().getBlInsuranceId());
                        carsBlackList.setBlBrokerId(carsBrokerblackList.get().getBlBrokerId());
                        carsBlackList.setBlFamilyName(carsBrokerblackList.get().getBlFamilyName());


                        if (policyListhing.getBlacklisted()) {
                            carsBlackList.setBlStatus("IN");
                        }

                        if (!policyListhing.getBlacklisted()) {
                            carsBlackList.setBlStatus("OU");
                        }

                        carsBlackList.setBlReason(policyListhing.getReason());
                        if (!Utility.isEmpty(policyListhing.getSetOn())) {
                            try {
                                Date setOnn = new SimpleDateFormat("dd-MM-yyyy").parse(policyListhing.getSetOn());
                                carsBlackList.setBlDate(new Timestamp(setOnn.getTime()));

                            } catch (ParseException e) {
                                e.printStackTrace();

                                response = "error in date format dd-MM-yyyy ";

                                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
                            }
                        }

                        carsBlackList.setBlNote(policyListhing.getNote());

                        carsBlackList.setBlSetBy(policyListhing.getSetBy());
                        carsBlackList.setSysVersionNumber(0);
                        carsBlackList.setSysCreatedBy(CREATED_BY_QUARTZ);
                        carsBlackList.setSysUpdatedBy(CREATED_BY_QUARTZ);
                        carsBlackList.setSysCreatedDate(new Timestamp(new Date().getTime()));
                        carsBlackList.setSysUpdatedDate(new Timestamp(new Date().getTime()));
                        db.carsBlackListRepository.save(carsBlackList);


                        response = "Broker Black white  listed Added State";

                        res = new ResponseEntity(response, HttpStatus.CREATED);


                    }


                } else {


                    System.out.println("new Cars black list");
                    CarsBlackList carsBlackList = new CarsBlackList();
                    carsBlackList.setBlId(UUID.randomUUID().toString());
                    carsBlackList.setBlInsuranceId(policyListhing.getInsuranceId());
                    carsBlackList.setBlBrokerId(brk.get().getBrokerId());
                    carsBlackList.setBlFamilyName(brk.get().getBrokerDesc());


                    if (policyListhing.getBlacklisted()) {
                        carsBlackList.setBlStatus("IN");
                    }

                    if (!policyListhing.getBlacklisted()) {
                        carsBlackList.setBlStatus("OU");
                    }

                    carsBlackList.setBlReason(policyListhing.getReason());
                    if (!Utility.isEmpty(policyListhing.getSetOn())) {
                        try {
                            Date setOnn = new SimpleDateFormat("dd-MM-yyyy").parse(policyListhing.getSetOn());
                            carsBlackList.setBlDate(new Timestamp(setOnn.getTime()));

                        } catch (ParseException e) {
                            e.printStackTrace();

                            response = "error in date format dd-MM-yyyy ";

                            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
                        }
                    }

                    carsBlackList.setBlNote(policyListhing.getNote());

                    carsBlackList.setBlSetBy(policyListhing.getSetBy());
                    carsBlackList.setSysVersionNumber(0);
                    carsBlackList.setSysCreatedBy(CREATED_BY_QUARTZ);
                    carsBlackList.setSysUpdatedBy(CREATED_BY_QUARTZ);
                    carsBlackList.setSysCreatedDate(new Timestamp(new Date().getTime()));
                    carsBlackList.setSysUpdatedDate(new Timestamp(new Date().getTime()));
                    db.carsBlackListRepository.save(carsBlackList);


                    response = "Broker Black white  listed Added";

                    res = new ResponseEntity(response, HttpStatus.CREATED);


                }
            } else {
                response = "BROKER NOT FOUND";
                res = new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }
        } else if (policyListhing.getProfileTypeId().equals("INSURED")) {

            Optional<CarsClient> carsClient = db.carsClientRepository
                    .findByClientReferenceOrClientNum1AndInsuranceId(policyListhing.getInsuranceId(), policyListhing.getProfileId());
            if (carsClient.isPresent()) {


                Optional<CarsBlackList> carsBlackListOptional = db.carsBlackListRepository.findTopByClientNumAndBlInsuranceIdOrderBySysUpdatedDateDesc(carsClient.get().getClientNum1(), policyListhing.getInsuranceId());
                if (carsBlackListOptional.isPresent()) {

                    if ((policyListhing.getBlacklisted() && carsBlackListOptional.get().getBlStatus().equals("IN")) || (!policyListhing.getBlacklisted() && carsBlackListOptional.get().getBlStatus().equals("OU"))) {

                        carsBlackListOptional.get().setBlSetBy(policyListhing.getSetBy());
                        if (policyListhing.getBlacklisted()) {
                            carsBlackListOptional.get().setBlStatus("IN");
                        }

                        if (!policyListhing.getBlacklisted()) {
                            carsBlackListOptional.get().setBlStatus("OU");
                        }
                        carsBlackListOptional.get().setBlReason(policyListhing.getReason());
                        carsBlackListOptional.get().setBlNote(policyListhing.getNote());
                        carsBlackListOptional.get().setSysUpdatedBy(CREATED_BY_QUARTZ);
                        carsBlackListOptional.get().setSysUpdatedDate(new Timestamp(new Date().getTime()));
                        if (!Utility.isEmpty(policyListhing.getSetOn())) {
                            try {
                                Date setOnn = new SimpleDateFormat("dd-MM-yyyy").parse(policyListhing.getSetOn());
                                carsBlackListOptional.get().setBlDate(new Timestamp(setOnn.getTime()));

                            } catch (ParseException e) {

                                e.printStackTrace();
                                response = "error in date format dd-MM-yyyy ";

                                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
                            }
                        }
                        db.carsBlackListRepository.save(carsBlackListOptional.get());

                        response = "CLIENT Black Listing Status Updated";
                        res = new ResponseEntity(response, HttpStatus.OK);
                    } else {


                        CarsBlackList carsBlackList = new CarsBlackList();
                        carsBlackList.setBlId(UUID.randomUUID().toString());
                        carsBlackList.setBlInsuranceId(policyListhing.getInsuranceId());
                        carsBlackList.setClientNum(carsBlackListOptional.get().getClientNum());
                        carsBlackList.setBlFirstName(carsBlackListOptional.get().getBlFirstName());
                        carsBlackList.setBlFatherName(carsBlackListOptional.get().getBlFatherName());
                        carsBlackList.setBlFamilyName(carsBlackListOptional.get().getBlFamilyName());

                        carsBlackList.setBlCar("INS");

                        carsBlackList.setBlNote(policyListhing.getNote());
                        carsBlackList.setBlReason(policyListhing.getReason());


                        if (policyListhing.getBlacklisted()) {
                            carsBlackList.setBlStatus("IN");
                        }

                        if (!policyListhing.getBlacklisted()) {
                            carsBlackList.setBlStatus("OU");
                        }


                        if (!Utility.isEmpty(policyListhing.getSetOn())) {
                            try {
                                Date setOnn = new SimpleDateFormat("dd-MM-yyyy").parse(policyListhing.getSetOn());
                                carsBlackList.setBlDate(new Timestamp(setOnn.getTime()));

                            } catch (ParseException e) {

                                e.printStackTrace();
                                response = "error in date format dd-MM-yyyy ";

                                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
                            }
                        }


                        carsBlackList.setBlSetBy(policyListhing.getSetBy());

                        carsBlackList.setSysVersionNumber(0);
                        carsBlackList.setSysCreatedBy(CREATED_BY_QUARTZ);
                        carsBlackList.setSysUpdatedBy(CREATED_BY_QUARTZ);
                        carsBlackList.setSysCreatedDate(new Timestamp(new Date().getTime()));
                        carsBlackList.setSysUpdatedDate(new Timestamp(new Date().getTime()));


                        db.carsBlackListRepository.save(carsBlackList);

                        response = "CLIENT Black LisTING CREATED";
                        res = new ResponseEntity(response, HttpStatus.OK);

                    }
                } else {


                    CarsBlackList carsBlackList = new CarsBlackList();
                    carsBlackList.setBlId(UUID.randomUUID().toString());
                    carsBlackList.setBlInsuranceId(policyListhing.getInsuranceId());
                    carsBlackList.setClientNum(carsClient.get().getClientNum1());
                    carsBlackList.setBlFirstName(carsClient.get().getClientFirstName());
                    carsBlackList.setBlFatherName(carsClient.get().getClientFatherName());
                    carsBlackList.setBlFamilyName(carsClient.get().getClientFamilyName());

                    carsBlackList.setBlCar("INS");

                    carsBlackList.setBlNote(policyListhing.getNote());
                    carsBlackList.setBlReason(policyListhing.getReason());


                    if (policyListhing.getBlacklisted()) {
                        carsBlackList.setBlStatus("IN");
                    }

                    if (!policyListhing.getBlacklisted()) {
                        carsBlackList.setBlStatus("OU");
                    }

                    carsBlackList.setSysVersionNumber(0);
                    carsBlackList.setSysCreatedBy(CREATED_BY_QUARTZ);
                    carsBlackList.setSysUpdatedBy(CREATED_BY_QUARTZ);
                    carsBlackList.setSysCreatedDate(new Timestamp(new Date().getTime()));
                    carsBlackList.setSysUpdatedDate(new Timestamp(new Date().getTime()));


                    if (!Utility.isEmpty(policyListhing.getSetOn())) {
                        try {
                            Date setOnn = new SimpleDateFormat("dd-MM-yyyy").parse(policyListhing.getSetOn());
                            carsBlackList.setBlDate(new Timestamp(setOnn.getTime()));

                        } catch (ParseException e) {
                            e.printStackTrace();
                            response = "error in date format dd-MM-yyyy ";

                            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
                        }
                    }

                    carsBlackList.setBlSetBy(policyListhing.getSetBy());
                    db.carsBlackListRepository.save(carsBlackList);

                    response = "CLIENT Black LisTING CREATED";
                    res = new ResponseEntity(response, HttpStatus.OK);


                }

            } else {
                response = "CLIENT NOT FOUND";
                res = new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }
        } else if (policyListhing.getProfileTypeId().equals("TP")) {
            Optional<CarsClient> carsClient = db.carsClientRepository
                    .findByClientReferenceOrClientNum1AndInsuranceId(policyListhing.getInsuranceId(), policyListhing.getProfileId());
            if (carsClient.isPresent()) {


                Optional<CarsBlackList> carsBlackListOptional = db.carsBlackListRepository.findTopByBlFirstNameAndBlFatherNameAndBlFamilyNameOrderBySysUpdatedDateDesc(policyListhing.getProfileFirstName().toUpperCase(Locale.ROOT), policyListhing.getProfileFatherName().toUpperCase(Locale.ROOT), policyListhing.getProfileLastName().toUpperCase(Locale.ROOT));
                if (carsBlackListOptional.isPresent()) {
                    if ((policyListhing.getBlacklisted() && carsBlackListOptional.get().getBlStatus().equals("IN")) || (!policyListhing.getBlacklisted() && carsBlackListOptional.get().getBlStatus().equals("OU"))) {

                        carsBlackListOptional.get().setBlSetBy(policyListhing.getSetBy());
                        if (policyListhing.getBlacklisted()) {
                            carsBlackListOptional.get().setBlStatus("IN");
                        }

                        if (!policyListhing.getBlacklisted()) {
                            carsBlackListOptional.get().setBlStatus("OU");
                        }
                        carsBlackListOptional.get().setBlReason(policyListhing.getReason());
                        carsBlackListOptional.get().setBlNote(policyListhing.getNote());
                        carsBlackListOptional.get().setSysUpdatedBy(CREATED_BY_QUARTZ);
                        carsBlackListOptional.get().setSysUpdatedDate(new Timestamp(new Date().getTime()));
                        if (!Utility.isEmpty(policyListhing.getSetOn())) {

                            try {
                                Date setOnn = new SimpleDateFormat("dd-MM-yyyy").parse(policyListhing.getSetOn());
                                carsBlackListOptional.get().setBlDate(new Timestamp(setOnn.getTime()));

                            } catch (ParseException e) {
                                e.printStackTrace();
                                response = "error in date format dd-MM-yyyy ";

                                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
                            }
                        }
                        db.carsBlackListRepository.save(carsBlackListOptional.get());

                        response = "CLIENT Black Listing Status Updated";
                        res = new ResponseEntity(response, HttpStatus.OK);
                    } else {


                        CarsBlackList carsBlackList = new CarsBlackList();
                        carsBlackList.setBlId(UUID.randomUUID().toString());
                        carsBlackList.setBlInsuranceId(policyListhing.getInsuranceId());
                        carsBlackList.setClientNum(carsClient.get().getClientNum1());
                        carsBlackList.setBlFirstName(carsClient.get().getClientFirstName());
                        carsBlackList.setBlFatherName(carsClient.get().getClientFatherName());
                        carsBlackList.setBlFamilyName(carsClient.get().getClientFamilyName());
                        carsBlackList.setBlCar("TP");

                        carsBlackList.setBlNote(policyListhing.getNote());
                        carsBlackList.setBlReason(policyListhing.getReason());


                        if (policyListhing.getBlacklisted()) {
                            carsBlackList.setBlStatus("IN");
                        }

                        if (!policyListhing.getBlacklisted()) {
                            carsBlackList.setBlStatus("OU");
                        }

                        carsBlackList.setSysVersionNumber(0);
                        carsBlackList.setSysCreatedBy(CREATED_BY_QUARTZ);
                        carsBlackList.setSysUpdatedBy(CREATED_BY_QUARTZ);
                        carsBlackList.setSysCreatedDate(new Timestamp(new Date().getTime()));
                        carsBlackList.setSysUpdatedDate(new Timestamp(new Date().getTime()));


                        if (!Utility.isEmpty(policyListhing.getSetOn())) {
                            try {
                                Date setOnn = new SimpleDateFormat("dd-MM-yyyy").parse(policyListhing.getSetOn());
                                carsBlackList.setBlDate(new Timestamp(setOnn.getTime()));

                            } catch (ParseException e) {

                                e.printStackTrace();
                                response = "error in date format dd-MM-yyyy ";

                                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
                            }
                        }

                        carsBlackList.setBlSetBy(policyListhing.getSetBy());
                        db.carsBlackListRepository.save(carsBlackList);

                        response = "CLIENT Black LisTING CREATED change status";
                        res = new ResponseEntity(response, HttpStatus.OK);

                    }
                } else {


                    CarsBlackList carsBlackList = new CarsBlackList();
                    carsBlackList.setBlId(UUID.randomUUID().toString());
                    carsBlackList.setBlInsuranceId(policyListhing.getInsuranceId());
                    carsBlackList.setClientNum(carsClient.get().getClientNum1());
                    carsBlackList.setBlFirstName(carsBlackListOptional.get().getBlFirstName());
                    carsBlackList.setBlFatherName(carsBlackListOptional.get().getBlFatherName());
                    carsBlackList.setBlFamilyName(carsBlackListOptional.get().getBlFamilyName());

                    carsBlackList.setBlCar("TP");

                    carsBlackList.setBlNote(policyListhing.getNote());
                    carsBlackList.setBlReason(policyListhing.getReason());


                    if (policyListhing.getBlacklisted()) {
                        carsBlackList.setBlStatus("IN");
                    }

                    if (!policyListhing.getBlacklisted()) {
                        carsBlackList.setBlStatus("OU");
                    }

                    carsBlackList.setSysVersionNumber(0);
                    carsBlackList.setSysCreatedBy(CREATED_BY_QUARTZ);
                    carsBlackList.setSysUpdatedBy(CREATED_BY_QUARTZ);
                    carsBlackList.setSysCreatedDate(new Timestamp(new Date().getTime()));
                    carsBlackList.setSysUpdatedDate(new Timestamp(new Date().getTime()));


                    if (!Utility.isEmpty(policyListhing.getSetOn())) {
                        try {
                            Date setOnn = new SimpleDateFormat("dd-MM-yyyy").parse(policyListhing.getSetOn());
                            carsBlackList.setBlDate(new Timestamp(setOnn.getTime()));

                        } catch (ParseException e) {
                            e.printStackTrace();
                            response = "error in date format dd-MM-yyyy ";

                            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
                        }
                    }

                    carsBlackList.setBlSetBy(policyListhing.getSetBy());
                    db.carsBlackListRepository.save(carsBlackList);

                    response = "CLIENT Black LisTING CREATED  ";
                    res = new ResponseEntity(response, HttpStatus.OK);

                }
            } else {
                response = "CLIENT  TP NOT FOUND";
                res = new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }

        } else if (policyListhing.getProfileTypeId().equals("E") || policyListhing.getProfileTypeId().equals("G") || policyListhing.getProfileTypeId().equals("A") || policyListhing.getProfileTypeId().equals("L") || policyListhing.getProfileTypeId().equals("H")) {

            Optional<CarsDtSupplier> carsDtSupplierOptional = db.carsDtSupplierRepository.findByInsIdAndInsSupplierCode(policyListhing.getInsuranceId(), policyListhing.getProfileId());
            if (carsDtSupplierOptional.isPresent()) {

                List<CarsBlackList> carsBlackListOptional = db.carsBlackListRepository.findByBlSupplierIdAndBlInsuranceId(carsDtSupplierOptional.get().getCeCode(), policyListhing.getInsuranceId());
                System.out.println("supplier size is " + carsBlackListOptional.size());
                if (carsBlackListOptional.size() > 0) {
                    if ((policyListhing.getBlacklisted() && carsBlackListOptional.get(0).getBlStatus().equals("IN")) || (!policyListhing.getBlacklisted() && carsBlackListOptional.get(0).getBlStatus().equals("OU"))) {
                        carsBlackListOptional.get(0).setBlSetBy(policyListhing.getSetBy());
                        if (policyListhing.getBlacklisted()) {
                            carsBlackListOptional.get(0).setBlStatus("IN");
                        }

                        if (!policyListhing.getBlacklisted()) {
                            carsBlackListOptional.get(0).setBlStatus("OU");
                        }
                        carsBlackListOptional.get(0).setBlReason(policyListhing.getReason());
                        carsBlackListOptional.get(0).setBlNote(policyListhing.getNote());
                        if (!Utility.isEmpty(policyListhing.getSetOn())) {

                            try {
                                Date setOnn = new SimpleDateFormat("dd-MM-yyyy").parse(policyListhing.getSetOn());
                                carsBlackListOptional.get(0).setBlDate(new Timestamp(setOnn.getTime()));

                            } catch (ParseException e) {
                                e.printStackTrace();
                                response = "error in date format dd-MM-yyyy ";

                                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
                            }
                        }
                        carsBlackListOptional.get(0).setSysUpdatedBy(CREATED_BY_QUARTZ);
                        carsBlackListOptional.get(0).setSysUpdatedDate(new Timestamp(new Date().getTime()));
                        db.carsBlackListRepository.save(carsBlackListOptional.get(0));
                        response = "supplier black white listed  updated";
                        res = new ResponseEntity(response, HttpStatus.OK);
                    } else {


                        CarsBlackList carsBlackList = new CarsBlackList();
                        carsBlackList.setBlId(UUID.randomUUID().toString());
                        carsBlackList.setBlInsuranceId(policyListhing.getInsuranceId());
                        //  carsBlackList.setClientNum(carsBlackListOptional.get(0).getClientNum());
                        carsBlackList.setBlFirstName(carsBlackListOptional.get(0).getBlFirstName());
                        carsBlackList.setBlFatherName(carsBlackListOptional.get(0).getBlFatherName());
                        carsBlackList.setBlFamilyName(carsBlackListOptional.get(0).getBlFamilyName());
                        Optional<CarsSupplier> carsSupplierOptional = db.carsSupplierRepository.findById(carsDtSupplierOptional.get().getCeCode());
                        carsSupplierOptional.ifPresent(carsBlackList::setBlSupplier);

                        carsBlackList.setBlNote(policyListhing.getNote());
                        carsBlackList.setBlReason(policyListhing.getReason());


                        if (policyListhing.getBlacklisted()) {
                            carsBlackList.setBlStatus("IN");
                        }

                        if (!policyListhing.getBlacklisted()) {
                            carsBlackList.setBlStatus("OU");
                        }

                        carsBlackList.setSysVersionNumber(0);
                        carsBlackList.setSysCreatedBy(CREATED_BY_QUARTZ);
                        carsBlackList.setSysUpdatedBy(CREATED_BY_QUARTZ);
                        carsBlackList.setSysCreatedDate(new Timestamp(new Date().getTime()));
                        carsBlackList.setSysUpdatedDate(new Timestamp(new Date().getTime()));


                        if (!Utility.isEmpty(policyListhing.getSetOn())) {
                            try {
                                Date setOnn = new SimpleDateFormat("dd-MM-yyyy").parse(policyListhing.getSetOn());
                                carsBlackList.setBlDate(new Timestamp(setOnn.getTime()));

                            } catch (ParseException e) {
                                e.printStackTrace();
                                response = "error in date format dd-MM-yyyy ";

                                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
                            }
                        }

                        carsBlackList.setBlSetBy(policyListhing.getSetBy());
                        db.carsBlackListRepository.save(carsBlackList);


                        response = "supplier black white listed  added new status";
                        res = new ResponseEntity(response, HttpStatus.OK);


                    }


                } else {

                    Optional<CarsSupplier> carsSupplierOptional = db.carsSupplierRepository.findById(carsDtSupplierOptional.get().getCeCode());


                    if (carsSupplierOptional.isPresent()) {


                        CarsBlackList carsBlackList = new CarsBlackList();
                        carsBlackList.setBlId(UUID.randomUUID().toString());
                        carsBlackList.setBlInsuranceId(policyListhing.getInsuranceId());
                        carsBlackList.setBlFirstName(carsSupplierOptional.get().getSupplierFirstName());
                        carsBlackList.setBlFatherName(carsSupplierOptional.get().getSupplierFatherName());
                        carsBlackList.setBlFamilyName(carsSupplierOptional.get().getSupplierFamilyName());
                        carsBlackList.setBlSupplier(carsSupplierOptional.get());


                        carsBlackList.setBlNote(policyListhing.getNote());
                        carsBlackList.setBlReason(policyListhing.getReason());


                        if (policyListhing.getBlacklisted()) {
                            carsBlackList.setBlStatus("IN");
                        }

                        if (!policyListhing.getBlacklisted()) {
                            carsBlackList.setBlStatus("OU");
                        }

                        carsBlackList.setSysVersionNumber(0);
                        carsBlackList.setSysCreatedBy(CREATED_BY_QUARTZ);
                        carsBlackList.setSysUpdatedBy(CREATED_BY_QUARTZ);
                        carsBlackList.setSysCreatedDate(new Timestamp(new Date().getTime()));
                        carsBlackList.setSysUpdatedDate(new Timestamp(new Date().getTime()));


                        if (!Utility.isEmpty(policyListhing.getSetOn())) {
                            try {
                                Date setOnn = new SimpleDateFormat("dd-MM-yyyy").parse(policyListhing.getSetOn());
                                carsBlackList.setBlDate(new Timestamp(setOnn.getTime()));

                            } catch (ParseException e) {
                                e.printStackTrace();
                                response = "error in date format dd-MM-yyyy ";

                                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
                            }
                        }

                        carsBlackList.setBlSetBy(policyListhing.getSetBy());
                        db.carsBlackListRepository.save(carsBlackList);


                        response = "supplier black white listed  added";
                        res = new ResponseEntity(response, HttpStatus.OK);
                    }
                }


            } else {
                response = "INS CODE NOT FOUND";
                res = new ResponseEntity(response, HttpStatus.NOT_FOUND);

            }

        }


        return res;


    }

    @Transactional(rollbackFor = Exception.class)
    public void savePolicyJson(Policies policies) throws Exception {
        CarsPolicyJson carsPolicyJson = new CarsPolicyJson();
        carsPolicyJson.setSysCreatedDate(LocalDateTime.now());
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(policies);
        carsPolicyJson.setData(json);
        carsPolicyJson.setSubject(policies.getPolicies().get(0).getPolicyNo());

        carsPolicyJsonRepository.save(carsPolicyJson);

    }

    public void getJsonPolyFromTable() throws Exception {
        if (carsPolicyJsonRepository.getPoliciesCount() > 0) {
            CarsPolicyJson carsPolicyJson = getPolicyJson();
            Policies policies = objectMapper.readValue(carsPolicyJson.getData(),
                    Policies.class);
            try {
                policyUpload(policies);
                carsPolicyJson.setStatus("ELABORATED");

            } catch (Exception exception) {
                carsPolicyJson.setStatus("ERROR");

            }

            carsPolicyJsonRepository.save(carsPolicyJson);
            getJsonPolyFromTable();

        }


    }

    public CarsPolicyJson getPolicyJson() {
        return carsPolicyJsonRepository.getPolicyJson().orElse(null);

    }
}