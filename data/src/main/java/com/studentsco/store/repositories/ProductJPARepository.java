package com.studentsco.store.repositories;

import com.studentsco.store.model.products.Product;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJPARepository extends CrudRepository<Product, Integer> {
    
    public List<Product> findByStockGreaterThan(Integer stock);
}
