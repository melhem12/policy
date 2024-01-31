package com.policy.view;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "v_address", schema = "portal")
@Data
public class AddressView implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "town_id", nullable = false)
    private String townId;

    @Column(name = "region_id", nullable = false)
    private String regionId;

    @Column(name = "region_code")
    private String regionCode;

    @Column(name = "region_name")
    private String regionName;

    @Column(name = "caza_id", nullable = false)
    private String cazaId;

    @Column(name = "caza_code")
    private String cazaCode;

    @Column(name = "caza_name")
    private String cazaName;

    @Column(name = "town_code")
    private String townCode;

    @Column(name = "town_name")
    private String townName;

    @Column(name = "TOWN_GOOGLE_NAME")
    private String townGoogleName;

    @Column(name = "TOWN_TERRITORY")
    private String townTerritory;

    @Column(name = "TOWN_LONGITUDE")
    private BigDecimal townLongitude;

    @Column(name = "TOWN_LATITUDE")
    private BigDecimal townLatitude;
}
