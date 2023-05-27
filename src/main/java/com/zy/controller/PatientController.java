package com.zy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.common.R;
import com.zy.domain.Employee;
import com.zy.domain.Patient;
import com.zy.domain.Ward;
import com.zy.dto.PatientDto;
import com.zy.service.EmployeeService;
import com.zy.service.PatientService;
import com.zy.service.WardService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private WardService wardService;


    /**
     * 增加病人信息
     * @param patientDto
     * @return
     */
    @PostMapping()
    private R<String> save(@RequestBody PatientDto patientDto){
        log.info("增加病人信息:{}",patientDto.toString());
        patientService.save(patientDto);
        return R.success("病人信息添加成功！");
    }

    /**
     * 删除病人信息
     * @param id
     * @return
     */
    @DeleteMapping()
    public R<String> delete(Long id){
        patientService.removeById(id);
        return R.success("病人信息删除成功");
    }

    /**
     * 病人信息分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        log.info("page={},pageSize={},name={}",page,pageSize,name);

        //第一步：
        //构造分页构造器
        Page<Patient> pageInfo = new Page<>(page,pageSize);
        Page<PatientDto> patientDtoPageInfo = new Page<>();

        //构造条件查询器
        LambdaQueryWrapper<Patient> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name),Patient::getName,name);
        queryWrapper.orderByDesc(Patient::getAdmissionDate);

        //执行查询
        patientService.page(pageInfo,queryWrapper);

        //第二步，拷贝：
        BeanUtils.copyProperties(pageInfo,patientDtoPageInfo,"records");

        //获取Patient返回的内容
        List<Patient> records = pageInfo.getRecords();

        List<PatientDto> list = records.stream().map(item->{
            PatientDto patientDto = new PatientDto();
            BeanUtils.copyProperties(item,patientDto);
            Long employeeId = item.getEmployeeId();

            Long wardId = item.getWardId();

            Employee employee = employeeService.getById(employeeId);
            Ward ward = wardService.getById(wardId);

            System.out.println("===============" + employeeId);

            if (employeeId != null){
                patientDto.setEmployeeName(employee.getName());
            }

            if (wardId != null){
                patientDto.setWardName(ward.getWardNumber());
            }
            return patientDto;
        }).collect(Collectors.toList());

        patientDtoPageInfo.setRecords(list);

        return R.success(patientDtoPageInfo);

    }

    /**
     * 根据ID修改某个病人的信息
     * @param patient
     * @return
     */
    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Patient patient){
        log.info(patient.toString());

        long id = Thread.currentThread().getId();
        log.info("线程id为：{}",id);

        patientService.modifyById(patient);
        return R.success("病人信息修改成功");
    }

    /**
     * 根据id查询某个病人的信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Patient> getById(@PathVariable Long id){
        log.info("根据id查询员工信息....");
        Patient pat = patientService.getById(id);
        if (pat != null){
            return R.success(pat);
        }

        return R.error("没有查询到对应员工信息");
    }

    @GetMapping("/list")
    public R<List<Patient>> list(){
        List<Patient> list = patientService.list();
        return R.success(list);
    }
}
