package com.zy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.common.R;
import com.zy.domain.Drug;
import com.zy.service.DrugService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/drug")
public class DrugController {

    @Autowired
    private DrugService drugService;

    /**
     * 添加药品信息
     * @param drug
     * @return
     */
    @PostMapping()
    private R<String> save(@RequestBody Drug drug){
        drugService.save(drug);
        return R.success("药品添加成功！");
    }

    /**
     * 修改药品信息
     * @param drug
     * @return
     */
    @PutMapping
    private R<String> update(@RequestBody Drug drug){
        drugService.updateById(drug);
        return R.success("药品信息修改成功！");
    }

    /**
     * 分页查询药品信息
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        log.info("page={},pageSize={},name={}",page,pageSize,name);
        //构造分页构造器
        Page<Drug> pageInfo = new Page<>(page, pageSize);

        //构造条件查询器
        LambdaQueryWrapper<Drug> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name),Drug::getName,name);
        queryWrapper.orderByDesc(Drug::getShelfTime);

        //执行查询
        drugService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    /**
     * 根据Id查询某一药品的详细信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Drug> getById(@PathVariable Long id){
        log.info("根据id查询员工信息....");
        Drug drug = drugService.getById(id);
        if (drug != null){
            return R.success(drug);
        }
        return R.error("没有该药品的信息");
    }

}
