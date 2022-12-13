package ecommerceBackend.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

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
    
    @OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="review_id")
    private List<Review> reviews = new ArrayList<Review>();
    
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
	
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	// do equals method
	@Override
	public int hashCode() {
        return Objects.hash(this.id, this.name, this.description, this.type, this.brand, this.quantity, this.price, this.reviews);
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
	        return Objects.equals(this.id, item.id) && Objects.equals(this.name, item.name) && Objects.equals(this.description, item.description)
        		&& Objects.equals(this.type, item.type) && Objects.equals(this.brand, item.brand) && Objects.equals(this.quantity, item.quantity)
        		&& Objects.equals(this.price, item.price) && Objects.equals(this.reviews, item.reviews);
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
