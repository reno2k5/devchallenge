package com.studentsco.store.model.customers;

import com.studentsco.store.model.products.ProductDO;
import com.studentsco.store.model.security.UserDO;
import java.util.Calendar;

public class PurchageHistoryDO {

    private Integer id;
    private ProductDO product;
    private Integer amount;
    private Calendar purchaseTime;
    private Double purchasePrice;
    private UserDO user;

    public PurchageHistoryDO() {
        super();
    }

    public PurchageHistoryDO(Integer id, ProductDO product, Integer amount, Calendar purchaseTime, Double purchasePrice, UserDO user) {
        this.id = id;
        this.product = product;
        this.amount = amount;
        this.purchaseTime = purchaseTime;
        this.purchasePrice = purchasePrice;
        this.user = user;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the product
     */
    public ProductDO getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(ProductDO product) {
        this.product = product;
    }

    /**
     * @return the amount
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * @return the purchaseTime
     */
    public Calendar getPurchaseTime() {
        return purchaseTime;
    }

    /**
     * @param purchaseTime the purchaseTime to set
     */
    public void setPurchaseTime(Calendar purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    /**
     * @return the purchasePrice
     */
    public Double getPurchasePrice() {
        return purchasePrice;
    }

    /**
     * @param purchasePrice the purchasePrice to set
     */
    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    /**
     * @return the user
     */
    public UserDO getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserDO user) {
        this.user = user;
    }

}
