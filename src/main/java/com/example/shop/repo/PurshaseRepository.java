package com.example.shop.repo;

import com.example.shop.models.Purshase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurshaseRepository extends CrudRepository<Purshase, Long> {

    @Query(value = "select * from purshase t where t.employee_id = ?1", nativeQuery = true)
    Iterable<Purshase> findByEmployeeId(Long employeeId);
}
