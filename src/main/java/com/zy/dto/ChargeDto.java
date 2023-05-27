package com.zy.dto;

import com.zy.domain.Charge;
import lombok.Data;

@Data
public class ChargeDto extends Charge {
    private String patientName; //病人姓名

    private String wardName; // 病房名称
}
