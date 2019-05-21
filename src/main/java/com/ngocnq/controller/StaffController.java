package com.ngocnq.controller;

import com.ngocnq.model.Department;
import com.ngocnq.model.Staff;
import com.ngocnq.service.DepartmentService;
import com.ngocnq.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class StaffController {
    private static final int PAGE_DEFAULT = 3;
    @Autowired
    StaffService staffService;
    @Autowired
    DepartmentService departmentService;
    @GetMapping("/")
    public ModelAndView list(@PageableDefault(size = PAGE_DEFAULT)Pageable pageable,@RequestParam(name = "search") Optional<String> search){
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
    // function create a new Staff
    @GetMapping(value = "/create")
    public ModelAndView showCreate(Pageable pageable){
         Page<Department> departments = departmentService.findAll(pageable);
        String message ="";
        ModelAndView modelAndView = new ModelAndView("create","staff",new Staff());
        modelAndView.addObject("message",message);
         modelAndView.addObject("departments",departments);
        return modelAndView;
    }
    @PostMapping(value = "/create-form")
    public ModelAndView create(@ModelAttribute(name = "staff")Staff staff){
        Staff currentStaff = staffService.findOne(staff.getId());
        if (currentStaff== null){
         staffService.save(staff);
        }
        else {
            Staff newStaff = new Staff();
            newStaff.setFirstName(staff.getFirstName());
            newStaff.setLastName(staff.getLastName());
            newStaff.setBirth(staff.getBirth());
            newStaff.setAddress(staff.getAddress());
            newStaff.setDepartment(staff.getDepartment());
            newStaff.setPhone(staff.getPhone());
            newStaff.setEmail(staff.getEmail());
            newStaff.setSex(staff.getSex());
            staffService.save(newStaff);
        }
        ModelAndView modelAndView = new ModelAndView("create","staff",staff);
        String message =" Creating Sucessfull !";
        modelAndView.addObject("message",message);
        return modelAndView;
    }
    // edit a Staff
    @GetMapping(value = "/edit/{id}")
    public ModelAndView showEdit(@PathVariable(name = "id")Long id,Pageable pageable){
        Staff currentStaff = staffService.findOne(id);
        Page<Department> departments = departmentService.findAll(pageable);
        String message = "";
        ModelAndView modelAndView = new ModelAndView("edit");
        if (currentStaff != null){
            modelAndView.addObject("staff",currentStaff);
            modelAndView.addObject("departments",departments);
        }
        else{
            message = " Fail ! ";
            modelAndView.addObject("message",message);
        }
        return modelAndView;
    }

    @PostMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable(name = "id")Long id,@ModelAttribute(name = "staff")Staff staff){
        Staff currentStaff = staffService.findOne(id);
        ModelAndView modelAndView = new ModelAndView("edit");
        String message = "";
        if (currentStaff == null){
            message = " Fail ! ";
        }
        else {
            staffService.save(staff);
            message = " Updating is successfull !";
        }
        modelAndView.addObject("message",message);
        return modelAndView;
    }
    // Delete a Staff
    @GetMapping(value = "/delete/{id}")
    public ModelAndView showDelete(@PathVariable(name = "id")Long id){
        Staff currentStaff = staffService.findOne(id);
        String message = "";
        ModelAndView modelAndView = new ModelAndView("delete");
        if(currentStaff == null){
            message = " Can Not Delete ! ";
            modelAndView.addObject("message",message);
        }
        else {
            modelAndView.addObject("staff",currentStaff);
        }
        return modelAndView;
    }

    @PostMapping(value = "delete/{id}")
    public ModelAndView delete(@PathVariable(name = "id")Long id){
        Staff deleteStaff = staffService.findOne(id);
        ModelAndView modelAndView = new ModelAndView("delete");
        String message = "";
        if(deleteStaff == null){
            message = " Can Not Delete ! ";
        }
        else {
            staffService.delete(id);
            message = "Deleting Successfull !";

        }
        modelAndView.addObject("message",message);
        return modelAndView;
    }
}
