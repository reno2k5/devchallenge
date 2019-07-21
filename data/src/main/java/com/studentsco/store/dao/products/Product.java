package com.studentsco.store.dao.products;

import com.studentsco.store.model.products.StockReductionException;
import com.studentsco.store.dao.security.User;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productSeq")
    @SequenceGenerator(name = "productSeq", sequenceName = "product_seq_pk", allocationSize = 1)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "price")
    private Double price;

    @ManyToMany
    @JoinTable(name = "product_liked",
            joinColumns = {@JoinColumn(name = "prod_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> likedBy;

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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the code to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the stock
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(Integer stock) {
        if (stock <= 0) {
            stock = 0;
        }
        this.stock = stock;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return the likedBy list
     */
    public Set<User> getLikedBy() {
        if (likedBy == null) {
            likedBy = new HashSet<>();
        }
        return likedBy;
    }

    /**
     * @param likedBy the liked to set
     */
    public void setLikedBy(Set<User> likedBy) {
        this.likedBy = likedBy;
    }

    /**
     * Reduces the stock of a product
     *
     * @param amount quantity of units the stock is to be decreased
     * @throws com.studentsco.store.model.products.StockReductionException when
     * amount is greater that current stock
     */
    public void reduceStock(int amount) throws StockReductionException {
        int currentStock = getStock();
        if (amount > currentStock) {
            throw new StockReductionException("No se cuenta con suficiente producto disponible");
        }
        setStock(currentStock - amount);
    }
}
