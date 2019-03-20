package imt.mmpj.storage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ITEM", schema = "PUBLIC")
public class Item {

    @Id
    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "COST")
    private double cost;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "IMAGEURL")
    private String imageUrl;

    public Item() {

    }

    public Item(String code, String name, double cost, int quantity, String imageUrl) {
        this.code = code;
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public String toString() {
    	return this.name;
    }
    
    @Override
	public int hashCode() {
    	int hash = 1;
        hash = hash * 17 + code.hashCode();
        return hash;
	}

	public boolean equals(Object item) {
    	return this.getCode().equals(((Item)item).getCode());
    }
}
