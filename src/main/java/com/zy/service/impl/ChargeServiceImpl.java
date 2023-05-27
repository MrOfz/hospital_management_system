package com.zy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.dao.ChargeMapper;
import com.zy.domain.Charge;
import com.zy.domain.Patient;
import com.zy.dto.ChargeDto;
import com.zy.service.ChargeService;
import com.zy.service.PatientService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChargeServiceImpl extends ServiceImpl<ChargeMapper, Charge> implements ChargeService {
    @Autowired
    private PatientService patientService;
    @Override
    public ChargeDto selectById(Long id) {
        Charge charge = this.getById(id);
        ChargeDto chargeDto = new ChargeDto();

        BeanUtils.copyProperties(charge,chargeDto);

        Patient patient = patientService.getById(charge.getPatientId());
        chargeDto.setPatientName(patient.getName());
        return chargeDto;
    }
}
