package com.zy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.common.R;
import com.zy.domain.Department;
import com.zy.domain.Employee;
import com.zy.dto.EmployeeDto;
import com.zy.service.DepartmentService;
import com.zy.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    /**
     * 员工登录
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    private R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        String password = employee.getPassword();
        //1.将页面提交的密码进行md5加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        //2.根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);
        //3.如果没有查询都则返回登录失败结果
        if (emp == null){
            return R.error("登录失败");
        }
        //4.密码比对，如果不一致则返回登录失败结果
        if (!emp.getPassword().equals(password)){
            return R.error("登录失败");
        }
        //5.查看员工状态，如果为已禁用状态，则返回员工已禁用结果
        if (emp.getStatus() == 0){
            return R.error("账户已被禁用");
        }
        //6.登录成功，将员工id存入Session并返回登录成功结果
        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);
    }

    /**
     * 员工退出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("退出成功！");
    }

    /**
     * 新增职工
     * @param employee
     * @return
     */
    @PostMapping()
    private R<String> save(HttpServletRequest request, @RequestBody Employee employee){
        //1.设置初始密码p@ssw0rd，需要进行md5加密处理
        employee.setPassword(DigestUtils.md5DigestAsHex("p@ssw0rd".getBytes()));

        employeeService.save(employee);

        return R.success("新增职工成功");
    }

    /**
     * 员工信息分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        //第一步：
        //构造分页构造器
        Page<Employee> pageInfo = new Page<>(page,pageSize);
        Page<EmployeeDto> employeeDtoPage = new Page<>();

        //构造条件查询器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);
        queryWrapper.orderByDesc(Employee::getUpdateTime);

        //执行查询
        employeeService.page(pageInfo,queryWrapper);

        //第二步，拷贝：
        BeanUtils.copyProperties(pageInfo,employeeDtoPage,"records");

        //获取Employee返回的内容
        List<Employee> records = pageInfo.getRecords();

        List<EmployeeDto> list = records.stream().map(item->{
            EmployeeDto employeeDto = new EmployeeDto();
            BeanUtils.copyProperties(item,employeeDto);
            Long deptId = item.getDeptId();
            System.out.println("===============" + deptId);
            Department department = departmentService.getById(deptId);

            if (deptId != null){
                employeeDto.setDeptName(department.getName());
            }
            return employeeDto;
        }).collect(Collectors.toList());

        employeeDtoPage.setRecords(list);

        return R.success(employeeDtoPage);
    }

    /**
     * 根据ID修改员工信息
     * @param employee
     * @return
     */
    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee){
        log.info(employee.toString());
        //employee.setUpdateTime(LocalDateTime.now());
        //employee.setUpdateUser((Long) request.getSession().getAttribute("employee"));

        long id = Thread.currentThread().getId();
        log.info("线程id为：{}",id);

        employeeService.updateById(employee);
        return R.success("员工信息添加成功");
    }

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Integer id){
        log.info("根据id查询员工信息....");
        Employee emp = employeeService.getById(id);
        //System.out.println("=====================" + emp);
        if (emp != null){
            return R.success(emp);
        }

        return R.error("没有查询到对应员工信息");
    }

    @GetMapping("/list")
    public R<List<Employee>> list(){
        List<Employee> list = employeeService.list();
        return R.success(list);
    }
}
