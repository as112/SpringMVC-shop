package com.example.shop.repo;

import com.example.shop.models.Purchase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

    @Query(value = "select * from purshase t where t.employee_id = ?1", nativeQuery = true)
    Iterable<Purchase> findByEmployeeId(Long employeeId);
}
