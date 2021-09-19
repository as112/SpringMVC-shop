package com.example.shop.controllers;

import com.example.shop.models.Purchase;
import com.example.shop.models.ReportPurchase;
import com.example.shop.repo.PurchaseRepository;
import com.example.shop.repo.ReportPurchaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class Reporter {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    ReportPurchaseRepo reportPurchaseRepo;

    @Scheduled(fixedRate = 60000, initialDelay = 10000)
    public void reportPurchase() {

        Iterable<Purchase> purchases = purchaseRepository.findAll();
        purchases.forEach(purchase -> {

            ReportPurchase purchaseArch = new ReportPurchase();

            purchaseArch.setPurchaseId(purchase.getPurchaseId());
            purchaseArch.setCategoryName(purchase.getProduct().getCategory().getCategoryName());
            purchaseArch.setDescription(purchase.getProduct().getDescription());
            purchaseArch.setCount(purchase.getCount());
            purchaseArch.setTotalPrice(purchase.getTotalPrice());
            purchaseArch.setEmployeeFirstName(purchase.getEmployee().getFirstname());
            purchaseArch.setEmployeeLastName(purchase.getEmployee().getLastname());
            if(!purchase.equals(purchaseArch)) {
                reportPurchaseRepo.save(purchaseArch);
            }
        });
    }

}
