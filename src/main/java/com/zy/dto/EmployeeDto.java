package com.zy.dto;

import com.zy.domain.Employee;
import lombok.Data;

@Data
public class EmployeeDto extends Employee {
    private String deptName; //科室名称
}
