package com.zy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.dao.DrugMapper;
import com.zy.domain.Drug;
import com.zy.service.DrugService;
import org.springframework.stereotype.Service;

@Service
public class DrugServiceImpl extends ServiceImpl<DrugMapper, Drug> implements DrugService {

}
