package ecommerceBackend.entity;

import java.util.Objects;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// On local connection; get url is: http://localhost:8080/demo/items

@Entity
public class Item {
	
	private @Id @GeneratedValue Long id;
//    private String bid;
    private String name;
    private String description;
    private String type;
    private String brand;
    private int quantity;
    private double price;
    
    public Item() {
    }

	public Item(String name, String description, String type, String brand, int quantity, double price) {
		//this.bid = bid;
		this.name = name;
		this.description = description;
		this.type = type;
		this.brand = brand;
		this.quantity = quantity;
		this.price = price;
	}

	// tbh not sure if we switcharoo to using only Bid, but keeping this temporarily
	public Long getId() {
//		
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

//	public String getBid() {
//		return bid;
//	}
//
//	public void setBid(String bid) {
//		this.bid = bid;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	// do equals method
	@Override
	public int hashCode() {
        return Objects.hash(this.id, this.name, this.description, this.type, this.brand, this.quantity, this.price);
//		return super.hashCode();
	}

	@Override
	public boolean equals(Object o) {
//		return super.equals(obj);
		 if (this == o)
	            return true;
	        if (!(o instanceof Item))
	            return false;
	        Item item = (Item) o;
	        return Objects.equals(this.id, item.id) && Objects.equals(this.name, item.name);
	        //FINISH EQUALS
	}
	//hascode
	
//    private String bid;
//    private String name;
//    private String description;
//    private String type;
//    private String brand;
//    private int quantity;
//    private int price;
	@Override
    public String toString() {
        return "Item{" 
        		+ "id=" + this.id 
        		+ ", name='" + this.name + '\'' 
        		+ ", description='" + this.description + '\''
        		+ ", type='" + this.type + '\''
        		+ ", brand='" + this.brand + '\''
        		+ ", quantity='" + this.quantity + '\''
        		+ ", price='" + this.price
        		+ '}';
    }

	

}
