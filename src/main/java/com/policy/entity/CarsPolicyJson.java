package com.policy.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "CARS_POLICY_JSON")
@Data
public class CarsPolicyJson implements Serializable {


    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy_json_seq")
    @SequenceGenerator(name = "policy_json_seq", sequenceName = "policy_json_seq", allocationSize = 1)
    @Column(name = "POLICY_JSON_ID")
    private Long id;

    @Column(name = "POLICY_JSON_DATA")
    private String data;

    @Column(name = "POLICY_JSON_STATUS")
    private String status;


    @Column(name = "POLICY_JSON_SUBJECT")
    private String subject;

    @Column(name = "SYS_CREATED_DATE")
    private LocalDateTime sysCreatedDate;
}
