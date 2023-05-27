package com.zy.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Charge implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;

    private Long wardId;

    private Long patientId;

    private String chargeItem;

    private BigDecimal price;

    private int count;

    private BigDecimal totalMoney;

    private LocalDateTime chargingTime;
}
