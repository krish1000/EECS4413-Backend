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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class ShoppingCart {

	private @Id @GeneratedValue Long id;
	
//	@Column(name = "user_id")
//	private Long userId;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name="user_id", referencedColumnName="id", insertable=false, updatable=false)
	
//	@OneToOne(mappedBy="shoppingCart")
//	@JoinColumn(name="user_id")
//	@MapsId 
    @OneToOne(mappedBy = "shoppingCart")
//    @JoinColumn(name = "user_id")
	private User userId;
    
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="shopping_cart_item_id")
	private List<ShoppingCartItem> shoppingCartItems = new ArrayList<ShoppingCartItem>();
  
	public ShoppingCart() {
		
	}
	
//	public ShoppingCart(Long userId) {
//		this.userId = userId;
//	}
  

	// tbh not sure if we switcharoo to using only Bid, but keeping this temporarily
	public Long getId() {
//		
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	
//	public Long getUserId() {
//		return this.userId;
//	}
//	
//	public void setUserId(Long id) {
//		this.userId = id;
//	}
	
//	public List<ShoppingCartItem> getShoppingCartItems() {
//		return this.shoppingCartItems;
//	}
//	public List<ShoppingCartItem> setShoppingCartItems(List<ShoppingCartItem> scItems) {
//		return this.shoppingCartItems = scItems;
//	}
//	
//	public void addShoppingCartItemId(ShoppingCartItem item) {
//		this.shoppingCartItems.add(item);
//	}
	
//	// deletes a single shopping cart item
//	public void deleteShoppingCartItemId(ShoppingCartItem item) {
//		for (int i = 0; i < this.getShoppingCartItems().size(); i++) {
//			if (this.getShoppingCartItems().get(i).getId() == item.getId()) {
//				this.shoppingCartItems.remove(i);
//			}
//		}
//	}
//	
//	// deletes all shopping cart items
//	public void deleteAllShoppingCartItems() {
//		this.shoppingCartItems.clear();
//	}
	
	
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
      return Objects.hash(this.getId());
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
	        return Objects.equals(this.getId(), sc.getId());
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
//      		+ ", itemIds=" + this.shoppingCartItems
      		+ '}';
  }

	public List<ShoppingCartItem> getShoppingCartItems() {
		return shoppingCartItems;
	}

	public void setShoppingCartItems(List<ShoppingCartItem> shoppingCartItems) {
		this.shoppingCartItems = shoppingCartItems;
	}


//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
}
