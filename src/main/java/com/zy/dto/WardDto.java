package com.zy.dto;

import com.zy.domain.Ward;
import lombok.Data;

@Data
public class WardDto extends Ward {
    private String deptName;//科室名称

    private String deptHead;//科室负责人
}
