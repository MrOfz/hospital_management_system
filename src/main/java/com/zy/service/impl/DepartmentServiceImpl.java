package com.zy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.common.CustomException;
import com.zy.dao.DepartmentMapper;
import com.zy.domain.Department;
import com.zy.domain.Employee;
import com.zy.domain.Ward;
import com.zy.service.DepartmentService;
import com.zy.service.EmployeeService;
import com.zy.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {
    @Autowired
    private WardService wardService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Ward> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Ward::getDeptId,id);
        int count = wardService.count(queryWrapper);

        if(count>0){
            throw new CustomException("该科室关联了其他信息，无法删除");
        }

        LambdaQueryWrapper<Employee> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Employee::getDeptId,id);
        int count1 = employeeService.count(queryWrapper1);

        if (count1>0){
            throw new CustomException("该科室关联了其他信息，无法删除");
        }

        departmentMapper.deleteById(id);
    }
}
