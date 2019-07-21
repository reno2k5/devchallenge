package com.studentsco.store.api;

import com.studentsco.store.model.products.Product;
import com.studentsco.store.model.security.User;
import com.studentsco.store.repositories.ProductJPARepository;
import com.studentsco.store.repositories.UsersJPARepository;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    public static final Integer PAGE_SIZE = 3;

    @Autowired
    private ProductJPARepository repository;

    @Autowired
    private UsersJPARepository userRepository;

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

    @RequestMapping(value = "/{id}/price/{price}", method = RequestMethod.PUT)
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

    @RequestMapping(value = "/{id}/like")
    public ResponseEntity<?> likeProduct(@PathVariable("id") Integer id, Principal principal) {

        Optional<Product> product;
        User user = userRepository.findByUsername(principal.getName());

        product = repository.findById(id);
        if (!product.isPresent()) {
            return new ResponseEntity<>("Producto " + id + " no encontrado, no se puede actualizar", HttpStatus.NOT_FOUND);
        }

        if (user == null) {
            throw new UsernameNotFoundException(principal.getName());
        }

        product.get().getLikedBy().add(user);
        repository.save(product.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = {"/sort/name/page/{page}", "/", "/sort/name", "/page/{page}"}, method = RequestMethod.GET)
    public List<Product> getAvailableProductsSortByName(@PathVariable("page") Optional<Integer> page) {

        Integer pageNumber = 0;
        if (page.isPresent()) {
            pageNumber = page.get();
        }

        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("name").ascending());

        List<Product> products = repository.findByStockGreaterThan(0, pageable);

        products.forEach(product -> {
            product.setLikedBy(new HashSet<>());
        });

        return products;
    }

    @RequestMapping(value = {"/sort/popularity", "/sort/popularity/page/{page}"}, method = RequestMethod.GET)
    public List<Product> getAvailableProductsByPopularity(@PathVariable("page") Optional<Integer> page) {
        Integer pageNumer = 0;
        if (page.isPresent()) {
            pageNumer = page.get();
        }

        Pageable pageable = PageRequest.of(pageNumer, PAGE_SIZE);

        List<Product> products = repository.findByStockGreaterThanSortedByPopularity(0, pageable);

        products.forEach(product -> {
            product.setLikedBy(new HashSet<>());
        });

        return products;
    }

    @RequestMapping(value = {"/find/name/{name}", "/find/name/{name}/page/{page}"}, method = RequestMethod.GET)
    public List<Product> getProductsByName(@PathVariable("name") String name, @PathVariable("page") Optional<Integer> page) {
        Integer pageNumer = 0;
        if (page.isPresent()) {
            pageNumer = page.get();
        }
        
        Pageable pageable = PageRequest.of(pageNumer, PAGE_SIZE);
        List<Product> products = repository.findByNameContaining(name, pageable);
        products.forEach(product -> {
            product.setLikedBy(new HashSet<>());
        });
        
        return products;
    }
}
