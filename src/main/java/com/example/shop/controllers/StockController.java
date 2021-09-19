package com.example.shop.controllers;

import com.example.shop.models.Product;
import com.example.shop.repo.EmployeeRepository;
import com.example.shop.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class StockController {

    @Autowired
    private ProductRepository productRepository;
    private EmployeeRepository employeeRepository;

    @PostMapping("/buy")
    public String buy(@RequestParam String categoryName,
                      @RequestParam String description,
                      @RequestParam int count, Model model) throws Exception {
        Product product = productRepository.findByDescription(description).orElseThrow(() -> new Exception());
        product.setBalance(product.getBalance() + count);
        productRepository.save(product);
        model.addAttribute("productId", product.getProductId());
        model.addAttribute("count", count);
        model.addAttribute("step", 3);
        return "purchase-add";
    }
}
