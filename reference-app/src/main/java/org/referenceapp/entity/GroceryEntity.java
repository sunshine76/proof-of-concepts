package org.referenceapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by schinta6 on 12/23/15.
 */

@Entity
@Table(name="Grocery")
public class GroceryEntity {

    @Id
    @Column(name="ITEM_ID")
    private int id;
    private String name;
    private int  quantity;
    private float price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
