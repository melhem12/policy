package com.policy.service;

import com.policy.LOV.SupplierIntermTypeLov;
import com.policy.bean.AddProfileRequest;
import com.policy.config.Utility;
import com.policy.entity.CarsDtSupplier;
import com.policy.entity.CarsSupplier;
import com.policy.error.DuplicateResultException;
import com.policy.error.NotFoundException;
import com.policy.repository.AddressViewRepository;
import com.policy.repository.CarsSupplierRepository;
import com.policy.repository.DB;
import com.policy.repository.SupplierIntermRepository;
import com.policy.view.AddressView;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class ProfileService {
    @Autowired
    DB db;


    @Transactional(rollbackFor = {Exception.class})
    public void addProfile(AddProfileRequest addProfileRequest, String username) {


        CarsSupplier carsSupplier = new CarsSupplier();

        if (addProfileRequest == null) {
            throw new NotFoundException("Empty object");
        }
        if (Utility.isEmpty(addProfileRequest.getSupplierId())) {

            throw new NotFoundException("NO SUPPLIER   ID :" + addProfileRequest.getSupplierId());

        }

        Optional<SupplierIntermTypeLov> interm_lov = db.supplierIntermRepository.findById(addProfileRequest.getSupplierType());
        if (!interm_lov.isPresent()) {
            throw new NotFoundException("NO INTERM of ID :" + addProfileRequest.getSupplierType());
        }
        Optional<CarsSupplier> carsSupplierOptional = db.carsSupplierRepository.findSupplierByInsCodeAndInsSupplierCode("10", addProfileRequest.getSupplierId());
        if (carsSupplierOptional.isPresent()) {
            throw new DuplicateResultException("supplier is already exist :" + carsSupplier.getSupplierId());

        }


        System.out.println("supplier_req.getInterm()   " + addProfileRequest.getSupplierType());
        Long highestNumber = db.carsSupplierRepository.findLatestSupplierNumberByInterm(addProfileRequest.getSupplierType());
        String proposed_id = addProfileRequest.getSupplierType() + "." +
                (highestNumber + 1);
        boolean exist = db.carsSupplierRepository.existsById(proposed_id);
        if (exist) {
            throw new DuplicateResultException("Primary key already exist: ");
        }
        ;
        carsSupplier.setSupplierId(proposed_id);
        carsSupplier.setSupplierNum(highestNumber.intValue() + 1);


        carsSupplier.setSupplierInterm(interm_lov.get().getCode());


        String firstName = Utility.isEmpty(addProfileRequest.getSupplierFirstName()) ? "" : addProfileRequest.getSupplierFirstName() + " ";
        String fatherName = Utility.isEmpty(addProfileRequest.getSupplierFatherName()) ? "" : addProfileRequest.getSupplierFatherName() + " ";
        String familyName = Utility.isEmpty(addProfileRequest.getSupplierFamilyName()) ? "" : addProfileRequest.getSupplierFamilyName() + " ";
        String prefixFam = Utility.isEmpty(addProfileRequest.getSupplierPrefixFamily()) ? "" : addProfileRequest.getSupplierPrefixFamily() + " ";


        carsSupplier.setSupplierPrefixFam(prefixFam);
        carsSupplier.setSupplierFirstName(firstName);
        carsSupplier.setSupplierFatherName(fatherName);
        carsSupplier.setSupplierFamilyName(familyName);
        carsSupplier.setSupplierName(firstName + " " +
                fatherName + " " +
                prefixFam + " " +
                familyName);

        //
        List<AddressView> addressViewList = db.addressViewRepository.findByTownNameAndCazaNameAndRegionName(addProfileRequest.getSupplierBusinessTownName(), addProfileRequest.getSupplierBusinessCazaName(), addProfileRequest.getSupplierBusinessMouhafazaName());
        carsSupplier.setSupplierBusStreet(addProfileRequest.getSupplierBusinessMouhafazaName() + " " + addProfileRequest.getSupplierBusinessCazaName() + addProfileRequest.getSupplierBusinessTownName());

        if (!addressViewList.isEmpty()) {
            AddressView addressView = addressViewList.get(0);
            carsSupplier.setSupplierBusDistrict(addressView.getRegionCode());
            carsSupplier.setSupplierBusSector(addressView.getCazaCode());
            carsSupplier.setSupplierBusCity(addressView.getTownCode());
        }


        carsSupplier.setSupplierHomePhone(addProfileRequest.getSupplierLandPhone());

        carsSupplier.setSupplierMobilePhone(addProfileRequest.getSupplierMobilePhone());

        carsSupplier.setSupplierOutNetwork(addProfileRequest.getSupplierNetwork());

        carsSupplier.setSysCreatedBy(username);
        carsSupplier.setSysCreatedDate(LocalDateTime.now());
        carsSupplier.setSysUpdatedBy(username);
        carsSupplier.setSysUpdatedDate(LocalDateTime.now());
        carsSupplier.setSysVersionNumber(1L);

        CarsSupplier saved = db.carsSupplierRepository.save(carsSupplier);

        if (!Utility.isEmpty(addProfileRequest.getSupplierId())) {
            CarsDtSupplier carsDtSupplier = new CarsDtSupplier();
            carsDtSupplier.setInsSupplierCode(addProfileRequest.getSupplierId());
            carsDtSupplier.setInsId("10");
            carsDtSupplier.setSupplierType(addProfileRequest.getSupplierType());
            carsDtSupplier.setCeCode(saved.getSupplierId());
            carsDtSupplier.setDtId("10" + "." + saved.getSupplierInterm() +"." +addProfileRequest.getSupplierId());
            carsDtSupplier.setSysCreatedBy(username);
            carsDtSupplier.setSysCreatedDate(LocalDateTime.now());
            carsDtSupplier.setSysUpdatedBy(username);
            carsDtSupplier.setSysUpdatedDate(LocalDateTime.now());
            carsDtSupplier.setSysVersionNumber(1L);
            carsDtSupplier.setInsSupplierDesc(saved.getSupplierName());
            db.carsDtSupplierRepository.save(carsDtSupplier);
        }


    }


    @Transactional(rollbackFor = {Exception.class})
    public void updateProfile(AddProfileRequest addProfileRequest, String username) {


        if (addProfileRequest == null) {
            throw new NotFoundException("Empty object");
        }


        if (Utility.isEmpty(addProfileRequest.getSupplierId())) {

            throw new NotFoundException("NO SUPPLIER   ID :" + addProfileRequest.getSupplierId());

        }


        Optional<SupplierIntermTypeLov> interm_lov = db.supplierIntermRepository.findById(addProfileRequest.getSupplierType());
        if (!interm_lov.isPresent()) {
            throw new NotFoundException("NO INTERM of ID :" + addProfileRequest.getSupplierType());
        }


        Optional<CarsSupplier> carsSupplierOptional = db.carsSupplierRepository.findSupplierByInsCodeAndInsSupplierCode("10", addProfileRequest.getSupplierId());


        if (!carsSupplierOptional.isPresent()) {

        } else {
            CarsSupplier carsSupplier = carsSupplierOptional.get();
            carsSupplier.setSupplierInterm(interm_lov.get().getCode());


            String firstName = Utility.isEmpty(addProfileRequest.getSupplierFirstName()) ? "" : addProfileRequest.getSupplierFirstName() + " ";
            String fatherName = Utility.isEmpty(addProfileRequest.getSupplierFatherName()) ? "" : addProfileRequest.getSupplierFatherName() + " ";
            String familyName = Utility.isEmpty(addProfileRequest.getSupplierFamilyName()) ? "" : addProfileRequest.getSupplierFamilyName() + " ";
            String prefixFam = Utility.isEmpty(addProfileRequest.getSupplierPrefixFamily()) ? "" : addProfileRequest.getSupplierPrefixFamily() + " ";


            carsSupplier.setSupplierPrefixFam(prefixFam);
            carsSupplier.setSupplierFirstName(firstName);
            carsSupplier.setSupplierFatherName(fatherName);
            carsSupplier.setSupplierFamilyName(familyName);
            carsSupplier.setSupplierName(firstName + " " +
                    fatherName + " " +
                    prefixFam + " " +
                    familyName);

            //
            List<AddressView> addressViewList = db.addressViewRepository.findByTownNameAndCazaNameAndRegionName(addProfileRequest.getSupplierBusinessTownName(), addProfileRequest.getSupplierBusinessCazaName(), addProfileRequest.getSupplierBusinessMouhafazaName());
            carsSupplier.setSupplierBusStreet(addProfileRequest.getSupplierBusinessMouhafazaName() + " " + addProfileRequest.getSupplierBusinessCazaName() + addProfileRequest.getSupplierBusinessTownName());

            if (!addressViewList.isEmpty()) {
                AddressView addressView = addressViewList.get(0);
                carsSupplier.setSupplierBusDistrict(addressView.getRegionCode());
                carsSupplier.setSupplierBusSector(addressView.getCazaCode());
                carsSupplier.setSupplierBusCity(addressView.getTownCode());
            }


            carsSupplier.setSupplierHomePhone(addProfileRequest.getSupplierLandPhone());

            carsSupplier.setSupplierMobilePhone(addProfileRequest.getSupplierMobilePhone());

            carsSupplier.setSupplierOutNetwork(addProfileRequest.getSupplierNetwork());
            carsSupplier.setSysUpdatedBy(username);
            carsSupplier.setSysUpdatedDate(LocalDateTime.now());
            carsSupplier.setSysVersionNumber(1L);

            CarsSupplier saved = db.carsSupplierRepository.save(carsSupplier);

            Optional<CarsDtSupplier> carsDtSupplierOptional = db.carsDtSupplierRepository.findByInsIdAndInsSupplierCode("10", addProfileRequest.getSupplierId());
            if (carsDtSupplierOptional.isPresent()) {
                CarsDtSupplier carsDtSupplier = carsDtSupplierOptional.get();
                carsDtSupplier.setInsSupplierDesc(saved.getSupplierName());

                carsDtSupplier.setSysUpdatedBy(username);
                carsDtSupplier.setSysUpdatedDate(LocalDateTime.now());
                carsDtSupplier.setSysVersionNumber(1L);
                db.carsDtSupplierRepository.save(carsDtSupplier);
            }


        }


    }


}
