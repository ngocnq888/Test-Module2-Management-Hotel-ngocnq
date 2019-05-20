package com.ngocnq.service;

import com.ngocnq.model.Department;
import com.ngocnq.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StaffService  {
    Page<Staff> findAll(Pageable pageable);
    Page<Staff> findAllByDepartment(Department department,Pageable pageable);
    Page<Staff> findAllByFirstNameContains(String firstName,Pageable pageable);
    Staff findOne(Long id);
    Staff save(Staff staff);
    void delete(Long id);
}
