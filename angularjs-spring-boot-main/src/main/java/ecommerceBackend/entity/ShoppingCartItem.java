package ecommerceBackend.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ShoppingCartItem {

	private @Id @GeneratedValue Long id;
	
	private Long itemId;
	
	private Long shoppingCartId;
	
	@Column(nullable = false)
	private int quantity;
	
	private double price;
	
	public ShoppingCartItem() {
		
	}
	
	public ShoppingCartItem(int quantity) {
		this.quantity = quantity;
	}
  
//	public ShoppingCartItem() {
//		this.userId = this.user.getId();
//	}

	// tbh not sure if we switcharoo to using only Bid, but keeping this temporarily
	public Long getId() {
//		
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemId() {
		return this.itemId;
	}

	public void setItemId(Long id) {
		this.itemId = id;
	}
	
	public Long getShoppingCartId() {
		return shoppingCartId;
	}

	public void setShoppingCartId(Long shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
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
	

//	public String getBid() {
//		return bid;
//	}
//
//	public void setBid(String bid) {
//		this.bid = bid;
//	}
	
//	public void deleteItem(Item item) {
//		items.remove(item);
//	}
	
	// do equals method
	@Override
	public int hashCode() {
      return Objects.hash(this.getId(), this.getId());
//		return super.hashCode();
	}

	@Override
	public boolean equals(Object o) {
//		return super.equals(obj);
		 if (this == o)
	            return true;
	        if (!(o instanceof ShoppingCartItem))
	            return false;
	        ShoppingCartItem sc = (ShoppingCartItem) o;
	        return Objects.equals(this.getId(), sc.getId()) && Objects.equals(this.itemId, sc.itemId);
	        //FINISH EQUALS
	}
	//hascode
	
//  private String bid;
//  private String name;
//  private String description;
//  private String type;
//  private String brand;
//  private int quantity;
//  private int price;
	@Override
  public String toString() {
      return "ShoppingCartItem{" 
      		+ "id=" + this.getId() 
      		+ ", itemIds=" + this.itemId
      		+ ", shoppingCartId=" + this.itemId
      		+ '}';
  }

}
