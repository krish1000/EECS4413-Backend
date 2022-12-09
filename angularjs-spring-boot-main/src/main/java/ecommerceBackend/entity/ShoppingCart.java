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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class ShoppingCart {

	private @Id @GeneratedValue Long id;
	
//	@Column(name = "user_id")
	private Long userId;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name="user_id", referencedColumnName="id", insertable=false, updatable=false)
//	private User user;
	
//	@Column(name = "cart_items")
	@ElementCollection
	private List<ShoppingCartItem> itemIds = new ArrayList<ShoppingCartItem>();
  
	public ShoppingCart() {
		
	}
	
	public ShoppingCart(Long userId) {
		this.userId = userId;
	}
  

	// tbh not sure if we switcharoo to using only Bid, but keeping this temporarily
	public Long getId() {
//		
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	
	public Long getUserId() {
		return this.userId;
	}
	
	public List<ShoppingCartItem> getItems() {
		return this.itemIds;
	}
	
	public void addShoppingCartItemId(ShoppingCartItem item) {
		this.itemIds.add(item);
	}
	
	public void deleteShoppingCartItemId(ShoppingCartItem item) {
		for (int i = 0; i < this.getItems().size(); i++) {
			if (this.getItems().get(i).getId() == item.getId()) {
				this.itemIds.remove(i);
			}
		}
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
      return Objects.hash(this.getId(), this.itemIds);
//		return super.hashCode();
	}

	@Override
	public boolean equals(Object o) {
//		return super.equals(obj);
		 if (this == o)
	            return true;
	        if (!(o instanceof ShoppingCart))
	            return false;
	        ShoppingCart sc = (ShoppingCart) o;
	        return Objects.equals(this.getId(), sc.getId()) && Objects.equals(this.itemIds, sc.itemIds);
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
      return "ShoppingCart{" 
      		+ "id=" + this.getId() 
      		+ ", itemIds=" + this.itemIds
      		+ '}';
  }

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
}
