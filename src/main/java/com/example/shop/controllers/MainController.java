package com.example.shop.controllers;

import com.example.shop.models.Category;
import com.example.shop.models.Employee;
import com.example.shop.repo.CategoryRepository;
import com.example.shop.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    public static Long employeeNow;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EmployeeRepository employeeRepository;
    private String pass;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }

    @PostMapping("/")
    public String authorization(@RequestParam Long id,
                                @RequestParam String pass, Model model) throws Exception {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new Exception());
        if(!employee.getPassword().equals(pass)) {
            return "redirect:/";
        }
        else {
            Iterable<Category> category = categoryRepository.findAll();
            model.addAttribute("category", category);
            model.addAttribute("step", 1);
            setEmployeeNow(employee.getEmployeeId());
            return "purchase-add";
        }
    }

    public static Long getEmployeeNow() {
        return employeeNow;
    }

    public void setEmployeeNow(Long employeeNow) {
        this.employeeNow = employeeNow;
    }
}
