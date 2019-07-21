package com.studentsco.store.repositories;

import com.studentsco.store.model.products.Product;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJPARepository extends JpaRepository<Product, Integer> {
    
    public List<Product> findByStockGreaterThan(Integer stock, Pageable pageable);
   
    @Query("FROM Product p WHERE p.stock >= :stock ORDER BY size(p.likedBy) DESC")
    public List<Product> findByStockGreaterThanSortedByPopularity(@Param("stock") Integer stock, Pageable pageable);
}
