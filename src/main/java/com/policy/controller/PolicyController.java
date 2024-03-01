package com.policy.controller;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.policy.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.policy.repository.DB;
import com.policy.service.PolicyService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/auth/constant")
@Service
@ComponentScan("com.policy")
public class PolicyController {

    private static Logger logger = LoggerFactory.getLogger(PolicyController.class);


    @Autowired
    public DB db;


    @Autowired
    public PolicyService policyService;
    public static String CREATED_BY_QUARTZ = "Test03";
    public static int i = 0;
    public static String insuranceCode = "10";


//    @PostMapping("/uploadPolicy")
//    @Async
//    public ResponseEntity<Policies> policyUpload(@Validated @RequestBody Policies policies) throws Exception {
//        try {
//            logger.info("Inside Validation");
////            policyService.policyUpload(policies);
////            return new ResponseEntity(HttpStatus.CREATED);
//       return policyService.policyUpload(policies);
//
//
//        } catch (
//
//                Exception e) {
//            e.printStackTrace();
//            StringWriter sw = new StringWriter();
//            PrintWriter pw = new PrintWriter(sw);
//            e.printStackTrace(pw);
//            String sStackTrace = sw.toString();
//            logger.error("failed", sStackTrace);
//            return new ResponseEntity(sStackTrace, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }


    @PostMapping("/uploadPolicy")
    public ResponseEntity<Policies> policyUpload(@Validated @RequestBody Policies policies) throws Exception {
        try {
            logger.info("Inside Validation");
            policyService.savePolicyJson(policies);
            return new ResponseEntity<>(policies, HttpStatus.CREATED);
//            return policyService.policyUpload(policies);


        } catch (

                Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String sStackTrace = sw.toString();
            logger.error("failed", sStackTrace);
            return new ResponseEntity(sStackTrace, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/deletePolicy")
    public ResponseEntity<String> deletePolicy(@RequestBody PolicyToDelete policyToDelete) throws Exception {


        try {
            logger.info("Inside Validation");


            return policyService.deletePolicyFunction(policyToDelete.getInsuranceId(), policyToDelete.getPolicyId(), policyToDelete.getBranchId()
                    , policyToDelete.getPolicyNumber(), policyToDelete.getVehicleId(), policyToDelete.getAmendment(), policyToDelete.getCertificate());

        } catch (

                Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String sStackTrace = sw.toString();
            logger.error("failed", sStackTrace);
            return new ResponseEntity(sStackTrace, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/blackWhiteListing")
    public ResponseEntity<String> blackWhiteListing(@RequestBody PolicyListhing policyListhing) throws Exception {


        try {
            logger.info("Inside Validation");
            return policyService.blackWhiteListing(policyListhing);

        } catch (

                Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String sStackTrace = sw.toString();
            logger.error("failed", sStackTrace);
            return new ResponseEntity(sStackTrace, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
