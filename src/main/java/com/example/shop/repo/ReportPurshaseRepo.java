package com.example.shop.repo;

import com.example.shop.models.ReportPurshase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportPurshaseRepo extends CrudRepository<ReportPurshase, Long> {

}
