package com.ngocnq.controller;

import com.ngocnq.model.Staff;
import com.ngocnq.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class StaffController {
    @Autowired
    StaffService staffService;
    @GetMapping("/")
    public ModelAndView list(Pageable pageable, Optional<String> search){
        Page<Staff> staffs;
        if (search.isPresent()){
            staffs = staffService.findAllByFirstNameContains(search.get(),pageable);
        }
        else {
            staffs = staffService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("list","staffs",staffs);
        return modelAndView;
    }
}
