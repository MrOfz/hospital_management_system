package com.zy.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Drug {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String role;

    private Integer remainingCapacity;

    private Integer status;

    private BigDecimal price;

    private String image;

    private LocalDateTime shelfTime;
    private String description;
}
