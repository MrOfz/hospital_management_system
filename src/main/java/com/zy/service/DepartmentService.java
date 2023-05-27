package com.zy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.domain.Department;

public interface DepartmentService extends IService<Department> {
    void remove(Long id);
}
