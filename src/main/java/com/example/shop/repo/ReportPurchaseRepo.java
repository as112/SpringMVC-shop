package com.example.shop.repo;

import com.example.shop.models.ReportPurchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportPurchaseRepo extends CrudRepository<ReportPurchase, Long> {

}
