package com.studentsco.store.repositories;

import com.studentsco.store.dao.customers.PurchaseHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseHistoryJPARepository extends CrudRepository<PurchaseHistory, Integer> {
    
}
