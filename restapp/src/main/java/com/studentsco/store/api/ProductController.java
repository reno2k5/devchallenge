package com.studentsco.store.api;

import com.studentsco.store.model.products.Product;
import com.studentsco.store.repositories.ProductJPARepository;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {

        Product repoProd = repository.save(product);

        return new ResponseEntity<>(repoProd, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id) {
        Optional<Product> product = repository.findById(id);

        if (!product.isPresent()) {
            return new ResponseEntity<>(new CustomError("No se pudo borrar producto con id " + id), HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}/stock/{stock}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStock(@PathVariable("id") Integer id, @PathVariable("stock") Integer newStock) {
        Optional<Product> product;

        if (newStock <= 0) {
            return new ResponseEntity<>(new CustomError("Stock no puede ser negativo para cualquier producto"), HttpStatus.CONFLICT);
        }
        product = repository.findById(id);
        if (!product.isPresent()) {
            return new ResponseEntity<>(new CustomError("Producto " + id + " no encontrado, no se puede actualizar"), HttpStatus.NOT_FOUND);
        }

        product.get().setStock(newStock);
        repository.save(product.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/price/{price}")
    public ResponseEntity<?> updatePrice(@PathVariable("id") Integer id, @PathVariable("price") Double price) {
        Logger adminLogger = LogManager.getLogger("adminLog");
        Optional<Product> product;
        Double originalPrice;

        if (price < 0.00) {
            return new ResponseEntity<>(new CustomError("Precio no puede ser negativo"), HttpStatus.CONFLICT);
        }

        product = repository.findById(id);
        if (!product.isPresent()) {
            return new ResponseEntity<>(new CustomError("Producto " + id + " no encontrado, no se puede actualizar"), HttpStatus.NOT_FOUND);
        }

        originalPrice = product.get().getPrice();
        product.get().setPrice(price);
        repository.save(product.get());
        adminLogger.info("Product " + id + "-" + product.get().getName() + " changed price from " + originalPrice + " to " + price);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
