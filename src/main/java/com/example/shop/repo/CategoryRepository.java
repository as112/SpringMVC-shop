package com.example.shop.repo;

import com.example.shop.models.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Query(value = "select * from category t where t.category_name = ?1", nativeQuery = true)
    Category findByCategoryName(String categoryName);
}
