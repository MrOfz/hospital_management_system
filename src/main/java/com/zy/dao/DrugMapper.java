package com.zy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.domain.Drug;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DrugMapper extends BaseMapper<Drug> {
}
