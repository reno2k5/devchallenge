package com.studentsco.store.model.customers;

import com.studentsco.store.model.products.Product;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "purchase_history")
public class PurchaseHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchaseHistorySeq")
    @SequenceGenerator(name = "purchaseHistorySeq", sequenceName = "purchase_history_seq_pk", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "purchase_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar purchaseTime;

    @Column(name = "price")
    private Double purchasePrice;

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
    public Product getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(Product product) {
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
}

