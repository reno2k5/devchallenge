package com.studentsco.store.api;

import com.studentsco.store.model.products.Product;
import com.studentsco.store.repositories.ProductJPARepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    
    @Autowired
    private ProductJPARepository repository;
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        
        Product repoProd = repository.save(product);
        
        return new ResponseEntity<>(repoProd, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id){
        Optional<Product> product = repository.findById(id);
        
        if(!product.isPresent()){
            return new ResponseEntity<>(new CustomError("No se pudo borrar producto con id "+ id), HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
