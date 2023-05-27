package com.zy.dto;

import com.zy.domain.Patient;
import lombok.Data;


@Data
public class PatientDto extends Patient {
    private String employeeName; //医生的姓名

    private String wardName; //病房号
}
