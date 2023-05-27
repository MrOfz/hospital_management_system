package com.zy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.dao.WardMapper;
import com.zy.domain.Ward;
import com.zy.service.WardService;
import org.springframework.stereotype.Service;

@Service
public class WardServiceImpl extends ServiceImpl<WardMapper, Ward> implements WardService {

}
