package com.studentsco.store.repositories;

import com.studentsco.store.model.Configuration;
import com.studentsco.store.model.products.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Configuration.class)
@TestPropertySource(locations = "classpath:data-application-test.properties")
public class ProductRepositoryTest {
    
    @Autowired
    private ProductJPARepository repository;
    
    private Product product;
    
    @Before
    public void prepare(){
        product = new Product();
        product.setName("applepie");
        product.setDescription("Apple pie");
        product.setStock(25);
        product.setPrice(2.50);
    }
    
    @After
    public void cleanup(){
        repository.deleteAll();
    }
    
    @Test
    public void testInsert(){
        repository.save(product);
    }
    
    public void testDelete(){
        repository.save(product);
        repository.delete(product);
    }
    
}
