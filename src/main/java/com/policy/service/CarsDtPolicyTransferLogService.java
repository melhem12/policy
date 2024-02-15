package com.policy.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.policy.entity.CarsDtPolicyTransferLog;
import com.policy.entity.CarsErrorlog;
import com.policy.repository.DB;

@Service
public class CarsDtPolicyTransferLogService {

    public static String CREATED_BY_QUARTZ = "Transfer";
    public static int i = 0;
    public static String insuranceCode = "11";
    @Autowired
    public DB db;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertTransferLog(String policyStatus, String insuranceId, String BatchNumber, String policyId, String policyNumber) {

        CarsDtPolicyTransferLog log = new CarsDtPolicyTransferLog();

        log.setDtId(UUID.randomUUID().toString());
        log.setScriptName(BatchNumber);
        log.setInsId(insuranceId);
        log.setPolPolicyId(policyId);
        log.setPolPolicyNum(policyNumber);
        log.setPolTransferStatus(policyStatus);
        log.setSysVersionNumber(0);
        log.setSysCreatedBy(CREATED_BY_QUARTZ);
        log.setSysUpdatedBy(CREATED_BY_QUARTZ);
        log.setSysCreatedDate(new Timestamp(new Date().getTime()));
        log.setSysUpdatedDate(new Timestamp(new Date().getTime()));
        db.carsDtPolicyTransferLogRepository.save(log);
    }

}
