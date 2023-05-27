package com.zy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.dao.PatientMapper;
import com.zy.domain.Department;
import com.zy.domain.Employee;
import com.zy.domain.Patient;
import com.zy.service.DepartmentService;
import com.zy.service.EmployeeService;
import com.zy.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient> implements PatientService {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;


    @Override
    @Transactional
    public Boolean modifyById(Patient patient) {
        Patient oldPat = this.getById(patient);
        System.out.println("++++++++++++++++++++++++++"+oldPat.toString());

        //获取更改主治医生前的科室信息
        LambdaQueryWrapper<Employee> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Employee::getId,oldPat.getEmployeeId());

        Employee employee1 = employeeService.getOne(queryWrapper1);

        LambdaQueryWrapper<Department> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(Department::getId,employee1.getDeptId());

        Department department1 = departmentService.getOne(queryWrapper2);

        //获取更改主治医生后的科室信息
        LambdaQueryWrapper<Employee> queryWrapper3 = new LambdaQueryWrapper<>();
        queryWrapper3.eq(Employee::getId,patient.getEmployeeId());

        Employee employee2 = employeeService.getOne(queryWrapper3);

        LambdaQueryWrapper<Department> queryWrapper4 = new LambdaQueryWrapper<>();
        queryWrapper4.eq(Department::getId,employee2.getDeptId());

        Department department2 = departmentService.getOne(queryWrapper4);

        if (patient.getEmployeeId() != oldPat.getEmployeeId()){
            if ("无".equals(oldPat.getTransferRecords())){
                patient.setTransferRecords(department1.getName()+ "=====>" + department2.getName());
                this.updateById(patient);
            }else {
                patient.setTransferRecords(oldPat.getTransferRecords()+ "=====>" + department2.getName());
                this.updateById(patient);
            }
        }else {
            this.updateById(patient);
        }

        return true;
    }
}
