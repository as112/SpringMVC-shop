package com.example.shop.repo;

import com.example.shop.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query(value = "select * from product t order by t.product_id", nativeQuery = true)
    Iterable<Product> findAll();

    @Query(value = "select * from product t where t.category_id = ?1", nativeQuery = true)
    Iterable<Product> findByCategoryId(Long categoryId);

    @Query(value = "select * from product t where t.description = ?1", nativeQuery = true)
    Optional<Product> findByDescription(String description);
}