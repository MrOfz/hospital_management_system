package com.zy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.domain.Charge;
import com.zy.dto.ChargeDto;

public interface ChargeService extends IService<Charge> {
    ChargeDto selectById(Long id);
}
