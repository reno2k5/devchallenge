package com.studentsco.store.services.impl;

import com.studentsco.store.services.BuyService;
import com.studentsco.store.dao.customers.PurchaseHistory;
import com.studentsco.store.dao.products.Product;
import com.studentsco.store.dao.security.User;
import com.studentsco.store.model.products.StockReductionException;
import com.studentsco.store.repositories.ProductJPARepository;
import com.studentsco.store.repositories.PurchaseHistoryJPARepository;
import com.studentsco.store.repositories.UsersJPARepository;
import com.studentsco.store.services.PurchaseException;
import com.studentsco.store.services.UserNotFoundException;
import java.util.GregorianCalendar;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyServiceImpl implements BuyService {

    Logger logger = LogManager.getLogger(BuyServiceImpl.class);

    @Autowired
    private ProductJPARepository productRepository;

    @Autowired
    private PurchaseHistoryJPARepository purchaseRepository;

    @Autowired
    private UsersJPARepository userRepository;

    @Override
    public void buy(String username, int productId, int amount) throws PurchaseException, UserNotFoundException {
        Optional<Product> repoProduct = productRepository.findById(productId);
        User user = userRepository.findByUsername(username);

        if (!repoProduct.isPresent()) {
            throw new PurchaseException("El producto no existe");
        }
        if (user == null) {
            throw new UserNotFoundException("El usuario no existe");
        }

        Product product = repoProduct.get();

        if (amount > product.getStock()) {
            throw new PurchaseException("No hay suficientes ítems en existencia");
        }

        PurchaseHistory purchase = new PurchaseHistory();
        purchase.setProduct(product);
        purchase.setAmount(amount);
        purchase.setPurchasePrice(product.getPrice());
        purchase.setPurchaseTime(GregorianCalendar.getInstance());
        purchase.setUser(user);

        try {

            purchaseRepository.save(purchase);

            product.reduceStock(amount);
            productRepository.save(product);

        } catch (StockReductionException ex) {
            String message = "No se pudo guardar la compra";
            throw new PurchaseException(message, ex);
        }

    }
}
