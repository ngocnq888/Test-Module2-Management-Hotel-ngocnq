package com.ngocnq.service.impl;

import com.ngocnq.model.Department;
import com.ngocnq.model.Staff;
import com.ngocnq.repository.StaffRepository;
import com.ngocnq.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class StaffServiceImpl implements StaffService {
    @Autowired
    StaffRepository staffRepository;
    @Override
    public Page<Staff> findAll(Pageable pageable) {
        return staffRepository.findAll(pageable);
    }

    @Override
    public Page<Staff> findAllByDepartment(Department department, Pageable pageable) {
        return staffRepository.findAllByDepartment(department,pageable);
    }

    @Override
    public Page<Staff> findAllByFirstNameContains(String firstName, Pageable pageable) {
        return staffRepository.findAllByFirstNameContains(firstName,pageable);
    }

    @Override
    public Staff findOne(Long id) {
        return staffRepository.findOne(id);
    }

    @Override
    public void save(Staff staff) {
        staffRepository.save(staff);
    }

    @Override
    public void delete(Long id) {
        staffRepository.delete(id);
    }
}
