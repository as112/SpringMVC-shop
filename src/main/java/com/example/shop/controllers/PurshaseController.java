package com.example.shop.controllers;

import com.example.shop.models.Employee;
import com.example.shop.models.Product;
import com.example.shop.models.Purshase;
import com.example.shop.repo.CategoryRepository;
import com.example.shop.repo.EmployeeRepository;
import com.example.shop.repo.ProductRepository;
import com.example.shop.repo.PurshaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PurshaseController {

    @Autowired
    private PurshaseRepository purshaseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/purshase")
    public String purshases(Model model) {
        Iterable<Purshase> purshases = purshaseRepository.findAll();
        model.addAttribute("purshases", purshases);
        return "purshase";
    }

    @GetMapping("/purshase/add")
    public String addPurshase(@RequestParam(value = "categoryId", required = false) Long categoryId,
                              Model model) throws Exception {
        if(categoryId != null) {
            Iterable<Product> product = productRepository.findByCategoryId(categoryId);
            model.addAttribute("products", product);
            model.addAttribute("step", 2);
        }
        else {
            System.out.println("error");
        }
        return "purshase-add";
    }

    @PostMapping("/purshase/add")
    public String purshaseAdd(@RequestParam Long productId,
                              @RequestParam int count, Model model) throws Exception {
        int balance;
        Employee employee = employeeRepository.findById(MainController.getEmployeeNow()).orElseThrow(() -> new Exception());
        Product product = productRepository.findById(productId).orElseThrow(() -> new Exception());
        Purshase purshase = new Purshase(product, count, count*product.getPrice(), employee);

        balance = product.getBalance() - purshase.getCount();
        if(balance >= 0) {

            product.setBalance(balance);
            purshaseRepository.save(purshase);
            model.addAttribute("purshase", purshase);
            return "check";
        }
        else {
            purshase.setCount(product.getBalance());
            purshase.setTotalPrice(product.getPrice()*product.getBalance());
            product.setBalance(0);
            purshaseRepository.save(purshase);
            model.addAttribute("product", product);
            model.addAttribute("balanceDeficit", balance * (-1));
            model.addAttribute("address_host_stock","http://localhost:8082/");

            return "stock";
        }

    }
}
