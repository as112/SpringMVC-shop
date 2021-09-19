package com.example.shop.controllers;

import com.example.shop.models.Employee;
import com.example.shop.models.Purchase;
import com.example.shop.repo.EmployeeRepository;
import com.example.shop.repo.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @GetMapping("/employee")
    public String employees(Model model) {
        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "employee";
    }

    @GetMapping("/employee/add")
    public String addEmployee(@ModelAttribute("employee") Employee employee) {

        return "employee-add";
    }

    @PostMapping("/employee/add")
    public String employeeAdd(@ModelAttribute("employee") @Valid Employee employee,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "employee-add";
        employeeRepository.save(employee);
        return "redirect:/employee";
    }

    @GetMapping("/employee/{id}")
    public String employeeStat(@PathVariable(value = "id") Long id,  Model model) throws Exception {
        double sum = 0;

        if(!employeeRepository.existsById(id)) {
            return "redirect:/employee";
        }
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new Exception());
        Iterable<Purchase> purshase = purchaseRepository.findByEmployeeId(employee.getEmployeeId());
        for (Purchase i:purshase) {
            sum = sum + i.getTotalPrice();
        }
        model.addAttribute("sum", sum);
        model.addAttribute("employee", employee);
        model.addAttribute("purshase", purshase);
        return "employee-stat";
    }

    @GetMapping("/employee/{id}/edit")
    public String employeeEdit(@PathVariable(value = "id") Long id,  Model model) throws Exception {
        if(!employeeRepository.existsById(id)) {
            return "redirect:/employee";
        }
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new Exception());
        model.addAttribute("employee", employee);
        return "employee-edit";
    }

    @PostMapping("/employee/{id}/edit")
    public String employeeUpdate (@PathVariable(value = "id") Long id,
                                  @ModelAttribute("employee") @Valid Employee employeeFromForm,
                                  BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors())
            return "employee-edit";
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new Exception());
        employee.setFirstname(employeeFromForm.getFirstname());
        employee.setLastname(employeeFromForm.getLastname());
        employee.setRank(employeeFromForm.getRank());
        employee.setPassword(employeeFromForm.getPassword());
        employeeRepository.save(employee);

        return "redirect:/employee";
    }
    @PostMapping("/employee/{id}/remove")
    public String employeeDelete (@PathVariable(value = "id") Long id,
                                  Model model) throws Exception {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new Exception());

        employeeRepository.delete(employee);

        return "redirect:/employee";
    }
}

