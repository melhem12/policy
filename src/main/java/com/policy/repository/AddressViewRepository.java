package com.policy.repository;

import com.policy.view.AddressView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressViewRepository extends JpaRepository<AddressView,String> {
    List<AddressView> findByTownNameAndCazaNameAndRegionNameIgnoreCase(String townName, String cazaName, String regionName);
;
@Query(value = "    SELECT * " +
        "    FROM v_address " +
        "    WHERE SOUNDEX(town_name) = SOUNDEX(?1)  AND SOUNDEX(caza_name) = SOUNDEX(?2) " +
        "AND  SOUNDEX(region_name) = SOUNDEX(?3) ",nativeQuery = true)
    List<AddressView> findByTownNameAndCazaNameAndRegionName(String townName, String cazaName, String regionName);
}
