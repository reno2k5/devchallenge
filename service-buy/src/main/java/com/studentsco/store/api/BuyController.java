package com.studentsco.store.api;

import com.studentsco.store.services.BuyService;
import com.studentsco.store.services.PurchaseException;
import com.studentsco.store.services.UserNotFoundException;
import java.security.Principal;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/buy")
public class BuyController {

    Logger logger = LogManager.getLogger(BuyController.class);

    @Autowired
    private BuyService buyService;

    @RequestMapping(value = "/product/{id}/amount/{amount}", method = RequestMethod.POST)
    public ResponseEntity<?> buyProducts(@PathVariable("id") Integer productId,
            @PathVariable("amount") Integer amount, Principal principal) {

        String username = principal.getName();

        try {
            buyService.buy(username, productId, amount);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (PurchaseException | UserNotFoundException ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }

    }
}
