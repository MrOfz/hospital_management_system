package com.zy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.domain.Patient;

public interface PatientService extends IService<Patient> {
    Boolean modifyById(Patient patient);
}
