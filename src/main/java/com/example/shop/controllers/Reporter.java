package com.example.shop.controllers;

import com.example.shop.models.Category;
import com.example.shop.models.Purshase;
import com.example.shop.models.ReportPurshase;
import com.example.shop.repo.CategoryRepository;
import com.example.shop.repo.PurshaseRepository;
import com.example.shop.repo.ReportPurshaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class Reporter {

    @Autowired
    PurshaseRepository purshaseRepository;

    @Autowired
    ReportPurshaseRepo reportPurshaseRepo;

    @Scheduled(fixedRate = 60000, initialDelay = 10000)
    public void reportPurshase() {

        Iterable<Purshase> purshases = purshaseRepository.findAll();
        purshases.forEach(purshase -> {

            ReportPurshase purshaseArch = new ReportPurshase();

            purshaseArch.setPurshaseId(purshase.getPurshaseId());
            purshaseArch.setCategoryName(purshase.getProduct().getCategory().getCategoryName());
            purshaseArch.setDescription(purshase.getProduct().getDescription());
            purshaseArch.setCount(purshase.getCount());
            purshaseArch.setTotalPrice(purshase.getTotalPrice());
            purshaseArch.setEmployeeFirstName(purshase.getEmployee().getFirstname());
            purshaseArch.setEmployeeLastName(purshase.getEmployee().getLastname());
            if(!purshase.equals(purshaseArch)) {
                reportPurshaseRepo.save(purshaseArch);
            }
        });
    }

}
