package com.studentsco.store.api;

import com.studentsco.store.model.customers.PurchaseHistory;
import com.studentsco.store.model.products.Product;
import com.studentsco.store.model.products.StockReductionException;
import com.studentsco.store.repositories.ProductJPARepository;
import com.studentsco.store.repositories.PurchaseHistoryJPARepository;
import java.util.GregorianCalendar;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/buy")
public class BuyController {

    Logger logger = LogManager.getLogger(BuyController.class);

    @Autowired
    ProductJPARepository productRepository;

    @Autowired
    PurchaseHistoryJPARepository purchaseRepository;

    @RequestMapping(value="/product/{id}/amount/{amount}", method = RequestMethod.POST)
    public ResponseEntity<?> buyProducts(@PathVariable("id") Integer productId,
            @PathVariable("amount") Integer amount) {

        Optional<Product> repoProduct = productRepository.findById(productId);
        if (!repoProduct.isPresent()) {
            return new ResponseEntity<>("El producto no existe", HttpStatus.NOT_FOUND);
        }

        Product product = repoProduct.get();

        if (amount > product.getStock()) {
            return new ResponseEntity<>("Cantidad solicitada supera disponibilidad", HttpStatus.CONFLICT);
        }

        PurchaseHistory purchase = new PurchaseHistory();
        purchase.setProduct(product);
        purchase.setAmount(amount);
        purchase.setPurchasePrice(product.getPrice());
        purchase.setPurchaseTime(GregorianCalendar.getInstance());

        try {

            purchaseRepository.save(purchase);

            product.reduceStock(amount);
            productRepository.save(product);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (StockReductionException ex) {
            String message = "No se pudo guardar la compra";
            logger.error(message, ex);
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }

    }
}
