package com.zy.controller;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.common.R;
import com.zy.domain.Charge;
import com.zy.domain.Employee;
import com.zy.domain.Patient;
import com.zy.domain.Ward;
import com.zy.dto.ChargeDto;
import com.zy.dto.PatientDto;
import com.zy.service.ChargeService;
import com.zy.service.PatientService;
import com.zy.service.WardService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/charge")
public class ChargeController {
    @Autowired
    private ChargeService chargeService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private WardService wardService;

    /**
     * 添加收费信息
     * @param chargeDto
     * @return
     */
    @PostMapping()
    private R<String> save(@RequestBody ChargeDto chargeDto) {
        chargeService.save(chargeDto);
        return R.success("收费信息添加成功！");
    }

    @DeleteMapping
    private R<String> delete(Long id){
        chargeService.removeById(id);
        return R.success("收费信息删除成功");
    }

    /**
     * 修改病人的信息
     * @param charge
     * @return
     */
    @PutMapping
    private R<String> update(@RequestBody Charge charge){
        chargeService.updateById(charge);
        return R.success("收费信息修改成功");
    }

    /**
     * 收费信息分页查询
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        //第一步：
        //构造分页构造器
        Page<Charge> pageInfo = new Page<>(page, pageSize);
        Page<ChargeDto> chargeDtoPage = new Page<>();


        if (StringUtils.isNotEmpty(name)) {
            LambdaQueryWrapper<Patient> queryWrapperPatient = new LambdaQueryWrapper<>();
            queryWrapperPatient.like(StringUtils.isNotEmpty(name), Patient::getName, name);
            List<Patient> patientList = patientService.list(queryWrapperPatient);
            if (patientList.size() == 0) {
                return R.success(new Page<ChargeDto>());
            }
            List<Charge> list = new ArrayList<>();

            patientList.forEach(patient -> {
                //构造条件查询器
                LambdaQueryWrapper<Charge> queryWrapper = new LambdaQueryWrapper<>();
                //添加过滤条件
                queryWrapper.eq(Charge::getPatientId, patient.getId());
                List<Charge> list1 = chargeService.list(queryWrapper);
                list.addAll(list1);

            });
            // 执行查询
            pageInfo.setRecords(
                    list.subList(
                            Math.min((page - 1) * pageSize, list.size()),
                            Math.min(page * pageSize, list.size())
                    )
            );
            pageInfo.setTotal(list.size());
            pageInfo.setPages(page);
        } else {
            //构造条件查询器
            LambdaQueryWrapper<Charge> queryWrapper = new LambdaQueryWrapper<>();
            //添加过滤条件
            queryWrapper.orderByDesc(Charge::getChargingTime);
            //执行查询
            chargeService.page(pageInfo, queryWrapper);
        }

        // 第二步，拷贝：
        BeanUtils.copyProperties(pageInfo, chargeDtoPage, "records");

        //获取Patient返回的内容
        List<Charge> records = pageInfo.getRecords();

        List<ChargeDto> list = records.stream().map(item -> {
            ChargeDto chargeDto = new ChargeDto();
            BeanUtils.copyProperties(item, chargeDto);
            Long patientId = item.getPatientId();

            Long wardId = item.getWardId();

            Patient pat = patientService.getById(patientId);
            Ward ward = wardService.getById(wardId);

            System.out.println("===============" + patientId);

            if (patientId != null && pat != null) {
                chargeDto.setPatientName(pat.getName());
            }

            if (wardId != null) {
                chargeDto.setWardName(ward.getWardNumber());
            }
            return chargeDto;
        }).collect(Collectors.toList());

        chargeDtoPage.setRecords(list);

        return R.success(chargeDtoPage);

    }

    /**
     * 根据id查询某一条收费信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    private R<ChargeDto> getById(@PathVariable Long id){
        ChargeDto chargeDto = chargeService.selectById(id);
        if (chargeDto != null){
            return R.success(chargeDto);
        }
        return R.error("没有此信息");
    }

}
