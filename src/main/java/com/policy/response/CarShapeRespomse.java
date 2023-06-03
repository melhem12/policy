package com.policy.response;

import org.springframework.beans.factory.annotation.Value;

public interface CarShapeRespomse {
    @Value("#{target.BRAND_CODE}")
      String getBrandCode();

    @Value("#{target.BRAND_DESCRIPTION}")
    String getBrandDesc();

    @Value("#{target.TRADEMARK_CODE}")
    String getTrademarkCode();

    @Value("#{target.TRADEMARK_DESCRIPTION}")
    String getTrademarkDesc();

    @Value("#{target.SHAPE_CODE}")
    String getShapeCode();

    @Value("#{target.SHAPE_DESCRIPTION}")
    String getShapeDesc();

    @Value("#{target.DESCRIPTION}")
    String getDesc();




}
