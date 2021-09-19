package com.example.shop.controllers;

import com.example.shop.models.Employee;
import com.example.shop.models.Product;
import com.example.shop.models.Purchase;
import com.example.shop.repo.CategoryRepository;
import com.example.shop.repo.EmployeeRepository;
import com.example.shop.repo.ProductRepository;
import com.example.shop.repo.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PurchaseController {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/purchase")
    public String purchases(Model model) {
        Iterable<Purchase> purchases = purchaseRepository.findAll();
        model.addAttribute("purchases", purchases);
        return "purchase";
    }

    @GetMapping("/purchase/add")
    public String addPurchase(@RequestParam(value = "categoryId", required = false) Long categoryId,
                              Model model) throws Exception {
        if(categoryId != null) {
            Iterable<Product> product = productRepository.findByCategoryId(categoryId);
            model.addAttribute("products", product);
            model.addAttribute("step", 2);
        }
        else {
            System.out.println("error");
        }
        return "purchase-add";
    }

    @PostMapping("/purchase/add")
    public String purchaseAdd(@RequestParam Long productId,
                              @RequestParam int count, Model model) throws Exception {
        int balance;
        Employee employee = employeeRepository.findById(MainController.getEmployeeNow()).orElseThrow(() -> new Exception());
        Product product = productRepository.findById(productId).orElseThrow(() -> new Exception());
        Purchase purchase = new Purchase(product, count, count*product.getPrice(), employee);

        balance = product.getBalance() - purchase.getCount();
        if(balance >= 0) {

            product.setBalance(balance);
            purchaseRepository.save(purchase);
            model.addAttribute("purchase", purchase);
            return "check";
        }
        else {
            purchase.setCount(product.getBalance());
            purchase.setTotalPrice(product.getPrice()*product.getBalance());
            product.setBalance(0);
            purchaseRepository.save(purchase);
            model.addAttribute("product", product);
            model.addAttribute("balanceDeficit", balance * (-1));
            model.addAttribute("address_host_stock","http://localhost:8082/");

            return "stock";
        }

    }
}
