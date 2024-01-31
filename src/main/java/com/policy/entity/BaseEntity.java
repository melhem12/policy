package com.policy.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@NoArgsConstructor
public class BaseEntity {


    @Column(name="SYS_CREATED_BY")
    private String sysCreatedBy;

    @Column(name="SYS_CREATED_DATE")
    private LocalDateTime sysCreatedDate;

    @Column(name="SYS_UPDATED_BY")
    private String sysUpdatedBy;

    @Column(name="SYS_UPDATED_DATE")
    private LocalDateTime sysUpdatedDate;

    @Column(name="SYS_VERSION_NUMBER")
    private Long sysVersionNumber;


}
