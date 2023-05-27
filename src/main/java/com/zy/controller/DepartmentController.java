package com.zy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.common.R;
import com.zy.domain.Department;
import com.zy.domain.Employee;
import com.zy.domain.Ward;
import com.zy.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    /**
     * 添加科室
     * @param department
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Department department){
        departmentService.save(department);
        return R.success("新科室添加成功");
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping()
    public R<String> delete(Long id){
        log.info("被删除的id:{}",id);
        departmentService.remove(id);
        return R.success("科室信息删除成功");
    }



    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Department department){
        log.info("病房表：{}",department.toString());

//        long id = Thread.currentThread().getId();
//        log.info("线程id为：{}",id);

        departmentService.updateById(department);
        return R.success("员工信息添加成功");
    }

    /**
     * 查询所有科室表的分页信息
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        log.info("page={},pageSize={},name={}",page,pageSize,name);
        //构造分页构造器
        Page<Department> pageInfo = new Page<>(page, pageSize);

        //构造条件查询器
        LambdaQueryWrapper<Department> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name),Department::getName,name);

        //执行查询
        departmentService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }

    @GetMapping("/{id}")
    public R<Department> getById(@PathVariable Long id){
        log.info("根据id查询病房信息....");
        Department department = departmentService.getById(id);
        if (department != null){
            return R.success(department);
        }

        return R.error("没有该科室的信息");
    }

    @GetMapping("/list")
    public R<List<Department>> list(){
        List<Department> list = departmentService.list();
        return R.success(list);
    }


}
