package com.ngocnq.service;

import com.ngocnq.model.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentService {
    Page<Department> findAll(Pageable pageable);
}
