package com.example.shop.controllers;

import com.example.shop.models.Category;
import com.example.shop.models.Product;
import com.example.shop.repo.CategoryRepository;
import com.example.shop.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class ProductController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product")
    public String products(Model model) {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "product";
    }

    @GetMapping("/product/add")
    public String addProduct(@ModelAttribute("productFromForm") Product product, Model model) {

        Iterable<Category> category =categoryRepository.findAll();
        model.addAttribute("category", category);
        return "product-add";
    }

    @PostMapping("/product/add")
    public String productAdd(//@ModelAttribute("categoryFromForm") Category categoryFromForm,
                             @RequestParam Long categoryId,
                             @ModelAttribute("productFromForm") @Valid Product productFromForm,
                             BindingResult bindingResult, Model model) throws Exception {
        if(categoryId == -1)
            bindingResult.addError(new FieldError("category", "category", "Choose category"));
        if(bindingResult.hasErrors()) {
            Iterable<Category> category =categoryRepository.findAll();
            model.addAttribute("category", category);
            return "product-add";
        }
        Category categoryFromForm = categoryRepository.findById(categoryId).orElseThrow(() -> new Exception());
        productFromForm.setCategory(categoryFromForm);
        productRepository.save(productFromForm);
        return "redirect:/product";
    }

    @GetMapping("/product/{id}/edit")
    public String productEdit(@PathVariable(value = "id") Long id, Model model) throws Exception {
        if(!productRepository.existsById(id)) {
            return "redirect:/product";
        }
        Product product = productRepository.findById(id).orElseThrow(() -> new Exception());
        Iterable<Category> category =categoryRepository.findAll();
        model.addAttribute("category", category);
        model.addAttribute("product", product);
        return "product-edit";
    }

    @PostMapping("/product/{id}/edit")
    public String productUpdate(@PathVariable(value = "id") Long id,
                                @RequestParam Long categoryId,
                                @RequestParam String description,
                                @RequestParam double price,
                                @RequestParam int balance,
                                Model model) throws Exception {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new Exception());
        Product product = productRepository.findById(id).orElseThrow(() -> new Exception());
        product.setCategory(category);
        product.setDescription(description);
        product.setPrice(price);
        product.setBalance(balance);
        productRepository.save(product);

        return "redirect:/product";
    }

    @PostMapping("/product/{id}/remove")
    public String productUpdate(@PathVariable(value = "id") Long id,
                                Model model) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(() -> new Exception());
        productRepository.delete(product);
        return "redirect:/product";
    }
}
