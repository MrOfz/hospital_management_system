package com.zy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.common.R;
import com.zy.domain.Department;
import com.zy.domain.Employee;
import com.zy.domain.Patient;
import com.zy.domain.Ward;
import com.zy.dto.PatientDto;
import com.zy.dto.WardDto;
import com.zy.service.DepartmentService;
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
@RequestMapping("/ward")
public class WardController {
    @Autowired
    private WardService wardService;

    @Autowired
    private DepartmentService departmentService;

    /**
     * 添加病人信息
     * @param wardDto
     * @return
     */
    @PostMapping()
    private R<String> save(@RequestBody WardDto wardDto){
        log.info("增加病人信息:{}",wardDto.toString());
        wardService.save(wardDto);
        return R.success("病人信息添加成功！");
    }

    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Ward ward){
        log.info("病房表：{}",ward.toString());

//        long id = Thread.currentThread().getId();
//        log.info("线程id为：{}",id);

        wardService.updateById(ward);
        return R.success("员工信息添加成功");
    }

    /**
     * 根据Id查询病房信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Ward> getById(@PathVariable Long id){
        log.info("根据id查询病房信息....");
        Ward ward = wardService.getById(id);
        if (ward != null){
            return R.success(ward);
        }

        return R.error("没有该病房的信息");
    }

    /**
     * 分页查询所有的病房信息
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        log.info("page={},pageSize={},name={}",page,pageSize,name);
        //构造分页构造器
        Page<Ward> pageInfo = new Page<>(page, pageSize);
        Page<WardDto> wardDtoPageInfo = new Page<>();

        //构造条件查询器
        LambdaQueryWrapper<Ward> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name),Ward::getWardNumber,name);

        //执行查询
        wardService.page(pageInfo,queryWrapper);

        //第二步，拷贝：
        BeanUtils.copyProperties(pageInfo,wardDtoPageInfo,"records");

        //获取Patient返回的内容
        List<Ward> records = pageInfo.getRecords();

        List<WardDto> list = records.stream().map(item->{
            WardDto wardDto = new WardDto();
            BeanUtils.copyProperties(item,wardDto);
            Long deptId = item.getDeptId();
            Department dept = departmentService.getById(deptId);

            System.out.println("===============" + deptId);

            if (deptId != null){
                wardDto.setDeptName(dept.getName());
                wardDto.setDeptHead(dept.getHead());
            }
            return wardDto;
        }).collect(Collectors.toList());

        wardDtoPageInfo.setRecords(list);

        return R.success(wardDtoPageInfo);
    }

    /**
     * 根据病房查询所有
     * @return
     */
    @GetMapping("/list")
    public R<List<Ward>> list(){
        List<Ward> list = wardService.list();
        return R.success(list);
    }
}
