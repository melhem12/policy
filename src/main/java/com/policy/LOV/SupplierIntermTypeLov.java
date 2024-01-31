package com.policy.LOV;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "supplier_interm_vw")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierIntermTypeLov  implements Serializable {


    @Id
    @Column(name = "code")
    private String code;
    @Column(name = "description")
    private String description;
}
