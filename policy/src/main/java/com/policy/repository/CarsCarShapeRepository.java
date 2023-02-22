package com.policy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.policy.entity.CarsCarShape;
@Repository
@EnableJpaRepositories
public interface CarsCarShapeRepository extends JpaRepository<CarsCarShape,String> {

}
