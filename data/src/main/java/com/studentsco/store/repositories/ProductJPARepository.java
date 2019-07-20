package com.studentsco.store.repositories;

import com.studentsco.store.model.products.Product;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJPARepository extends  PagingAndSortingRepository<Product, Integer> {
    
    public List<Product> findByStockGreaterThan(Integer stock, Pageable pageable);
    
   
}
