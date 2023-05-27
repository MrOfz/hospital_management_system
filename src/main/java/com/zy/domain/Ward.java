package com.zy.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Ward implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private String bedCount; //床位数

    private String wardNumber; //病房号

    private Long deptId; //科室表Id

    private Integer status;
}
