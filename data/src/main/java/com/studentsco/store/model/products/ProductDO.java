package com.studentsco.store.model.products;

import com.studentsco.store.model.security.UserDO;
import java.util.Set;

public class ProductDO {

    private Integer id;
    private String name;
    private String description;
    private Integer stock;
    private Double price;
    private Set<UserDO> likedBy;

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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @return the likedBy
     */
    public Set<UserDO> getLikedBy() {
        return likedBy;
    }

    /**
     * @param likedBy the likedBy to set
     */
    public void setLikedBy(Set<UserDO> likedBy) {
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
